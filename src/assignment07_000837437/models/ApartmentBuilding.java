package assignment07_000837437.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Scanner;
/**
 * Implementation of an Building that describes an apartment building and its occupants
 * This is the class header.
 *
 * @date March 26, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public class ApartmentBuilding extends HighRise implements Dwelling{
    /**The number of occupants in a floor of an apartment building*/
    private int occupantsPerFloor;

    /**
     * Constructor to determine the name, xPosition, the number of floors and the number of occupants in a floor of an apartment building
     *
     * @param name the name of an apartment building
     * @param xPosition the x location of an apartment building
     * @param numberOfFloors the number of floors of an apartment building
     * @param occupantsPerFloor the number of occupants in a floor of an apartment building
     */
    private ApartmentBuilding(String name, double xPosition, int numberOfFloors, int occupantsPerFloor) {
        super(name, xPosition, numberOfFloors);
        this.occupantsPerFloor = occupantsPerFloor;
    }

    /**
     * create method asks the user the name, the X position, the number of floors and the number of occupants in a floor of an apartment building,
     * then creates an apartment building object by calling its private constructor and stores it in a local variable.
     * @return an apartment building
     * */
    public static ApartmentBuilding create(){
        Scanner sc=new Scanner(System.in);
        System.out.print("\t\t\t\tApartment Building Name? ");
        String nameApartmentBuilding=sc.nextLine();             // asks the user the name of an apartment building
        System.out.print("\t\t\t\tPosition? ");
        double xPosition=sc.nextDouble();                       // asks the user the x Position of an apartment building
        System.out.print("\t\t\t\tNumber of Floors? ");
        int floors=sc.nextInt();                                // asks the user the number of floors of an apartment building
        System.out.print("\t\t\t\tNumber of Occupants Per Floor? ");
        int occupantsPerFloor=sc.nextInt();                     // asks the user the number of occupants in a floor of an apartment building,
        return new ApartmentBuilding(nameApartmentBuilding,xPosition,floors,occupantsPerFloor); //create an apartment building
    }

    /**
     * The number of occupants of an apartment building equals the total of occupants of all floors in an apartment building.
     * @return an integer number describes the number of occupants of an apartment building
     * */
    @Override
    public int getNumberOfOccupants() {
        return this.occupantsPerFloor*getNumberOfFloors();
    }

    /**
     * The draw method of an apartment building that draws it on a GraphicsContext object
     * */
    @Override
    public void draw(GraphicsContext gc){
        double height=10, width=65;                 //the height and width of a floor in an apartment building
        double yLocation=Village.Y_FLOOR + height;  //the y location of the first floor
        gc.setStroke(Color.GREEN);
        gc.setFont(Font.font("System", 10));
        gc.setLineWidth(0.7);
        gc.strokeText(getName(),getXPosition(),yLocation+height+20);        // drawing the name of an apartment building
        for (int countFloor=0; countFloor<getNumberOfFloors(); countFloor++) {   // drawing for each floor
            gc.setFill(Color.RED);
            gc.fillRect(getXPosition(),             // the xLocation is stable, but yLocation will decrease for each floor
                    (getNumberOfFloors()!=1 && countFloor==getNumberOfFloors()-1)? (yLocation-10) : yLocation,
                    width,                          // the width of an floor is stable, but its height will change for each floor
                    (countFloor==0) ? (height + 5) : ((getNumberOfFloors()!=1 && countFloor==getNumberOfFloors()-1) ? (height+10) : height));
            gc.setFill(Color.WHITE);
            for (int nextXLocation = 10, countRoom = 0; countRoom < 5; countRoom++, nextXLocation += 10)    //draw the windows for each floor
                gc.fillRect(getXPosition() + nextXLocation, yLocation+2, 5, 5);
            yLocation-=height;
        }
    }

    /**
     * The toString method prints the name of the building, its x location, the number of floors and the number of occupants in a floor of an apartment building
     * @return a string the name of the building, its x location, the number of floors and the number of occupants in a floor of an apartment building
     */
    @Override
    public String toString() {
        return "\n\tApartment Building: occupants per floor = "+this.occupantsPerFloor+super.toString();
    }
}
