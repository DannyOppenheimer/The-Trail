package window;

import java.awt.event.ActionListener; 
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import player.Player;
import player.Trail;

public class Shop {

	private Player player;
	private Trail trail;

	private JLabel dayNum, coinNum;
	private JTextArea mainTextArea;
	private JPanel shopPanel, choiceButtonPanel;

	public final static int FOOD_PRICE = 5;
	public final static int WATER_PRICE = 3;
	public final static int CLOTHING_PRICE = 7;
	public final static int ALE_PRICE = 10;
	public final static int HORSE_PRICE = 35;
	public final static int POTION_PRICE = 15;
	
	private static final String[] SHOP_LANG = {
			//arriving
			"Out in a distance, I see an old peddlar wheeling his cart full of wares down the path.\nMaybe he can help",
			"An old man with a cart full of supplies steadily makes his way down the path.\nI\'ll wave him down, maybe he can help.",
			//leaving
			"Packing up his cart, the peddler walks back down the beaten path.\nTells me he, \"Aint never heard of a town \'round here\"",
			"Closing up shop, the peddlar ambles back down the path.\nSays he doesn't know how to get out of this godforsaken forest.",
			//not enough money
			"I stare at the empty bottom of my satchel. \"Not enough money, eh?\" the vender says.",
			"The familiar jingle of coins at the bottom of my bag is gone. The peddlar refuses buisness",

	};

	private ActionListener shopHandler = (event) -> {
		String shopChoice = event.getActionCommand();
		Random randomLang = new Random();
		
		if(shopChoice == "exit") {
			choiceButtonPanel.setVisible(true);
			trail.modifyDays(1);
			dayNum.setText("Day: " + Integer.toString(trail.getDays()));
			shopPanel.setVisible(false);
			mainTextArea.setText(SHOP_LANG[randomLang.nextInt((3 - 2) + 1) + 2]);
		} else if(shopChoice == "meat") {
			if(player.getGoldPieces() < FOOD_PRICE) {
				mainTextArea.setText(SHOP_LANG[randomLang.nextInt((5 - 4) + 1) + 4]);
			} else {
				player.modifyGoldPieces(-FOOD_PRICE);
				coinNum.setText("Coins:" + Integer.toString(player.getGoldPieces()));
				player.setFood(player.getFood() + 1);
				mainTextArea.setText("The man smiles, and gives me a slab of meat.\nIt will be good sustanence.");
			}
		} else if(shopChoice == "water") {
			if(player.getGoldPieces() < WATER_PRICE) {
				mainTextArea.setText(SHOP_LANG[randomLang.nextInt((5 - 4) + 1) + 4]);
			} else {
				player.modifyGoldPieces(-WATER_PRICE);
				coinNum.setText("Coins:" + Integer.toString(player.getGoldPieces()));
				player.setWater(player.getWater() + 1);
				mainTextArea.setText("The man smiles, and gives me corked bottle of water.\nIt will be good sustanence.");
			}
		} else if(shopChoice == "clothes") {
			if(player.getGoldPieces() < CLOTHING_PRICE) {
				mainTextArea.setText(SHOP_LANG[randomLang.nextInt((5 - 4) + 1) + 4]);
			} else {
				player.modifyGoldPieces(-CLOTHING_PRICE);
				coinNum.setText("Coins:" + Integer.toString(player.getGoldPieces()));
				player.setClothing(player.getClothing() + 1);
				mainTextArea.setText("The man smiles, and gives me a worn cloak.\nIt should keep me warm and safe.");
			}
		} else if(shopChoice == "ale") {
			if(player.getGoldPieces() < ALE_PRICE) {
				mainTextArea.setText(SHOP_LANG[randomLang.nextInt((5 - 4) + 1) + 4]);
			} else {
				player.modifyGoldPieces(-ALE_PRICE);
				coinNum.setText("Coins:" + Integer.toString(player.getGoldPieces()));
				player.setAle(player.getAle() + 1);
				mainTextArea.setText("The man grins, and gives me bottle of ale.\nIt'll give me a laugh through the night");
			}
		} else if(shopChoice == "horse") {
			if(player.getGoldPieces() < HORSE_PRICE) {
				mainTextArea.setText(SHOP_LANG[randomLang.nextInt((5 - 4) + 1) + 4]);
			} else {
				player.modifyGoldPieces(-HORSE_PRICE);
				coinNum.setText("Coins:" + Integer.toString(player.getGoldPieces()));
				player.setHorse(player.getHorse() + 1);
				mainTextArea.setText("The man smiles, leads one of his horses to my side.\nI'll make much better time now.");
			}
		} else if(shopChoice == "potion") {
			if(player.getGoldPieces() < POTION_PRICE) {
				mainTextArea.setText(SHOP_LANG[randomLang.nextInt((5 - 4) + 1) + 4]);
			} else {
				player.modifyGoldPieces(-POTION_PRICE);
				coinNum.setText("Coins:" + Integer.toString(player.getGoldPieces()));
				player.setPotion(player.getPotion() + 1);
				mainTextArea.setText("The man smiles mysteriously, and gives me strange purple-red vial.\nIt should keep me in shape with the danger that prowls at the path's edge.");
			}
		}
	};

	public Shop(Player p, Trail t) {

		player = p;
		trail = t;
		
	}

	public void openShop(JTextArea jta, JPanel jp, JLabel jl1, JLabel jl2, JPanel jp2) {
		Random randomLang = new Random();
		
		jp2.setVisible(false);
		choiceButtonPanel = jp2;
		mainTextArea = jta;
		shopPanel = jp;
		dayNum = jl1;
		coinNum = jl2;
		jp.setVisible(true);
		
		mainTextArea.setText(SHOP_LANG[randomLang.nextInt(1)]);
	}

	public ActionListener getShopHandler() {

		return shopHandler;
	}
}
