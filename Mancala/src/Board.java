import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.Timer;

/**
 * Board for Mancala game
 * 
 * @author Jack Gilcrest
 * @date 09.12.2018
 */
public class Board {
	private final int STARTING_STONES_PER_CUP = 4; // stones placed in each game cup @ time of initialization
	private ArrayList<Cup> cups = new ArrayList<Cup>(); // ArrayList storing Cup objects to simulate a board
	boolean playerTurn; // Tracking whether it is the Player or the AI Opponent's turn
	public boolean animationInProgress = false; // reflects whether an animation is running on screen

	// class variables stating board index ranges
	private final int PLAYER_FIRST_CUP = 0;
	private final int PLAYER_GOAL_CUP = 6;
	private final int AI_FIRST_CUP = 7;
	private final int AI_GOAL_CUP = 13;

	// class variables for action listener
	private int AL_stones_placed;
	private int AL_stones_held;
	private int AL_index;

	// class variable for index of move passed by UI
	private int UI_move;

	/**
	 * construct a new board object
	 */
	public Board() {
		initialize();
	}

	/**
	 * Retrieve an ArrayList<Cup> that reflects the current game state
	 * 
	 * @return the ArrayList<Cup> of cups in the game
	 */
	public ArrayList<Cup> getBoard() {
		return cups;
	}

	/**
	 * Run all initializing methods for starting a new game
	 */
	private void initialize() {
		initCups();
		chooseFirst();
		resetALVariables();
		UI_move = -1;
	}

