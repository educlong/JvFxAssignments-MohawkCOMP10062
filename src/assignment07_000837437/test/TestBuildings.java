package assignment07_000837437.test;

import assignment07_000837437.models.Village;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Program draw a single screen from the description a village, configured according to user input.
 * This is the class header.
 *
 * @date March 26, 2021
 * @author DUC LONG NGUYEN (Paul)
 */
public class TestBuildings extends Application {

    /**
     * Start method (use this instead of main).
     *
     * @param stage The FX stage to draw on
     * @param WIDTH_OF_SCREEN The width of screen
     * @param HEIGHT_OF_SCREEN The height of screen
     * @throws Exception
     */
    int WIDTH_OF_SCREEN=400, HEIGHT_OF_SCREEN=200;
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(WIDTH_OF_SCREEN, HEIGHT_OF_SCREEN);
        stage.setTitle("Building!");
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.setResizable(false);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // ________ MY CODE GOES HERE ___________
        Village v = Village.create();   // Create a village
        v.draw(gc);                     //draw this village
        System.out.println(v + "\nTotal population: " + v.getPopulation());
        stage.show();
    }

    /**
     * The actual main method that launches the app.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
