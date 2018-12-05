import java.io.*;
import java.util.Scanner;

public class Scores {

	private static String url = "scores";
	private static final int NUM_PARTICIPANTS = 2;

	/**
	 * Return score
	 * 
	 * @param player
	 *            return player score if true, return AI score otherwise
	 * @return the requested score
	 **/
	public static int getScore(boolean player) {
		int scores[] = getScore();
		if (player)
			return scores[0];
		else
			return scores[1];

	}

	/**
	 * increase score by 1
	 * 
	 * @param player
	 *            increase player score if true, increase AI score otherwise
	 **/
	public static void increaseScore(boolean player) {
		int scores[] = getScore();
		int index;
		if (player)
			index = 0;
		else
			index = 1;
		scores[index]++;
		writeScore(scores);
	}

	/**
     * Return an array of two ints containing the player and AI scores
     * getScore[0] is the player score, getScore[1] is the AI score
     * @return the scores as an integer array of length 2
     **/
    private static int[] getScore() {
    	String line;
    	int temp[] = new int[2];
    	try {
			FileReader f = new FileReader(url);
			BufferedReader b = new BufferedReader(f);
			int i = 0;
			while ( (line = b.readLine()) != null) {
				temp[i] = Integer.parseInt(line);
				i++;	
			}
			b.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
    }

	/**
	 * Reset score back to 0
	 **/
	private static void resetScore() {
		int scores[] = { 0, 0 };
		writeScore(scores);
	}

	/**
	 * Write the scores to a file
	 * 
	 * @param the
	 *            scores as an integer array of length 2
	 **/
	private static void writeScore(int[] scores) {
		
		try {
			FileWriter f = new FileWriter(url);
			BufferedWriter b = new BufferedWriter(f);
			b.write(String.valueOf(scores[0]));
			b.newLine();
			b.write(String.valueOf(scores[1]));
			b.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}