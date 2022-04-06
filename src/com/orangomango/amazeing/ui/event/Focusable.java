package com.orangomango.amazeing.ui.event;

// Every focusable is clickable by definition

import java.util.*;

import com.orangomango.amazeing.ui.scene.*;

public interface Focusable {
	
	public void toggleFocused();
	public boolean isFocused();
	public Screens getScreen();

	public static <T extends Focusable> ClickEvent unFocusOthers(List<Clickable> others, T me){
		return () -> {
			for (Clickable i : others){
				if (i instanceof Focusable && i != me){
					System.out.println("Unfocusing item");
					Focusable item = (Focusable)i;
					if (item.isFocused()){
						item.toggleFocused();
			 		}
				}
			}
		};
	}
}