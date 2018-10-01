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
	private ArrayList<Cup> cups;
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
		initializeCups();
		chooseFirst();
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
	
//	private void setRects() {
//		cups.get(0).setRectangle(a, b, c, d);
//		cups.get(1).setRectangle(a, b, c, d);
//		cups.get(2).setRectangle(a, b, c, d);
//		cups.get(3).setRectangle(a, b, c, d);
//		cups.get(4).setRectangle(a, b, c, d);
//		cups.get(5).setRectangle(a, b, c, d);
//		cups.get(6).setRectangle(a, b, c, d);
//		cups.get(7).setRectangle(a, b, c, d);
//		cups.get(8).setRectangle(a, b, c, d);
//		cups.get(9).setRectangle(a, b, c, d);
//		cups.get(10).setRectangle(a, b, c, d);
//		cups.get(11).setRectangle(a, b, c, d);
//	}
	
	private void doPlayerMove() {
		
	}

	private void doComputerMove() {
		
	}
}
