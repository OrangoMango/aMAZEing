package com.orangomango.amazeing.ui.scene;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

import com.orangomango.amazeing.ui.event.*;

public class SettingsScreen extends Screen{
	public SettingsScreen(GraphicsContext gc, ClickEvent ev){
		super(gc, ev, Screens.SETTINGS);
	}

	@Override
	public void display(){
		clearScreen();
		gc.setFill(Color.web("#48d9c8"));
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		drawBackButton();
	}
}