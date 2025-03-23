package snakegame;

import javax.swing.*;

public class SnakeGame extends JFrame {
	
	SnakeGame() {
		// should be the first statement in a constructor.
		super("Snake Game");
		// JPanel is a component in Swing, which is like a div in css
		add(new Board());
		// to display the window and refresh continuously
		pack();
		
		// to display the window
		setVisible(true);
		setResizable(false);
		// change default location from left-top origin
		setLocationRelativeTo(null);
		
	}

	public static void main(String[] args) {
		new SnakeGame();

	}

}
