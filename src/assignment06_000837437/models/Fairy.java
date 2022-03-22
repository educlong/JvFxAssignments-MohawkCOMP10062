package assignment06_000837437.models;
/**
 * Implementation of a human who is a fairy
 * This is the class header.
 *
 * @date March 13, 2021
 * @author DUC LONG NGUYEN (Paul)
 */
public class Fairy extends Inhabitant{

    /**
     * Constructor to determine the name and basic attributes of a fairy
     *
     * @param nameInhabitant the name of a fairy
     * @param strengthRating the strength ability of a fairy
     * @param agilityRating the agility ability of a fairy
     * @param armourRating the armour ability of a fairy
     * @param healthRating the health ability of a fairy
     */
    public Fairy(String nameInhabitant, int strengthRating, int agilityRating, int armourRating, int healthRating) {
        super(nameInhabitant, strengthRating, agilityRating, armourRating, healthRating);
    }

    /**
     * Constructor to determine the name and other default attributes of a fairy
     *
     * @param nameInhabitant the name of a fairy
     */
    public Fairy(String nameInhabitant) {
        super(nameInhabitant);
    }

    /**
     * A fairy can replenish a healer’s magic using a spell.
     * The spell resets the healer’s magic rating randomly to a value between 0 and 10.
     * @param healer the healer is replenish his/her magic ratings
     * */
    public void spellReplenishMagic(Human healer){
        if (!alive() || healer.isHunter()) return;
        healer.setMagicRating(Math.min(healer.getMagicRating() + ((int) (Math.random() * 10) +1),10));
    }

    /**
     * The toString method prints the class name, the name of the humanoid,
     * whether they are alive or dead, and all their ratings (health, strength, etc.).
     * @return a string the class name, the name of a fairy and all his/her ratings
     */
    @Override
    public String toString() {
        return "Fairy: "+ super.toString();
    }
}
