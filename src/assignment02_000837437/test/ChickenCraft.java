package assignment02_000837437.test;

import java.util.Scanner;

import assignment02_000837437.models.Chicken;

/**
 *  Program simulate the world of “ChickenCraft”, configured according to user input.
 * This is the class header.
 *
 * @date Jan 31, 2021
 * @author DUC LONG NGUYEN (Paul)
 * 
 */

public class ChickenCraft {

    /**
     * Start method ( main).
     *
     */
	public static void main(String[] args) {
		System.out.println("Welcome to ChickenCraft!\n");
		Chicken syd=new Chicken("Syd",4,0.1,true);						// set 3 object default
		Chicken nancy=new Chicken("Nancy",4,0.6,true);
		Chicken johnette=new Chicken("Johnette",4,0,false);
		while(true) {
			System.out.println("1."+syd+"\n2."+nancy+"\n3."+johnette);	// print these objects and a menu
			System.out.print("Let's go ->\t1. Feed\t\t2. Play\t\t3. Hit\t\t4. Get Eggs\t\t5. Quit\n\t");
			Scanner sc=new Scanner(System.in);
			
			// *** CODING FOR choosing functions from 1 to 5
	        // *** input
			int choice=choisingFunctions(sc);
			if(choice==5) {
				System.out.println("Thank you! See you soon!");	// if user choose 5 -> Quit.
				System.exit(0);
				break;
			}
			
			// *** CODING FOR choosing chicken: Syd, Nancy or Johnette
	        // *** input
			System.out.print("\t");
			int whichChicken=choisingChicken(sc);
			Chicken chickenSelected = (whichChicken==1) ? syd : ((whichChicken==2) ? nancy : johnette);

			// *** processing (except function 2)
			menu(sc,choice,chickenSelected);
			
			// *** processing for function choice=2 (a Chicken play with a different Chicken)
			if(choice==2) {
				if(chickenSelected.checkDeadChicken())
					System.out.println("\t "+chickenSelected.getNameChicken()+" is dead");	//cannot if chicken is dead
				else {
					System.out.print("\t Play with");
					int playWith=choisingChicken(sc);
					if(playWith!=whichChicken) {		// A chicken cannot play with itself
						Chicken chickenPlayed = (playWith==1) ? syd : ((playWith==2) ? nancy : johnette);
						playWithChicken(chickenSelected,chickenPlayed);
					}
				}
			}
			System.out.println();
		}
	}

	/**
     * The method to choice a function for a chicken
     *
     * @param sc The Scanner class is used to get user input
     * @param choice Choosing functions: 1->feed, 3->hit, 4->get eggs.
     * @param chickenSelected choosing a chicken to do one of these functions above.
     * 
     */
	private static void menu(Scanner sc,int choice, Chicken chickenSelected) {
		// TODO Auto-generated method stub
		switch (choice) {
			case 1: {
				feedChicken(sc,chickenSelected);
				break;
			}
			case 3:{
				hitChicken(chickenSelected);
				break;
			}
			case 4:{
				getEggs(chickenSelected);
				break;
			}
			default:{
				break;	//case 2: processing in main
			}
		}
	}
	
	/**
     * The method to a chicken play with a different chicken
     *
     * @param chickenSelected play with chickenPlayed
     * @param chickenPlayed play with chickenSelected
     */
	private static void playWithChicken(Chicken chickenSelected, Chicken chickenPlayed) {
		if(chickenSelected.playChicken(chickenPlayed))
			System.out.println("\t Success!");
		else
			System.out.println("\t "+ chickenPlayed.getNameChicken() + " is dead.");		
	}
	
	/**
     * The method to get eggs from a chicken
     *
     * @param chickenSelected chicken selected to get eggs
     */
	private static void getEggs(Chicken chickenSelected) {
		int getEggs=chickenSelected.getEggsChicken();
		if(getEggs==-1)
			System.out.println("\t "+chickenSelected.getNameChicken()+" is dead");	//cannot if this chicken dead
		else
			System.out.println("\t You got "+getEggs+" eggs.");		
	}

	/**
     * The method to hit a chicken
     *
     * @param chickenSelected chicken is hit
     */
	private static void hitChicken( Chicken chickenSelected) {
		if(chickenSelected.checkDeadChicken())
			System.out.println("\t "+chickenSelected.getNameChicken()+" is dead");	//cannot if this chicken dead
		else {
			chickenSelected.hitChicken();
			if(chickenSelected.checkDeadChicken())
				System.out.println("\t "+chickenSelected.getNameChicken()+" is dead");	//if after hit, chicken is dead
			else
				System.out.println("\t Ouch!");
		}		
	}

	/**
     * The method to feed a chicken
     *
     * @param sc The Scanner class is used to get user input
     * @param chickenSelected chicken is fed
     */
	private static void feedChicken(Scanner sc, Chicken chickenSelected) {
		if(chickenSelected.checkDeadChicken())
			System.out.println("\t "+chickenSelected.getNameChicken()+" is dead");	//cannot if this chicken dead
		else {
			// *** Announcement the amount of seed make a chicken dead
			System.out.print("\t How much seed? (If you feed "+chickenSelected.getNameChicken()
							+" > "+(2.0-chickenSelected.getSeedsChicken())+"kg, "
							+chickenSelected.getNameChicken()+" will be dead): ");
			double howMuchSeed=sc.nextDouble();			// *** input: the amount of seed user feed

			chickenSelected.feedChicken(howMuchSeed);	// *** processing
			if(chickenSelected.checkDeadChicken())
				System.out.println("\t "+chickenSelected.getNameChicken()+" is dead");	//dead if feeding too much
			else
				System.out.println("\t Success!");
		}		
	}

	/**
     * The method to choose function from user
     *
     * @param sc The Scanner class is used to get user input
     * @return The number is selected to do a function
     */
	private static int choisingFunctions(Scanner sc) {
		// TODO Auto-generated method stub
		int choice;
		while(true) {
			System.out.print("Choice: ");
			choice=sc.nextInt();
			if(choice>0 && choice<6)
				break;
			System.out.print("\tInvalid input. ");
		}
		return choice;
	}

	/**
     * The method to choose a chicken from user
     *
     * @param sc The Scanner class is used to get user input
     * @return The number is selected to choose a chicken
     */
	private static int choisingChicken(Scanner sc) {
		// TODO Auto-generated method stub
		int chicken;
		while(true) {
			System.out.print(" Which chicken? ");
			chicken=sc.nextInt();
			if(chicken>0 && chicken<4) {
				break;
			}
			System.out.print("\t Invalid input. ");
		}
		return chicken;
	}
}
