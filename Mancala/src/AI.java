import java.util.ArrayList;

public class AI {

	// class variables stating board index ranges
	private static final int AI_FIRST_CUP = 7;
	private static final int AI_GOAL_CUP = 13;

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
		for (int i = AI_GOAL_CUP - 1; i >= AI_FIRST_CUP; i--) {
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
		for (int i = AI_GOAL_CUP - 1; i >= AI_FIRST_CUP; i--) {
			if (cups.get(i).getNumStones() == 0) { // if current cup has no stones
				int qDistance = 0; //
				int tDistance = 0;
				for (int q = i; q >= AI_FIRST_CUP; q--) {
					qDistance++;
					if (cups.get(q).getNumStones() == qDistance && cups.get(12 - q).getNumStones() != 0)
						index = q;
				}
				for (int t = i; t < 13; t++) {
					tDistance++;
					if (cups.get(t).getNumStones() + tDistance == 14 && cups.get(12 - t).getNumStones() != 0)
						index = t;
				}
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
		for (int i = AI_GOAL_CUP - 1; i >= AI_FIRST_CUP; i--) {
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
		for (int i = AI_FIRST_CUP; i < AI_GOAL_CUP; i++) {
			if (cups.get(i).getNumStones() != 0)
				index = i;
		}
		if(index == -1) //this should never occur.
			System.out.println("There are no AI Opponent game cups containing stones.");
		return index;
	}
	
}
