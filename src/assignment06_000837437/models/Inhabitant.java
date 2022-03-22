package assignment06_000837437.models;
/**
 * Implementation of a inhabitant has a name
 * who has numeric ratings of four basic attributes that describe his/her abilities
 * This is the class header.
 *
 * @date March 13, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public class Inhabitant {
    /**All Inhabitants have names that cannot change.*/
    private String nameInhabitant;
    /**They all have a strengthRating that describe their Strength ability*/
    private int strengthRating;
    /**They all have an agilityRating that describe their Agility ability*/
    private int agilityRating;
    /**They all have an armourRating that describe their Armour ability*/
    private int armourRating;
    /**They all have an healthRating that describe their Health ability*/
    private int healthRating;

    /**
     * Constructor to determine the name and basic attributes of an inhabitant
     *
     * @param nameInhabitant the name of an inhabitant
     * @param strengthRating the strength ability of an inhabitant
     * @param agilityRating the agility ability of an inhabitant
     * @param armourRating the armour ability of an inhabitant
     * @param healthRating the health ability of an inhabitant
     */
    public Inhabitant(String nameInhabitant, int strengthRating, int agilityRating, int armourRating, int healthRating) {
        this.nameInhabitant = nameInhabitant;
        this.strengthRating = strengthRating;
        this.agilityRating = agilityRating;
        this.armourRating = armourRating;
        this.healthRating = healthRating;
    }

    /**
     * Constructor to determine the name and other default attributes of an inhabitant
     *
     * @param nameInhabitant the name of an inhabitant
     */
    public Inhabitant(String nameInhabitant) {
        this.nameInhabitant = nameInhabitant;
        this.strengthRating= (int) (Math.random() * 10) +1; //within the range 0 to 10
        this.agilityRating=(int) (Math.random() * 10) +1;
        this.armourRating=(int) (Math.random() * 10) +1;
        this.healthRating=(int) (Math.random() * 10) +1;
    }

    /**
     * The increaseRating method describe that these attributes of an inhabitant can be raised by one point at a time, within the range 0 to 10
     * */
    public void increaseRating() {
        this.strengthRating=alive() ? (this.strengthRating>=10 ? 10 : Math.max(++this.strengthRating, 0)): 0;   //within the range 0 to 10
        this.agilityRating=(alive() ? (this.agilityRating>=10 ? 10 : Math.max(++this.agilityRating,0)): 0);
        this.armourRating=(alive() ? (this.armourRating>=10 ? 10 : Math.max(++this.armourRating,0)): 0);
        this.healthRating=(alive() ? (this.healthRating>=10 ? 10 : Math.max(++this.healthRating,0)): 0);
    }

    /**
     * The decreaseRating method describe that these attributes of an inhabitant can be lowered by one point at a time, within the range 0 to 10
     * */
    public void decreaseRating() {
        this.strengthRating=(alive() ? (this.strengthRating>10 ? 10 : Math.max(--this.strengthRating, 0)): 0);  //within the range 0 to 10
        this.agilityRating=(alive() ? (this.agilityRating>10 ? 10 : Math.max(--this.agilityRating,0)): 0);
        this.armourRating=(alive() ? (this.armourRating>10 ? 10 : Math.max(--this.armourRating,0)): 0);
        this.healthRating=(alive() ? (this.healthRating>10 ? 10 : Math.max(--this.healthRating,0)): 0);
    }

    /**
     * The name of an inhabitant can be easily retrieved by anyone.
     * @return a string name of the inhabitant
     * */
    public String getNameInhabitant() {
        return nameInhabitant;
    }

    /**
     * The strength ability of an inhabitant can be easily retrieved by anyone.
     * @return an integer number represents the strength ability of the inhabitant
     * */
    public int getStrengthRating() {
        this.strengthRating=(alive() ? (this.strengthRating>=10 ? 10 : Math.max(this.strengthRating, 0)): 0);   //within the range 0 to 10
        return strengthRating;
    }

    /**
     * The agility ability of an inhabitant can be easily retrieved by anyone.
     * @return an integer number represents the agility ability of the inhabitant
     * */
    public int getAgilityRating() {
        this.agilityRating=(alive() ? (this.agilityRating>=10 ? 10 : Math.max(this.agilityRating,0)): 0);   //within the range 0 to 10
        return agilityRating;
    }

    /**
     * The armour ability of an inhabitant can be easily retrieved by anyone.
     * @return an integer number represents the armour ability of the inhabitant
     * */
    public int getArmourRating() {
        this.armourRating=(alive() ? (this.armourRating>=10 ? 10 : Math.max(this.armourRating,0)): 0);  //within the range 0 to 10
        return armourRating;
    }

    /**
     * The health ability of an inhabitant can be easily retrieved by anyone.
     * @return an integer number represents the health ability of the inhabitant
     * */
    public int getHealthRating() {
        this.healthRating=(this.healthRating>=10 ? 10 : Math.max(this.healthRating,0)); //within the range 0 to 10
        return healthRating;
    }

    /**
     * The alive method describe that an Inhabitant is alive if their heath rating is greater than 0, otherwise they are dead
     * @return a boolean type that is equal true, meaning this inhabitant is alive, and is equal false, meaning this inhabitant is dead
     * */
    public boolean alive(){
        return getHealthRating()>0;
    }

    /**
     * set the health ability for an inhabitant
     * @param healthRating the health ability of an inhabitant
     * */
    protected void setHealthRating(int healthRating) {
        this.healthRating = (alive() ? (Math.min(healthRating, 10)) : 0);   //within the range 0 to 10
    }

    /**
     * The toString method prints the class name, the name of the humanoid,
     * whether they are alive or dead, and all their ratings (health, strength, etc.).
     * @return a string the class name, the name of an inhabitant and all his/her ratings
     */
    @Override
    public String toString() {
        return this.nameInhabitant + " {Inhabitant: Strength Rating = " + this.strengthRating +
                ", Agility Rating = " + this.agilityRating + ", Armour Rating = " + this.armourRating +
                ", Health Rating = " + this.healthRating + "} is " +(alive() ? "alive." : "dead.");
    }

    /**
     * The attackScore method describe that when an Inhabitant other than a Hunter attacks,
     * it produces a damage score equal to the average of its strength, agility, and health.
     * Dead inhabitants cannot attack themselves.
     * @return an integer number describes a damage score
     * */
    public int attackScore(){
        return alive() ? (int)((this.strengthRating+this.agilityRating+this.healthRating)/3) : 0;
    }

    /**
     * The defendScore method describe that When an inhabitant defends itself against an attack,
     * it will lose health points equal to the damage score of the attack divided by its armor rating.
     * If the damage score divided by the armor rating is less than 1, than it will lose 1 health point.
     * Dead inhabitants cannot defend themselves.
     *
     * @param attackDamage the damage score of the attack
     * @return an integer number describes the health rating of an inhabitant after defending.
     * */
    public int defendScore(int attackDamage){
        this.healthRating = (alive() ? (this.healthRating-Math.max((int)(attackDamage/this.armourRating),1)) : 0);
        return alive() ? this.healthRating : 0;
    }

}
