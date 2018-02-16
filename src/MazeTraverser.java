//  Description: A class that represents any entity that can traverse through the maze.
import java.awt.Color;
import java.awt.Graphics;

public abstract class MazeTraverser {

	public static final int DEFAULT_DIAMETER = 50 * 3;

	protected int row, col, x, y, diameter;
	protected Color color;

	/**
	 * Constructs a new MazeTraverser
	 * @param row initial row of the MazeTraverser
	 * @param col initial column of the MazeTraverser
	 * @param x initial x-position of the MazeTraverser
	 * @param y initial y-position of the MazeTraverser
	 * @param color The color of the MazeTraverser
	 */
	public MazeTraverser(int row, int col, int x, int y, Color color) {
		this.row = row;
		this.col = col;
		this.x = x;
		this.y = y;
		this.color = color;
		diameter = DEFAULT_DIAMETER;
	}

	/**
	 * Moves the MazeTraverser up 2 pixels
	 */
	public void up() {
		y = y - 2;
	}

	/**
	 * Moves the MazeTraverser down 2 pixels
	 */
	public void down() {
		y = y + 2;
	}

	/**
	 * Moves the MazeTraverser left 2 pixels
	 */
	public void left() {
		x = x - 2;
	}

	/**
	 * Moves the MazeTraverser right 2 pixels
	 */
	public void right() {
		x = x + 2;
	}

	/**
	 * Sets the x-position of the MazeTraverser
	 * @param x the x-position
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the y-position of the MazeTraverser
	 * @param y the y-position
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Sets the row of the MazeTraverser
	 * @param r the row
	 */
	public void setRow(int r) {
		row = r;
	}

	/**
	 * Sets the column of the MazeTraverser
	 * @param c the column
	 */
	public void setCol(int c) {
		col = c;
	}

	/**
	 * Gets the row of the MazeTraverser
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Gets the column of the MazeTraverser
	 * @return the column
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Gets the x-position of the MazeTraverser
	 * @return the x-position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y-position of the MazeTraverser
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Draws the MazeTraverser as a solid circle.
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x - diameter / 2, y - diameter / 2, diameter, diameter); // Draws the circle at the center of the square
	}
}
