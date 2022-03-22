package assignment04_000837437.test;

import assignment04_000837437.models.SlotMachine;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 *  Program draw a single screen from the game Slot Machine, configured according to user input.
 * This is the class header.
 *
 * @date March 1st, 2021
 * @author DUC LONG NGUYEN (Paul)
 */
public class TestSlotMachine extends Application {

    /**
     * Start method (use this instead of main).
     *
     * @param stage The FX stage to draw on
     * @param WIDTH_OF_SCREEN The width of screen
     * @param HEIGHT_OF_SCREEN The height of screen
     * @throws Exception
     */
    int WIDTH_OF_SCREEN=200, HEIGHT_OF_SCREEN=220;
    @Override
    public void start(Stage stage) {
        stage.setTitle("Section 1.4 Animated!"); // window title here
        Canvas canvas = new Canvas(WIDTH_OF_SCREEN, HEIGHT_OF_SCREEN); // canvas size here
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // This code starts a "thread" which will run your animation
        Thread t = new Thread(() -> animate(gc));
        t.start();
    }

    /**
     * Animation thread. This is where you put your animation code.
     *
     * Note: Although most of what you will do will probably work, there is a
     * possibility of the application appearing to freeze after a while because
     * the drawing is not happening in a thread safe way. If this is happening,
     * create a private draw() method with parameters for x, y, etc. to do the
     * actual drawing work, then call it like this...
     *
     * Platform.runLater(() -> {
     *      draw(x,y...);
     * });
     *
     * @param gc The drawing surface
     */
    public void animate(GraphicsContext gc) {
        // ________ STARING TO DRAW ___________
        gc.setFont(Font.font("System", 20));
        gc.setLineWidth(2);
        // *** CODING FOR SLOT MACHINE 1
        // *** setup fot Slot Machine 1
        int numFaces1=9, numWheels1=5;
        String[]faces1= {"Apple","Cherry","Kiwi","Strawberry","Banana","Orange","Pineapple","Grapes","Mango"};
        Color[]colors1= {Color.RED,Color.BLACK,Color.BLUE,Color.GREEN,Color.GRAY};
        // *** CODING FOR SLOT MACHINE 1
        // *** setup fot Slot Machine 1
        int numFaces2=7, numWheels2=7;
        String[]faces2= {"Apple","Cherry","Kiwi","Strawberry","Banana","Orange","Pineapple"};
        Color[]colors2=new Color[numWheels2];
        for (int count=0; count<numWheels2;count++){
            colors2[count]=Color.BLUE;
        }
        // *** input from Scanner
        Scanner sc = new Scanner(System.in);
        while (true) {
            int choiceSlotMachine;
            System.out.print("Choose a machine. Machine 1 is colorful and has " + numWheels1
                    + " wheels with " + numFaces1 + " faces.\nMachine 2 is monochrome and has "
                    + numWheels2 + " wheels with " + numFaces2 + " faces. \nChoice 1 or 2 : ");
            while (true) {                                  //choice slotmachine 1 or 2 (try again if wrong input)
                choiceSlotMachine = sc.nextInt();
                // *** process
                if (choiceSlotMachine == 1 || choiceSlotMachine == 2)
                    break;
                else
                    System.out.print("Invalid input. Try again: ");
            }
            while (true) {
                SlotMachine slotMachine1 = new SlotMachine(numWheels1, colors1, numFaces1, faces1);
                SlotMachine slotMachine2 = new SlotMachine(numWheels2, colors2, numFaces2, faces2);
                SlotMachine slotMachineChoice = new SlotMachine();
                if (choiceSlotMachine == 1)                 //choice slot machine 1 or 2
                    slotMachineChoice = slotMachine1;       //and stored selected SlotMachine into slotMachineChoice
                else
                    slotMachineChoice = slotMachine2;
                // *** output
                displayResutl(gc,slotMachineChoice);        // display result to a new screen and console screen
                // *** process
                System.out.print("\tChoose a function (1 =  Spin Again.    2 = Lotsa spins.    3 = No more spins): ");
                int choiceFunction = sc.nextInt();          //choice functions (spin again, lotsa spins or no more spins)
                if (choiceFunction == 2) {
                    slotMachineChoice.histogram();          //showing histogram if function=2
                } else if (choiceFunction == 3) {           //exit if function=2
                    System.out.println("\t_____BYE!____");
                    break;
                } else if (choiceFunction != 1)
                    System.out.print("\tInvalid input. Try again\n");
            }
            System.out.print("===================================\nWould you like to continue (y/n)? "); //asking user to continue or not
            if ((new Scanner(System.in)).next().equalsIgnoreCase("n")) System.exit(0); //if no, end of the program
        }
    }

    /**
     * The method to print Selected Slot Machine to console screen and draw the text of selected faces to new screen.
     *
     * @param gc class GraphicsContext is used to issue draw calls to a Canvas using a buffer
     * @param slotMachineChoice Selected Slot Machine
     */
    private void displayResutl(GraphicsContext gc, SlotMachine slotMachineChoice) {
        System.out.println(slotMachineChoice);                          //print Slot Machine chosen.
        gc.clearRect(0, 0, WIDTH_OF_SCREEN, HEIGHT_OF_SCREEN);  //clear for new screen
        slotMachineChoice.drawWheels(gc,30,5);        //draw Slot Machine to screen
    }

    /**
     * Use this method instead of Thread.sleep(). It handles the possible
     * exception by catching it, because re-throwing it is not an option in this
     * case.
     *
     * @param duration Pause time in milliseconds.
     */
    public static void pause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
        }
    }

    /**
     * Exits the app completely when the window is closed. This is necessary to
     * kill the animation thread.
     */
    @Override
    public void stop() {
        System.exit(0);
    }

    /**
     * Launches the app
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
