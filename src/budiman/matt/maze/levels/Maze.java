//  Description: A class that represents the actual maze and its contents.
package budiman.matt.maze.levels;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import budiman.matt.maze.traversers.*;
import budiman.matt.maze.gui.frames.GameFrame;
import budiman.matt.maze.items.*;

public class Maze {

	private Square[][] maze; // The array in which the maze contents are stored
	private Player player; // A pointer to the player.
	private ArrayList<MazeTraverser> mazeTraversers; // An ArrayList of all of the MazeTraversers

	public static final int ROWS = 4;
	public static final int COLUMNS = 4;

	private int x, y; // Position of the maze
	private int rStart, cStart; // Starting row and column of Player
	private GameFrame frame; // pointer to the GameFrame

	/**
	 * Constructs a new Maze given string blueprints and the color of the player.
	 * @param traversers String blueprint of the all of the positions of each traverser
	 * @param items String blueprint of all of the items
	 * @param playerColor the color of the player
	 */
	public Maze(String[][] traversers, String[][] items, Color playerColor, GameFrame frame) {
		mazeTraversers = new ArrayList<MazeTraverser>();
		maze = new Square[ROWS][COLUMNS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLUMNS; c++) {
				Square s = new Square(c * Square.WIDTH, r * Square.HEIGHT);
				maze[r][c] = s;
				if (traversers[r][c] == "P") {
					player = new Player(r, c, s.tX, s.tY, playerColor);
					s.setTraverser(player);
					mazeTraversers.add(player);
					rStart = r;
					cStart = c;
				}
				if (items[r][c] == "L") {
					s.setItem(new Life(r, c, s.tX, s.tY));
				}
				if (items[r][c] == "B") {
					s.setItem(new EnergyBoost(r, c, s.tX, s.tY));
				}
				if (items[r][c] == "H") {
					s.setItem(new Hazard(r, c, s.tX, s.tY));
				}
				if (items[r][c] == "G") {
					s.setItem(new Goal(r, c, s.tX, s.tY));
				}
			}
		}
		this.frame = frame;
	}

	/**
	 * Draws the maze.
	 * @param g
	 */
	public void paint(Graphics g) {
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLUMNS; c++) {
				maze[r][c].draw(g);
			}
		}
	}

	/**
	 * Gets a reference to the player.
	 * @return the reference
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Places the player in the maze at maze[r][c]
	 * @param r the row
	 * @param c the column
	 */
	public void setPlayer(int r, int c) {
		Square to = maze[r][c];
		maze[player.getRow()][player.getCol()].setTraverser(null);
		to.setTraverser(player);
		player.setRow(r);
		player.setCol(c);
		if (to.item != null) {
			to.item.interactWith(player); // The item interacts with the player
			if (to.item instanceof Hazard) {
				if (player.getLives() <= 0) {
					frame.gameOver(); // Game Over
				}
				else {
					setPlayer(rStart, cStart); // Restart the player at the beginning
					frame.refreshLivesLabel(); // Update lives label to reflect the lost life
				}

			}
			if (to.item instanceof Goal) {
				frame.victory(); // Display the victory message
			}
			if (to.item instanceof Life) {
				to.item = null; // Remove the item
				frame.refreshLivesLabel(); // Update lives label to reflect the gained life.
			}
			if (to.item instanceof EnergyBoost) {
				to.item = null; // Remove the item
				frame.refreshEnergyLabel(); // Update the energy label to reflect the energy boost.
			}
		}
	}

	/**
	 * Gets a reference to the maze.
	 * @return the reference
	 */
	public Square[][] getMaze() {
		return maze;
	}

	/**
	 * Gets the width of each square in the maze.
	 * @return the square width
	 */
	public int getSquareWidth() {
		return Square.WIDTH;
	}

	/**
	 * Gest the height of each squar ein the maze.
	 * @return the square height
	 */
	public int getSquareHeight() {
		return Square.HEIGHT;
	}

	/**
	 * Gets the x-position of the center of a specified square
	 * @param r the row of the specified square
	 * @param c the column of the specified square
	 * @return the x-position of the center
	 */
	public int getTX(int r, int c) {
		return maze[r][c].tX;
	}

	/**
	 * Gets the y-position of the center of a specified square
	 * @param r the row of the specified square
	 * @param c the column of the specified square
	 * @return the y-position of the center
	 */
	public int getTY(int r, int c) {
		return maze[r][c].tY;
	}

	/**
	 * A class that defines a square in the maze.
	 */
	private class Square {
		public static final int WIDTH = 100 * 3;
		public static final int HEIGHT = 100 * 3;
		public final Color DEFAULT_COLOR = Color.BLACK; // Color of the square

		private int x, y, tX, tY;
		private Color color;
		private MazeTraverser traverser; // Variable to hold a traverser at any given moment
		private Item item; // Variable to hold an item

		/**
		 * Constructs a new square
		 * @param x the x-position of the top left-hand corner of the square
		 * @param y the y-position of the top left-hand corner of the square
		 */
		public Square(int x, int y) {
			this.x = x;
			this.y = y;
			tX = this.x + WIDTH / 2; // x-position of the center of the square
			tY = this.y + HEIGHT / 2; // y-position of the center of the square
			color = DEFAULT_COLOR;
		}

		/**
		 * Places a traverser in the square
		 * @param t the traverser to place
		 */
		public void setTraverser(MazeTraverser t) {
			traverser = t;
			if (t != null) {
				t.setX(tX);
				t.setY(tY);
			}
		}

		/**
		 * Places an item in the square
		 * @param i the item to place
		 */
		public void setItem(Item i) {
			item = i;
		}

		public void draw(Graphics g) {
			g.setColor(color);
			g.fillRect(x, y, WIDTH, HEIGHT);
			if (item != null && player.lightIsOn()) {
				item.draw(g);
			}
			if (traverser != null)
				traverser.draw(g); // Draw the traverser
			for (int i = 0; i < mazeTraversers.size(); i++)
				mazeTraversers.get(i).draw(g); // Draw every single traverser in the maze
		}
	}
}
