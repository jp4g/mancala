import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * HelpPanel
 * 
 * @author Peter Cooke
 */
public class HelpPanel extends JPanel{
	
	public JButton backButton;
	
	public HelpPanel() {
		setLayout(null);
		
		backButton = new JButton("Back");
		
		backButton.setSize(180, 60);
		backButton.setBounds(1000, 690, 180, 60);
		backButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.window.showHomePanel();
			}
		});
		
		add(backButton);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Font font = g.getFont().deriveFont(24.0f);
		g.setFont(font);
		g.drawString("Rules", 550, 100);
		g.drawString("The objective of Mancala is to get as many stones in your bank as possible. The player", 200, 150);
		g.drawString("will always be on the bottom side of the board in this game and the computer will be", 100, 200);
		g.drawString("the top. The bins on the top and bottom will each contain four stones at the start of the game.", 100, 250);
		g.drawString("The moves consist of the player choosing one bin to take each of the stones from and then", 100, 300);
		g.drawString("moving counter clockwise placing a stone in each bin unil they run out.  If the player gets back to ", 100, 350);
		g.drawString("their bank, they put one stone in their bank.  If they get to the opponent's bin, they skip", 100, 400);
		g.drawString("it. If your last stone placement is in your bank you get another turn.  If the last stone placed", 100, 450);
		g.drawString("is in an empty bin, the player gains that stone and the stones opposite of that bin and puts", 100, 500);
		g.drawString("them in their bank.  Once the bins on one side of the board are empty, the game ends.", 100, 550);
		g.drawString("The player who still has stones on their side captures them.  Whoever has the most stones ", 100, 600);
		g.drawString("in their bank wins. ", 100, 650);
		
	}
}
