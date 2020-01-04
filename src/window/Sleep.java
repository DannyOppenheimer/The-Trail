package window;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import player.Player;
import player.Trail;

public class Sleep {

	private Player player;
	private Trail trail;

	private final static String[] SLEEP_LANG = new String[] {
			"Curling up in a small patch of moss, I drift off to sleep",
			"Propping myself up onto a thick tree trunk, I doze off",
			"After hard days of work, I take a nap on the forest floor, gazing at the stars." };
	
	public Sleep(Player p, Trail t)  {
		
		player = p;
		trail = t;
		
	}

	public void sleep(JTextArea jta, JLabel jl1, JLabel jl2) {
		Random randomLang = new Random();
		player.setTiredness(0);
		jta.setText(SLEEP_LANG[randomLang.nextInt(3)]);
		jl1.setText("Tiredness: " + Integer.toString(player.getTiredness()) + "%");
		trail.modifyDays(1);
		jl2.setText("Day: " + Integer.toString(trail.getDays()));
	}
}
