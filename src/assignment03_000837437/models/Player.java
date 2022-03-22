package assignment03_000837437.models;

/**
 * Implementation of a player has an player's name (String), integer number of score and 3 dice
 * Player can take a turn with 3 dice. It's scored after taking a turn.
 * This is the class header.
 *
 * @date Feb 8, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public class Player {
    /**The name of a player */
    private String name;
    /**The score of a player */
    private int score;
    /**3 dice of a player */
    private Die die1, die2, die3;

    /**
     * Constructor to determine initial values of a player
     * The player’s dice have 6 sides, and creates the 3 Dice objects
     * @param name the name of a player
     *
     */
    public Player(String name) {
        this.name = name;
        die1=new Die(6);   //The player’s dice have 6 sides, and creates the 3 Dice objects
        die2=new Die(6);
        die3=new Die(6);
        this.score=0;               // A player starts with 0 points
    }

    /**
     * Constructor to determine initial values of a player
     *
     * @param name the name of a player
     *
     */
    public Player(String name, int numSides) {
        this.name = name;
        die1=new Die(numSides); //The player’s dice have numSides sides, and creates the 3 Dice objects
        die2=new Die(numSides);
        die3=new Die(numSides);
        this.score=0;           // A player starts with 0 points
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    /**
     * The takeTurn method rolls all 3 dice,
     * adds up the two highest numbers for the turn score,
     * adds the turn score to the player’s total score,
     * and returns the turn score.
     *
     * @return the turn score after taking a turn
     *
     */
    public int takeTurn(){
        die1.roll();    //rolling the first die of a player
        die2.roll();    //rolling the second die
        die3.roll();    //rolling the third die
        int turnScore;  //turnScore: adds up the two highest numbers for the turn score,
        turnScore = Math.max(Math.max(die1.getShowing()+die2.getShowing(), die2.getShowing()+die3.getShowing()),die3.getShowing()+die1.getShowing());
        this.score+=turnScore;  //adds the turn score to the player’s total score,
        return turnScore;       //and returns the turn score.
    }

    /**
     * The toString method returns a string containing the player’s name, their score, and their three dice.
     *
     * @return a string including the player’s name, their score, and their three dice.
     */
    @Override
    public String toString() {
        return "Player " + this.name +": "+"score=" + this.score + ", dice={"+this.die1+", "+this.die2+", "+this.die3+"}";
    }
}
