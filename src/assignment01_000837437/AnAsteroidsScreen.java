package assignment01_000837437;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 *  Program draw a single screen from the classic video game Asteroids, configured according to user input.
 *  (this program used folder images)
 * This is the class header.
 *
 * @date Jan 26, 2021
 * @author DUC LONG NGUYEN (Paul)
 */
public class AnAsteroidsScreen extends Application {

    /**
     * Start method (use this instead of main).
     *
     * @param stage The FX stage to draw on
     * @param WIDTH_OF_SCREEN The width of screen
     * @param HEIGHT_OF_SCREEN The height of screen
     * @param gc Class is used to issue draw calls to a Canvas using a buffer.
     * @param scanner Class is used to get user input
     * @param number0fAsteroids The number of Asteroids
     * @param score The score of player displayed in the northwest of the screen
     * @param lives The number of lives remaining
     * @param shipPlayerX The x location of the player’s spaceship
     * @param shipPlayerY The y location of the player’s spaceship
     * @param shipColor The color of the player’s spaceship and the alien spaceship
     * @throws Exception
     */
    GraphicsContext gc;
    int WIDTH_OF_SCREEN=550, HEIGHT_OF_SCREEN=550;
    Scanner scanner;
    @Override
    public void start(Stage stage) throws Exception {
        String copyrightAuthor = "Duc Long Nguyen (Paul), 2021 ";
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(WIDTH_OF_SCREEN, HEIGHT_OF_SCREEN);  // Set canvas Size in Pixels
        stage.setTitle("An Asteroids Screen");                          // Set window title
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.setResizable(false);                                      //disable screen to expand
        gc = canvas.getGraphicsContext2D();

        // ________ STARING TO DRAW ___________
        gc.drawImage(new Image(getClass().getResourceAsStream("images/startsky.jpg")),0,0); //set background for screen
        scanner = new Scanner(System.in);
        int number0fAsteroids, score, lives, shipPlayerX, shipPlayerY, shipColor;
        String wrongInput="Invalid input. ";

        // *** CODING FOR asteroid
        // *** input
        while(true) {
            System.out.print("Enter the number of asteroids (minimun 0):");
            number0fAsteroids=scanner.nextInt();
            if (number0fAsteroids>=0)
                break;
            else
                System.out.print(wrongInput);
        }
        // *** processing and output
        asteroid(number0fAsteroids);


        // *** CODING FOR scores and Copyright Author's name
        // *** input
        while(true) {
            System.out.print("Enter the current score (minimun 0):");
            score=scanner.nextInt();
            if (score>=0)
                break;
            else
                System.out.print(wrongInput);
        }
        // *** processing and output
        scoresAndCopyright(score,copyrightAuthor);

        // *** CODING FOR Lives Remaining
        // *** input
        while(true) {
            System.out.print("Enter the number of lives remaining (minimun 0):");
            lives=scanner.nextInt();
            if (lives>=0)
                break;
            else
                System.out.print(wrongInput);
        }
        // *** processing and output
        livesRemaining(lives);

        // *** CODING FOR Space ship of the player
        // *** input to enter the X location of the player's ship
        while(true) {
            System.out.print("Enter the X location of the player's ship (0-500):");
            shipPlayerX=scanner.nextInt();
            if (shipPlayerX>=0 && shipPlayerX<=500)
                break;
            else
                System.out.print(wrongInput);
        }
        // *** input to enter the Y location of the player's ship
        while(true) {
            System.out.print("Enter the Y location of the player's ship (0-500):");
            shipPlayerY=scanner.nextInt();
            if (shipPlayerY>=0 && shipPlayerY<=500)
                break;
            else
                System.out.print(wrongInput);
        }
        Color color = null;
        // *** input to choose the color of the space ship
        while(true){
            System.out.print("The color of the space ship, using the number choices below: " +
                    "\n\t1 - RED\n\t2 - BLUE\n\t3 - GREEN\nEnter your choice: ");
            shipColor=scanner.nextInt();
            if(shipColor==1) {
                color=Color.RED;
                break;
            }
            else if(shipColor==2) {
                color=Color.BLUE;
                break;
            }
            else if(shipColor==3) {
                color=Color.GREEN;
                break;
            }
            else{
                    System.out.print(wrongInput);
            }
        }
        // *** processing and output
        spaceshipOfPlayer(color, shipPlayerX, shipPlayerY);

        // *** CODING FOR Space ship of the Alien
        // *** input
        while(true){
            System.out.print("The color of the alien space ship, using the number choices below: " +
                    "\n\t1 - PURPLR\n\t2 - PINK\n\t3 - ORANGE\nEnter your choice: ");
            shipColor=scanner.nextInt();
            if(shipColor==1) {
                color=Color.PURPLE;
                break;
            }
            else if(shipColor==2) {
                color=Color.PINK;
                break;
            }
            else if(shipColor==3) {
                color=Color.ORANGE;
                break;
            }
            else{
                System.out.print(wrongInput);
            }
        }
        // *** processing and output
        spaceshipOfAlien(color);

        // *** show screen to stage
        stage.show();
        // ________ ENDING TO DRAW ___________
    }

