package trailAnimals;

import java.util.Random;

public class Deer extends Animals {
	
	public Deer() {
		
		Random statRandom = new Random();
		setName("Deer");
		setAttack(3);
		setHealth(statRandom.nextInt(statRandom.nextInt((6 - 5) + 1) + 5));
		setSpeed(5);
		setCashReward(6);
		setMeatReward(4);
	}
}
