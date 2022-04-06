package com.orangomango.amazeing.ui.scene;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Alert;

import java.util.*;

import com.orangomango.amazeing.ui.*;
import com.orangomango.amazeing.ui.event.*;
import static com.orangomango.amazeing.Main.DEFAULT_WIDTH;
import static com.orangomango.amazeing.Main.DEFAULT_HEIGHT;
import static com.orangomango.amazeing.Main.MAIN_FONT;

public class CreateSeed extends Screen {
	private Difficulty selectedDifficulty = Difficulty.SUPER_EASY;
	
	public CreateSeed(GraphicsContext gc, ClickEvent evt){
		super(gc, evt, Screens.SELECT_SEED);
	}

	@Override
	public void display(){
		clearScreen();
		gc.setFill(Color.web("#48d9c8"));
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		drawBackButton();
		double factorX = this.gc.getCanvas().getWidth()/DEFAULT_WIDTH;
		double factorY = this.gc.getCanvas().getHeight()/DEFAULT_HEIGHT;

		TextBox seedBox = new TextBox(gc, 215*factorX, 125*factorY, 500*factorX, 50*factorY, screenType, true);
		seedBox.setOnClick(Focusable.unFocusOthers(elements, seedBox));
		seedBox.setOnSubmit(() -> {
			try {
				long i = Long.parseLong(seedBox.getText());
				System.out.println(i);
			} catch (NumberFormatException ex){
				System.out.println("Error!");
			}
		});
		Switch difficulty = new Switch(gc, 310*factorX, 200*factorY, 120*factorX, 50*factorY);

		Label widthL = new Label(gc, 55*factorX, 350*factorY, "Width: ", Font.loadFont(MAIN_FONT, 45));
		Label heightL = new Label(gc, 310*factorX, 350*factorY, "Height: ", Font.loadFont(MAIN_FONT, 45));
		TextBox widthT = new TextBox(gc, 205*factorX, 305*factorY, 60*factorX, 50*factorY, screenType, true);
		widthT.setCondition(difficulty::isSelected);
		widthT.setMaxLength(2);
		TextBox heightT = new TextBox(gc, 470*factorX, 305*factorY, 60*factorX, 50*factorY, screenType, true);
		heightT.setMaxLength(2);
		heightT.setCondition(difficulty::isSelected);
		widthT.setOnClick(Focusable.unFocusOthers(elements, widthT));
		heightT.setOnClick(Focusable.unFocusOthers(elements, heightT));
		add(widthT);
		addFocus(widthT);
		add(heightT);
		addFocus(heightT);

		List<Button> buttons = new ArrayList<>();
		Button superEasy = new Button(gc, 72*factorX, 300*factorY, 100*factorX, 100*factorY, 173, 1, 100, 100);
		Button easy = new Button(gc, 183*factorX, 300*factorY, 100*factorX, 100*factorY, 275, 1, 100, 100);
		Button medium = new Button(gc, 294*factorX, 300*factorY, 100*factorX, 100*factorY, 377, 1, 100, 100);
		Button hard = new Button(gc, 405*factorX, 300*factorY, 100*factorX, 100*factorY, 1, 103, 100, 100);
		Button extraHard = new Button(gc, 516*factorX, 300*factorY, 100*factorX, 100*factorY, 103, 103, 100, 100);
		Button insane = new Button(gc, 627*factorX, 300*factorY, 100*factorX, 100*factorY, 205, 103, 100, 100);
		buttons.add(superEasy);
		buttons.add(easy);
		buttons.add(medium);
		buttons.add(hard);
		buttons.add(extraHard);
		buttons.add(insane);
		for (Button but : buttons){
			but.setCondition(() -> !difficulty.isSelected());
		}
		DifficultySelector ds = new DifficultySelector(gc, 61, 315, 677, 70, buttons);
		superEasy.setOnClick(() -> {
			selectedDifficulty = Difficulty.SUPER_EASY;
			ds.update(0);
		});
		easy.setOnClick(() -> {
			selectedDifficulty = Difficulty.EASY;
			ds.update(1);
		});
		medium.setOnClick(() -> {
			selectedDifficulty = Difficulty.MEDIUM;
			ds.update(2);
		});
		hard.setOnClick(() -> {
			selectedDifficulty = Difficulty.HARD;
			ds.update(3);
		});
		extraHard.setOnClick(() -> {
			selectedDifficulty = Difficulty.EXTRA_HARD;
			ds.update(4);
		});
		insane.setOnClick(() -> {
			selectedDifficulty = Difficulty.INSANE;
			ds.update(5);
		});
		add(superEasy);
		add(easy);
		add(medium);
		add(hard);
		add(extraHard);
		add(insane); 
		ds.show();
		ds.update(0);
		  
		difficulty.setOnClick(() -> {
                    
                        // FIX BUG WHEN CLEARING 1 PX LESS
                    
			gc.clearRect(30*factorX+1, 275*factorY+1, 740*factorX-1, 128*factorY-1);
			gc.setFill(Color.web("#48d9c8"));
			gc.fillRect(30*factorX, 275*factorY, 740*factorX, 128*factorY);
			if (difficulty.isSelected()){
				widthL.show();
				heightL.show();
				widthT.show();
				heightT.show();
			} else {
				ds.show();
			}
		});
		Button create = new Button(gc, 585*factorX, 415*factorY, 175*factorX, 55*factorY, "#96ec81");
		create.setOnClick(() -> {
			try {
				int sizeX = 0;
				int sizeY = 0;
				if (difficulty.isSelected()){
					sizeX = Integer.parseInt(widthT.getText());
					sizeY = Integer.parseInt(heightT.getText());
				} else {
					Random r = new Random();
					sizeX = r.nextInt(selectedDifficulty.getMaxSize()-selectedDifficulty.getMinSize())+selectedDifficulty.getMinSize();
					sizeY = r.nextInt(selectedDifficulty.getMaxSize()-selectedDifficulty.getMinSize())+selectedDifficulty.getMinSize();
				}
				MazeDisplay md = new MazeDisplay(gc, sizeX, sizeY, seedBox.getText().equals("") ? null : Long.parseLong(seedBox.getText()));
				md.display();
			} catch (NumberFormatException ex){
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Please check your values!");
				alert.setContentText("Inputs are not numbers.");
				alert.setTitle("NumberFormatException");
				alert.showAndWait();
			}
		});
		Label seedL = new Label(gc, 40*factorX, 170*factorY, "Seed: ", Font.loadFont(MAIN_FONT, 45));
		seedL.show();
		Label difficultyL = new Label(gc, 40*factorX, 245*factorY, "Custom size: ", Font.loadFont(MAIN_FONT, 45));
		difficultyL.show();

		add(seedBox);
		addFocus(seedBox);
		add(difficulty);
		add(create);
		seedBox.show();
		difficulty.show();
		create.show();
	}
}