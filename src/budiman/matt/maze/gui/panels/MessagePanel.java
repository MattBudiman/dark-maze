//  Description: A Panel that can hold a message.
package budiman.matt.maze.gui.panels;
import java.awt.Graphics;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

public class MessagePanel extends JPanel{
	protected String message;
	protected Color color;
	
	public MessagePanel(String text, Color color) {
		message = text;
		this.color = color;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 4000, 4500);
		g.setColor(color);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
		g.drawString(message, 100, 400);
	}
}
