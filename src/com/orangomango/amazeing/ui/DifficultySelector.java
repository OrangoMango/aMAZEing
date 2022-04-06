package com.orangomango.amazeing.ui;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

import java.util.*;

public class DifficultySelector extends GUIElement {
	private String imagePath = "#8c65d6";
	private List<Button> buttons;
	private int selected = -1;
	
	public DifficultySelector(GraphicsContext gc, double x, double y, double w, double h, List<Button> bs){
		super(gc, x, y, w, h);
		this.buttons = bs;
	}

	public void update(int num){
		gc.clearRect(this.x, this.y, this.w, this.h);
		this.selected = num;
		show();
	}

	@Override
	public void show(){
		gc.setFill(Color.web(imagePath));
		gc.fillRect(this.x, this.y, this.w, this.h);
		int count = 0;
		for (Button b : this.buttons){
			double[] data = b.getDisplayData();
			b.show();
			gc.save();
			gc.setLineWidth(3);
			gc.setStroke(this.selected == count++ ? Color.RED : Color.BLACK);
			gc.strokeRect(data[0], data[1], data[2], data[3]);
			gc.restore();
		}
		gc.setStroke(null);
	}
}