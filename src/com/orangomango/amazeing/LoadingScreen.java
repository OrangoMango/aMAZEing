package com.orangomango.amazeing;

import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
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
    }

    public void setOnFinish(FinishedEvent ev){
        this.onFinish = ev;
    }

    public void finish(){
        this.onFinish.finish();
    }

    private static void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    private static void downloadFile(String link, String path, Task task) {
        if (task.isCancelled()){
            return;
        }
        try (InputStream in = new URL(link).openStream()) {
            Files.copy(in, Paths.get(path));
        } catch (IOException ex) {
            task.cancel();
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Internet error");
                alert.setHeaderText("Connection error!");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                deleteDirectory(new File(GAME_HOME));
                System.exit(1);
            });
        }
    }

    public Scene getScene(){
        Profile.prepareDirectory();
        GridPane pane = new GridPane();
        Label info = new Label("Downloading resources...");
        info.setStyle("-fx-font-size: 18");
        ProgressBar bar = new ProgressBar();
        bar.setPrefWidth(DEFAULT_WIDTH);

        Task<Object> task = new Task<Object>(){
            @Override
            protected Object call() throws Exception{
                updateProgress(0, 2);
                updateMessage("Downloading spritesheet...");
                downloadFile("https://www.github.com/OrangoMango/aMAZEing/raw/master/resources/spritesheet.png", GAME_HOME+File.separator+"resources"+File.separator+"spritesheet.png", this);
                updateProgress(1, 2);
                updateMessage("Downloading font...");
                downloadFile("https://www.github.com/OrangoMango/aMAZEing/raw/master/resources/main_font.ttf",GAME_HOME+File.separator+ "resources"+File.separator+"main_font.ttf", this);
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
