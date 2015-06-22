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
		this.setLocationRelativeTo(null); // 視窗位置置中
		this.getContentPane().setLayout(new BorderLayout());	
		this.getContentPane().add(new GamePanel());
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Pacman();
	}

	public class GamePanel extends JPanel {
		private Image pmImage; // 小精靈圖片 (64*64 pixels)
		private int xCoordinate = 200; // 小精靈初始位置
		private int yCoordinate = 200;
		boolean key_right, key_left, key_down, key_up; // Input booleans

		URL urlForImage;
		ImageIcon usFlag;
		
		public GamePanel() {
			loadImage("C");
			this.setFocusable(true); // 取得焦點, 否則KeyListener會無作用
			addKeyListener(new GameInput()); // Add it to the JPanel
		}
		
		// 讀取圖片
		public void loadImage(String img) {
			urlForImage = getClass().getResource("./images/"+img+".gif");
			usFlag = new ImageIcon(urlForImage);
			pmImage = usFlag.getImage();
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.white); // 底色白色
			g.drawImage(pmImage, xCoordinate, yCoordinate, this); // 繪圖
			
			// 往下
			if (key_down) { loadImage("D"); yCoordinate++; }
			
			// 往上
			if (key_up) { loadImage("U"); yCoordinate--; }
			
			// 往右
			if (key_right) { loadImage("R"); xCoordinate++; }
				
			// 左
			if (key_left) { loadImage("L"); xCoordinate--; }
				
			// 延遲 - 小精靈跑的速度	
			for (int index = 0; index < 10000000; index++) {
			}
			
			repaint();
		}

		private class GameInput implements KeyListener {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				// 檢查方向鍵是否放開
				if (e.getKeyCode() == e.VK_DOWN) key_down = false;
				if (e.getKeyCode() == e.VK_UP) key_up = false;
				if (e.getKeyCode() == e.VK_RIGHT) key_right = false;
				if (e.getKeyCode() == e.VK_LEFT) key_left = false;
			}

			public void keyPressed(KeyEvent e) {
				// 檢查方向鍵是否按下
				if (e.getKeyCode() == e.VK_DOWN) key_down = true;
				if (e.getKeyCode() == e.VK_UP) key_up = true;
				if (e.getKeyCode() == e.VK_RIGHT) key_right = true;
				if (e.getKeyCode() == e.VK_LEFT) key_left = true;
			}
		}
	}
}