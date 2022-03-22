package assignment07_000837437.models;
/**
 * Implementation of a HighRise method that describes the number of floors of an apartment building
 * This is the class header.
 *
 * @date March 26, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public abstract class HighRise extends Building{
    /**Description the number of floors of an apartment building*/
    private int numberOfFloors;

    /**
     * Constructor to determine the name, xPosition and the number of floors of an apartment building
     *
     * @param name the name of an apartment building
     * @param xPosition the x location of an apartment building
     * @param numberOfFloors the number of floors of an apartment building
     */
    public HighRise(String name, double xPosition, int numberOfFloors) {
        super(name, xPosition);
        this.numberOfFloors = numberOfFloors;
    }

    /**
     * The number of floors of an apartment building can be easily retrieved by anyone.
     * @return an integer number describes the number of floors of an apartment building
     * */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * The toString method prints the class name, the name of the building, its x location and the number of floors of an apartment building
     * @return a string the class name, the name of the building, its x location and the number of floors of an apartment building
     */
    @Override
    public String toString() {
        return "\n\t\tType... High Rise: number of floors = "+ this.numberOfFloors+super.toString();
    }
}
