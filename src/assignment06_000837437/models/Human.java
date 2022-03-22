package assignment06_000837437.models;
/**
 * Implementation of a human who is a hunter or a healer
 * If human is a healer, a healer is going to be a friend of a single werewolf and start with a magic ratings
 * This is the class header.
 *
 * @date March 13, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public class Human extends Inhabitant{
    /**A Human can be a Healer or a Hunter. =true -> hunter, =false -> healer.  */
    private boolean hunter;
    /**A Healer is a friend of a single Werewolf and this does not change.*/
    private Werewolf friend;
    /**A healer starts with a magic rating that is randomly set between 0 and 10.*/
    private int magicRating;

    /**
     * Constructor to determine the name and basic attributes of a human
     *
     * @param nameInhabitant the name of a human
     * @param strengthRating the strength ability of a human
     * @param agilityRating the agility ability of a human
     * @param armourRating the armour ability of a human
     * @param healthRating the health ability of a human
     * @param hunter if the human is a hunter or a healer
     * @param friend a friend (a werewolf) of a healer if the human is a healer
     */
    public Human(String nameInhabitant, int strengthRating, int agilityRating,
                 int armourRating, int healthRating, boolean hunter, Werewolf friend) {
        super(nameInhabitant, strengthRating, agilityRating, armourRating, healthRating);
        this.hunter=hunter;
        this.friend= !this.hunter ? (alive() ? friend : null) : null;                           //if the human is a hunter, friend = null
        this.magicRating= !this.hunter ? (alive() ? ((int) (Math.random() * 10) +1) : 0) : -1;  //if the human is a hunter, magicRating = -1
    }

    /**
     * Constructor to determine the name and other default attributes of a human
     *
     * @param nameInhabitant the name of a human
     * @param friend a friend (a werewolf) of a healer if the human is a healer
     */
    public Human(String nameInhabitant, Werewolf friend) {
        super(nameInhabitant);
        this.hunter = (friend == null); //=true if the human is a hunter. Otherwise, =false if the human is a healer
        this.friend= friend;            //if the human is a hunter, friend = null
        this.magicRating= ((friend!=null) ? ((int) (Math.random() * 10) +1) : -1);      //if the human is a hunter, magicRating = -1
    }

    /**
     * The isHunter method determines if the human is a hunter or not
     * @return a boolean type that is equal true, meaning this human is a hunter, and is equal false, meaning this human is a healer
     * */
    public boolean isHunter() {
        return this.hunter;
    }

    /**
     * The getFriend method determines the friend of a healer
     * @return a werewolf object that is a friend of a healer if the human is a healer. Otherwise, return null if the human is a hunter
     * */
    public Werewolf getFriend() {
        return !hunter ? (alive() ? this.friend : null) : null;
    }

    /**
     * The getMagicRating method determines the magic rating of a healer
     * @return an integer number represents the magic rating of a healer
     * */
    public int getMagicRating() {
        return !hunter ? (alive() ? this.magicRating : 0) : -1;
    }

    /**
     * set the magic rating of a healer, if the human is a hunter, the magic rating = -1
     * @param magicRating magic rating of a healer
     * */
    protected void setMagicRating(int magicRating) {
        this.magicRating = !hunter ? (alive() ? magicRating : 0) : -1;
    }

    /**
     * The attackScore method describe that when a Hunter attacks, it does twice the normal damage.
     * it produces a damage score equal to the average of its strength, agility, and health.
     * Dead inhabitants cannot attack themselves.
     * @return an integer number describes a damage score
     * */
    @Override
    public int attackScore() {
        return this.hunter ? super.attackScore()*2 : super.attackScore();
    }

    /**
     * The healingScore method describe that when a Healer uses their magic,
     * they can produce a health boost to help another Inhabitant.
     * The healing score is equal to their magic rating divided by 2, but the healer loses 1 point from their magic rating.
     * If its magic rating is 0 or less, it cannot heal. Dead Healers can’t heal.
     * @param inhabitant an inhabitant is helped a health boost through the healingScore method from a healer.
     * */
    public void healingScore(Inhabitant inhabitant){
        if (!alive() || this.hunter || this.magicRating<=0) return;     //If its magic rating is 0 or less, it cannot heal
        inhabitant.setHealthRating(inhabitant.getHealthRating() + (int)(this.magicRating/2));
        this.magicRating--;     //the healer loses 1 point from their magic rating.
    }

    /**
     * The toString method prints the class name, the name of the humanoid,
     * whether they are alive or dead, and all their ratings (health, strength, hunter, friend, magicRating, etc.).
     * @return a string the class name, the name of a hunter or healer and all his/her ratings
     */
    @Override
    public String toString() {
        return "Human -> "+(hunter? "Hunter: " : "Healer: ") +super.toString() + (hunter ? "" :
                (" The magic rating of this healer = " +magicRating+"\n\tThe friend of this healer is a " + friend));
    }
}