	/**
	 * Randomly select whether the Player or AI Opponent moves first and set class
	 * variable 'playerTurn' accordingly
	 */
	private void chooseFirst() {
		playerTurn = true;
		//Random r = new Random();
		//playerTurn = r.nextBoolean();
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
	public void updateAllCupsInstantly(Graphics g) {
		for (int i = 0; i < cups.size(); i++)
			cups.get(i).drawStoneCount(g);
	}

	/**
	 * Draws the number of stones in each cup
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		for (int i = 0; i < cups.size(); i++)
			cups.get(i).drawStoneCount(g);
	}

	/**
	 * Initialize all Cup objects used and add them to the ArrayList<Cup> cups class
	 * variable States the dimensions for each up individually.
	 */
	private void initCups() {
		// Player game cups
		cups.add(new GameCup(new Rectangle(240, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(360, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(485, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(610, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(735, 410, 105, 105)));
		cups.add(new GameCup(new Rectangle(855, 410, 105, 105)));
		// Player goal cup
		cups.add(new GoalCup(new Rectangle(980, 235, 105, 280)));

		// AI Opponent game cups
		cups.add(new GameCup(new Rectangle(855, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(735, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(610, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(485, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(360, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(240, 240, 105, 105)));
		// AI Opponent goal cup
		cups.add(new GoalCup(new Rectangle(115, 235, 102, 280)));
	}

	/**
	 * AI Opponent evaluates the board and returns its chosen move.
	 * 
	 * @return the index of the cup to begin moving from
	 */
	private int getComputerPos() {
		// Evaluate each cup to see if a second turn can be achieved
		/** 1/4 preferred move type **/
		for (int i = AI_GOAL_CUP - 1; i >= AI_FIRST_CUP; i--) {
			if (cups.get(i).getNumStones() == AI_GOAL_CUP - i)
				return i;
		}

		// Distance++; // increment distance as moving away from AI Opponent's goal cup
		// If the last stone can move to the AI Opponent's cup, that cup is empty and
		// the
		// Player'ss cup have some stones,
		// then do that move

		/** 2/4 preferred move type **/
		for (int i = AI_GOAL_CUP - 1; i >= AI_FIRST_CUP; i--) {
			if (cups.get(i).getNumStones() == 0) { // if current cup has no stones
				int qDistance = 0; //
				int tDistance = 0;
				for (int q = i; q >= AI_FIRST_CUP; q--) {
					qDistance++;
					if (cups.get(q).getNumStones() == qDistance && cups.get(12 - q).getNumStones() != 0)
						return q;
				}
				for (int t = i; t < 13; t++) {
					tDistance++;
					if (cups.get(t).getNumStones() + tDistance == 14 && cups.get(12 - t).getNumStones() != 0)
						return t;
				}
			}
		}

		// Evaluate each cup to see if a stone can be placed in the bank
		/** 3/4 preferred move type **/
		for (int i = AI_GOAL_CUP - 1; i >= AI_FIRST_CUP; i--) {
			if (i + cups.get(i).getNumStones() >= AI_GOAL_CUP)
				return i;
		}

		// Iterate from the AI Opponent's first game cup to its last game cup
		// Returns the first cup it finds that has stones
		/** 4/4 preferred move type **/
		for (int i = AI_FIRST_CUP; i < AI_GOAL_CUP; i++) {
			if (cups.get(i).getNumStones() != 0)
				return i;
		}

		// No cup on the AI Opponent's side of the board had stones and the AI cannot
		// make a move
		// This should not ever occur.
		System.out.println("There are no AI Opponent game cups containing stones.");
		return -1;
	}

	/**
	 * Passes a move from the UI into the board class
	 * 
	 * @param index the index of the move being passed from the UI
	 */
	public void giveMoveUI(int index) {
		UI_move = index;
	}

	/**
	 * UI accepts player input until a given move passes validation
	 * 
	 * @return the index of the cup to begin moving from
	 */
	private int getPlayerPos() {
		while (UI_move == -1) {
		}
		return UI_move;
	}

	/**
	 * Determines whether Player or AI Opponent gets to move next and returns the
	 * index of their move accordingly
	 * 
	 * @return the index of the next move
	 */
	private int moveIndex() {
		if (playerTurn)
			return getPlayerPos(); // get move position from ui input subsystem
		else
			return getComputerPos(); // get move position from ai subsystem
	}

	/**
	 * Executes a single move and updates graphics accordingly
	 */
	public void doMove() {
		AL_stones_placed = 0;
		AL_index = moveIndex(); // get the starting index of the move
		animationInProgress = true; // prevent further ui input while true
		AL_stones_held = ((GameCup) cups.get(AL_index)).removeStones(); // remove and remember stones from chosen cup
		AL_index = trueIndex(AL_index); // find the index for the cup to start placing stones in

		// Use timer to do animations
		Timer timer = new Timer(1000, getActionListener());
		timer.start();
		turnEnd(AL_index);
	}

	/**
	 * Create an ActionListener object to reflect a move in the UI
	 * 
	 * @return the ActionListener objecy
	 */
	private ActionListener getActionListener() {
		ActionListener a = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (AL_stones_placed == AL_stones_held || AL_stones_placed > AL_stones_held) {
					animationInProgress = false;
					((Timer) e.getSource()).stop();
				}
				cups.get(AL_index++).addStone();
				AL_index = trueIndex(AL_index);
				AL_stones_placed++;
				System.out.println("Pause for 1 second");
				Main.window.repaintGamePanel();
			}
		};
		return a;
	}

	/**
	 * Reset class varaibles necessary for ActionListener after end of a move
	 */
	private void resetALVariables() {
		AL_stones_placed = -1;
		AL_stones_held = -1;
		AL_index = -1;
	}

	/**
	 * determines whether the last stone placed in a turn was in a cup that ends the
	 * turn (i.e. either goal cup (index 6 and 13) or in a cup with 0 stones
	 * 
	 * @param index the index to start at
	 * @throws InterruptedException
	 */
	private void turnEnd(int index) {
		if (index == 13 || index == 6 || cups.get(index).getNumStones() == 0)
			playerTurn = !playerTurn;
		else {
			doMove();
		}
	}

	/**
	 * Determines the index of the cup that the first stone of a turn should be
	 * placed in
	 * 
	 * @param index the index chosen for the move
	 * @return the index to begin placing stones in
	 */
	private int trueIndex(int index) {
		// prefix increment index to place first stone in the cup next to the chosen cup
		// account for index being out of bounds of board by taking remainder of index /
		// 14, then return
		return ++index % 14;
	}

	/**
	 * Refers to playerTurn boolean to determine if a cup is the opposing player's
	 * cup
	 * 
	 * @param index the index of the cup being evaluated
	 * @return true if the cup is the opposing player's goal cup, and false
	 *         otherwise
	 */
	private boolean opposingGoalCup(int index) {
		if (playerTurn)
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
		return cups.get(6).getNumStones() > cups.get(13).getNumStones();
	}

	/**
	 * Evaluate a given move to see if it conforms to the rules
	 * 
	 * @param move an integer giving the position to start moving from
	 * @return true if the move is valid, and false otherwise
	 */
	public boolean validateMove(int move) {
		// validate that the computer or player chose a cup that is on their side
		if (playerTurn) {
			// validate that the move index falls within the Player's game cup range
			if (move > PLAYER_GOAL_CUP || move < PLAYER_FIRST_CUP) {
				System.out.println("Move chosen was on the AI Opponent's side of the board. Invalid move for Player.");
				return false;
			}
		} else {
			// validate that the move index falls within the AI Opponent's game cup range
			if (move >= AI_GOAL_CUP || move < AI_FIRST_CUP) {
				System.out.println("Move chosen was on the Player's side of the board. Invalid move for AI Opponent.");
				return false;
			}
		}

		// validate that a bank was not chosen
		if (move == PLAYER_GOAL_CUP || move == AI_GOAL_CUP) {
			System.out.println("Cannot do move inside bank.");
			return false;
		}

		// validate that an empty cup was not chosen
		else if (cups.get(move).getNumStones() == 0) {
			System.out.println("No stones in cup.");
			return false;
		}
		
		// validate that the animation is not in progress
		else if (animationInProgress)
			return false;

		// all validations have been successfully passed
		else
			return true;

	}

	/**
	 * Choose which screen to display at end condition.
	 */
	private void handleEnd() {
		if (tie())
			Main.window.showEndPanel(EndCondition.TIE);
		else {
			if (playerWins())
				Main.window.showEndPanel(EndCondition.WIN);
			else
				Main.window.showEndPanel(EndCondition.LOSE);
		}
	}

}
