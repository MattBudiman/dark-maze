//  Description: A class that represents an item with which the player can interact.

import java.awt.Color;
import java.awt.Graphics;

public abstract class Item {

	protected int row, col, x, y;
	protected Color color;

	/**
	 * Defines how subclasses of Item are constructed.
	 * @param row the row of the square in which the item is located
	 * @param col the column of the square in which the item is located
	 * @param x the x-position of the item (Center of Square)
	 * @param y the y-position of the item (Center of Square)
	 * @param color the color of the item
	 */
	public Item(int row, int col, int x, int y, Color color) {
		this.row = row;
		this.col = col;
		this.x = x;
		this.y = y;
		this.color = color;
	}

	// Causes the item to interact with the player in some way.
	public abstract void interactWith(Player p);

	//Draws the item on the maze.
	public abstract void draw(Graphics g);
}
