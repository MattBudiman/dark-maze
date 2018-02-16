//  Description: An item that represents a hazard. A hazard takes away one life from the
//               player and causes the player to restart at the original starting location.

import java.awt.Color;
import java.awt.Graphics;

public class Hazard extends Item {

	public static final int DEFAULT_DIAMETER = 50 * 3;

	private int innerDiameter;
	private int outerDiameter;

	/**
	 * Constructs a new hazard.
	 * @param row the row of the square in which the hazard is located
	 * @param col the column of the square in which the hazard is located
	 * @param x the x-position of the hazard (Center of square)
	 * @param y the y-position of the hazard (Center of square)
	 */
	public Hazard(int row, int col, int x, int y) {
		super(row, col, x, y, Color.RED);
		innerDiameter = DEFAULT_DIAMETER;
		outerDiameter = DEFAULT_DIAMETER + 50;
	}

	/**
	 * Subtracts one life from the player.
	 */
	public void interactWith(Player p) {
		p.setLives(p.getLives() - 1);
	}

	/**
	 * Draws the hazard as a red ring.
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x - outerDiameter / 2, y - outerDiameter / 2, 50 * 4, 50 * 4);
		g.setColor(Color.BLACK);
		g.fillOval(x - innerDiameter / 2, y - innerDiameter / 2, 50 * 3, 50 * 3);
	}

}
