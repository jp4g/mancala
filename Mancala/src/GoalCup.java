import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Goal cup for Mancala board
 * @author Jack Gilcrest
 * @date 09.12.2018
 */
public class GoalCup implements Cup {

	private int num_stones;
	private Cup next_cup;
	private boolean player; // true if cup is owned by the player, false if the cup is owned by the computer
	private Rectangle ui_rectangle;
	
	public GoalCup(Rectangle r) { 
		ui_rectangle = r;
		num_stones = 0;
	}
	
	/**
	 * Add a single stone to the cup
	 */
	public void addStone() {
		num_stones++;
	}

	/**
	 * Add (n = stones) stones to the cup
	 * @param stones the number of stones being added to the cup
	 */
	public void addStone(int stones) {
		num_stones += stones;
	}

	/**
	 * The number of stones in the cup
	 * @return stones in cup
	 */
	public int getNumStones() {
		return num_stones;
	}

	/**
	 * Adds a reference to the next cup
	 * @param the proceeding cup clockwise when represented graphically
	 */
	public void setNextCup(Cup next) {
		next_cup = next;
	}

	/**
	 * Returns the reference to the next up
	 * @return proceeding cup clockwise when represented graphically 
	 */
	public Cup getNextCup() {
		return next_cup;
	}

	/**
	 * Set the class boolean "player" to reflect cup ownership
	 * @param player true if cup should be owned by player, false otherwise
	 */
	public void setPlayerOwner(boolean player) {
		this.player = player;
	}
	
	/**
	 * Return boolean value reflecting ownership of cup
	 * @return true if owned by player, and false otherwise
	 */
	public boolean getPlayerOwner() {
		return player;
	}
	
	/**
	 * Add reference to the rectangle representing the cup in the UI
	 * @param r Rectangle object in UI
	 */
	public void setRectangle(Rectangle r) {
		r = ui_rectangle;
	}
	
	/**
	 * Return the rectangle representing the cup in the UI
	 * @return the rectangle
	 */
	public Rectangle getRectangle() {
		return ui_rectangle;
	}

	@Override
	public void drawStoneCount(Graphics g) {
		Font font = new Font("Verdana", Font.BOLD, 24);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(num_stones), ui_rectangle.x + 47, ui_rectangle.y+150);
		
	}
	
}
