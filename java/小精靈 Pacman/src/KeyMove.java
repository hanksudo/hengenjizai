// Allows you to move an image using the keyboard
// Written by WolfCoder 1-07-2005
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JPanel;

public class KeyMove extends JApplet {
	public class DemoPanel extends JPanel {
		private Image testImage; // Image used for testing (16*16 pixels)
		private int playerX = 30;
		private int playerY = 30; // The player's current position in the applet
		boolean key_right, key_left, key_down, key_up; // Input booleans

		public void gameInit() {
			URL urlForImage = getClass().getResource("./images/c.gif");
			ImageIcon usFlag = new ImageIcon(urlForImage);
			testImage = usFlag.getImage();
			// testImage = getImage(urlForImage); // Load the test image
			// Image note:
			// The transparent color in the GIF file is the transparent color of
			// the GIF in the Applet
			setFocusable(true); // IMPORTANT IMPORTANT! This allows the
			// KeyListener to work!!!
			GameInput game_input = new GameInput(); // Make a new video game
			// KeyListener
			addKeyListener(game_input); // Add it to the JPanel
		}

		public void paintComponent(Graphics page) {
			super.paintComponent(page);
			setBackground(Color.white); // Use a black background
			page.drawImage(testImage, playerX, playerY, this); // Draw the image
			// at player's
			// position
			if (key_down) // Move the player down
				playerY++;
			if (key_up) // Move the player up
				playerY--;
			if (key_right) // Move the player to the right
				playerX++;
			if (key_left) // Move the player to the left
				playerX--;
			for (int index = 0; index < 10000000; index++) {
			} // Slow the Applet down to comfortable speeds
			repaint(); // next frame...
		}

		private class GameInput implements KeyListener {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				// Check using getKeyCode for all four directional keys!
				if (e.getKeyCode() == e.VK_DOWN) // If down is released...
					key_down = false;
				if (e.getKeyCode() == e.VK_UP) // If up is released...
					key_up = false;
				// Ect...
				if (e.getKeyCode() == e.VK_RIGHT)
					key_right = false;
				if (e.getKeyCode() == e.VK_LEFT)
					key_left = false;
			}

			public void keyPressed(KeyEvent e) {
				// Check using getKeyCode for all four directional keys!
				if (e.getKeyCode() == e.VK_DOWN) // If down is pressed...
					key_down = true;
				if (e.getKeyCode() == e.VK_UP) // If up is pressed...
					key_up = true;
				// Ect...
				if (e.getKeyCode() == e.VK_RIGHT)
					key_right = true;
				if (e.getKeyCode() == e.VK_LEFT)
					key_left = true;
			}
		}
	}

	public void init() {
		setSize(256, 256);
		DemoPanel demopanel = new DemoPanel(); // Make a new JPanel for the
												// video demo

		demopanel.gameInit(); // Call the game initialization function before
								// adding the JPanel

		getContentPane().add(demopanel); // Add the JPanel
	}
}