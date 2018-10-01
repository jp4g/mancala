import java.awt.BorderLayout;
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

	public static boolean hasGameStarted;
	
	public static JPanel panel;
	public JFrame window = new JFrame("Mancala");
	
	public Toolkit tk = Toolkit.getDefaultToolkit();
	
	//===================================================================== Menu Buttons
	public static JButton startButton;
	public static JButton quitButton;

	//===================================================================== Image Resources
	public static BufferedImage mancalaBoard = null;
	public static BufferedImage mancala = null;
	public static BufferedImage winImage = null;
	public static BufferedImage loseImage = null;
	public static BufferedImage drawImage = null;


	public GameInteraction() {

<<<<<<< HEAD
		hasGameStarted = false;
		loadImages();
=======
		mancalaBoard = loadImage("MancalaBoard.png");
		mancala = loadImage("Mancala.png");
		
		
		//bgImage = loadImage("background.png");
		//drawImage = loadImage("Draw.png");
		//winImage = loadImage("Winner.png");
		//loseImage = loadImage("Loser.png");
>>>>>>> parent of 6401930... Merge branch 'master' of https://github.com/jp4g/mancala

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
		
		
		startButton = new JButton("Start");
		quitButton = new JButton("Quit");
		
		startButton.setBounds(500, 600, 180, 60);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hasGameStarted = true;
				repaint();
				startGame();
			}
		});
		panel.add(startButton);
		
		quitButton.setBounds(500, 670, 180, 60);
		quitButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
			}
		});
		panel.add(quitButton);
		
		JLabel m  = new JLabel(new ImageIcon(mancala));
		panel.add(m);
		
		window.add(panel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(!hasGameStarted) {
			g.drawImage(mancala, 90, 50, null);
		} else {
			startButton.setVisible(false);
			quitButton.setVisible(false);
			g.drawImage(mancalaBoard, 0, 0, null);
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
