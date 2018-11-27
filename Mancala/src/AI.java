import java.util.ArrayList;

public class AI {

	// class variables stating board index ranges
	private static final int AI_FIRST_CUP = 7;
	private static final int AI_GOAL_CUP = 13;

	/**
	 * Do 4 different searches for specific moves and return the best move found.
	 * Returns -1 if no move is found. This should never happen: if there are no moves, the AI should not get another turn.
	 * @param cups the ArrayList of cup objects
	 * @return the index of the AI Opponent's chosen move
	 */
	public static int getMoveIndex(ArrayList<Cup> cups) {
		int move = move1(cups);
		if(move != -1)
			return move;
		move = move2(cups);
		if(move != -1)
			return move;
		move = move3(cups);
		return move4(cups);
	}
	
	/**
	 * Evaluate each cup to see if a second turn can be achieved. 
	 * This is the most preferred move type.
	 * Returns -1 if no move is found.
	 * @param cups the ArrayList of cup objects
	 * @return the index of the cup to begin the move from
	 */
	private static int move1(ArrayList<Cup> cups) {
		int index = -1;
		for (int i = AI_GOAL_CUP - 1; i >= AI_FIRST_CUP; i--) { // find a cup that allows for a second turn
			if (cups.get(i).getNumStones() == AI_GOAL_CUP - i)
				index = i;
		}
		return index;
	}

	/**
	 * Evaluate each cup to if the player's stones can be captured. 
	 * This is the second most preferred move type.
	 * Returns -1 if no move is found.
	 * @param cups the ArrayList of cup objects
	 * @return the index of the cup to begin the move from
	 */
	private static int move2(ArrayList<Cup> cups) {
		int index = -1;
		for(int i = AI_FIRST_CUP; i < AI_GOAL_CUP; i++) {
			int target_index = (cups.get(i).getNumStones() + i) % 14; // find index of cup that last stone is placed in
			int player_index = AI_GOAL_CUP - target_index - 1; // find corresponding cup on player's side
			if(target_index >= AI_FIRST_CUP && target_index < AI_GOAL_CUP) { // ensure that target index is on AI's side
				if (cups.get(target_index).getNumStones() == 0 && cups.get(player_index).getNumStones() != 0) // check capture conditions
					index = i;
			}
		}
		return index;
	}
			
	/**
	 * Evaluate each cup to if a stone can be placed in the AI Opponent's bank. 
	 * This is the third most preferred move type.
	 * Returns -1 if no move is found.
	 * @param cups the ArrayList of cup objects
	 * @return the index of the cup to begin the move from
	 */
	private static int move3(ArrayList<Cup> cups) {
		int index = -1;
		for (int i = AI_GOAL_CUP - 1; i >= AI_FIRST_CUP; i--) { // find a cup that has enough stones to reach the AI Opponent's bank
			if (i + cups.get(i).getNumStones() >= AI_GOAL_CUP)
				index = i;
		}
		return index;
	}
	
	/**
	 * Scan each cup and return the index of the first cup containing stones
	 * This is the least preferred move type.
	 * Returns -1 if no move is found.
	 * @param cups the ArrayList of cup objects
	 * @return the index of the cup to begin the move from
	 */
	private static int move4(ArrayList<Cup> cups) {
		int index = -1;
		for (int i = AI_FIRST_CUP; i < AI_GOAL_CUP; i++) { // find a cup with stones
			if (cups.get(i).getNumStones() != 0)
				index = i;
		}
		if(index == -1) //this should never occur.
			System.out.println("There are no AI Opponent game cups containing stones.");
		return index;
	}
	
}
