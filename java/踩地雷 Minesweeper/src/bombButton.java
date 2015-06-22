

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * @comment: 炸彈按鈕
 * @author Han-Hong Wang
 * @msn: phodra@hotmail.com
 * @date 2007/5/12
 */
public class bombButton extends JButton implements MouseListener {
		int bombState, x, y;
		boolean clicked = false;
		public bombButton(int _x, int _y) {
			x = _x;
			y = _y;
			this.setBackground(new Color(236, 233, 216));
			this.addMouseListener(this);
			this.setFocusable(false);
		}
		
		// 設置炸彈狀態
		public void setBombState(int _bombState) {
			bombState = _bombState;
		}
		
		// 取得炸彈狀態
		public int getBombState() {
			return bombState;
		}
		
		public boolean getClicked() {
			return clicked;
		}
		
		// 設成已按下按鍵
		public void Clicked(boolean state) {
			clicked = state;
			if (state) {
				this.setBorder(BorderFactory.createLoweredBevelBorder());
			
				if (this.getBombState()!=0) {
					URL imageURL = this.getClass().getResource("/images/"+this.getBombState()+".gif");
					this.setIcon(new ImageIcon(imageURL));
				}
			} else {
				this.setBorder(BorderFactory.createRaisedBevelBorder());
				this.setIcon(null);
			}
		}
		
		public void actionPerformed(ActionEvent e) {
			
			

		}
		
		public void mousePressed(MouseEvent e) {
			switch (e.getButton()) {
				// 按下滑鼠左鍵
				case MouseEvent.BUTTON1:
					// 擴展區域
					Minesweeper.Spread(x,y);
					
					// 如果踩到炸彈, 把底色改成紅的
					if (this.getBombState()==9 && Minesweeper.GameOver==false) {
						Minesweeper.GameOver = true;
						Minesweeper.showAllBombs();
						this.setBackground(Color.red);
					}
					break;
				// 按下滑鼠右鍵
				case MouseEvent.BUTTON3:
					System.err.println("right");break;
			}
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}