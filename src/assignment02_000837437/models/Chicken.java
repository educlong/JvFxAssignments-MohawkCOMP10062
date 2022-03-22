package assignment02_000837437.models;

import java.text.DecimalFormat;

public class Chicken {
	private String nameChicken;		//the name of chicken
	private int heartsChicken;		//represent the health of a chicken
	private double seedsChicken;	//in kilograms
	private boolean happyChicken;	//happyChicken = true meaning a chicken is happy, false meaning sad
	/**
     * The service method to check a chicken dead or alive
     *
     * @return true if this chicken dead
     */
	public boolean checkDeadChicken() {
		return this.seedsChicken > 2.0  || this.heartsChicken<=0;
	}

	public Chicken(String nameChicken, int heartsChicken, double seedsChicken, boolean happyChicken) {
		super();
		this.nameChicken = nameChicken;
		this.heartsChicken = heartsChicken;
		this.seedsChicken = seedsChicken;
		this.happyChicken = happyChicken;
	}

	public Chicken() {				//* When a Chicken is “born”, it alive
		this.nameChicken="Nancy";	//* By default,its name is “Nancy”,
		this.heartsChicken=4;		//* it has 4 hearts,
		this.seedsChicken=0.1;		//* it has 0.1kg of seed,
		this.happyChicken=true;		//* and it's happy.
	}

	public String getNameChicken() {
		return nameChicken;
	}

	public void setNameChicken(String nameChicken) {
		this.nameChicken = nameChicken;
	}

	public int getHeartsChicken() {
		return heartsChicken;
	}

	public void setHeartsChicken(int heartsChicken) {
		this.heartsChicken = heartsChicken;
	}

	public double getSeedsChicken() {
		return seedsChicken;
	}

	public void setSeedsChicken(double seedsChicken) {
		this.seedsChicken = seedsChicken;
	}

	public boolean isHappyChicken() {
		return happyChicken;
	}

	public void setHappyChicken(boolean happyChicken) {
		this.happyChicken = happyChicken;
	}

	@Override
	public String toString() {						//* For example: Sad DEAD Chicken Syd: 3kg seeds, 4 hearts.
		// TODO Auto-generated method stub
		return ((this.happyChicken && this.checkDeadChicken()==false)?" Happy ":" Sad ") 
				+(this.checkDeadChicken() ? "DEAD ":"") + "Chicken "+this.nameChicken+": "
				+(new DecimalFormat("#.##")).format(this.seedsChicken)+"kg seeds, "+this.heartsChicken+" hearts.";
	}
	
	/**
     * The service method to feed a chicken
     *
     * @param seedAmount The amount of seed user feed
     */
	public void feedChicken(double seedAmount) {
		if(checkDeadChicken()) return;				//* If chicken with more than 2 kg of seed in its stomach will die. 
		this.seedsChicken+=seedAmount;
		if(this.heartsChicken<4)					//* Is hearts go up by 1 (maximum 4) every time you feed it
			this.heartsChicken++;
	}
	
	/**
     * The service method to a chicken play with a different chicken
     *
     * @param chicken The chicken is played with
     * @return true if both are alive
     */
	public boolean playChicken(Chicken chicken) {	//* Giving a Chicken a different Chicken to play with.
		if(checkDeadChicken() || chicken.checkDeadChicken()) return false;
		this.happyChicken=true;						//* This makes both Chickens happy.
		chicken.happyChicken=true;
		return true;
	}

	/**
     * The service method to hit a chicken
     */
	public void hitChicken() {
		if(checkDeadChicken()) return;	//* Hit a Chicken and make its hearts go down by 1, and makes it dead (if it has 0 hearts)
		this.heartsChicken--;
		this.happyChicken=false;		//* Hitting a Chicken always makes it unhappy
	}

	/**
     * The service method to get eggs from a chicken
     * 
     * @return The number of eggs, if this chicken dead, return -1
     */
	public int getEggsChicken() {
		if(checkDeadChicken()) return -1;
		int eggsPerQuarterKg=(int) (this.seedsChicken/0.25);				//* Get one egg for every 0.25 kg of seed
		this.seedsChicken -= eggsPerQuarterKg*0.25;							//* The seeds used are gone from its stomach afterwards
		int eggs=this.happyChicken ? eggsPerQuarterKg*2 :eggsPerQuarterKg;	//* If the Chicken is happy, you get twice as many eggs.
		this.happyChicken=false;											//* But laying eggs makes a Chicken unhappy. 
		return eggs;
	}
}
