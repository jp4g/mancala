
public class Main {
	
	public static GameWindow window;
	public static Board board;
	
	public static void main(String[] args) {
		initialize();
	}
	
	/**
	 * initialize all functions necessary for game operation
	 */
	private static void initialize() {
		board = new Board();
		window = new GameWindow(board);	
	}
}
