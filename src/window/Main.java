package window;

import java.io.IOException;

import player.Player;
import player.Trail;

public class Main {

	public static void main(String[] args) {

		Player player = new Player();
		Trail trail = new Trail();
		
		
		
		// Load all the code in, and them 'invoke' the creation of the window "Later"
		javax.swing.SwingUtilities.invokeLater(() -> {

			try {

				new Window(player, trail);

			} catch (IOException e) {

				e.printStackTrace();
			}

		});

	}

}
