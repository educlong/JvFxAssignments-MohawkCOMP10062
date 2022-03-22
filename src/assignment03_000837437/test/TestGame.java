package assignment03_000837437.test;

import assignment03_000837437.models.Game;
import java.util.Scanner;

/**
 *  Program simulate the game of “DICE”, configured according to user input.
 * This is the class header.
 *
 * @date Feb 8, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public class TestGame {
    /**
     * Start method ( main).
     * @param args unused
     */
    public static void main(String[] args) {
        System.out.print("DICE GAME!");
        while(true) {
            Game game1=new Game("Paul","Mary");              //creates 2 Game objects, One game should use default dice,
            Game game2=new Game("Peter","Luke",14); //the other uses dice

            //** Output for game1 Object
            System.out.println("\n\n\n"+game1); //Print the game object
            game1.playTextGame();               //Call its playTextGame method
            System.out.println(game1);          //Print the game object again

            //** Output for game2 Object
            System.out.println("\n\n\n"+game2);
            game2.playTextGame();
            System.out.println(game2);

            System.out.print("Would you like to continue (y/n)? "); //asking user to continue or not
            if ((new Scanner(System.in)).next().equalsIgnoreCase("n")) break; //if no, end of the program
        }
    }
}
