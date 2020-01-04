package window;

import javax.swing.JTextArea;

import player.Player;
import player.Trail;


public class Statistics {
	
	private Player player;
	
	public Statistics(Player p, Trail t) {
		
		 player = p;
	}
	
	public void setStatistics(JTextArea jta) {
		jta.setText("-------------------------------Statistics-------------------------------" + "\n");
		jta.append("Your strength modifier:⠀" + player.getStrength() + "⠀⠀⠀⊱♠♣♠⊰⠀⠀⠀");
		jta.append("Your speed modifier:⠀" + player.getSpeed());
		jta.append("\n------------------------------------------------------------------------");
	}
	
}
