//1000x415 for thematic images
package window;

import java.awt.Color;  
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.io.File;
import java.io.IOException;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.Timer;

import player.Player;
import player.Trail;

public class Window {

	private JFrame window;

	private Container container;

	private JPanel titleNamePanel, startButtonPanel, coverPage, fader, highScorePanel, mainTextPanel, playerPanel, distancePanel, choiceButtonPanel, shopPanel, invenPanel;

	private JLabel titleNameLabel, coverPageImage, highScoreLabel, dayNum, hpNum, hungerNum, thirstNum, tirednessNum, drunkNum, coinNum, distanceNum;

	private JButton startButton, exitButton, stats, inven, shop, sleep, move, shopFood, shopWater, shopClothing, shopAle, shopHorse, shopPotion, shopExit, invenFood, invenWater, invenClothes, invenAle, invenHorse, invenPotion;

	private JTextArea mainTextArea;

	private static final Font TITLE_FONT = new Font("PAPYRUS", Font.BOLD, 55);
	private static final Font PARAGRAPH_FONT = new Font("PAPYRUS", Font.PLAIN, 20);
	private static final Font SMALL_FONT = new Font("PAPYRUS", Font.PLAIN, 15);

	private Timer stopWatch;
	private int counter = 0;
	private int delay = 10;
	
	private final ImageIcon TITLE_PAGE_PIC = new ImageIcon("Images/CoverPage.png");
	private final ImageIcon MEAT_ICON = new ImageIcon("Images/meatIcon.png");
	
	/*
	private ImageIcon picStable = new ImageIcon("Images/mainPictureTrailGood.png");
	private ImageIcon picUnstable = new ImageIcon("Images/mainPictureTrailBad.png");
	private ImageIcon picLose = new ImageIcon("Images/mainPictureTrailDead.png");
	private ImageIcon gifSleep = new ImageIcon("Images/sleep.gif");
	private ImageIcon picStats = new ImageIcon("Images/stats.png");
	private ImageIcon picWin = new ImageIcon("Images/youWinScreen.png");
	 */

	private Trail trail;
	private Player player;
	private Move movement;
	private Shop shopping;
	private Sleep sleeping;
	private Statistics statistics;
	private Inventory inventory;

	// TitleScreenHandler tsHandler = new TitleScreenHandler();
	private final ActionListener tsHandler = (event) -> {
		String titleScreenChoice = event.getActionCommand();
		if (titleScreenChoice.contentEquals("start")) {

			startFade(255);
			
			// set the old start panel items to invisible
			titleNamePanel.setVisible(false);
			startButtonPanel.setVisible(false);
			coverPage.setVisible(false);
			highScorePanel.setVisible(false);
			fader.setVisible(true);
			
			/*try { 

				this.createGameScreen(); 

			} catch (IOException e) {e.printStackTrace(); }*/

		} else if (titleScreenChoice.contentEquals("exit")) {

			this.exitGame();

		}
	};

	private final ActionListener choiceHandler = (event) -> {
		String dayChoice = event.getActionCommand();
		Turn.calcTurn(shop, invenPanel, player);

		if(dayChoice.contentEquals("move")) {

			movement.moveConsequence(hungerNum, thirstNum, tirednessNum, dayNum, distanceNum, hpNum, mainTextArea);

		} else if(dayChoice.contentEquals("stats")) {

			statistics.setStatistics(mainTextArea);

		} else if(dayChoice.contentEquals("inven")) {

			inventory.openInventory(invenPanel);

		} else if(dayChoice.contentEquals("sleep")) {

			sleeping.sleep(mainTextArea, tirednessNum, dayNum);

		} else if(dayChoice.contentEquals("shop")) {

			shopping.openShop(mainTextArea, shopPanel, dayNum, coinNum, choiceButtonPanel);
		} 
	};

	public Window(Player p, Trail t) throws IOException {

		// sets our fields up for the trail and player
		player = p;
		trail = t;

		// ensures cross-platform usage is correct
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// adds our custom papyrus font to the enviroment
		try {
			GraphicsEnvironment graphEn = GraphicsEnvironment.getLocalGraphicsEnvironment();
			graphEn.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Texts/PAPYRUS.ttf")));

		} catch (FontFormatException | IOException e) { e.printStackTrace(); }

		// creating a new window 1000x800
		window = new JFrame("The Trail");
		window.setSize(1000, 800);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white);
		window.setResizable(false);
		window.setLayout(null);
		window.setFocusable(true);
		window.setVisible(true);

