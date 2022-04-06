package com.orangomango.amazeing.ui.scene;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.orangomango.amazeing.ui.*;
import static com.orangomango.amazeing.Main.DEFAULT_WIDTH;
import static com.orangomango.amazeing.Main.DEFAULT_HEIGHT;
import static com.orangomango.amazeing.Main.VERSION;
import static com.orangomango.amazeing.Main.MAIN_FONT;

public class Home extends Screen {
	public Home(GraphicsContext gc){
		super(gc, null, Screens.HOME);
	}

	@Override
	public void display(){
		clearScreen();
		gc.setFill(Color.LIME);
		gc.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		double factorX = gc.getCanvas().getWidth()/DEFAULT_WIDTH;
		double factorY = gc.getCanvas().getHeight()/DEFAULT_HEIGHT;
		
		gc.setFill(Color.web("#8ab7d1"));
		gc.fillRect(150*factorX, 25*factorY, 500*factorX, 100*factorY);
		
		Label info = new Label(gc, 25*factorX, 480*factorY, "aMAZEing made by OrangoMango - v"+VERSION, Font.loadFont(MAIN_FONT, 25));
		info.show();

		Button play = new Button(gc, 40*factorX, 190*factorY, 150*factorX, 200*factorY, 295, 1, 120, 160);
		play.setOnClick(() -> Screens.getPlayModeScreen(gc).display());
		Button help = new Button(gc, 230*factorX, 190*factorY, 150*factorX, 200*factorY, 173, 1, 120, 160);
		help.setOnClick(() -> Screens.getHelpScreen(gc).display());
		Button upgrades = new Button(gc, 420*factorX, 190*factorY, 150*factorX, 200*factorY, 1, 163, 120, 160);
		Button settings = new Button(gc, 610*factorX, 190*factorY, 150*factorX, 200*factorY, 123, 163, 120, 160);
		settings.setOnClick(() -> Screens.getSettingsScreen(gc).display());
		
		play.show();
		add(play);
		help.show();
		add(help);
		upgrades.show();
		add(upgrades);
		settings.show();
		add(settings);
	}
}