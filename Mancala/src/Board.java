/**
 * Board for Mancala game
 * @author Jack Gilcrest
 * @date 09.12.2018
 */
public class Board {
	private final int STARTING_STONES_PER_CUP = 4;
	private Cup[] cups;
	boolean playerTurn;
	
	
	public Board() {
		initialize();
		cups = initializeCupArray();
	}
	
	private void initialize() {
		initializeCupArray();
		
	}
	
	private Cup[] initializeCupArray() {
		Cup[] temp = new Cup[14];
		for(int i = 0; i < 2; i++) {
			cups[i*6] = new GoalCup();
			for(int j = 0; j < 5; j++) {
				cups[(i*6) + j] = new GameCup(STARTING_STONES_PER_CUP);
			}
		}
		for(int i = 0; i < 11; i++) {
			temp[i].setNextCup(temp[i+1]);
		}
		temp[11].setNextCup(temp[0]);
		return temp;
	}
	
	private void chooseFirst() {
		
	}
	}
	
}
