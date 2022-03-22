package assignment07_000837437.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Scanner;

/**
 * Implementation of a building that describes an house, the number of bedrooms and its occupants
 * This is the class header.
 *
 * @date March 26, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public class House extends Building implements Dwelling{
    /**The number of bedrooms in a house*/
    private int bedrooms;
    /**The number of occupants in a house*/
    private int occupants;

    /**
     * Constructor to determine the name, xPosition, the number of bedrooms and the number of occupants in a house
     *
     * @param name the name of a house
     * @param xPosition the x location of a house
     * @param bedrooms the number of bedrooms of a house
     * @param occupants the number of occupants in a house
     */
    private House(String name, double xPosition, int bedrooms, int occupants) {
        super(name, xPosition);
        this.bedrooms = bedrooms;
        this.occupants = occupants;
    }

    /**
     * create method asks the user the name, the X position, the number of Bedrooms and the number of occupants of a house
     * then creates a house object by calling its private constructor and stores it in a local variable.
     * @return a house
     * */
    public static House create(){
        Scanner sc=new Scanner(System.in);
        System.out.print("\t\t\t\tHouse Name? ");
        String nameHouse=sc.nextLine();     // asks the user the name of a house
        System.out.print("\t\t\t\tPosition? ");
        double xPosition=sc.nextDouble();   // asks the user the x Position of a house
        System.out.print("\t\t\t\tNumber of Bedrooms? ");
        int bedrooms=sc.nextInt();          // asks the user the number of bedrooms of a house
        System.out.print("\t\t\t\tNumber of Occupants? ");
        int occupants=sc.nextInt();         // asks the user the number of occupants of a house
        return new House(nameHouse,xPosition,bedrooms,occupants);   //create a house
    }

    /**
     * The number of occupants of a house
     * @return an integer number describes the number of occupants of a house
     * */
    @Override
    public int getNumberOfOccupants() {
        return this.occupants;
    }

    /**
     * The toString method prints the name of the house, its x location, the number of bedrooms and the number of occupants of a house
     * @return a string the name of the house, its x location, the number of bedrooms and the number of occupants of a house
     */
    @Override
    public String toString() {
        return "\n\tHouse: bedroom = "+this.bedrooms+", occupants = "+this.occupants+super.toString();
    }

    /**
     * The draw method of a house that draws it on a GraphicsContext object
     * */
    @Override
    public void draw(GraphicsContext gc) {
        double yLocation=Village.Y_FLOOR, height=25, width=25;              // the y location, height and width of a house
        gc.setFill(Color.RED);
        gc.fillRect(getXPosition(),yLocation,width,height);                 // drawing a house
        gc.setFill(Color.WHITE);
        gc.fillRect(getXPosition()+2,yLocation+2,5,5);    // drawing a window of a house
        gc.fillRect(getXPosition()+15,yLocation+5,8,18);  // drawing a door of a house
        gc.setStroke(Color.GREEN);
        gc.setFont(Font.font("System", 10));
        gc.setLineWidth(0.7);
        gc.strokeText(getName(),getXPosition(),yLocation+height+15);    // drawing the name of a house
    }
}
