import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameWindow extends JFrame {

	public boolean onHomePanel;
	public boolean onHelpPanel;
	public boolean onGamePanel;
	
	public Toolkit tk = Toolkit.getDefaultToolkit();
	
	public GamePanel gamePanel;
	public HomePanel homePanel;
	public HelpPanel helpPanel;

	
	public GameWindow() {
		gamePanel = new GamePanel();
		homePanel = new HomePanel();
		helpPanel = new HelpPanel();
		
		setBounds(tk.getScreenSize().width / 2 - 600, tk.getScreenSize().height / 2 - 400, 1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		setFocusable(true);
		setResizable(false);
		setTitle("Mancala");

		add(homePanel);
	}
	
	public void showGamePanel() {
		add(gamePanel);
		remove(homePanel);
		remove(helpPanel);
		revalidate();
		gamePanel.repaint();
		setGamePanel();
	}

	public void showHomePanel() {
		add(homePanel);
		remove(helpPanel);
		remove(gamePanel);
		revalidate();
		homePanel.repaint();
		setHomePanel();
	}
	
	public void showHelpPanel() {
		add(helpPanel);
		remove(homePanel);
		revalidate();
		helpPanel.repaint();
		setHelpPanel();
	}

	public void repaintGamePanel(){
		gamePanel.repaint();
	}
	
	public void closeWindow() {
		dispose();
	}

	

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
	
	private void setGamePanel() {
		onHomePanel = false;
		onGamePanel = true;
		onHelpPanel = false;
	}
	
	private void setHomePanel() {
		onHomePanel = true;
		onGamePanel = false;
		onHelpPanel = false;
	}
	
	private void setHelpPanel() {
		onHomePanel = false;
		onGamePanel = false;
		onHelpPanel = true;
	}
}
