package com.orangomango.amazeing.game;

public class Camera {
	private double x;
	private double y;
	private double tileWidth = 32;
	private double wallWidth = 1.5;
	private double fovX, fovY;
	private double offsetX, offsetY;
	private int wx, wy;

	public Camera(double x, double y, int wx, int wy){
		this.x = x;
		this.y = y;
		this.wx = wx;
		this.wy = wy;
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

	public void setTileWidth(double value){
		this.tileWidth = value;
	}
	
	public double getTileWidth(){
		return this.tileWidth;
	}

	public void setFovX(double value){ this.fovX = value; }

	public void setFovY(double value){ this.fovY = value; }

	public double getFovX(){
		return this.fovX;
	}

	public double getFovY(){
		return this.fovY;
	}

	public int getWorldWidth(){ return this.wx; }

	public int getWorldHeight(){ return this.wy; }
}