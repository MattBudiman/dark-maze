//  Description: A Panel that contains a "YOU WIN" message.
package budiman.matt.maze.gui.panels;

import java.awt.Color;

public class VictoryPanel extends MessagePanel {
	public static final String DEFAULT_TEXT = "YOU WIN";
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	public VictoryPanel() {
		super(DEFAULT_TEXT, DEFAULT_COLOR);
	}
}
