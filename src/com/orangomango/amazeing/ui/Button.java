package com.orangomango.amazeing.ui;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.image.*;

import java.util.function.BooleanSupplier;

import com.orangomango.amazeing.ui.event.*;
import static com.orangomango.amazeing.Main.SPRITESHEET;

public class Button extends GUIElement implements Clickable {
	private ClickEvent event = () -> System.out.println("Event fired");
	private BooleanSupplier testCondition = () -> true;
	private int ix, iy, iw, ih;
	
	public Button(GraphicsContext gc, double x, double y, double w, double h, int ix, int iy, int iw, int ih){
		super(gc, x, y, w, h);
		this.ix = ix;
		this.iy = iy;
		this.iw = iw;
		this.ih = ih;
	}

	//test
	public Button(GraphicsContext gc, double x, double y, double w, double h, String test){
		this(gc, x, y, w, h, 0, 0, (int)w, (int)h);
	}

	public double[] getDisplayData(){
		return new double[]{this.x, this.y, this.w, this.h};
	}
	
	public void setCondition(BooleanSupplier bs){
		this.testCondition = bs;
	}

	@Override
	public void show(){
		Image image = new Image(SPRITESHEET);
		this.gc.drawImage(image, this.ix, this.iy, this.iw, this.ih, this.x, this.y, this.w, this.h);
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
		if (!this.testCondition.getAsBoolean()) return;
		this.event.fire();
	}
}