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
		//initializeCups();
		//chooseFirst();
	}
	
	private void initializeCups() { //p1: game cups[0-5] goal cup[6]; p2: game cups[7-12] goal cup[13]
		for (int i = 0; i < 2; i++) { // make cup objects and add to ArrayList
			for (int j = 0; j < 6; j++)
				cups.add(new GameCup());
			cups.add(new GoalCup());
		}
		//setRect();
		for (int i = 0; i < 14; i++) { // initialize variables within cup object
			cups.get(i).setNextCup(cups.get((i + 1) % 14));
			//cups.get(i).setRectangle(w, h, x, y);
			if (i / 7 == 0)
				cups.get(i).setPlayerOwner(true);
			else
				cups.get(i).setPlayerOwner(false);
		}
	}

	private void chooseFirst() {
		Random r = new Random();
		playerTurn = r.nextBoolean();
	}

	private void doMove() {
		boolean turn = playerTurn;
		while(turn == playerTurn) {
			if(playerTurn)
				doPlayerMove();
			else
				doComputerMove();
		}
	}
	
	/**
	 * Checks if the point is a valid spot to place 
	 * @param p Point of where player clicked
	 * @return The positions index, -1 if not found
	 */
	public int getBoardPosition(Point p) {
		for(int i = 0; i < cups.size(); i++) 
			if(cups.get(i).getRectangle().contains(p))
				return i;
		return -1;
	}
	
	
	
	
	/**
	 * Change board to reflect the move
	 * @param cupIndex
	 */
	reflectMove(int cupIndex) {
		
	}
	
	private void initCups() {
		cups.add(new GameCup(new Rectangle(240, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(360, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(485, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(610, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(735, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(855, 410, 105, 105)));
		
		//Add end cup
		cups.add(new GameCup(new Rectangle(980, 235, 105, 290)));
		
		cups.add(new GameCup(new Rectangle(855, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(735, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(610, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(485, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(360, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(240, 240, 105, 105)));
		
		//Add end cup
		cups.add(new GameCup(new Rectangle(110, 235, 105, 290)));
	}
	
	private void doPlayerMove() {
		ArrayList startBoard = new ArrayList(cups.subList(0, 6));
		ArrayList secondBoard = new ArrayList(cups.subList(7, 13));
		int stoneSelection = startBoard.getNumStones(move); //Get move selection
		if (stoneSelection == 0) { //If no stones in cup selection
			System.out.println("No stones in cup.");
			
		}
		startBoard.removeStones(move); //Remove stones from cup selected
		int currentPosition = move + 1;
		for (int stonesLeft = stoneSelection; stonesLeft > 0; --stonesLeft) 
		{
			if (currentPosition < 13) 
			{
				cups.addStone;
				currentPosition++;
			} else if (currentPosition = 13)// on final cup, loop back to start
				
			{
				cups.addStone;
				currentPosition = 0;
			}
		}

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
	}

	private void doComputerMove() {
		
	}
}
