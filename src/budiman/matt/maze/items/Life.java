//  Description: An item that grants the player an extra life.
package budiman.matt.maze.items;

import java.awt.Graphics;
import java.awt.Color;

import budiman.matt.maze.traversers.MazeTraverser;
import budiman.matt.maze.traversers.Player;

public class Life extends Item {
	
	/**
	 * Constructs a new Life item.
	 * @param row the row of the square in which the Life item is located
	 * @param col the column of the square in which the Life item is located
	 * @param x the x-position of the Life (Center of square)
	 * @param y the y-position of the Life (Center of square)
	 */
	public Life(int row, int col, int x, int y) {
		super(row, col, x, y, Color.BLUE);
	}
	
	/**
	 * Adds one to the player's lives total.
	 */
	public void interactWith(Player p) {
		p.setLives(p.getLives() + 1);
	}
	
	/**
	 * Draws the life as a blue plus sign.
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x - 50, y - 15, 100, 30);
		g.fillRect(x - 15, y - 50, 30, 100);
	}
}
