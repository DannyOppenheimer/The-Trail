package trailAnimals;

import java.util.Random;

public class Snake extends Animals {
	
	public Snake() {
		
		Random statRandom = new Random();
		setName("Snake");
		setAttack(statRandom.nextInt((4 - 2) + 1) + 2);
		setHealth(statRandom.nextInt((5 - 3) + 1) + 3);
		setSpeed(3);
		setCashReward(4);
		setMeatReward(0);
	}
}
