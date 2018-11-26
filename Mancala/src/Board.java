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
	private final int STARTING_STONES_PER_CUP = 4;
	private ArrayList<Cup> cups = new ArrayList<Cup>();
	boolean playerTurn;
	public boolean animationInProgress = false;
	private int index;
	private int i;
	private int stones_held;

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
	public void updateAllCupsInstantly(Graphics g) {
		for (int i = 0; i < cups.size(); i++)
			cups.get(i).drawStoneCount(g);
	}

	public void paint(Graphics g) {
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
		cups.add(new GoalCup(new Rectangle(980, 235, 105, 280)));

		cups.add(new GameCup(new Rectangle(855, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(735, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(610, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(485, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(360, 240, 105, 105)));
		cups.add(new GameCup(new Rectangle(240, 240, 105, 105)));

		// Add end cup
		cups.add(new GoalCup(new Rectangle(115, 235, 102, 280)));
	}

	private int getComputerPos() {
		return 0;
	}

	private int getPlayerPos() {
		return 0;
	}

	public void doMove() {
		int startingPosition;
		if (playerTurn)
			startingPosition = getPlayerPos(); // get move position from ui input subsystem
		else
			startingPosition = getComputerPos(); // get move position from ai subsystem

		int stones_held = ((GameCup) cups.get(startingPosition)).removeStones();
		int index = trueIndex(startingPosition);
		for (int i = 0; i < stones_held; i++) {
			cups.get(++index).addStone();
			index = trueIndex(index);
			// Main.window.repaintGamePanel();// update ui
			// updateCupWithDelay(index);
		}
		turnEnd(index);
	}

	public void doMove(int indx) {
		animationInProgress = true;
		i = 0;
		index = indx;
		stones_held = ((GameCup) cups.get(index)).removeStones();

		index = trueIndex(index);

		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// I think im going to have to add a check in here for turnEnd
				// Then if I can keep going reset i, stones_held to keep the counter going
				if (i == stones_held || i > stones_held) {
					animationInProgress = false;
					((Timer) e.getSource()).stop();
				}

				cups.get(index++).addStone();
				index = trueIndex(index);
				i++;
				System.out.println("Pause for 1 second");
				Main.window.repaintGamePanel();
			}
		});
		timer.start();
		turnEnd(index);
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
			doMove(index);
		}
	}

	private int trueIndex(int index) {
		if (opposingGoalCup(index))
			index++;
		return index % 14;
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

	private void doComputerMove() {
		// if BestMove get extra turn
		int Distance = 0;
		int MaxMove = -1;
		int computerMove = -1;
		for (int i = 12; i > 6; i--) {
			Distance++;
			MaxMove = Math.max(cups.get(i).getNumStones(), cups.get(i - 1).getNumStones() - 1);

			int StoneAmount = cups.get(i).getNumStones();
			if (StoneAmount == Distance) {
				computerMove = i;

			}

			// if can't get BestMove, try to move The movement allows the last stone to be
			// placed on the computer side,
			// and when the last stone is placed, the number of the stones at that place is
			// the same as the top and bottom.
			/** make it find empty, not equal **/
			
			
			
			//else if (cups.get(i).getNumStones() > Distance + 7) {
			//	if (cups.get(cups.get(i).getNumStones() + i - 14).getNumStones() == cups
			//			.get(cups.get(i).getNumStones() + i - 2).getNumStones()) {
			//		computerMove = i;
			//	}
				// else, If there is a scoring area that can move the stone across the computer,
				// choose the farthest after moving.
			//	else {
			//		computerMove = MaxMove;
			//	}
			//}

			// else, If 7 has a stone, move 7。 If not, move 8 and so on.
			else {
				for (int p = 7; p < 13; p++) {
					if (cups.get(p).getNumStones() != 0) {
						computerMove = p;
					} else {
						System.out.println("No stones in cup.");
					}
				}
			}
		}
	}

}