		// create the container where our compenents will be stored
		container = window.getContentPane();
		
		fader = new JPanel();
		fader.setBounds(0, 0, 1000, 800);
		fader.setBackground(Color.white);
		fader.setOpaque(true);
		fader.setVisible(false);
		container.add(fader);
		
		// create the panel for the main title
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, 100, 800, 150);
		titleNamePanel.setBackground(Color.black);
		titleNamePanel.setOpaque(false);
		titleNamePanel.setVisible(true);

		titleNameLabel = new JLabel("The Trail");
		titleNameLabel.setFont(TITLE_FONT);
		titleNameLabel.setForeground(Color.black);

		// add the two components ^above^ to the container
		titleNamePanel.add(titleNameLabel);
		container.add(titleNamePanel);

		// panel that contains the start and exit buttons
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(400, 300, 200, 100);
		startButtonPanel.setOpaque(false);
		startButtonPanel.setLayout(new GridLayout(2, 1));
		startButtonPanel.setVisible(true);
		container.add(startButtonPanel);

		startButton = new JButton("Start");
		startButton.setForeground(Color.black);
		startButton.setBackground(Color.black);
		startButton.setOpaque(false);
		startButton.setBorderPainted(true);
		startButton.setFocusPainted(false);
		startButton.setFont(PARAGRAPH_FONT);
		startButton.setBorder(new LineBorder(Color.black, 1));
		startButton.setActionCommand("start");
		startButton.addActionListener(tsHandler);
		startButtonPanel.add(startButton);

		exitButton = new JButton("Exit");
		exitButton.setForeground(Color.black);
		exitButton.setBackground(Color.black);
		exitButton.setOpaque(false);
		exitButton.setBorderPainted(true);
		exitButton.setFocusPainted(false);
		exitButton.setFont(PARAGRAPH_FONT);
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(tsHandler);
		exitButton.setBorder(new LineBorder(Color.BLACK, 1));
		startButtonPanel.add(exitButton);

		// making a label with the highscore stored in HighScore.dat
		highScorePanel = new JPanel();
		highScorePanel.setBounds(10, 10, 225, 50);
		highScorePanel.setBackground(Color.black);
		highScorePanel.setOpaque(false);
		highScorePanel.setVisible(true);

		highScoreLabel = new JLabel("Highscore: " + HighScore.getHighScore()[0] + " days");
		highScoreLabel.setFont(PARAGRAPH_FONT);
		highScoreLabel.setForeground(Color.black);
		highScorePanel.add(highScoreLabel);
		container.add(highScorePanel);

		// add the picture to the main page
		coverPage = new JPanel();
		coverPage.setBounds(0, -10, 1000, 800);
		container.add(coverPage);
		coverPageImage = new JLabel();
		coverPageImage.setIcon(TITLE_PAGE_PIC);
		coverPage.add(coverPageImage);

	}

	// creates the main game interface
	public void createGameScreen() throws IOException {

		// creation of a new panel and text area that will house our main storyline lang.
		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(100, 500, 800, 400);
		mainTextPanel.setOpaque(false);
		container.add(mainTextPanel);

		mainTextArea = new JTextArea();
		mainTextArea.setOpaque(false);
		mainTextArea.setFont(PARAGRAPH_FONT);
		mainTextArea.setBounds(100, 500, 800, 400);
		mainTextArea.setLineWrap(true);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setEditable(false);		
		if (trail.getDays() == 0) {
			mainTextArea.setText("I sit up from the hard earth, dizzy. Trees are all around me. Where am I?");
		}
		mainTextPanel.add(mainTextArea);

		// our info and progress
		playerPanel = new JPanel();
		playerPanel.setBounds(0, 0, 1000, 100);
		playerPanel.setOpaque(false);
		playerPanel.setLayout(new GridLayout(1, 8));
		playerPanel.setForeground(Color.white);
		container.add(playerPanel);

		distancePanel = new JPanel();
		distancePanel.setBounds(302, 675, 398, 50);
		distancePanel.setOpaque(false);
		distancePanel.setLayout(new GridLayout(1, 1));
		container.add(distancePanel);

		// days
		dayNum = new JLabel("Day: " + Integer.toString(trail.getDays()));
		dayNum.setFont(SMALL_FONT);
		dayNum.setForeground(Color.white);
		playerPanel.add(dayNum);

		// health
		hpNum = new JLabel("HP: " + Integer.toString(player.getHealth()));
		hpNum.setFont(SMALL_FONT);
		hpNum.setForeground(Color.white);
		playerPanel.add(hpNum);

		// hunger

		hungerNum = new JLabel("Hunger: " + Integer.toString(player.getHunger()) + "%");
		hungerNum.setFont(SMALL_FONT);
		hungerNum.setForeground(Color.white);
		playerPanel.add(hungerNum);

		// thirst
		thirstNum = new JLabel("Thirst: " + Integer.toString(player.getThirst()) + "%");
		thirstNum.setFont(SMALL_FONT);
		thirstNum.setForeground(Color.white);
		playerPanel.add(thirstNum);

		// tiredness
		tirednessNum = new JLabel("Tiredness: " + Integer.toString(player.getTiredness()) + "%");
		tirednessNum.setFont(SMALL_FONT);
		tirednessNum.setForeground(Color.white);
		playerPanel.add(tirednessNum);

		// tiredness
		drunkNum = new JLabel("Drunkness: " + Integer.toString(player.getDrunkness()) + "%");
		drunkNum.setFont(SMALL_FONT);
		drunkNum.setForeground(Color.white);
		playerPanel.add(drunkNum);

		// coins
		coinNum = new JLabel("Coins: " + Integer.toString(player.getGoldPieces()));
		coinNum.setFont(SMALL_FONT);
		coinNum.setForeground(Color.white);
		playerPanel.add(coinNum);

		// distance
		distanceNum = new JLabel("Distance:" + Integer.toString(trail.getDistance()) + " miles left to go");
		distanceNum.setFont(SMALL_FONT);
		distanceNum.setForeground(Color.white);
		distancePanel.add(distanceNum);

		choiceButtonPanel = new JPanel();
		choiceButtonPanel.setBounds(150, 580, 700, 50);
		choiceButtonPanel.setOpaque(false);
		choiceButtonPanel.setLayout(new GridLayout(1, 5));
		container.add(choiceButtonPanel);

		//statistics action
		statistics = new Statistics(player, trail);
		stats = new JButton("Stats");
		stats.setBackground(Color.black);
		stats.setForeground(Color.white);
		stats.setFont(PARAGRAPH_FONT);
		stats.setOpaque(true);
		stats.setBorderPainted(true);
		stats.setFocusPainted(false);
		stats.addActionListener(choiceHandler);
		stats.setActionCommand("stats");
		choiceButtonPanel.add(stats);

		//inventory button
		inventory = new Inventory(player);
		inven = new JButton("Inventory");
		inven.setBackground(Color.black);
		inven.setForeground(Color.white);
		inven.setFont(PARAGRAPH_FONT);
		inven.setOpaque(true);
		inven.setBorderPainted(true);
		inven.setFocusPainted(false);
		inven.addActionListener(choiceHandler);
		inven.setActionCommand("inven");
		choiceButtonPanel.add(inven);

		//shop button
		shopping = new Shop(player, trail);
		shop = new JButton("Shop");
		shop.setBackground(Color.black);
		shop.setForeground(Color.white);
		shop.setFont(PARAGRAPH_FONT);
		shop.setOpaque(true);
		shop.setBorderPainted(true);
		shop.setFocusPainted(false);
		shop.addActionListener(choiceHandler);
		shop.setActionCommand("shop");
		choiceButtonPanel.add(shop);

		//sleep button
		sleeping = new Sleep(player, trail);
		sleep = new JButton("Sleep");
		sleep.setBackground(Color.black);
		sleep.setForeground(Color.white);
		sleep.setFont(PARAGRAPH_FONT);
		sleep.setOpaque(true);
		sleep.setBorderPainted(true);
		sleep.setFocusPainted(false);
		sleep.addActionListener(choiceHandler);
		sleep.setActionCommand("sleep");
		choiceButtonPanel.add(sleep);

		//move button
		movement = new Move(player, trail, this);
		move = new JButton("Move");
		move.setBackground(Color.black);
		move.setForeground(Color.white);
		move.setFont(PARAGRAPH_FONT);
		move.setOpaque(true);
		move.setBorderPainted(true);
		move.setFocusPainted(false);
		move.addActionListener(choiceHandler);
		move.setActionCommand("move");
		choiceButtonPanel.add(move);

		this.createSubPanels();
	}


	//create all the components of our game that are mroe sub-classes of the main game
	//purely organizational
	private void createSubPanels() {

		//create a large JPanel to house the buttons for the different shop options
		shopPanel = new JPanel();
		shopPanel.setBounds(250, 110, 500, 350);
		shopPanel.setBackground(Color.black);
		shopPanel.setLayout(new GridLayout(7, 1));
		shopPanel.setOpaque(false);
		shopPanel.setVisible(false);
		container.add(shopPanel);

		shopFood = new JButton("- $" + Shop.FOOD_PRICE, MEAT_ICON);
		shopFood.setBackground(Color.black);
		shopFood.setForeground(Color.white);
		shopFood.setFont(PARAGRAPH_FONT);
		shopFood.setOpaque(true);
		shopFood.setBorderPainted(true);
		shopFood.setFocusPainted(false);
		shopFood.addActionListener(shopping.getShopHandler());
		shopFood.setActionCommand("meat");
		shopPanel.add(shopFood);

		shopWater = new JButton("Water: $" + Shop.WATER_PRICE);
		shopWater.setBackground(Color.black);
		shopWater.setForeground(Color.white);
		shopWater.setFont(PARAGRAPH_FONT);
		shopWater.setOpaque(true);
		shopWater.setBorderPainted(true);
		shopWater.setFocusPainted(false);
		shopWater.addActionListener(shopping.getShopHandler());
		shopWater.setActionCommand("water");
		shopPanel.add(shopWater);

		shopClothing = new JButton("Clothes: $" + Shop.CLOTHING_PRICE);
		shopClothing.setBackground(Color.black);
		shopClothing.setForeground(Color.white);
		shopClothing.setFont(PARAGRAPH_FONT);
		shopClothing.setOpaque(true);
		shopClothing.setBorderPainted(true);
		shopClothing.setFocusPainted(false);
		shopClothing.addActionListener(shopping.getShopHandler());
		shopClothing.setActionCommand("clothes");
		shopPanel.add(shopClothing);

		shopAle = new JButton("Ale: $" + Shop.ALE_PRICE);
		shopAle.setBackground(Color.black);
		shopAle.setForeground(Color.white);
		shopAle.setFont(PARAGRAPH_FONT);
		shopAle.setOpaque(true);
		shopAle.setBorderPainted(true);
		shopAle.setFocusPainted(false);
		shopAle.addActionListener(shopping.getShopHandler());
		shopAle.setActionCommand("ale");
		shopPanel.add(shopAle);

		shopHorse = new JButton("Horse: $" + Shop.HORSE_PRICE);
		shopHorse.setBackground(Color.black);
		shopHorse.setForeground(Color.white);
		shopHorse.setFont(PARAGRAPH_FONT);
		shopHorse.setOpaque(true);
		shopHorse.setBorderPainted(true);
		shopHorse.setFocusPainted(false);
		shopHorse.addActionListener(shopping.getShopHandler());
		shopHorse.setActionCommand("horse");
		shopPanel.add(shopHorse);

		shopPotion = new JButton("Potion: $" + Shop.POTION_PRICE);
		shopPotion.setBackground(Color.black);
		shopPotion.setForeground(Color.white);
		shopPotion.setFont(PARAGRAPH_FONT);
		shopPotion.setOpaque(true);
		shopPotion.setBorderPainted(true);
		shopPotion.setFocusPainted(false);
		shopPotion.addActionListener(shopping.getShopHandler());
		shopPotion.setActionCommand("potion");
		shopPanel.add(shopPotion);

		shopExit = new JButton("EXIT");
		shopExit.setBackground(Color.black);
		shopExit.setForeground(Color.white);
		shopExit.setFont(PARAGRAPH_FONT);
		shopExit.setOpaque(true);
		shopExit.setBorderPainted(true);
		shopExit.setFocusPainted(false);
		shopExit.addActionListener(shopping.getShopHandler());
		shopExit.setActionCommand("exit");
		shopPanel.add(shopExit);

		//panel for inventory, and all the buttons accociated
		invenPanel = new JPanel();
		invenPanel.setBounds(250, 110, 500, 350);
		invenPanel.setBackground(Color.black);
		invenPanel.setLayout(new GridLayout(7, 1));
		invenPanel.setOpaque(false);
		invenPanel.setVisible(false);
		container.add(invenPanel);

		invenFood = new JButton("Meat - " + player.getFood());
		invenFood.setBackground(Color.black);
		invenFood.setForeground(Color.white);
		invenFood.setFont(PARAGRAPH_FONT);
		invenFood.setOpaque(true);
		invenFood.setBorderPainted(true);
		invenFood.setFocusPainted(false);
		invenFood.addActionListener(inventory.getInvenHandler());
		invenFood.setActionCommand("meat");
		invenPanel.add(invenFood);

		invenWater = new JButton("Water - " + player.getWater());
		invenWater.setBackground(Color.black);
		invenWater.setForeground(Color.white);
		invenWater.setFont(PARAGRAPH_FONT);
		invenWater.setOpaque(true);
		invenWater.setBorderPainted(true);
		invenWater.setFocusPainted(false);
		invenWater.addActionListener(inventory.getInvenHandler());
		invenWater.setActionCommand("water");
		invenPanel.add(invenWater);

		invenClothes = new JButton("Clothes - " + player.getClothing());
		invenClothes.setEnabled(false);
		invenClothes.setBackground(Color.black);
		invenClothes.setForeground(Color.white);
		invenClothes.setFont(PARAGRAPH_FONT);
		invenClothes.setOpaque(true);
		invenClothes.setBorderPainted(true);
		invenClothes.setFocusPainted(false);
		invenClothes.addActionListener(inventory.getInvenHandler());
		invenClothes.setActionCommand("clothes");
		invenPanel.add(invenClothes);

		invenAle = new JButton("Ale - " + player.getAle());
		invenAle.setBackground(Color.black);
		invenAle.setForeground(Color.white);
		invenAle.setFont(PARAGRAPH_FONT);
		invenAle.setOpaque(true);
		invenAle.setBorderPainted(true);
		invenAle.setFocusPainted(false);
		invenAle.addActionListener(inventory.getInvenHandler());
		invenAle.setActionCommand("ale");
		invenPanel.add(invenAle);

		invenHorse = new JButton("Horse - " + player.getHorse());
		invenHorse.setEnabled(false);
		invenHorse.setBackground(Color.black);
		invenHorse.setForeground(Color.white);
		invenHorse.setFont(PARAGRAPH_FONT);
		invenHorse.setOpaque(true);
		invenHorse.setBorderPainted(true);
		invenHorse.setFocusPainted(false);
		invenHorse.addActionListener(inventory.getInvenHandler());
		invenHorse.setActionCommand("horse");
		invenPanel.add(invenHorse);

		invenPotion = new JButton("Potion - " + player.getPotion());
		invenPotion.setBackground(Color.black);
		invenPotion.setForeground(Color.white);
		invenPotion.setFont(PARAGRAPH_FONT);
		invenPotion.setOpaque(true);
		invenPotion.setBorderPainted(true);
		invenPotion.setFocusPainted(false);
		invenPotion.addActionListener(inventory.getInvenHandler());
		invenPotion.setActionCommand("potion");
		invenPanel.add(invenPotion);

	}

	// exits the game, and destroys all JFrame objects
	public void exitGame() {

		window.dispose();
		System.exit(0);

	}


	public void youLose() {
		choiceButtonPanel.setVisible(false);

	}

	// a swing timer thats fades a grayscale of all 255 shades in 2.55 seconds
	public void startFade(int c) {
		
		ActionListener event = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(counter == 0) {
					
					stopWatch.stop();
					window.getContentPane().setBackground(Color.black);	
					fader.setVisible(false);
					
					try {
						
						createGameScreen();
						
					} catch (IOException e1) { e1.printStackTrace(); }
					
				} else {
					
					counter--;
					fader.setBackground(new Color(counter, counter, counter));
					
				}
				
			}
		};
		
		stopWatch = new Timer(delay, event);
		stopWatch.setInitialDelay(0);
		counter = c;
		stopWatch.start();
		
	}
}
