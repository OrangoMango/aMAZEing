package com.orangomango.amazeing.ui.scene;

import javafx.scene.canvas.*;

import com.orangomango.amazeing.game.*;

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
		System.out.println("Generating maze...");
		
		Maze maze = new Maze(this.width, this.height, this.seed);
		Camera camera = maze.getCamera();
		camera.setBlockWidth(100);
		camera.setWallWidth(10); // 150 10
		camera.setOffset(125, 110);
		
		System.out.println("Done!");
		System.out.println("Maze:\n"+maze);
		
		maze.draw(gc);
		maze.startMainloop(gc);
	}
}