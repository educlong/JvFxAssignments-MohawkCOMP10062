package assignment07_000837437.models;

/**
 * Implementation of a Building has a name and its X location
 * This is the class header.
 *
 * @date March 26, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public abstract class Building implements Drawable{
    /**A Building have a name that cannot change.*/
    private String name;
    /**The X location of a building*/
    private double xPosition;

    /**
     * Constructor to determine the name and xPosition of a building
     *
     * @param name the name of a building
     * @param xPosition the x location of a building
     */
    public Building(String name, double xPosition) {
        this.name = name;
        this.xPosition = xPosition;
    }

    /**
     * The X location of a building can be easily retrieved by anyone.
     * @return an integer number describes a X location
     * */
    @Override
    public double getXPosition() {
        return this.xPosition;
    }

    /**
     * The name of a building can be easily retrieved by anyone.
     * @return an string describes a name of this building
     * */
    public String getName() {
        return name;
    }

    /**
     * The toString method prints the class name, the name of the building and its x location.
     * @return a string the class name, the name of the building and its x location.
     */
    @Override
    public String toString() {
        return "\n\t\t\tType... Building: name = "+this.name+", xPosition = "+this.xPosition;
    }
}
