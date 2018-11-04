import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameInteraction extends JPanel {

	public static boolean onHomeScreen;
	public static boolean onHelpScreen;
	public static boolean onGameScreen;
	
	public static JPanel panel;
	public JFrame window = new JFrame("Mancala");
	
	public Toolkit tk = Toolkit.getDefaultToolkit();
	
	//===================================================================== Menu Buttons
	public static JButton startButton;
	public static JButton quitButton;
	public static JButton helpButton;
	public static JButton backButton;

	//===================================================================== Image Resources
	public static BufferedImage mancalaBoard = null;
	public static BufferedImage mancala = null;
	public static BufferedImage winImage = null;
	public static BufferedImage loseImage = null;
	public static BufferedImage drawImage = null;


	public GameInteraction() {
		
		//======================================================= Game Setup
		loadImages();
		loadHomeScreen();
		
		window.setBounds(tk.getScreenSize().width / 2 - 600, tk.getScreenSize().height / 2 - 400, 1200, 800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setAlwaysOnTop(true);
		window.add(this);
		window.setVisible(true);
		window.setFocusable(true);
		window.setResizable(false);
		window.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

		});
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		//======================================================= Initialize Buttons
		startButton = new JButton("Start");
		helpButton = new JButton("Help");
		quitButton = new JButton("Quit");
		backButton = new JButton("Back");
		
		//======================================================= Start Button 
		startButton.setBounds(500, 550, 180, 60);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadGameScreen();
				startGame();
			}
		});
		panel.add(startButton);
		
		//======================================================= Help Button
		helpButton.setBounds(500, 620, 180, 60);
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadHelpScreen();
			}
		});
		panel.add(helpButton);
		
		//======================================================= Quit Button
		quitButton.setBounds(500, 690, 180, 60);
		quitButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
			}
		});
		panel.add(quitButton);
		
		//======================================================= Back Button
		backButton.setBounds(1000, 690, 180, 60);
		backButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				loadHomeScreen();
			}
		});
		panel.add(backButton);
		backButton.setVisible(false);
		
		
		
		JLabel m  = new JLabel(new ImageIcon(mancala));
		panel.add(m);
		
		window.add(panel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(onHomeScreen) {
			backButton.setVisible(false);
			startButton.setVisible(true);
			helpButton.setVisible(true);
			quitButton.setVisible(true);
			g.drawImage(mancala, 90, 50, null);
		}
		
		if(onGameScreen) {
			backButton.setVisible(true);
			startButton.setVisible(false);
			helpButton.setVisible(false);
			quitButton.setVisible(false);
			g.drawImage(mancalaBoard, 0, 0, null);
		}
		
		if(onHelpScreen) {
			startButton.setVisible(false);
			helpButton.setVisible(false);
			quitButton.setVisible(false);
			backButton.setVisible(true);
			
			Font font = g.getFont().deriveFont(36.0f);
			g.setFont(font);
			g.drawString("Rules", 550, 100);
			g.drawString("1. ", 200, 150);
			g.drawString("2. ", 200, 200);
			g.drawString("3. ", 200, 250);
			g.drawString("4. ", 200, 300);
			g.drawString("5. ", 200, 350);
			g.drawString("6. ", 200, 400);
			g.drawString("7. ", 200, 450);
			g.drawString("8. ", 200, 500);
			
		}
		
		
		
		//Every board object will have to have a defined space (eg rectangle) in their class
		//It will also need a draw() method to draw the amount of stones it has
		//I will then loop through every object after each turn and call the paint method
		//will probably have to put in a sleep so it is not instant
	}
	
	public static void loadImages() {
		mancalaBoard = loadImage("MancalaBoard.png");
		mancala = loadImage("Mancala.png");
		
		//drawImage = loadImage("Draw.png");
		//winImage = loadImage("Winner.png");
		//loseImage = loadImage("Loser.png");
	}
	
	/**
	 * Change to Help Screen
	 */
	private void loadHelpScreen() {
		onHelpScreen = true;
		onHomeScreen = false;
		onGameScreen = false;
		repaint();
	}
	
	/**
	 * Change to Game Screen
	 */
	private void loadGameScreen() {
		onHelpScreen = false;
		onHomeScreen = false;
		onGameScreen = true;
		repaint();
	}
	
	/**
	 * Change to Home Screen
	 */
	private void loadHomeScreen() {
		onHelpScreen = false;
		onHomeScreen = true;
		onGameScreen = false;
		repaint();
	}

	public static BufferedImage loadImage(String i) {
		BufferedImage image = null;
		URL resource = GameInteraction.class.getResource("resources/"+i);
		try {
			image = ImageIO.read(resource);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Put code in here for what you want to happen when the game starts
	 */
	public static void startGame() {
		
	}
	
	
	public static void resetGame() {
		
	}
	

	public static void main(String[] args) {
		new GameInteraction();
	}

}
