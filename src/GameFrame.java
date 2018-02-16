//  Description: The GameFrame is the frame in which the game will be displayed and played.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class GameFrame extends JFrame {

	public static final int WIDTH = 400 * 3;
	public static final int HEIGHT = 450 * 3;

	private JPanel everything; // Contains the GamePanel and ControllerPanel
	private GamePanel game; // The GamePanel
	private ControllerPanel control; // The ControllerPanel for controlling the player
	private Player p; // A pointer to the player in the maze

	public GameFrame(String[][] traversers, String[][] items, Color playerColor) {
		setSize(WIDTH, HEIGHT);
		setTitle("Dark Maze");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		game = new GamePanel(traversers, items, playerColor, this);
		p = game.getPlayer();
		control = new ControllerPanel();
		everything = new JPanel();
		everything.setLayout(new BorderLayout());
		everything.add(game, BorderLayout.CENTER);
		everything.add(control, BorderLayout.SOUTH);
		add(everything);
	}

	/**
	 * Displays a "GAME OVER" message on the frame.
	 */
	public void gameOver() {
		remove(everything);
		repaint();
		add(new GameOverPanel());
		setVisible(true);
		repaint();
	}

	/**
	 * Displays a "YOU WIN" message on the frame.
	 */
	public void victory() {
		remove(everything);
		repaint();
		add(new VictoryPanel());
		setVisible(true);
		repaint();
	}

	/**
	 * Updates the lives label on the controller panel
	 */
	public void refreshLivesLabel() {
		control.livesLabel.setText("LIVES: " + p.getLives());
	}

	/**
	 * Updates the energy label on the controller panel
	 */
	public void refreshEnergyLabel() {
		control.energyLabel.setText("ENERGY: " + p.getEnergy());
	}

	/**
	 * Sets the text of the light toggle button.
	 * @param text the new text to replace the old text on the button
	 */
	public void setToggleButtonText(String text) {
		control.toggleLight.setText(text);
	}

	/**
	 * ControllerPanel is a class that contains arrow buttons to control the player,
	 * a toggle button to control the light, and labels showing the number of lives and
	 * the amount of energy that the player has.
	 */
	private class ControllerPanel extends JPanel {
		private JButton up;
		private JButton down;
		private JButton left;
		private JButton right;

		private JButton toggleLight;

		private JLabel livesLabel, energyLabel, lightLabel;

		private JPanel leftPanel, rightPanel, movementPanel, statsPanel;

		public ControllerPanel() {
			this.setSize(400, 50);
			this.setLayout(new BorderLayout());
			leftPanel = new JPanel(new BorderLayout()); // The left side of the ControllerPanel
			rightPanel = new JPanel(new BorderLayout()); // The right side of the Controller Panel
			movementPanel = new JPanel(new GridLayout(2, 3)); // The panel that holds the arrow buttons
			statsPanel = new JPanel(new BorderLayout()); // The panel that holds the player statistics

			livesLabel = new JLabel("LIVES: " + p.getLives());
			energyLabel = new JLabel("ENERGY: " + p.getEnergy());
			lightLabel = new JLabel("LIGHT:"); // Label that identifies the light toggle button

			statsPanel.add(livesLabel, BorderLayout.NORTH);
			statsPanel.add(energyLabel, BorderLayout.SOUTH);

			up = new JButton("UP");
			down = new JButton("DOWN");
			left = new JButton("LEFT");
			right = new JButton("RIGHT");
			toggleLight = new JButton("TURN ON");

			up.addActionListener(new ControlListener());
			down.addActionListener(new ControlListener());
			left.addActionListener(new ControlListener());
			right.addActionListener(new ControlListener());
			toggleLight.addActionListener(new ControlListener());

			leftPanel.add(lightLabel, BorderLayout.CENTER);
			leftPanel.add(toggleLight, BorderLayout.SOUTH);
			movementPanel.add(new JPanel());
			movementPanel.add(up);
			movementPanel.add(new JPanel());
			movementPanel.add(left);
			movementPanel.add(down);
			movementPanel.add(right);

			rightPanel.add(movementPanel);
			add(leftPanel, BorderLayout.WEST);
			add(statsPanel, BorderLayout.NORTH);
			add(rightPanel, BorderLayout.EAST);
			System.out.println(this.getSize());
		}

		/**
		 * Define the behavior of the arrow and light toggle buttons. The actionPerformed
		 * method calls the corresponding method of the GamePanel class.
		 */
		private class ControlListener implements ActionListener {

			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == up) {
					game.up();
				}
				if (event.getSource() == down) {
					game.down();
				}
				if (event.getSource() == left) {
					game.left();
				}
				if (event.getSource() == right) {
					game.right();
				}
				if (event.getSource() == toggleLight) {
					game.toggleLight();
				}
			}
		}
	}
}
