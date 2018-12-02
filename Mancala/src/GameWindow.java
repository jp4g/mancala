import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
/**
 * GameWindow
 *
 * @author Peter Cooke
 */
public class GameWindow extends JFrame {


	public Toolkit tk = Toolkit.getDefaultToolkit();

	public GamePanel gamePanel;
	public HomePanel homePanel;
	public HelpPanel helpPanel;
	public EndPanel endPanel;


	/**
	 * Initialize GameWindow With A Board
	 * @param e
	 */
	public GameWindow(Board board) {
		gamePanel = new GamePanel(board);
		homePanel = new HomePanel();
		helpPanel = new HelpPanel();
		endPanel = new EndPanel(EndCondition.LOSE);
		
		setBounds(tk.getScreenSize().width / 2 - 600, tk.getScreenSize().height / 2 - 400, 1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		setFocusable(true);
		setResizable(false);
		setTitle("Mancala");

		add(homePanel);
	}

	/**
	 * Safely Displays GamePanel
	 */
	public void showGamePanel() {
		add(gamePanel);
		remove(homePanel);
		remove(helpPanel);
		revalidate();
		gamePanel.repaint();
	}

	/**
	 * Safely Displays HomePanel
	 */
	public void showHomePanel() {
		add(homePanel);
		remove(endPanel);
		remove(helpPanel);
		remove(gamePanel);
		revalidate();
		homePanel.repaint();
	}

	/**
	 * Safely Displays HelpPanel
	 */
	public void showHelpPanel() {
		add(helpPanel);
		remove(homePanel);
		revalidate();
		helpPanel.repaint();
	}
	
	/**
	 * Safely Displays EndPanel
	 */
	public void showEndPanel(EndCondition e) {
		endPanel = new EndPanel(e);
		add(endPanel);
		remove(gamePanel);
		revalidate();
		endPanel.repaint();
	}

	/**
	 * Repaints GamePanel
	 */
	public void repaintGamePanel(){
		gamePanel.repaint();
	}

	/**
	 * Close the Game
	 */
	public void closeWindow() {
		dispose();
	}


	/**
	 * Load image from resources into BufferedImage to be painted.
	 * @param i
	 * @return
	 */
	public static BufferedImage loadImage(String i) {
		BufferedImage image = null;
		URL resource = GamePanel.class.getResource("resources/"+i);
		try {
			image = ImageIO.read(resource);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
