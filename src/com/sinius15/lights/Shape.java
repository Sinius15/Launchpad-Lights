package com.sinius15.lights;

import com.sinius15.lights.shapes.ArrowShape;
import com.sinius15.lights.shapes.CrossShape;
import com.sinius15.lights.shapes.DotShape;
import com.sinius15.lights.shapes.LinesShape;
import com.sinius15.lights.util.Direction;

public abstract class Shape {
	
	public abstract String getName();
	public abstract void draw(Effect pad, int color, int row, int colomn, Direction dir);

	public static Shape[] shapes;
	public static String[] shapeStrings;
	
	public static void init(){
		shapes = new Shape[]{new DotShape(), new CrossShape(), new ArrowShape(), new LinesShape()};
		shapeStrings = new String[shapes.length];
		for(int i = 0; i < shapes.length; i++){
			shapeStrings[i] = shapes[i].getName();
		}
	}
	
	public static Shape stringToShape(String in){
		for(Shape s : shapes){
			if(s.getName().equals(in)){
				return s;
			}
		}
		return null;
	}
	
}
