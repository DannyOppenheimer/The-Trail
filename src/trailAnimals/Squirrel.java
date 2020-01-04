package trailAnimals;

import java.util.Random;

public class Squirrel extends Animals {
	
	public Squirrel() {
		
		Random statRandom = new Random();
		setName("Squirrel");
		setAttack(1);
		setHealth(1);
		setSpeed(10);
		setCashReward(statRandom.nextInt((2 - 1) + 1) + 1);
		setMeatReward(2);
	}
}
