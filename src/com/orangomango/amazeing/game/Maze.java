package com.orangomango.amazeing.game;

import javafx.scene.canvas.*;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.*;

import static com.orangomango.amazeing.Main.FPS;
import static com.orangomango.amazeing.Main.MAIN_FONT;

public class Maze {
	private int w, h;
	private Tile[][] maze;
	private long seed;
	private Camera camera;
	private Timeline loop;
	private Player player;
	public long startTime;
	private long startPauseTime;
	private String timeText = "Press a key to start";
	private boolean running;
	
	public Maze(int w, int h, Long s){
		this.w = w;
		this.h = h;
		this.maze = new Tile[h][w];
		if (s != null){
			this.seed = s;
		} else {
			Random r = new Random();
			this.seed = r.nextLong();
		}
		this.camera = new Camera(0, 0, this.w, this.h);
		generateMaze();
	}

	public Camera getCamera(){
		return this.camera;
	}
        
    public String getTimeText(){
            return this.timeText;
        }
	
/*  public void setCamera(Camera c){
		this.camera = c;
	}
*/
	public int getWidth(){
		return this.w;
	}
	
	public int getHeight(){
		return this.h;
	}

	public Tile getTileAt(int x, int y){
		try {
			return this.maze[y][x];
		} catch (ArrayIndexOutOfBoundsException ex){
			return null;
		}
	}

	private void generateMaze(){
		for (int i = 0; i < this.h; i++){
			for (int j = 0; j < this.w; j++){
				this.maze[i][j] = new Tile(j, i, Direction.NONE);
			}
		}
		
		Random r = new Random(this.seed);
		int posX = this.w/2;
		int posY = 0;
		setVisited(r, posX, posY);
	}

	private void setVisited(Random r, int x, int y){
		if (!getTileAt(x, y).visited){
			this.getTileAt(x, y).visited = true;
		}
		Tile ad = getRandomAdiacentTile(r, getTileAt(x, y));
		while (ad != null){
			connect(getTileAt(x, y), getTileAt(ad.getX(), ad.getY()));
			setVisited(r, ad.getX(), ad.getY());
			ad = getRandomAdiacentTile(r, getTileAt(x, y));
		}
	}

	private void connect(Tile tile1, Tile tile2){
		if (tile1.getY() - tile2.getY() == 1){
			tile1.setDirection(Direction.addConn(tile1.getDirection(), Direction.N));
			tile2.setDirection(Direction.addConn(tile2.getDirection(), Direction.S));
		}
		if (tile1.getX() - tile2.getX() == 1){
			tile1.setDirection(Direction.addConn(tile1.getDirection(), Direction.W));
			tile2.setDirection(Direction.addConn(tile2.getDirection(), Direction.E));
		}
		if (tile1.getY() - tile2.getY() == -1){
			tile1.setDirection(Direction.addConn(tile1.getDirection(), Direction.S));
			tile2.setDirection(Direction.addConn(tile2.getDirection(), Direction.N));
		}
		if (tile1.getX() - tile2.getX() == -1){
			tile1.setDirection(Direction.addConn(tile1.getDirection(), Direction.E));
			tile2.setDirection(Direction.addConn(tile2.getDirection(), Direction.W));
		}
	}

	private Tile getRandomAdiacentTile(Random r, Tile tile){
		int x = tile.getX();
		int y = tile.getY();
		List<Tile> tiles = new ArrayList<>();
		if (y != 0){
			tiles.add(getTileAt(x, y-1));
		}
		if (x != this.w-1){
			tiles.add(getTileAt(x+1, y));
		}
		if (y != this.h-1){
			tiles.add(getTileAt(x, y+1));
		}
		if (x != 0){
			tiles.add(getTileAt(x-1, y));
		}

		// Use iterator
		List<Tile> toRemove = new ArrayList<>();
		for (Tile t : tiles){
			if (t.visited){
				toRemove.add(t);
			}
		}
		for (Tile t : toRemove){
			tiles.remove(t);
		}
		if (tiles.size() == 0){
			return null;
		}
		int number = r.nextInt(tiles.size());
		return tiles.get(number);
	}

	public void startMainloop(GraphicsContext gc){
		if (loop != null) return;
		loop = new Timeline(new KeyFrame(Duration.millis(1000/FPS), e -> {
			draw(gc);
			if (startTime != 0){
				long now = System.currentTimeMillis();
				long millis = now-startTime;
				long seconds = millis/1000L;
				millis -= seconds*1000;
				String txt = String.format("%s.%s", seconds, millis);
                this.timeText = txt.substring(0, txt.length()-1);
			}
		}));
		loop.setCycleCount(Animation.INDEFINITE);
		loop.play();
		this.running = true;
	}

	public void stopMainloop(){
		this.loop.stop();
		this.running = false;
		this.startPauseTime = 0;
	}

	public void pauseMainLoop(){
		this.loop.pause();
		this.running = false;
		this.startPauseTime = System.currentTimeMillis();
	}

	public void resumeMainLoop(){
		this.loop.play();
		this.running = true;
		this.startTime += System.currentTimeMillis()-this.startPauseTime;
	}

	public boolean isRunning(){
		return this.running;
	}

	public void draw(GraphicsContext gc){
		gc.save();
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		gc.translate(this.camera.getOffsetX(), this.camera.getOffsetY());
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(camera.getWallWidth());
		gc.strokeRect((0-camera.getX())*camera.getTileWidth(), (0-camera.getY())*camera.getTileWidth(), camera.getWorldWidth()*camera.getTileWidth(), camera.getWorldHeight()*camera.getTileWidth());
		for (Tile[] tL : this.maze){
			for (Tile t : tL){
				t.draw(gc, this.camera);
			}
		}
		if (this.player == null){
			this.player = new Player(gc, this.camera, this);
		}
		player.display();
		gc.restore();
		gc.setFill(Color.BLACK);
		gc.setFont(Font.loadFont(MAIN_FONT, 70));
		gc.fillText(this.timeText, 25, 70);
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Seed: "+this.seed+"\n");
		for (Tile[] tL : this.maze){
			for (Tile t : tL){
				builder.append(t+" ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
