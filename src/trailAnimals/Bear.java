package trailAnimals;

import java.util.Random;

public class Bear extends Animals {
	
	public Bear() {
		
		Random statRandom = new Random();
		setName("Bear");
		setAttack(statRandom.nextInt((3 - 2) + 1) + 2);
		setHealth(statRandom.nextInt((10 - 5) + 1) + 5);
		setSpeed(3);
		setCashReward(8);
		setMeatReward(5);
	}

}
