/**
 * Interface for Mancala cups
 * Used for goal cups and game cups
 * @author Jack Gilcrest
 * @date 09.12.2018
 */
public interface Cup {

	/**
	 * Add a single stone to the cup
	 */
	public void addStone();
	
	/**
	 * Add (n = stones) stones to the cup
	 * @param stones the number of stones being added to the cup
	 */
	public void addStone(int stones);
	
	/**
	 * The number of stones in the cup
	 * @return stones in cup
	 */
	public int getNumStones();
	
	/**
	 * Adds a reference to the next cup
	 * @param the proceeding cup clockwise when represented graphically
	 */
	public void setNextCup(Cup next);
	
	/**
	 * Returns the reference to the next up
	 * @return proceeding cup clockwise when represented graphically 
	 */
	public Cup getNextCup();
	
}
