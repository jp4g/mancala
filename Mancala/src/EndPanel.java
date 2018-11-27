import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

enum EndCondition{
	WIN,LOSE,TIE;
}

public class EndPanel extends JPanel{
	
	private EndCondition ec;
	private JButton mainMenuButton;
	
	public EndPanel(EndCondition e) {
		ec = e;
		
		mainMenuButton = new JButton("Main Menu");
		
		setLayout(null);
		
		mainMenuButton.setBounds(500, 520, 180, 60);
		mainMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.window.showHomePanel();
			}
		});
		
		add(mainMenuButton);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font font = g.getFont().deriveFont(72.0f);
		g.setFont(font);
		switch (ec) {
		case WIN:
			g.drawString("You Win!", 450, 400);
			break;
		case LOSE:
			g.drawString("You Lose!", 450, 400);
			break;
		case TIE:
			g.drawString("Draw!", 450, 400);
			break;
		}
	}
	
}
