package assignment03_000837437.models;

/**
 * Implementation of a game object has a number of rounds to roll, the number of sides of a die and 2 player
 * This is the class header.
 *
 * @date Feb 8, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public class Game {
    /**3 rounds to roll */
    public static final int NUM_ROUNDS=3;
    /**The number of sides of a die */
    private int numSides;
    /**2 player play this game. */
    private Player player1, player2;

    /**
     * Constructor default to determine initial values of a game
     * A Game uses numSides dice, and creates the 2 Player objects
     * @param p1Name the name of a first player
     * @param p2Name the name of a second player
     * @param numSides the number of sides of a die
     *
     */
    public Game(String p1Name, String p2Name,int numSides) {
        this.numSides = numSides;
        player1 = new Player(p1Name,numSides);
        player2 = new Player(p2Name,numSides);
    }

    /**
     * Constructor default to determine initial values of a game
     * A Game uses 6-sided dice, and creates the 2 Player objects
     * @param p1Name the name of a first player
     * @param p2Name the name of a second player
     *
     */
    public Game(String p1Name, String p2Name) {
        this.numSides = 6;
        player1 = new Player(p1Name);
        player2 = new Player(p2Name);
    }

    /**
     * The playTextGame method plays a multi-round game using the NUM_ROUNDS constant to determine how many rounds.
     */
    public void playTextGame(){
        for (int i=1;i<=NUM_ROUNDS;i++)
            System.out.println("\tRound "+i
                    +".\n\t\t\t"+player1.getName()+" rolls "+player1.takeTurn()     //Each round, it calls the takeTurn method for each Player
                    +".\n\t\t\t"+player2.getName() +" rolls "+player2.takeTurn()    //and reports the score for that round for each Player
                    +".\n\t\tResult:\n\t\t\t"+player1+"\n\t\t\t"+player2);          //then prints each Player object to System.out
    }

    /**
     * The toString method returns a string with the number of sides on the dice,
     * each player’s name and score, and the name of the player who is currently winning
     *
     * @return a string including number of sides on the dice, each player’s name and score, and the winner
     */
    @Override
    public String toString() {
        return "Game Summary: Best of "+NUM_ROUNDS+" ("+this.numSides+"-sided). "   //the number of rounds and the number of sides of a die
                +player1.getName()+" has "+player1.getScore()+" points, "           //name and score of player 1
                +player2.getName()+" has "+player2.getScore()+" points. "           //name and score of pkayer 2
                +((player1.getScore() == player2.getScore()) ? "It's a tie so far..." :     //print if player1's score = player2's score
                (((player1.getScore()> player2.getScore()) ? player1.getName() : player2.getName()) +" is Winning"));   //print winner.
    }
}
