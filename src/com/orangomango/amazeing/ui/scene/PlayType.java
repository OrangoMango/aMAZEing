package com.orangomango.amazeing.ui.scene;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

import java.util.*;

import com.orangomango.amazeing.ui.*;
import com.orangomango.amazeing.ui.event.*;
import static com.orangomango.amazeing.Main.DEFAULT_WIDTH;
import static com.orangomango.amazeing.Main.DEFAULT_HEIGHT;

public class PlayType extends Screen {
	public PlayType(GraphicsContext gc, ClickEvent evt){
		super(gc, evt, Screens.PLAY_TYPE);
	}

	@Override
	public void display(){
		clearScreen();
		gc.setFill(Color.web("#48d9c8"));
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		drawBackButton();
		double factorX = this.gc.getCanvas().getWidth()/DEFAULT_WIDTH;
		double factorY = this.gc.getCanvas().getHeight()/DEFAULT_HEIGHT;
		Button randomSeed = new Button(this.gc, 72*factorX, 170*factorY, 150*factorX, 200*factorY, 375, 163, 120, 160);
		randomSeed.setOnClick(() -> {
			Random r = new Random();
			int sizeX = r.nextInt((Difficulty.INSANE).getMaxSize())+1;
			int sizeY = r.nextInt((Difficulty.INSANE).getMaxSize())+1;
			long seed = r.nextLong();
			MazeDisplay md = new MazeDisplay(gc, sizeX, sizeY, seed);
			md.display();
		});
		Button savedSeed = new Button(this.gc, 314*factorX, 170*factorY, 150*factorX, 200*factorY, 375, 325, 120, 160);
		Button createSeed = new Button(this.gc, 556*factorX, 170*factorY, 150*factorX, 200*factorY, 417, 1, 120, 160);
		createSeed.setOnClick(() -> Screens.getCreateSeedScreen(gc).display());

		randomSeed.show();
		add(randomSeed);
		savedSeed.show();
		add(savedSeed);
		createSeed.show();
		add(createSeed);
	}
}