import java.awt.Rectangle;
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> d7e4d738f2bf1c4b9bd0369ba0799a5faa2df00c

/**
 * Game cup for Mancala board
 * @author Jack Gilcrest
 * @date 09.12.2018
 */
public class GameCup implements Cup{
	private final int STARTING_STONES = 4;
	private int num_stones;
	private Cup next_cup;
<<<<<<< HEAD
	private boolean player; // true if cup is owned by the player, false if the cup is owned by the computer
	private Rectangle ui_rectangle;
	
	public GameCup() {
		num_stones = STARTING_STONES;
=======
	private Rectangle area;
	//private player owner;
	
	public GameCup(int start_stones, Rectangle a) {
		num_stones = start_stones;
		area = a;
>>>>>>> d7e4d738f2bf1c4b9bd0369ba0799a5faa2df00c
	}
	
	/**
	 * Add a single stone to the cup
	 */
	public void addStone() {
		num_stones++;
	}

	/**
	 * removes all stones in the cup
	 * @return the number of stones in the cup before emptying
	 */
	public int removeStones() {
		int temp = num_stones;
		num_stones = 0;
		return temp;
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
	
<<<<<<< HEAD
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
	
	private void makeRect() {
		ArrayList<Rectangle> r;
		r.add(w1, h1, x1, y1);
		r.add(w2, h2, x2, y2);
	}
	
=======
	
	/**
	 * Gets the selection area for the cup on the screen 
	 * @return
	 */
	public Rectangle getSelectionArea() {
		return area;
	}
>>>>>>> d7e4d738f2bf1c4b9bd0369ba0799a5faa2df00c
}