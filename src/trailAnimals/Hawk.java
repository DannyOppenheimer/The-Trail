package trailAnimals;

import java.util.Random;

public class Hawk extends Animals {

	public Hawk() {
		Random statRandom = new Random();
		setName("Hawk");
		setAttack(statRandom.nextInt((3 - 2) + 1) + 2);
		setHealth(statRandom.nextInt((6 - 4) + 1) + 4);
		setSpeed(5);
		setCashReward(4);
		setMeatReward(1);
	}
	
}
