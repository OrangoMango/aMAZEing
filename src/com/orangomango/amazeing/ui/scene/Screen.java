package com.orangomango.amazeing.ui.scene;

import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;

import java.util.*;
import com.orangomango.amazeing.ui.*;
import com.orangomango.amazeing.ui.event.*;
import static com.orangomango.amazeing.Main.DEFAULT_WIDTH;
import static com.orangomango.amazeing.Main.DEFAULT_HEIGHT;
import static com.orangomango.amazeing.Main.CURRENT_SCREEN;

public abstract class Screen {
	protected GraphicsContext gc;
	protected ClickEvent evt;
	protected List<Clickable> elements = new ArrayList<>();
	protected List<Focusable> toFocus = new ArrayList<>();
	protected Screens screenType;
	public static Focusable CURRENT_FOCUS;

	protected Screen(GraphicsContext gc, ClickEvent onBack, Screens stype){
		this.gc = gc;
		this.evt = onBack;
		this.screenType = stype;
		CURRENT_SCREEN = this.screenType;
		this.gc.getCanvas().setOnMousePressed(ev -> {
			for (Clickable i : elements){
				if (i.isClicked(ev.getX(), ev.getY())){
					i.handleClick();
				}
			}
		});
		this.gc.getCanvas().setOnKeyReleased(ev -> {
			for (Focusable f : this.toFocus){
				if (f.isFocused()){
					TextBox item;
					if (f instanceof TextBox){
						item = (TextBox)f;
					} else {
						continue;
					}
					System.out.println("Writing as "+item);
					if (ev.getCode() == KeyCode.BACK_SPACE && item.typedText.length() >= 1){
						item.typedText = item.typedText.substring(0, item.typedText.length()-1);
					} else if (ev.getCode() == KeyCode.ENTER){
						item.handleSubmit();
					} else if (ev.getText().matches(item.onlyDigits ? "[-|\\d]*" : "\\w") && item.typedText.length() < item.getMaxLength()) {
						item.typedText += ev.getText();
					}
					item.update();
				}
			}
		});
	}

	protected void clearScreen(){
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}

	protected void drawBackButton(){
		double factorX = this.gc.getCanvas().getWidth()/DEFAULT_WIDTH;
		double factorY = this.gc.getCanvas().getHeight()/DEFAULT_HEIGHT;
		
		Button back = new Button(this.gc, 30, 30, 170, 55, 1, 1, 170, 55);
		back.setOnClick(this.evt);
		add(back);
		back.show();
	}

	protected void add(Clickable c){
		this.elements.add(c);
	}

	protected void addFocus(Focusable f){
		this.toFocus.add(f);
	}

	public abstract void display();
}