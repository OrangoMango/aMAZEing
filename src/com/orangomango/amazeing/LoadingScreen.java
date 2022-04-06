package com.orangomango.amazeing;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;

import static com.orangomango.amazeing.Main.DEFAULT_WIDTH;
import static com.orangomango.amazeing.Main.DEFAULT_HEIGHT;

public class LoadingScreen {
    public Scene getScene(){
        GridPane pane = new GridPane();
        Label info = new Label("Downloading resources...");
        info.setStyle("-fx-font-size: 18");
        ProgressBar bar = new ProgressBar();
        bar.setPrefWidth(DEFAULT_WIDTH);
        
        pane.add(info, 0, 0);
        pane.add(bar, 0, 1);
        return new Scene(pane, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
