package com.sinius15.launchpadLights.util;

import com.sinius15.launchpad.pattern.LaunchpadPattern;
import com.sinius15.launchpadLights.Animation;
import com.sinius15.launchpadLights.frame.LightFrame;

public class AnimationThread extends Thread{
	
	Animation ani;
	float speed;
	Runnable onEnd = null;
	boolean isRunning = true;
	
	public AnimationThread(Animation ani, float speed){
		this.ani = ani;
		this.speed = speed;
	}
	
	@Override
	public void run() {
		try {
			mainLoop :
			for(LaunchpadPattern p : ani.animations){
				LightFrame.getLaunchpad().showPattern(p);
				for(int j = 0; j < speed; j++){
					Thread.sleep(1);
					if(!isRunning)
						break mainLoop;
				}
			}
		}catch(InterruptedException e){
			//do nothing
		}finally{
			if(onEnd != null){
				System.out.println("onEnd starting...");
				onEnd.run();
			}
		}
	}
	
	public void setOnEnd(Runnable r){
		this.onEnd = r;
	}
	
	public void stopRunning(){
		isRunning = false;
	}
	
}
