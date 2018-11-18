import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 * Board for Mancala game
 * 
 * @author Jack Gilcrest
 * @date 09.12.2018
 */
public class Board {
	private final int STARTING_STONES_PER_CUP = 4;
	private ArrayList<Cup> cups = new ArrayList<Cup>();
	boolean playerTurn;

	/**
	 * construct a new board object
	 */
	public Board() {
		initialize();
	}

	/**
	 * Gets the board array list
	 * 
	 * @return an array list representing the current state of the board
	 */
	public ArrayList<Cup> getBoard() {
		return cups;
	}

	private void initialize() {
		initCups();
		chooseFirst();
	}

	private void chooseFirst() {
		Random r = new Random();
		playerTurn = r.nextBoolean();
	}

	/**
	 * Checks if the point is a valid spot to place
	 * 
	 * @param p Point of where player clicked
	 * @return The positions index, -1 if not found
	 */
	public int getBoardPosition(Point p) {
		for (int i = 0; i < cups.size(); i++)
			if (cups.get(i).getRectangle().contains(p))
				return i;
		return -1;
	}

	/**
	 * Update All Cups
	 */
	public void update(Graphics g) {
		for (int i = 0; i < cups.size(); i++)
			cups.get(i).drawStoneCount(g);

	}

	private void initCups() {
		cups.add(new GameCup(new Rectangle(240, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(360, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(485, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(610, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(735, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(855, 410, 105, 105)));

		// Add end cup
		cups.add(new GoalCup(new Rectangle(980, 235, 105, 290)));

		cups.add(new GameCup(new Rectangle(855, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(735, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(610, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(485, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(360, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(240, 240, 105, 105)));

		// Add end cup
		cups.add(new GoalCup(new Rectangle(110, 235, 105, 290)));
	}

	//private void doPlayerMove() {
		//ArrayList startBoard = new ArrayList(cups.subList(0, 6));
		//ArrayList secondBoard = new ArrayList(cups.subList(7, 13));
		//int stoneSelection = startBoard.getNumStones(move); //Get move selection
		//if (stoneSelection == 0) { //If no stones in cup selection
		//	System.out.println("No stones in cup.");
		//	
		//}
		//startBoard.removeStones(move); //Remove stones from cup selected
		//int currentPosition = move + 1;
		//for (int stonesLeft = stoneSelection; stonesLeft > 0; --stonesLeft) 
		//{
		//	if (currentPosition < 13) 
		//	{
		//		cups.addStone;
		//		currentPosition++;
		//	} else if (currentPosition = 13)// on final cup, loop back to start
		//		
		//	{
		//		cups.addStone;
		//		currentPosition = 0;
		//	}
		//}

/*
		if (currentPosition < 6 && startBoard.get(currentPosition) == 1) 
		{
			// last stone goes to empty spot

			startBoard.set(6, startBoard.get(6) + secondBoard.get(5 - currentPosition) + 1);
			startBoard.set(currentPosition, 0);
			secondBoard.set(5 - currentPosition, 0);
			turnOver = true;
		}

		if (currentPosition != 6) 
		{
			turn = !turn;

		}
*/
	

	private int getComputerPos() {
		return 0;
	}
	
	private int getPlayerPos() {
		return 0;
	}

	private void doMove() {
		int startingPosition;
		if(playerTurn) 
			startingPosition = getPlayerPos(); //get move position from ui input subsystem
		else
			startingPosition = getComputerPos(); //get move position from ai subsystem
		int stones_held = ((GameCup) cups.get(startingPosition)).removeStones();
		int index = trueIndex(startingPosition) ;
		for(int i = 0; i < stones_held; i++) {
				cups.get(index++).addStone();
				index = trueIndex(index);
				//update ui
			}
		turnEnd(index);
		}

	private void doMove(int index) {
		int stones_held = ((GameCup) cups.get(index)).removeStones();
		index = trueIndex(index) ;
		for(int i = 0; i < stones_held; i++) {
			cups.get(index++).addStone();
			index = trueIndex(index);
			//update ui
		}
		turnEnd(index);
	}
	
	/**
	 * determines whether the last stone placed in a turn was in a cup that ends the turn
	 * (i.e. either goal cup (index 6 and 13) or in a cup with 0 stones
	 * @param index the index to start at
	 */
	private void turnEnd(int index) {
		if(index == 13 || index == 6 || cups.get(index).getNumStones() == 0)
			playerTurn = !playerTurn;
		else {
			doMove(index);
		}
	}

	private int trueIndex(int index) {
		if(opposingGoalCup(index))
			index++;
		return index%14;
	}
	
	/**
	 * Refers to playerTurn boolean to determine if a cup is the opposing player's cup
	 * @param index the index of the cup being evaluated
	 * @return true if the cup is the opposing player's goal cup, and false otherwise
	 */
	private boolean opposingGoalCup(int index) {
		if(playerTurn)
			return index == 13;
		else
			return index == 6;
	}
	
	/**
	 * Checks player cups to see if empty then computer cups to see if entry
	 * 
	 * @return true if either the player cups or computer cups are completely empty
	 */
	private boolean endCondition() {
		boolean playerCupsEmpty = true;
		for (int i = 0; i < 6; i++) {
			if (cups.get(i).getNumStones() != 0)
				playerCupsEmpty = false;
		}
		if (playerCupsEmpty)
			return true;
		else {
			for (int i = 7; i < 13; i++) {
				if (cups.get(i).getNumStones() != 0)
					return false;
			}
			return true;
		}
	}

	/**
	 * Determine if the game ends in a tie. Should not be used before endCondition
	 * returns true
	 * 
	 * @return true if the player and computer have equal scores at the end of the
	 *         game, and false otherwise
	 */
	private boolean tie() {
		return cups.get(6) == cups.get(13);
	}

	/**
	 * Determine if the game ends by the player winning.
	 * 
	 * @return true if the player wins, false if the computer wins.
	 */
	private boolean playerWins() {
		return cups.get(6) > cups.get(13);
	}

}
