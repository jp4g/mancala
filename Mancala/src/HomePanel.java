import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * HomePanel
 * 
 * @author Peter Cooke
 */
public class HomePanel extends JPanel {

	public BufferedImage mancala = null;
	
	public JButton startButton;
	public JButton quitButton;
	public JButton helpButton;
	
	public HomePanel() {
		mancala = GameWindow.loadImage("Mancala.png");
		
		startButton = new JButton("Start");
		helpButton = new JButton("Help");
		quitButton = new JButton("Quit");
		
		setLayout(null);
		
		// ======================================================= Start Button
		startButton.setBounds(500, 550, 180, 60);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.window.showGamePanel();
			}
		});

		// ======================================================= Help Button
		helpButton.setBounds(500, 620, 180, 60);
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.window.showHelpPanel();
			}
		});

		// ======================================================= Quit Button
		quitButton.setBounds(500, 690, 180, 60);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.window.closeWindow();
			}
		});
		
		add(startButton);
		add(helpButton);
		add(quitButton);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mancala, 90, 50, null);
	}

}
