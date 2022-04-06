package com.orangomango.amazeing.ui.event;

public interface Clickable {
	public void handleClick();
	public void setOnClick(ClickEvent event);
	public boolean isClicked(double x, double y);
}