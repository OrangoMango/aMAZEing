package com.orangomango.amazeing;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.TilePane;

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
    public static final String MAIN_FONT = "file:///C:/Users/Khloud/OneDrive/Documents/NetBeansProjects/aMAZEing/src/resources/main_font.ttf";
    public static final String SPRITESHEET = "file:///C:/Users/Khloud/OneDrive/Documents/NetBeansProjects/aMAZEing/src/resources/spritesheet.png";
    /**
     * Game Frames Per Second
     */
    public static final double FPS = 100;
    private Stage stage;
    public static Screens CURRENT_SCREEN = Screens.HOME;

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("aMAZEing by OrangoMango v" + VERSION);

        Canvas canvas = new Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        canvas.setFocusTraversable(true);
        updateCanvas(canvas);
		/*this.stage.widthProperty().addListener((o, oldV, newV) -> {
			canvas.setWidth((double)newV);
			updateCanvas(canvas);
		});
		this.stage.heightProperty().addListener((o, oldV, newV) -> {
			canvas.setHeight((double)newV);
			updateCanvas(canvas);
		});*/
        
        stage.setScene(new Scene(new TilePane(canvas), DEFAULT_WIDTH, DEFAULT_HEIGHT));
        //LoadingScreen loading = new LoadingScreen();
        
        //stage.initStyle(StageStyle.UNDECORATED);
        //stage.setScene(loading.getScene());
        stage.show();
    }

    private void updateCanvas(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Home h = Screens.getHomeScreen(gc);
        //MazeDisplay h = new MazeDisplay(gc, 20, 20, null);
        h.display();
    }
}
