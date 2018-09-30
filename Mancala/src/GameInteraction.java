import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameInteraction extends JPanel {

	public JPanel panel;
	public JFrame window = new JFrame("Mancala");
	
	public Toolkit tk = Toolkit.getDefaultToolkit();
	public BufferedImage mancalaBoard = null;
	public BufferedImage mancala = null;
	public BufferedImage winImage = null;
	public BufferedImage loseImage = null;
	public BufferedImage drawImage = null;


	public GameInteraction() {

		mancalaBoard = loadImage("MancalaBoard.png");
		mancala = loadImage("Mancala.png");
		
		
		//drawImage = loadImage("Draw.png");
		//winImage = loadImage("Winner.png");
		//loseImage = loadImage("Loser.png");

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
		panel.setLayout(null);
		window.add(panel);
		
		JButton startButton= new JButton("Start");
		startButton.setBounds(500, 500, 180, 60);
		panel.add(startButton);
		
		JButton quitButton= new JButton("Quit");
		quitButton.setBounds(500, 600, 180, 60);
		panel.add(quitButton);
		
		quitButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
			}
		});
		
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		//super.paintComponent(g);
		g.drawImage(mancala, 90, 50, null);
		
		//Every board object will have to have a defined space (eg rectangle) in their class
		//It will also need a draw() method to draw the amount of stones it has
		//I will then loop through every object after each turn and call the paint method
		//will probably have to put in a sleep so it is not instant
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
	

	public static void main(String[] args) {
		new GameInteraction();
	}

}
