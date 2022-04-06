package com.orangomango.amazeing.game;

public class Camera {
	private double x;
	private double y;
	private double blockWidth = 32;
	private double wallWidth = 1.5;
	private int fovX, fovY;
	private double offsetX, offsetY;

	public Camera(double x, double y, int fx, int fy){
		this.x = x;
		this.y = y;
		this.fovX = fx;
		this.fovY = fy;
	}

	public void setOffset(double x, double y){
		this.offsetX = x;
		this.offsetY = y;
	}

	public double getOffsetX(){
		return this.offsetX;
	}

	public double getOffsetY(){
		return this.offsetY;
	}

	public void setX(double v){
		this.x = v;
	}

	public double getX(){
		return this.x;
	}

	public void setY(double v){
		this.y = v;
	}
	
	public double getY(){
		return this.y;
	}

	public void setWallWidth(double value){
		this.wallWidth = value;
	}

	public double getWallWidth(){
		return this.wallWidth;
	}

	public void setBlockWidth(double value){
		this.blockWidth = value;
	}
	
	public double getBlockWidth(){
		return this.blockWidth;
	}

	public int getFovX(){
		return this.fovX;
	}

	public int getFovY(){
		return this.fovY;
	}
}