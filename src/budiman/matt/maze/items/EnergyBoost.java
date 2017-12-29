//  Description: An EnergyBoost is an item that boosts the player's energy so that he or
//               she can operate the light that illuminates the dark maze.
package budiman.matt.maze.items;

import java.awt.Graphics;
import java.awt.Color;

import budiman.matt.maze.traversers.MazeTraverser;
import budiman.matt.maze.traversers.Player;

public class EnergyBoost extends Item {
	
	/**
	 * Constructs a new EnergyBoost item.
	 * @param row the row of the square in which the energy boost item is located
	 * @param col the column of the square in which the energy boost item is located
	 * @param x the x-position of the energy boost (Center of square)
	 * @param y the y-position of the energy boost (Center of square)
	 */
	public EnergyBoost(int row, int col, int x, int y) {
		super(row, col, x, y, Color.ORANGE);
	}
	
	/**
	 * Boosts player's energy by 1.
	 */
	public void interactWith(Player p) {
		p.setEnergy(p.getEnergy() + 1);
	}
	
	/**
	 * Draws the energy boost as an orange plus sign.
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x - 50, y - 15, 100, 30);
		g.fillRect(x - 15, y - 50, 30, 100);
	}
}
