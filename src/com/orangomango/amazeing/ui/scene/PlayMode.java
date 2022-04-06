package com.orangomango.amazeing.ui.scene;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

import com.orangomango.amazeing.ui.*;
import com.orangomango.amazeing.ui.event.*;
import static com.orangomango.amazeing.Main.DEFAULT_WIDTH;
import static com.orangomango.amazeing.Main.DEFAULT_HEIGHT;

public class PlayMode extends Screen {
	public PlayMode(GraphicsContext gc, ClickEvent evt){
		super(gc, evt, Screens.PLAY_MODE);
	}

	@Override
	public void display(){
		clearScreen();
		gc.setFill(Color.web("#48d9c8"));
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		drawBackButton();
		double factorX = this.gc.getCanvas().getWidth()/DEFAULT_WIDTH;
		double factorY = this.gc.getCanvas().getHeight()/DEFAULT_HEIGHT;
		Button single = new Button(this.gc, 200*factorX, 170*factorY, 150*factorX, 200*factorY, 253, 327, 120, 160);
		single.setOnClick(() -> Screens.getPlayTypeScreen(gc).display());
		add(single);
		Button multi = new Button(this.gc, 430*factorX, 170*factorY, 150*factorX, 200*factorY, 131, 327, 120, 160);
		add(multi);

		single.show();
		multi.show();
	}
}