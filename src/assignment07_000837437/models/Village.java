package assignment07_000837437.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Scanner;

/**
 * Implementation of an Village that describes buildings and its population
 * This is the class header.
 *
 * @date March 26, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public class Village {
    /**The default Y location of a Village*/
    public static final double Y_FLOOR=150;
    /**The number of buildings including houses and apartment buildings in a village*/
    private int size;
    /**The name of a village*/
    private String name;
    /**All buildings of a village stored in an array*/
    private Building[]buildings;

    /**
     * Constructor to determine the name and the number of buildings of a village
     *
     * @param name the name of a village
     * @param size the number of buildings of a village
     */
    private Village(int size, String name) {
        this.size = size;
        this.name = name;
        this.buildings=new Building[size];      // All buildings of a village stored in an array
        for (int count=0; count<size;count++){
            Scanner sc=new Scanner(System.in);
            System.out.print("\tBuilding "+(count+1)+"...\n\t\t");      // typing for each building of this village
            String apartmentOrHouse="";
            while (true) {
                System.out.print("(A)partment Building or (H)ouse? ");  // typing an apartment building or a house, A= apartment, H= house
                apartmentOrHouse = sc.next();
                if (apartmentOrHouse.equalsIgnoreCase("h") || apartmentOrHouse.equalsIgnoreCase("a")) break;
                else System.out.print("\t\tWrong input. ");
            }   // create an apartment building or a house after entering
            this.buildings[count]= apartmentOrHouse.equalsIgnoreCase("h") ? House.create() : ApartmentBuilding.create();
        }
    }

    /**
     * create method asks the user the name, the number of buildings of a village
     * then creates a village object by calling its private constructor and stores it in a local variable.
     * @return a village
     * */
    public static Village create(){
        Scanner sc=new Scanner(System.in);
        System.out.print("What's the village name? ");  // input the name of a village
        String nameVillage=sc.nextLine();
        int numsBuildings=0;
        while (true) {
            System.out.print("How many buildings does it have? (>0): ");
            numsBuildings = sc.nextInt();               // input the number of buildings of a village
            if (numsBuildings>0) break;
            else System.out.print("Wrong input. ");
        }
        return new Village(numsBuildings,nameVillage);  // create a village
    }

    /**
     * The population of a village
     * @return an integer number describes the population of a village
     * */
    public int getPopulation(){
        int sumPopulation=0;
        for (Building building : buildings)
            sumPopulation+=((Dwelling)building).getNumberOfOccupants();
        return sumPopulation;
    }

    /**
     * The draw method of a village that draws it on a GraphicsContext object
     * */
    public void draw(GraphicsContext gc){
        for (Building building : buildings)
            building.draw(gc);                  // drawing each building in a village
        gc.setFont(Font.font("System", 20));
        gc.setLineWidth(2);
        gc.setStroke(Color.BLUE);               // set color for the text of the name of a village
        gc.strokeText(this.name,5,15); //draw with locationX=5 and locationY=15 for this text
    }

    /**
     * The toString method prints the name of the village, and all the buildings of this village
     * @return a string the name of the village, and all the buildings of this village
     */
    @Override
    public String toString() {
        String printBuilding="";
        for (Building building : buildings)
            printBuilding+=building;
        return "\nVillage of "+this.name+":"+printBuilding;
    }

}
