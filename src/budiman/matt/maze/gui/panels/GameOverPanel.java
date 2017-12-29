//  Description: A Panel that contains a "GAME OVER" message.
package budiman.matt.maze.gui.panels;

import java.awt.Color;

public class GameOverPanel extends MessagePanel{
	public static final String DEFAULT_TEXT = "GAME OVER";
	public static final Color DEFAULT_COLOR = Color.WHITE; // Color of text
	
	public GameOverPanel() {
		super(DEFAULT_TEXT, DEFAULT_COLOR);
	}
}
