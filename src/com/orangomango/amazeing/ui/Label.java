package com.orangomango.amazeing.ui;

import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
import javafx.scene.text.Font;
import javafx.scene.canvas.*;

public class Label extends GUIElement {

	private String text;
	private Font font;
	
	public Label(GraphicsContext gc, double x, double y, String text, Font font){
		super(gc, x, y, 0, 0);
		this.text = text;
		this.font = font;
	}

	@Override
	public void show(){
		this.gc.setFill(Color.BLACK);
		this.gc.setFont(this.font);
		this.gc.fillText(this.text, this.x, this.y);
	}
}