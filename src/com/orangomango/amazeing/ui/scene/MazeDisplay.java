package com.orangomango.amazeing.ui.scene;

import javafx.scene.canvas.*;

import com.orangomango.amazeing.game.*;
import static com.orangomango.amazeing.Main.DEFAULT_HEIGHT;
import static com.orangomango.amazeing.Main.DEFAULT_WIDTH;

public class MazeDisplay extends Screen {
	private int width, height;
	private Long seed;
	
	public MazeDisplay(GraphicsContext gc, int w, int h, Long seed){
		super(gc, null, Screens.MAZE_DISPLAY);
		this.width = w;
		this.height = h;
		this.seed = seed;
	}

	@Override
	public void display(){
		clearScreen();

		double factorX = gc.getCanvas().getWidth()/DEFAULT_WIDTH;
		double factorY = gc.getCanvas().getHeight()/DEFAULT_HEIGHT;

		System.out.println("Generating maze...");
		
		Maze maze = new Maze(this.width, this.height, this.seed);
		Camera camera = maze.getCamera();
		// 100 10
		camera.setTileWidth(75);
		camera.setWallWidth(7.5);
		camera.setOffset(125, 110);
		camera.setFovX(DEFAULT_WIDTH*factorX);
		camera.setFovY(DEFAULT_HEIGHT*factorY);
		
		System.out.println("Done!");
		System.out.println("Maze:\n"+maze);
		
		maze.draw(gc);
		maze.startMainloop(gc);
	}
}
