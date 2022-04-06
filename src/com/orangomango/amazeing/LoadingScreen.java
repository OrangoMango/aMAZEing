package com.orangomango.amazeing;

import com.orangomango.amazeing.ui.event.FinishedEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.concurrent.Task;

import java.net.*;
import java.io.*;
import java.nio.file.*;

import com.orangomango.amazeing.profile.*;
import com.orangomango.amazeing.ui.event.FinishedEvent;
import static com.orangomango.amazeing.Main.GAME_HOME;
import static com.orangomango.amazeing.Main.DEFAULT_WIDTH;
import static com.orangomango.amazeing.Main.DEFAULT_HEIGHT;

public class LoadingScreen {
    private final Stage stage;
    private FinishedEvent onFinish;

    public LoadingScreen(Stage stage){
        this.stage = stage;
        Profile.prepareDirectory();
    }

    public void setOnFinish(FinishedEvent ev){
        this.onFinish = ev;
    }

    private void downloadFile(String link, String path) {
        try (InputStream in = new URL(link).openStream()) {
            Files.copy(in, Paths.get(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Scene getScene(){
        GridPane pane = new GridPane();
        Label info = new Label("Downloading resources...");
        info.setStyle("-fx-font-size: 18");
        ProgressBar bar = new ProgressBar();
        bar.setPrefWidth(DEFAULT_WIDTH);

        Task<Object> task = new Task<Object>(){
            @Override
            protected Object call() throws Exception{
                String home = System.getProperty("user.home");
                updateProgress(0, 2);
                updateMessage("Downloading spritesheet...");
                downloadFile("https://github.com/OrangoMango/aMAZEing/raw/main/resources/spritesheet.png", GAME_HOME+File.separator+"resources"+File.separator+"spritesheet.png");
                updateProgress(1, 2);
                updateMessage("Downloading font...");
                downloadFile("https://github.com/OrangoMango/aMAZEing/raw/main/resources/main_font.ttf",GAME_HOME+File.separator+ "resources"+File.separator+"main_font.ttf");
                updateProgress(2, 2);
                updateMessage("Done.");
                Thread.sleep(1000);
                return null;
            }
        };

        bar.progressProperty().bind(task.progressProperty());
        info.textProperty().bind(task.messageProperty());
        Thread t = new Thread(task);
        t.start();

        task.setOnSucceeded(event -> {
            this.stage.close();
            this.onFinish.finish();
        });
        
        pane.add(info, 0, 0);
        pane.add(bar, 0, 1);
        return new Scene(pane, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
