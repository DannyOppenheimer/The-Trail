package trailAnimals;

import other.Organism;

public class Animals implements Organism {
	
	private int attack;
	private int speed;
	private int cashReward;
	private int meatReward;
	private int health;
	private String name;
	
	public void setAttack(int a) {
		
		this.attack = a;
	}
	
	public int getAttack() {
		
		return attack;	
	}
	
	public void setSpeed(int s) {
		
		this.speed = s;
	}
	
	public int getSpeed() {
		
		return speed;
	}
	
	public void setCashReward(int m) {
		
		this.cashReward = m;
	}
	
	public int getCashReward() {
		
		return cashReward;
	}
	
	public void setMeatReward(int m) {
		
		this.meatReward = m;
	}
	
	public int getMeatReward() {
		
		return meatReward;
	}
	
	public void setName(String n) {
		
		this.name = n;
	}
	
	public String getName() {
		
		return name;
	}
	
	public int getHealth() {
		
		return health;
	}

	public void setHealth(int h) {
		
		this.health = h;
	}

	public void modifyHealth(int h) {
		
		this.health += h;
	}
	
}
