//  Description: An item that represents the end goal of the maze. The player wins if he or
//               she reaches the square in which this item is contained.
package budiman.matt.maze.items;

import java.awt.Color;
import java.awt.Graphics;

import budiman.matt.maze.traversers.MazeTraverser;
import budiman.matt.maze.traversers.Player;

public class Goal extends Item {

	public static final int DEFAULT_DIAMETER = 50 * 3;
	
	private int innerDiameter;
	private int outerDiameter;
	
	/**
	 * Constructs a new goal
	 * @param row the row of the square in which the goal is located
	 * @param col the column of the square in which the goal is located
	 * @param x the x-position of the goal (Center of square)
	 * @param y the y-position of the goal (Center of square)
	 */
	public Goal(int row, int col, int x, int y) {
		super(row, col, x, y, Color.GREEN);
		innerDiameter = DEFAULT_DIAMETER;
		outerDiameter = DEFAULT_DIAMETER + 50;
	}
	
	/**
	 * Makes the player win.
	 */
	public void interactWith(Player p) {
		p.win();
	}
	
	/**
	 * Draws the goal as a green ring.
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x - outerDiameter / 2, y - outerDiameter / 2, 50 * 4, 50 * 4);
		g.setColor(Color.BLACK);
		g.fillOval(x - innerDiameter / 2, y - innerDiameter / 2, 50 * 3, 50 * 3);
	}

}
