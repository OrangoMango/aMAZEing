package com.orangomango.amazeing.ui.scene;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.image.*;

import com.orangomango.amazeing.ui.event.*;
import static com.orangomango.amazeing.Main.DEFAULT_WIDTH;
import static com.orangomango.amazeing.Main.DEFAULT_HEIGHT;
import static com.orangomango.amazeing.Main.SPRITESHEET;

public class HelpScreen extends Screen {
	public HelpScreen(GraphicsContext gc, ClickEvent ev){
		super(gc, ev, Screens.HELP);
	}

	@Override
	public void display(){
		clearScreen();
		gc.setFill(Color.web("#48d9c8"));
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		drawBackButton();

		double factorX = gc.getCanvas().getWidth()/DEFAULT_WIDTH;
		double factorY = gc.getCanvas().getHeight()/DEFAULT_HEIGHT;
	
		Image image = new Image(SPRITESHEET);
		gc.drawImage(image, 245, 163, 128, 80, 140*factorX, 135*factorY, 128*factorX, 80*factorY);
		gc.drawImage(image, 1, 327, 128, 80, 375*factorX, 135*factorY, 128*factorX, 80*factorY);
		gc.drawImage(image, 245, 245, 128, 80, 140*factorX, 285*factorY, 128*factorX, 80*factorY);
	}
}