    /**
     * The method to draw the space ship of Alien
     *
     * @param color The color of the space ship of Alien
     */
    private void spaceshipOfAlien(Color color) {
        gc.setFill(color);
        int localX, localY;
        int count=0;
        while(true) {
            // *** input the X location of the alien space ships
            while(true) {
                System.out.print("Enter the X location of the alien space ships "+(count++)+" (0-500) " +
                        "or enter -1 to stop entering alien ships: ");
                localX=scanner.nextInt();
                if (localX>=-1 && localX<=500)
                    break;
                else
                    System.out.print("Invalid input. ");
            }
            if (localX==-1)     //if X location is entered -1, break while loop
                break;
            // *** input the Y location of the alien space ships
            while(true) {
                System.out.print("Enter the y location of the alien space ships "+(count++)+" (0-500): ");
                localY=scanner.nextInt();
                if (localY>=0 && localY<=500)
                    break;
                else
                    System.out.print("Invalid input. ");
            }
            // *** output
            gc.fillPolygon(new double[]{localX, localX + 5, localX + 10, localX + 15,
                            localX + 20, localX + 25, localX + 17, localX + 8},
                    new double[]{localY, localY - 3, localY - 8, localY - 8,
                                localY - 3, localY, localY + 5, localY + 5},
                    8);
        }
    }

    /**
     * The method to draw the space ship of Player
     *
     * @param color The color of the space ship of Player
     * @param localX The X location of the space ships of Player
     * @param localY The Y location of the space ships of Player
     */
    private void spaceshipOfPlayer(Color color, int localX, int localY) {
        gc.setFill(color);
        gc.fillPolygon(new double[]{localX,5+localX,localX,7+localX,10+localX,13+localX,
                        20+localX, 15+localX, 20+localX,13+localX,7+localX},
                new double[]{40+localY, 15+localY,10+localY,10+localY, 5+localY, 10+localY,
                            10+localY,15+localY, 40+localY, 25+localY, 25+localY},
                11);
    }

    /**
     * The method to show the number of lives remaining
     *
     * @param liveRemaining The number of lives remaining
     */
    private void livesRemaining(int liveRemaining) {
        int locationX=160;
        gc.setFill(Color.LIGHTSALMON);
        gc.setFont(new Font("System", 15));
        gc.fillText("Lives Remaining ("+liveRemaining+"): ", 10, 40);
        for (int count=0; count<liveRemaining; count++) {
            gc.fillPolygon(new double[]{locationX, 5+locationX, 10+locationX, 6+locationX, 4+locationX},
                        new double[]{40, 25, 40, 35, 35}, 5);
            locationX+=15;
        }
    }

    /**
     * The method to show the score of player and copyright of the name of Author
     *
     * @param score The score of player
     * @param copyrightAuthor The name of Author
     */
    private void scoresAndCopyright(int score,String copyrightAuthor) {
        // *** score
        gc.setFill(Color.YELLOWGREEN);
        gc.setFont(new Font("Times New Roman", 15));
        gc.fillText("Score: "+score, 10, 20);
        // *** copyright
        gc.setFont(new Font("Arial", 15));
        gc.fillText(copyrightAuthor, WIDTH_OF_SCREEN/2.0-100, HEIGHT_OF_SCREEN-10);
    }

    /**
     * The method to draw the asteroid
     *
     * @param numberAsteroid The number of the asteroid
     */
    private void  asteroid(int numberAsteroid) {
        int asteroidX, asteroidY;
        for(int count=0;count<numberAsteroid;count++) {
            int color=(int)(Math.random()*100)+156;
            gc.setFill(Color.rgb(color,color,color));
            // *** input the X location of the asteroid
            while(true) {
                System.out.print("Enter the X co-ordinate of the center of the asteroid"+(count+1)+" (0-500):");
                asteroidX=scanner.nextInt();
                if (asteroidX>=0 && asteroidX<=500)
                    break;
                else
                    System.out.print("Invalid input. ");
            }
            // *** input the Y location of the asteroid
            while(true) {
                System.out.print("Enter the y co-ordinate of the center of the asteroid"+(count+1)+" (0-500):");
                asteroidY=scanner.nextInt();
                if (asteroidY>=0 && asteroidY<=500)
                    break;
                else
                    System.out.print("Invalid input. ");
            }
            // *** output
            gc.fillOval(asteroidX,asteroidY,Math.random() * 20 + 20, Math.random() * 20 + 20);
        }
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
