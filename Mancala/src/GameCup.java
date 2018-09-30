import java.awt.Rectangle;

/**
 * Game cup for Mancala board
 * @author Jack Gilcrest
 * @date 09.12.2018
 */
public class GameCup implements Cup{

	private int num_stones;
	private Cup next_cup;
	private Rectangle area;
	//private player owner;
	
	public GameCup(int start_stones) {
		num_stones = start_stones;
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
	
	
	/**
	 * Gets the selection area for the cup on the screen 
	 * @return
	 */
	public Rectangle getSelectionArea() {
		return area;
	}
}