package window;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import player.Player;
import player.Trail;

public class Move {
	
	private Trail trail;
	private Player player;
	private Window window;
	
	private double runningHungerG;
	private double runningThirstG;
	private double runningTiredG;
	
	private final static String[] MOVE_LANG = new String[] {
			//good
			"I devote another day to pressing forward.\nThe uneven ground is becoming familiar to the soles of my worn shoes.",
			"I keep hiking foward. \nThe trees' canopies seemingly trapping me in this place.",
			"I take another day's worth of steps.\nThe leather strap of my satchel digging relentlessly into my shoulder.",
			"Thoughts of home coax movement into me.\nThe tally marks on my forearm for each day away taunting me, laughing at me.",
			//bad
			"My vision becomes blurred. My breaths ragged. I push on.",
			"I gasp for air. My stomach empty and my mind wrecked. I take uneven steps foward",
			//bad w/ horse
			"My horse trots forward, all I can do is cling to it's hide as unconsiousness threatens to take over.",
			//good /w horse
			"Sidling up to my horse, I gallop forward. The trees whiz past and hooves kick up a fine dust.",
			"Leaning close to the saddle, I hurtle through the forest at blinding speeds.",
			//dead
			"Taking one last look at the flair of the sun, I drop to the forest floor.\nIt's over. I didn't make it.", };

	public Move(Player p, Trail t, Window w) {
		
		player = p;
		trail = t;
		window = w;
	}
	
	
	public void moveConsequence(JLabel jl1, JLabel jl2, JLabel jl3, JLabel jl4,  JLabel jl5, JLabel jl6, JTextArea jta) {
	
		if(playerOkay() == 0) {
			
			jta.setText("Error 1.1.2: Move lang string not associated with player state...");
			
		} else if(playerOkay() == 5) {
			
			jta.setText(MOVE_LANG[9]);
			player.setHealth(0);
			jl6.setText("HP: " + Integer.toString(player.getHealth()));
		
			window.youLose();
			
		} else if(playerOkay() == 1) {
			
			Random randomLang = new Random();
			jta.setText(MOVE_LANG[randomLang.nextInt(1) + 4]);
			player.modifyHealth(-1);
			jl6.setText("HP: " + Integer.toString(player.getHealth()));
			
		} else if(playerOkay() == 2) {
			
			jta.setText(MOVE_LANG[6]);
			player.modifyHealth(-1);
			jl6.setText("HP: " + Integer.toString(player.getHealth()));
			
		} else if(playerOkay() == 3) {
			
			Random randomLang = new Random();
			jta.setText(MOVE_LANG[randomLang.nextInt(3)]);
			
		} else if(playerOkay() == 4) {
			
			Random randomLang = new Random();
			jta.setText(MOVE_LANG[randomLang.nextInt(1) + 7]);
			
		} 
		
		//subtract the players speed from the total distance left and update the JLabel
		trail.modifyDistance(-player.getSpeed());
		jl5.setText(Integer.toString(trail.getDistance()) + " miles left to go");
		
		//add 1 day to the running total and update the JLabel
		trail.modifyDays(1);
		jl4.setText("Day: " + Integer.toString(trail.getDays()));
		
		//editing all the palyers hunger, thirst, and tireness. Also updates the JLabels
		//stops values from going <100
		player.setHunger(player.getHunger() + calcHunger());
		player.setThirst(player.getThirst() + calcThirst());
		player.setTiredness(player.getTiredness() + calcRest());
		
		if(player.getThirst() >= 100) {
			
			player.setThirst(100);
			
		} if(player.getHunger() >= 100) {
			
			player.setHunger(100);
			
		} if(player.getTiredness() >= 100) {
			
			player.setTiredness(100);
		}
		
		jl1.setText("Hunger: " + Integer.toString(player.getHunger()) + "%");
		
		jl2.setText("Thirst: " + Integer.toString(player.getThirst()) + "%");
		
		jl3.setText("Tiredness: " + Integer.toString(player.getTiredness()) + "%");
		
	}
	
	//calculate the hunger to-be-lost based on clothing status, horse status, sobriety, and tiredness
	private int calcHunger() {
		runningHungerG = 0;
		
		if(player.getClothing() < 1) {
			runningHungerG++;
		}
		if(player.getHorse() < 1) {
			runningHungerG += 0.5;
		}
		if(player.getDrunkness() > 50) {
			runningHungerG++;
			if(player.getDrunkness() > 90) {
				runningHungerG += 0.5;
			}
		}
		if(player.getTiredness() <  50) {
			runningHungerG++;
		}
		return (int)Math.round((runningHungerG * 3) - 1);

	}
	//calculate the thirst to-be-lost based on clothing status, horse status, sobriety, and tiredness
	private int calcThirst() {
		runningThirstG = 0;
		if(player.getClothing() < 1) {
			runningThirstG++;
		}
		if(player.getHorse() < 1) {
			runningThirstG++;
		}
		if(player.getDrunkness() > 50) {
			runningThirstG++;
			if(player.getDrunkness() > 90) {
				runningThirstG += 0.5;
			}
		}
		if(player.getTiredness() <  50) {
			runningThirstG++;
		}
		return (int)Math.round((runningThirstG * 3) + 3);

	}

	//calculate the tiredness to-be-lost based on clothing status, horse status, sobriety, hunger, and thirst
	private int calcRest() {
		runningTiredG = 0;
		
		if(player.getClothing() < 1) {
			runningTiredG++;
		}
		if(player.getHorse() < 1) {
			runningTiredG += 0.5;
		}
		if(player.getDrunkness() > 50) {
			runningTiredG++;
			if(player.getDrunkness() > 90) {
				runningTiredG += 0.5;
			}
		}
		if(player.getHunger() > 50) {
			runningTiredG++;
		}
		if(player.getThirst() > 50) {
			runningTiredG++;
		}
		return (int)Math.round((runningTiredG * 3) + 4);
		
	}
	
	//checks if the player has good hunger, thirst, and tiredness
	private int playerOkay() {
		if(player.getHealth() <= 0) {
			return 5;
		}
		//bad without a horse.
		if((player.getHunger() >= 100 || player.getThirst() >= 100 || player.getTiredness() >= 100) && (player.getHorse() < 1)) {
			return 1;
		}
		//bad with horse
		if((player.getHunger() >= 100 || player.getThirst() >= 100 || player.getTiredness() >= 100) && (player.getHorse() >= 1)) {
			return 2;
		}
		//good without horse
		if((player.getHunger() < 100 && player.getThirst() < 100 && player.getTiredness() < 100) && (player.getHorse() < 1)) {
			return 3;
		}
		//good with horse
		if((player.getHunger() < 100 && player.getThirst() < 100 && player.getTiredness() < 100) && (player.getHorse() >= 1)) {
			return 4;
		}
		
		//welp it bronk
		return 0;
	}
}
