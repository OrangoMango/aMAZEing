package com.orangomango.amazeing.ui.scene;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

import com.orangomango.amazeing.ui.event.*;

import static com.orangomango.amazeing.Main.DEFAULT_HEIGHT;
import static com.orangomango.amazeing.Main.DEFAULT_WIDTH;

public class UpgradesScreen extends Screen {
    public UpgradesScreen(GraphicsContext gc, ClickEvent ev){
        super(gc, ev, Screens.UPGRADES);
    }

    @Override
    public void display(){
        clearScreen();

        gc.setFill(Color.LIME);
        gc.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        double factorX = gc.getCanvas().getWidth()/DEFAULT_WIDTH;
        double factorY = gc.getCanvas().getHeight()/DEFAULT_HEIGHT;

        drawBackButton();
    }
}
