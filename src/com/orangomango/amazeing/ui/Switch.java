package com.orangomango.amazeing.ui;

import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import com.orangomango.amazeing.ui.event.*;
import static com.orangomango.amazeing.Main.SPRITESHEET;

public class Switch extends GUIElement implements Clickable {
	private boolean selected = false;
	private ClickEvent event = () -> System.out.println("Event fired ["+this.selected+"]");
	
	public Switch(GraphicsContext gc, double x, double y, double w, double h){
		super(gc, x, y, w, h);
	}

	private void toggleSelected(){
		this.selected = !this.selected;
		show();
	}

	public boolean isSelected(){
		return this.selected;
	}

	@Override
	public void show(){
		Image image = new Image(SPRITESHEET);
		if (isSelected()){
			gc.drawImage(image, 375, 487, 120, 50, this.x, this.y, this.w, this.h);
		} else {
			gc.drawImage(image, 1, 487, 120, 50, this.x, this.y, this.w, this.h);
		}
	}

	@Override
	public boolean isClicked(double xv, double yv){
		return xv >= this.x &&  xv <= this.x+this.w && yv >= this.y && yv <= this.y+this.h;
	}

	@Override
	public void setOnClick(ClickEvent evt){
		this.event = evt;
	}

	@Override
	public void handleClick(){
		toggleSelected();
		this.event.fire();
	}
}