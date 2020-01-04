package player;

import java.util.Random;

import other.Organism;

public class Player implements Organism {

	private int food;
	private int clothing;
	private int water;
	private int horse;
	private int ale;
	private int goldPieces;
	private int potion;
		
	private int strength;
	private int speed;
	private int health;
	private int hunger;
	private int thirst;
	private int drunkness;
	private int tiredness;
		
	boolean completeKonami;
	
	public Player() {
		
		Random randomizer = new Random();
		
		setFood(3);
		setClothing(1);
		setWater(5);
		setHorse(0);
		setAle(0);
		setGoldPieces(35);
		setPotion(1);
		
		//statistics
		setStrength(randomizer.nextInt((3 - 2) + 1) + 2);
		setSpeed(randomizer.nextInt((5 - 3) + 1) + 3);
		setHealth(16);
		setHunger(0);
		setThirst(0);
		setDrunkness(0);
		setTiredness(0);
	}
	
	@Override
	public int getHealth() {
	
		return health;
	}

	@Override
	public void setHealth(int h) {
		
		this.health = h;
	}

	@Override
	public void modifyHealth(int h) {
		
		this.health += h;
	}
	
	public int getFood() {
		
		return food;
	}

	public void setFood(int f) {
		
		this.food = f;
	}

	public int getClothing() {
		
		return clothing;
	}

	public void setClothing(int c) {
		
		this.clothing = c;
	}

	public int getWater() {
		
		return water;
	}

	public void setWater(int w) {
		
		this.water = w;
	}

	public int getHorse() {
		
		return horse;
	}

	public void setHorse(int h) {
		
		this.horse = h;
	}

	public int getAle() {
		
		return ale;
	}

	public void setAle(int a) {
		
		this.ale = a;
	}

	public int getGoldPieces() {
		
		return goldPieces;
	}

	public void setGoldPieces(int gp) {
		
		this.goldPieces = gp;
	}

	public void modifyGoldPieces(int gp) {
		
		this.goldPieces += gp;
	}
	
	public int getPotion() {
		
		return potion;
	}

	public void setPotion(int p) {
		
		this.potion = p;
	}

	public int getStrength() {
		
		return strength;
	}

	public void setStrength(int s) {
		
		this.strength = s;
	}

	public int getSpeed() {
		
		return speed;
	}

	public void setSpeed(int s) {
		
		this.speed = s;
	}

	public int getHunger() {
		
		return hunger;
	}

	public void setHunger(int h) {
		
		this.hunger = h;
	}

	public int getThirst() {
		
		return thirst;
	}

	public void setThirst(int t) {
		
		this.thirst = t;
	}

	public int getDrunkness() {
		
		return drunkness;
	}

	public void setDrunkness(int s) {
		
		this.drunkness = s;
	}

	public int getTiredness() {
		
		return tiredness;
	}

	public void setTiredness(int r) {
		
		this.tiredness = r;
	}

	public boolean isCompleteKonami() {
		
		return completeKonami;
	}

	public void setCompleteKonami(boolean c) {
		
		this.completeKonami = c;
	}
	
	public String correctJLabel(String option) {
		
		if(option == "hunger") {
			return("Hunger: " + getTiredness() + "%");
		} else if(option == "thirst") {
			return("Thirst: " + getThirst() + "%");
		} else if(option == "tiredness") {
			return("Tiredness: " + getTiredness() + "%");
		} else if(option == "health") {
			return("HP: " + getHealth());
		} else if(option == "money") {
			return("Gold Coins: " + getGoldPieces());
		} 
		else return "Error 2.1, input not found";
		
	}
}
