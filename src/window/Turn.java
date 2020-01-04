package window;

import java.util.Random; 

import javax.swing.JButton;
import javax.swing.JPanel;

import player.Player;

public class Turn {
	
	//all methods in here are static becuase this class is technically not needed
	//it is purely organizational and for calculations. 
	
	public static void calcTurn(JButton jb1, JPanel invenPanel, Player player) {
		invenPanel.setVisible(false);
		player.setDrunkness(player.getDrunkness() - 10);
		
		if(shopAvailable()) {
			jb1.setEnabled(true);
		} else {
			jb1.setEnabled(false);
		}
	}
	
	public static boolean shopAvailable() {
		Random shopCoinFlip = new Random();
		if(shopCoinFlip.nextInt(3) == 0) {
			return true;
		}
		return false;
	}
	
	public static int whichAnimal() {
		Random randAnimal = new Random();

		if(randAnimal.nextInt(5) == 0) {
			return randAnimal.nextInt(6);
		} else {
			return 0;
		}
	}
	
	public static boolean innAvailable() {
		Random innCoinFlip = new Random();
		if(innCoinFlip.nextInt(5) == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isFighting() {
		Random fightDiceRoll = new Random();
		if(fightDiceRoll.nextInt(7) == 1) {
			return true;
		}
		return false;
	}
	
}
