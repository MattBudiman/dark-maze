//  Description: The Driver Class for the game.
import java.awt.Color;

public class DarkMaze {

	public static void main(String[] args) {

		//String blueprints for the level
		String[][] traversers = new String[5][5];
		String[][] items = new String[5][5];

		//Setting up the level;
		traversers[1][0] = "P";
		items[0][1] = "H";
		items[1][2] = "H";
		items[2][0] = "H";
		items[3][2] = "H";
		items[3][0] = "B";
		items[0][2] = "L";
		items[3][3] = "G";

		// Making a new game
		GameFrame game = new GameFrame(traversers, items, Color.WHITE);
		game.setVisible(true);
	}
}
