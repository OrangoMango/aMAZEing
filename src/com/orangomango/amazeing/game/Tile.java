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
		gc.setFill(Color.WHITE);
		//gc.fillRect((this.x-camera.getX())*camera.getBlockWidth(), (this.y-camera.getY())*camera.getBlockWidth(), camera.getBlockWidth(), camera.getBlockWidth());
		gc.drawImage(image, 497, 175, 100, 100, (this.x-camera.getX())*camera.getBlockWidth(), (this.y-camera.getY())*camera.getBlockWidth(), camera.getBlockWidth(), camera.getBlockWidth());
		gc.setLineWidth(camera.getWallWidth());
		gc.setStroke(Color.RED);
		if (this.direction == Direction.NESW) return;
		for (char c : Direction.getComplementar(this.direction).toString().toCharArray()){
                        gc.save();
                        boolean drawTheImg = false;
			if (Character.toString(c).equals("N") && this.y != 0){
				//gc.strokeLine((this.x-camera.getX())*camera.getBlockWidth(), (this.y-camera.getY())*camera.getBlockWidth(), (this.x-camera.getX()+1)*camera.getBlockWidth(), (this.y-camera.getY())*camera.getBlockWidth());
                                //gc.drawImage(image, 497, 163, 100, 10, (this.x-camera.getX())*camera.getBlockWidth(), (this.y-camera.getY())*camera.getBlockWidth(), camera.getBlockWidth(), camera.getWallWidth());
                                drawTheImg = true;
                                gc.translate((this.x-camera.getX())*camera.getBlockWidth(), (this.y-camera.getY())*camera.getBlockWidth());
                        } else if (Character.toString(c).equals("E") && this.x != camera.getFovX()-1){
				//gc.strokeLine((this.x-camera.getX()+1)*camera.getBlockWidth(), (this.y-camera.getY())*camera.getBlockWidth(), (this.x-camera.getX()+1)*camera.getBlockWidth(), (this.y-camera.getY()+1)*camera.getBlockWidth());
                                drawTheImg = true;
                                gc.translate((this.x-camera.getX())*camera.getBlockWidth()+camera.getBlockWidth(), (this.y-camera.getY())*camera.getBlockWidth());
                                gc.rotate(90);
                        } else if (Character.toString(c).equals("S") && this.y != camera.getFovY()-1){
				//gc.strokeLine((this.x-camera.getX())*camera.getBlockWidth(), (this.y-camera.getY()+1)*camera.getBlockWidth(), (this.x-camera.getX()+1)*camera.getBlockWidth(), (this.y-camera.getY()+1)*camera.getBlockWidth());
                                drawTheImg = true;
                                gc.translate((this.x-camera.getX())*camera.getBlockWidth()+camera.getBlockWidth(), (this.y-camera.getY())*camera.getBlockWidth()+camera.getBlockWidth());
                                gc.rotate(180);
                        } else if (Character.toString(c).equals("W") && this.x != 0){
				//gc.strokeLine((this.x-camera.getX())*camera.getBlockWidth(), (this.y-camera.getY())*camera.getBlockWidth(), (this.x-camera.getX())*camera.getBlockWidth(), (this.y-camera.getY()+1)*camera.getBlockWidth());
                                drawTheImg = true;
                                gc.translate((this.x-camera.getX())*camera.getBlockWidth(), (this.y-camera.getY())*camera.getBlockWidth()+camera.getBlockWidth());
                                gc.rotate(-90);
                        }
                        if (drawTheImg){
                            gc.drawImage(image, 497, 163, 100, 10, 0, 0, camera.getBlockWidth(), camera.getWallWidth());
                        }
                        gc.restore();
                }
	}

	@Override
	public String toString(){
		return this.direction.toString()+","+this.visited+String.format("(%d,%d)", this.x, this.y);
	}
}