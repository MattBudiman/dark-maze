//  Description: The JPanel in which the actual maze will be displayed.

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Maze maze; // Pointer to the actual maze
	private Timer upTimer, downTimer, leftTimer, rightTimer, toggleTimer; // Timers that correspond to each of the control actions
	private Player p; // Pointer to the player in the maze
	private GameFrame frame; // Pointer to the frame in which this GamePanel is instantiated

	private int toX, toY;

	private static final int DEFAULT_INTERVAL = 0; // Determines animation speed.
	private static final int LIGHT_UNIT = 1000; // 1000 milliseconds, or 1 seconds
	// The energy will fall at a rate of one unit per second.

	public GamePanel(String[][] traversers, String[][] items, Color playerColor, GameFrame frame) {
		maze = new Maze(traversers, items, playerColor, frame);
		this.setSize(400 * 3, 400 * 3);
		upTimer = new Timer(DEFAULT_INTERVAL, new TimerListener());
		downTimer = new Timer(DEFAULT_INTERVAL, new TimerListener());
		leftTimer = new Timer(DEFAULT_INTERVAL, new TimerListener());
		rightTimer = new Timer(DEFAULT_INTERVAL, new TimerListener());
		toggleTimer = new Timer(LIGHT_UNIT, new TimerListener());
		p = maze.getPlayer();
		this.frame = frame;
	}

	/**
	 * Returns a reference to the player in the maze
	 * @return a reference to the player in the maze
	 */
	public Player getPlayer() {
		return p;
	}

	/**
	 * Paints the GamePanel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		maze.paint(g);
	}

	/**
	 * Gets a reference to the maze
	 * @return a reference to the maze
	 */
	public Maze getMaze() {
		return maze;
	}

	/**
	 * Animates the player moving up one square.
	 */
	public void up() {
		if (!upTimer.isRunning() && !downTimer.isRunning() && !leftTimer.isRunning() && !rightTimer.isRunning()) {
			if (p.getRow() > 0) {
				toY = p.getY() - maze.getSquareHeight();
				upTimer.start();
			}
		}

	}

	/**
	 * Animates the player moving down one square.
	 */
	public void down() {
		if (!upTimer.isRunning() && !downTimer.isRunning() && !leftTimer.isRunning() && !rightTimer.isRunning()) {
			if (p.getRow() < maze.ROWS - 1) {
				toY = p.getY() + maze.getSquareHeight();
				downTimer.start();
			}
		}

	}

	/**
	 * Animates the player moving left one square.
	 */
	public void left() {
		if (!upTimer.isRunning() && !downTimer.isRunning() && !leftTimer.isRunning() && !rightTimer.isRunning()) {
			if (p.getCol() > 0) {
				toX = p.getX() - maze.getSquareWidth();
				leftTimer.start();
			}
		}

	}

	/**
	 * Animates the player moving right one square.
	 */
	public void right() {
		if (!upTimer.isRunning() && !downTimer.isRunning() && !leftTimer.isRunning() && !rightTimer.isRunning()) {
			if (p.getCol() < maze.COLUMNS - 1) {
				toX = p.getX() + maze.getSquareWidth();
				rightTimer.start();
			}
		}

	}

	/**
	 * Switches the light on or off. Won't do anything if the player's energy is zero.
	 */
	public void toggleLight() {
		if (p.lightIsOn()) {
			frame.setToggleButtonText("TURN ON");
			p.setLight(false);
			repaint();
			toggleTimer.stop();
		}
		else if (p.getEnergy() > 0){
			frame.setToggleButtonText("TURN OFF");
			p.setLight(true);
			repaint();
			toggleTimer.start();
		}
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == upTimer) {
				p.up();
				if (p.getY() <= toY) {
					upTimer.stop();
					maze.setPlayer(p.getRow() - 1, p.getCol());
				}
			}
			if (event.getSource() == downTimer) {
				p.down();
				if (p.getY() >= toY) {
					downTimer.stop();
					maze.setPlayer(p.getRow() + 1, p.getCol());
				}
			}
			if (event.getSource() == leftTimer) {
				p.left();
				if (p.getX() <= toX) {
					leftTimer.stop();
					maze.setPlayer(p.getRow(), p.getCol() - 1);
				}
			}
			if (event.getSource() == rightTimer) {
				p.right();
				if (p.getX() >= toX) {
					rightTimer.stop();
					maze.setPlayer(p.getRow(), p.getCol() + 1);
				}
			}
			if (event.getSource() == toggleTimer) {
				p.setEnergy(p.getEnergy() - 1);
				frame.refreshEnergyLabel();
				if (p.getEnergy() == 0) {
					toggleTimer.stop();
					frame.setToggleButtonText("TURN ON");
					p.setLight(false);
				}
			}
			repaint();
		}

	}
}
