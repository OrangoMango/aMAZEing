package com.orangomango.amazeing.game;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.animation.*;
import javafx.util.Duration;

import static com.orangomango.amazeing.Main.FPS;
import static com.orangomango.amazeing.Main.CURRENT_SCREEN;
import com.orangomango.amazeing.ui.scene.*;

public class Player {
	private GraphicsContext gc;
	private String imagePath = "#ff3333";
	private Camera camera;
	private Maze maze;
	private double x, y;
	public boolean moving;
	
	public Player(GraphicsContext gc, Camera camera, Maze maze){
		this.gc = gc;
		this.camera = camera;
		this.maze = maze;
		gc.getCanvas().setOnKeyPressed(ev -> { 
			if (CURRENT_SCREEN != Screens.MAZE_DISPLAY){
				return;
			}
			if (this.maze.startTime == 0){
				this.maze.startTime = System.currentTimeMillis();
			}
			switch (ev.getCode()){
				case UP:
				case W:
					System.out.println("PlayerUp");
					move(Direction.N);
					break;
				case RIGHT:
				case D:
					System.out.println("PlayerRight");
					move(Direction.E);
					break;
				case DOWN:
				case S:
					System.out.println("PlayerDown");
					move(Direction.S);
					break;
				case LEFT:
				case A:
					System.out.println("PlayerLeft");
					move(Direction.W);
					break;
				case SPACE:
					System.out.println("Pausing/Resuming");
					if (maze.isRunning()){
						maze.pauseMainLoop();
					} else {
						maze.resumeMainLoop();
					}
			}
		});
	}

	private void move(Direction dir){
		final double factor = camera.getTileWidth()/6;
		final String bDir = this.maze.getTileAt((int)Math.round(this.x/this.camera.getTileWidth()), (int)Math.round(this.y/this.camera.getTileWidth())).getDirection().toString();
		if (!bDir.contains(dir.toString()) || moving || !this.maze.isRunning()){
			return;
		}
		this.moving = true;
		Timeline t = new Timeline(new KeyFrame(Duration.millis(1000/FPS), e -> {
			switch (dir){
				case N:
					if (bDir.contains("N")) this.y -= factor;
					break;
				case E:
					if (bDir.contains("E")) this.x += factor;
					break;
				case S:
					if (bDir.contains("S")) this.y += factor;
					break;
				case W:
					if (bDir.contains("W")) this.x -= factor;
					break;
			}
			this.maze.getCamera().setX(this.x/this.maze.getCamera().getTileWidth());
			this.maze.getCamera().setY(this.y/this.maze.getCamera().getTileWidth());
			System.out.format("%.2f %.2f\n", this.x, this.y);
		}));
		t.setCycleCount(6);
		t.play();
		t.setOnFinished(event -> {
			this.moving = false;
			if ((int)Math.round(this.x/this.maze.getCamera().getTileWidth()) == this.maze.getWidth()-1 && (int)Math.round(this.y/this.maze.getCamera().getTileWidth()) == this.maze.getHeight()-1){
				System.out.println("MAZE FINISHED!!!");
				System.out.println(this.maze.getTimeText());
				this.maze.stopMainloop();
				Screens.getHomeScreen(gc).display();
			}
		});
	}

	/**
	 * Player keeps in the same position, only the camera moves.
	 */
	public void display(){
		gc.setFill(Color.web(imagePath));
		gc.fillRect(camera.getWallWidth()*2, camera.getWallWidth()*2, camera.getTileWidth()-camera.getWallWidth()*4, camera.getTileWidth()-camera.getWallWidth()*4);
	}

	@Override
	public String toString(){
		return String.format("Player at %.2f %.2f", this.x, this.y);
	}
}
