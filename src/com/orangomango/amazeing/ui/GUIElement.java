package com.orangomango.amazeing.ui;

import javafx.scene.canvas.*;

public abstract class GUIElement {
	protected GraphicsContext gc;
	protected double x, y, w, h;

	protected GUIElement(GraphicsContext gc, double x, double y, double w, double h){
		this.gc = gc;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public abstract void show();
}