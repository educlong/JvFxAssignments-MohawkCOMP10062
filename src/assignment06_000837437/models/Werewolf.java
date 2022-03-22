package assignment06_000837437.models;
/**
 * Implementation of a human who is a werewolf. Every Werewolf belongs to either the“Blue Moon” or “Red Moon”pack
 * and each Werewolf has two Fairies that they consider their allies.
 * This is the class header.
 *
 * @date March 13, 2021
 * @author DUC LONG NGUYEN (Paul)
 */
public class Werewolf extends Inhabitant{
    /**Every Werewolf belongs to either the “Blue Moon” or “Red Moon” pack. =true: blueMoon, =false: redMoon.*/
    private boolean blueMoon;
    /**Each Werewolf has two Fairies that they consider their allies. */
    private Fairy[] fairiesAllies;

    /**
     * Constructor to determine the name and basic attributes of a werewolf
     *
     * @param nameInhabitant the name of a werewolf
     * @param strengthRating the strength ability of a werewolf
     * @param agilityRating the agility ability of a werewolf
     * @param armourRating the armour ability of a werewolf
     * @param healthRating the health ability of a werewolf
     * @param blueOrRedMoon the kind of Moon pack of the werewolf. =true: blueMoon, =false: redMoon.
     * @param fairiesAllies the Fairies of Each Werewolf
     */
    public Werewolf(String nameInhabitant, int strengthRating, int agilityRating,
                    int armourRating, int healthRating, boolean blueOrRedMoon,
                    Fairy[] fairiesAllies) {
        super(nameInhabitant, strengthRating, agilityRating, armourRating, healthRating);
        this.blueMoon=blueOrRedMoon;
        this.fairiesAllies = new Fairy[2];
        if (alive())    //if the werewolf dead, the werewolf's allies would be null. Otherwise, adding the werewolf's allies
            for (int count=0; count<this.fairiesAllies.length; count++)     //if the fairy is dead, adding null.
                this.fairiesAllies[count]=(fairiesAllies[count].alive() ? fairiesAllies[count] : null);
    }

    /**
     * Constructor to determine the name and other default attributes of a werewolf
     *
     * @param nameInhabitant the name of a werewolf
     */
    public Werewolf(String nameInhabitant) {
        super(nameInhabitant);
        blueMoon=true;                      //by default, a werewolf belongs the "Blue Moon" pack
        this.fairiesAllies=new Fairy[2];    //if the werewolf dead, the werewolf's allies would be null.
        for (int count=0; count<this.fairiesAllies.length;count++)      //Otherwise, adding the werewolf's default allies
            this.fairiesAllies[count]=new Fairy("Fairy"+(count+1)+" of werewolf "+nameInhabitant);
    }

    /**
     * The isBlueMoon method determines the kind of Moon pack of the werewolf. =true: blueMoon, =false: redMoon.
     * Every Werewolf belongs to either the “Blue Moon” or “Red Moon” pack.
     * They are proud of their allegiance and would never change it.
     * @return a boolean type that is equal true, meaning the werewolf belongs "Blue Moon", and false meaning "Red Moon"
     * */
    public boolean isBlueMoon() {
        return blueMoon;
    }

    /**
     * The getFairiesAllies method determines the allies of a werewolf
     * @return a fairy array that is the allies of a werewolf if the werewolf is alive. Otherwise, return null if the werewolf is dead
     * */
    public Fairy[] getFairiesAllies() {
        if (!alive())
            this.fairiesAllies=new Fairy[2];    //return null if the werewolf is dead
        return this.fairiesAllies;
    }

    /**
     * However,the werewolves are not trusting and can change their minds about which Fairies they align with.
     * The setFairiesAllies method generates two fairies for a werewolf, meaning change a werewolf's minds about the allies.
     * @param fairiesAllies a fairy array that is the allies of a werewolf if the werewolf is alive.
     * */
    public void setFairiesAllies(Fairy[] fairiesAllies) {
        if (!alive()) return;                   //if the werewolf is dead, the werewolf's mind cannot change
        for (int count=0; count<fairiesAllies.length; count++) {
            if (!fairiesAllies[count].alive())
                continue;       //if a fairy is dead, keeping previous default fairy, but continuing to change the next fairy
            this.fairiesAllies[count] = fairiesAllies[count];
        }
    }

    /**
     * The toString method prints the class name, the name of the humanoid,
     * whether they are alive or dead, and all their ratings (health, strength, blueMoon, fairiesAllies, etc.).
     * @return a string the class name, the name of a werewolf, all his/her ratings and allies
     */
    @Override
    public String toString() {
        String werewolfAllies="";
        for (Fairy fairy: fairiesAllies){
            werewolfAllies+="\n\t\t- "+fairy;
        }
        return "Werewolf: "+super.toString()+" This werewolf belongs " + (blueMoon ? "Blue" : "Red") +
                " Moon. \n\tThe Fairies of this werewolf are: " + werewolfAllies;
    }
}
