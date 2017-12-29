//  Description: The Player class represents the player that traversers throughout the maze.
package budiman.matt.maze.traversers;
import java.awt.Color;

public class Player extends MazeTraverser {
	
	public static final int DEFAULT_LIVES = 3;
	public static final int DEFAULT_ENERGY = 5;
	
	private int lives; // Total number of lives the player has.
	private int energy; // Total energy the player has
	private boolean flashlightOn; // True if the light is on, off otherwise
	private boolean hasWon; // True if the player has won the game.

	/**
	 * Constructs a new Player object
	 * @param row the row of the player
	 * @param col the column of the player
	 * @param x the x-position of the player
	 * @param y the y-position of the player
	 * @param color the color of the player
	 */
	public Player(int row, int col, int x, int y, Color color) {
		super(row, col, x, y, color);
		lives = DEFAULT_LIVES;
		energy = DEFAULT_ENERGY;
		flashlightOn = false;
		hasWon = false;
	}
	
	/**
	 * Sets light on or off
	 * @param on true to set the light on, false otherwise
	 */
	public void setLight(boolean on) {
		flashlightOn = on;
	}
	
	/**
	 * Sets the total lives of the player to a specified value
	 * @param value the new value to replace the current total lives
	 */
	public void setLives(int value) {
		lives = value;
	}
	
	/**
	 * Gets the total lives of the player
	 * @return the total lives
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Sets the total energy of the player to a specified value
	 * @param value the new value to replace the current total energy
	 */
	public void setEnergy(int value) {
		energy = value;
	}
	
	/**
	 * Gets the total energy of the player.
	 * @return the total energy
	 */
	public int getEnergy() {
		return energy;
	}
	
	/**
	 * Gets whether whether the light is on or not.
	 * @return true if the light is on, false otherwise
	 */
	public boolean lightIsOn() {
		return flashlightOn;
	}
	
	/**
	 * Makes the player win.
	 */
	public void win() {
		hasWon = true;
	}
	
	/**
	 * Gets whether the player has won or not.
	 * @return true if the player has won, false otherwise.
	 */
	public boolean hasWon() {
		return hasWon;
	}
}
