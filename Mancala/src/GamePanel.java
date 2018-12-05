import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * GamePanel
 * 
 * @author Peter Cooke
 */
public class GamePanel extends JPanel implements MouseListener{
	
	public Board board;
	public JButton backButton;
	public BufferedImage mancalaBoard = null;

	/**
	 * Initialize GamePanel With A Board
	 * @param e
	 */
	public GamePanel() {
		board = Main.board;
		mancalaBoard = GameWindow.loadImage("MancalaBoard.png");
		setLayout(null);
		
		backButton = new JButton("Back");
		
		backButton.setBounds(1000, 690, 180, 60);
		backButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				//board = new Board();
				Main.window.showHomePanel();
			}
		});
		
		add(backButton);
		addMouseListener(this);
	}
	

	/**
	 * Paint Game Panel
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mancalaBoard, 0, 0, null);
		
		Main.board.paint(g);
		
		g.setColor(Color.BLACK);
		if(Main.board.playerTurn)
			g.drawString("Your Turn.", 500, 600);
		else
			g.drawString("Computer Turn...", 500, 600);
		
	}
	
	
	/**
	 * Pass a reference to the board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		Graphics g = getGraphics();
		
		int pos = Main.board.getBoardPosition(e.getPoint());

		System.out.println("mouseClicked Listener: pos = " + pos);
		if (pos == -1)
			return;

		if(Main.board.validateMove(pos))
			Main.board.doMove(pos);
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {}


	@Override
	public void mouseExited(MouseEvent arg0) {}


	@Override
	public void mousePressed(MouseEvent e) {}


	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
	
	private boolean isClickablePosition(int pos) {
		return (pos >= 0 && pos <=5) ? true : false;
	}

}


