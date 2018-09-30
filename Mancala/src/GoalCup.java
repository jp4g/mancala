/**
 * Goal cup for Mancala board
 * @author Jack Gilcrest
 * @date 09.12.2018
 */
public class GoalCup implements Cup {

	private int num_stones;
	private Cup next_cup;
	//private player owner;
	
	public GoalCup() { 
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

}
