package assignment06_000837437;

import assignment06_000837437.models.*;

import java.util.Arrays;

/**
 *  Program to test the models package
 * This is the class header.
 *
 * @date March 13, 2021
 * @author DUC LONG NGUYEN (Paul)
 */
public class Test {
    public static void main(String[] args) {
        Inhabitant trudeau=new Inhabitant("Justin Trudeau");
        Human putin=new Human("Vladimir Putin",null);
        Werewolf kim=new Werewolf("Kim Jong Un");
        Human obama=new Human("Barrack Obama", kim);
        Fairy trump=new Fairy("Donald Trump");

        System.out.println(trudeau);
        System.out.println(putin);
        System.out.println(kim);
        System.out.println(obama);
        System.out.println(trump);

        trump.spellReplenishMagic(obama);
        System.out.println("====================================\n"+obama);

        obama.healingScore(trudeau);
        System.out.println("====================================\nMagic Rating of "+obama.getNameInhabitant()+" = "+ obama.getMagicRating()+" => "+trudeau);
        System.out.println("Defend score (health rating) of "+trudeau.getNameInhabitant() +" after against the attack of "
                +putin.getNameInhabitant()+" (damage score = "+putin.attackScore()+") = "+trudeau.defendScore(putin.attackScore()));


        Fairy[] fairies={new Fairy("Gabriel",3,4,5,0),
                        new Fairy("Michael",6,7,8,3)};
        Werewolf lucifer=new Werewolf("Lucifer",9,10,4,0,true,fairies);

        System.out.println("====================================\n"+lucifer+"\n====================================");
        for (Fairy fairy : lucifer.getFairiesAllies())
            System.out.println(fairy);
        System.out.println(Arrays.toString(lucifer.getFairiesAllies()));

        Werewolf satan=new Werewolf("Satan");
        satan.setFairiesAllies(fairies);
        System.out.println("====================================\n"+satan);
        System.out.println(Arrays.toString(satan.getFairiesAllies()));

    }
}
