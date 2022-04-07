package com.orangomango.amazeing.game;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.image.*;

import static com.orangomango.amazeing.Main.SPRITESHEET;

public class Tile {
	private int x, y;
	public boolean visited;
	private Direction direction;
        private Image image = new Image(SPRITESHEET);

	public Tile(int x, int y, Direction dir){
		this.x = x;
		this.y = y;
		this.direction = dir;
	}

	public void setDirection(Direction d){
		this.direction = d;
	}

	public Direction getDirection(){
		return this.direction;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public void draw(GraphicsContext gc, Camera camera){
		if (((this.x-Math.round(camera.getX())+1)*camera.getTileWidth() > camera.getFovX() || (this.y-Math.round(camera.getY())+1)*camera.getTileWidth() > camera.getFovY()) || (this.x < Math.round(camera.getX()-camera.getFovX()) || this.y < Math.round(camera.getY()-camera.getFovY()))){
			return; // Tile is outside fov
		}
		gc.setFill(Color.WHITE);
		gc.drawImage(image, 497, 175, 100, 100, (this.x-camera.getX())*camera.getTileWidth(), (this.y-camera.getY())*camera.getTileWidth(), camera.getTileWidth(), camera.getTileWidth());
		gc.setLineWidth(camera.getWallWidth());
		gc.setStroke(Color.RED);
		if (this.direction == Direction.NESW) return;
		for (char c : Direction.getComplementar(this.direction).toString().toCharArray()){
			gc.save();
			boolean drawTheImg = false;
			if (Character.toString(c).equals("N") && this.y != 0){
				drawTheImg = true;
                gc.translate((this.x-camera.getX())*camera.getTileWidth(), (this.y-camera.getY())*camera.getTileWidth());
			} else if (Character.toString(c).equals("E") && this.x != camera.getWorldWidth()-1){
	 			drawTheImg = true;
				gc.translate((this.x-camera.getX())*camera.getTileWidth()+camera.getTileWidth(), (this.y-camera.getY())*camera.getTileWidth());
				gc.rotate(90);
			} else if (Character.toString(c).equals("S") && this.y != camera.getWorldHeight()-1){
				drawTheImg = true;
				gc.translate((this.x-camera.getX())*camera.getTileWidth()+camera.getTileWidth(), (this.y-camera.getY())*camera.getTileWidth()+camera.getTileWidth());
				gc.rotate(180);
			} else if (Character.toString(c).equals("W") && this.x != 0){
				drawTheImg = true;
				gc.translate((this.x-camera.getX())*camera.getTileWidth(), (this.y-camera.getY())*camera.getTileWidth()+camera.getTileWidth());
				gc.rotate(-90);
			}
			if (drawTheImg){
            	gc.drawImage(image, 497, 163, 100, 10, 0, 0, camera.getTileWidth(), camera.getWallWidth());
            }
            gc.restore();
		}
	}

	@Override
	public String toString(){
		return this.direction.toString()+","+this.visited+String.format("(%d,%d)", this.x, this.y);
	}
}