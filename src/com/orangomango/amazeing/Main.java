package com.orangomango.amazeing;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.TilePane;

import java.io.File;

import com.orangomango.amazeing.ui.scene.*;

/**
 * Game main class
 *
 * @version 1.0
 * @author OrangoMango
 */
public class Main extends Application {

    public static final String VERSION = "1.0";
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 500;
    public static final String GAME_HOME = System.getProperty("user.home")+ File.separator+".amazeing";
    public static final String MAIN_FONT;
    public static final String SPRITESHEET;
    /**
     * Game Frames Per Second
     */
    public static final double FPS = 30;
    private Stage stage;
    public static Screens CURRENT_SCREEN = Screens.HOME;

    static {
        MAIN_FONT = "file:///" + convertSlash(GAME_HOME+File.separator+"resources/main_font.ttf");
        SPRITESHEET = "file:///" + convertSlash(GAME_HOME+File.separator+"resources/spritesheet.png");
    }

    private static String convertSlash(String input){
        StringBuilder out = new StringBuilder();
        for (char c : input.toCharArray()){
            if (Character.toString(c).equals("\\")){
                out.append("/");
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Downloading content - aMAZEing");

		/*this.stage.widthProperty().addListener((o, oldV, newV) -> {
			canvas.setWidth((double)newV);
			updateCanvas(canvas);
		});
		this.stage.heightProperty().addListener((o, oldV, newV) -> {
			canvas.setHeight((double)newV);
			updateCanvas(canvas);
		});*/

        LoadingScreen loading = new LoadingScreen(this.stage);
        loading.setOnFinish(() -> {
            Stage gameStage = new Stage();
            gameStage.setTitle("aMAZEing by OrangoMango v" + VERSION);
            Canvas canvas = new Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            canvas.setFocusTraversable(true);
            updateCanvas(canvas);
            gameStage.setScene(new Scene(new TilePane(canvas), DEFAULT_WIDTH, DEFAULT_HEIGHT));
            gameStage.show();
        });
        if ((new File(GAME_HOME)).exists()){
            loading.finish();
        } else {
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(loading.getScene());
            stage.show();
        }
    }

    private void updateCanvas(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Home h = Screens.getHomeScreen(gc);
        //MazeDisplay h = new MazeDisplay(gc, 20, 20, null);
        h.display();
    }
}
