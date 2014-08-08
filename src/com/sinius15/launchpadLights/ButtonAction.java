package com.sinius15.launchpadLights;

import java.awt.Color;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;

import com.sinius15.launchpad.Launchpad;
import com.sinius15.launchpad.pattern.LaunchpadPattern;
import com.sinius15.launchpadLights.frame.LightFrame;

//every button has just 1 of these classes!!!!!
public class ButtonAction extends JButton{
	
	private static final long serialVersionUID = -1083612329746590565L;
	
	private Animation animation;
	private int waitTime; //in miliseconds
	private ButtonActionType pressType, releaseType, aniStopType;
	
	private ExecutorService exService ;
	private CompletionService<Object> complService;
	
	private Thread finishedChecker;
	
	public ButtonAction(String title, Animation animation, int speed, ButtonActionType pressType, ButtonActionType releaseType, ButtonActionType stopType) {
		super(title);
		this.animation = animation;
		this.waitTime = speed;
		this.pressType = pressType;
		this.releaseType = releaseType;
		this.aniStopType = stopType;
	}

	public void onButtonDown(){
		setForeground(Color.white);
		setBackground(Color.black);
		if(animation == null)
			return;
		switcher(pressType);
	}
	
	public void onButtonUp(){
		setForeground(Color.black);
		setBackground(Color.white);
		if(animation != null)
			setBackground(Color.pink);
		
		if(animation == null)
			return;
		
		switcher(releaseType);
	}
	
	private void switcher(ButtonActionType t){
		switch (t) {
			case cleanstop:
				stopAnimating(true);
				break;
			case nothing:
				break;
			case play:
				playAnimation();
				break;
			case stop:
				stopAnimating(false);
				break;
			case reset:
				LightFrame.getLaunchpad().reset();
				break;
			default:
				break;
		}
	}
	
	private void playAnimation(){
		complService.submit(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				for(LaunchpadPattern p : animation.animations){
					LightFrame.getLaunchpad().showPattern(p);
					Thread.sleep(waitTime);
				}
				return null;
			}
		});
	}

	private void stopAnimating(boolean cleanUp){
		try{
			exService.shutdown();
		}catch(Exception e){}
		exService = Executors.newCachedThreadPool();
		complService = new ExecutorCompletionService<Object>(exService);
		
		if(cleanUp){
			for(LaunchpadPattern p : animation.animations)
				LightFrame.getLaunchpad().showPattern(p.setColor(-99, Launchpad.COLOR_OFF));
		}
	}
	
	public void update(){
		exService = Executors.newCachedThreadPool();
		complService = new ExecutorCompletionService<Object>(exService);
		
		finishedChecker = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					Future<Object> result = complService.poll();
					if(result != null){
						switcher(aniStopType);
					}
				}
			}
		});
		finishedChecker.start();
	}
	
	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public float getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	public ButtonActionType getPressType() {
		return pressType;
	}
	
	public ButtonActionType getReleaseType() {
		return releaseType;
	}
	
	public void setPressType(ButtonActionType startType) {
		this.pressType = startType;
	}
	
	public void setReleaseType(ButtonActionType releaseType) {
		this.releaseType = releaseType;
	}

	public ButtonActionType getAniStopType() {
		return aniStopType;
	}

	public void setAniStopType(ButtonActionType aniStopType) {
		this.aniStopType = aniStopType;
	}

}
