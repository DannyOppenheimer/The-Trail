package window;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import player.Player;

public class Inventory {

	private Player player;

	private JPanel invenPanel;
	
	private ActionListener invenHandler = (event) -> {
		
		String invenChoice = event.getActionCommand();
		
		if(invenChoice == "meat") {
			
			System.out.println("MEAT");
			
		} else if(invenChoice == "water") {
			
			System.out.println("WATER");
			
		} else if(invenChoice == "ale") {
			
			System.out.println("ALE");
			
		} else if(invenChoice == "potion") {
			
			System.out.println("POTION");
			
		}
		
	};
	
	public Inventory(Player p) {
		
		player = p;

	}

	public void openInventory(JPanel jp) {
		
		invenPanel = jp;
		invenPanel.setVisible(true);
	}
	
	public ActionListener getInvenHandler() {
		
		return invenHandler;
		
	}
}
