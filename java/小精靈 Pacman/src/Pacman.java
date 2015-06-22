import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pacman extends JFrame {
	public Pacman() {
		this.setTitle("Pacman");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null); // ������m�m��
		this.getContentPane().setLayout(new BorderLayout());	
		this.getContentPane().add(new GamePanel());
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Pacman();
	}

	public class GamePanel extends JPanel {
		private Image pmImage; // �p���F�Ϥ� (64*64 pixels)
		private int xCoordinate = 200; // �p���F��l��m
		private int yCoordinate = 200;
		boolean key_right, key_left, key_down, key_up; // Input booleans

		URL urlForImage;
		ImageIcon usFlag;
		
		public GamePanel() {
			loadImage("C");
			this.setFocusable(true); // ���o�J�I, �_�hKeyListener�|�L�@��
			addKeyListener(new GameInput()); // Add it to the JPanel
		}
		
		// Ū���Ϥ�
		public void loadImage(String img) {
			urlForImage = getClass().getResource("./images/"+img+".gif");
			usFlag = new ImageIcon(urlForImage);
			pmImage = usFlag.getImage();
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.white); // ����զ�
			g.drawImage(pmImage, xCoordinate, yCoordinate, this); // ø��
			
			// ���U
			if (key_down) { loadImage("D"); yCoordinate++; }
			
			// ���W
			if (key_up) { loadImage("U"); yCoordinate--; }
			
			// ���k
			if (key_right) { loadImage("R"); xCoordinate++; }
				
			// ��
			if (key_left) { loadImage("L"); xCoordinate--; }
				
			// ���� - �p���F�]���t��	
			for (int index = 0; index < 10000000; index++) {
			}
			
			repaint();
		}

		private class GameInput implements KeyListener {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				// �ˬd��V��O�_��}
				if (e.getKeyCode() == e.VK_DOWN) key_down = false;
				if (e.getKeyCode() == e.VK_UP) key_up = false;
				if (e.getKeyCode() == e.VK_RIGHT) key_right = false;
				if (e.getKeyCode() == e.VK_LEFT) key_left = false;
			}

			public void keyPressed(KeyEvent e) {
				// �ˬd��V��O�_���U
				if (e.getKeyCode() == e.VK_DOWN) key_down = true;
				if (e.getKeyCode() == e.VK_UP) key_up = true;
				if (e.getKeyCode() == e.VK_RIGHT) key_right = true;
				if (e.getKeyCode() == e.VK_LEFT) key_left = true;
			}
		}
	}
}