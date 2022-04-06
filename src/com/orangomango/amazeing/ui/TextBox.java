package com.orangomango.amazeing.ui;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.function.BooleanSupplier;

import com.orangomango.amazeing.ui.event.*;
import com.orangomango.amazeing.ui.scene.*;
import static com.orangomango.amazeing.Main.CURRENT_SCREEN;

public class TextBox extends GUIElement implements Clickable, Focusable {
	private boolean focused;
	private ClickEvent onSubmit = () -> System.out.println("Submitted");
	public String typedText = "";
	private ClickEvent event = () -> System.out.println("Focused");
	private int maxLength = 28;
	public Screens screen;
	public boolean onlyDigits;
	private BooleanSupplier testCondition = () -> true;
	
	public TextBox(GraphicsContext gc, double x, double y, double w, double h, Screens screen, boolean onlyDigits){
		super(gc, x, y, w, h);
		this.screen = screen;
		this.onlyDigits = onlyDigits;
	}

	@Override
	public Screens getScreen(){
		return this.screen;
	}

	public void setCondition(BooleanSupplier bs){
		this.testCondition = bs;
	}

	public int getMaxLength(){
		return this.maxLength;
	}

	public String getText(){
		return this.typedText;
	}

	public void setMaxLength(int v){
		this.maxLength = v;
	}

	public void update(){
		gc.clearRect(this.x, this.y, this.w, this.h);
		show();
	}

	@Override
	public void toggleFocused(){
		this.focused = !this.focused;
		update();
	}

	@Override
	public boolean isFocused(){
		return this.focused;
	}

	@Override
	public void show(){
		gc.setStroke(this.focused ? Color.RED : Color.WHITE);
		gc.setLineWidth(4);
		gc.strokeRect(this.x, this.y, this.w, this.h);
		gc.setFill(Color.WHITE);
		gc.fillRect(this.x, this.y, this.w, this.h);
		gc.setFill(Color.BLACK);
		gc.setStroke(null);
		gc.setFont(new Font("sans-serif", 25));
		gc.fillText(this.typedText, this.x+7, this.y+this.h-13);
	}

	@Override
	public boolean isClicked(double xv, double yv){
		return xv >= this.x &&  xv <= this.x+this.w && yv >= this.y && yv <= this.y+this.h;
	}

	@Override
	public void setOnClick(ClickEvent evt){
		this.event = evt;
	}

	public void setOnSubmit(ClickEvent evt){
		this.onSubmit = evt;
	}

	@Override
	public void handleClick(){
		boolean result = this.testCondition.getAsBoolean();
		if (!result) return;
		toggleFocused();
		this.event.fire();
	}

	public void handleSubmit(){
		this.onSubmit.fire();
	}
}