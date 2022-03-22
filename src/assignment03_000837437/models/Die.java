package assignment03_000837437.models;

/**
 * Implementation of a die has an integer number of sides.
 * It can roll itself, and it can report the side that’s currently showing.
 * This is the class header.
 *
 * @date Feb 8, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public class Die {
    /**the number of sides of a die. **/
    private int numSides;
    /**the number currently showing in a side of a die. **/
    private int showing;

    /**
     * Constructor to determine the number of sides of this die.
     *
     * @param numSides the number of sides of this die.
     *
     */
    public Die(int numSides) {
        this.numSides = numSides;
    }

    /**
     * The roll method generates a random number between 1 and numSides and stores that as the number showing.
     */
    public void roll(){
        this.showing=(int)(Math.random()*this.numSides)+1;  //a random number between 1 and numSides and stores that as the number showing.
    }

    public int getNumSides() {
        return numSides;
    }

    public int getShowing() {
        return showing;
    }

    /**
     * The toString method prints the number currently showing and the number of sides.
     * @return a string including the number currently showing and the number of sides.
     */
    @Override
    public String toString() {
        return this.showing+"/"+this.numSides;
    }
}
