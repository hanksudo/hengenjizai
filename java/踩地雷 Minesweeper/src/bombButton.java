

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
 * @comment: ���u���s
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
		
		// �]�m���u���A
		public void setBombState(int _bombState) {
			bombState = _bombState;
		}
		
		// ���o���u���A
		public int getBombState() {
			return bombState;
		}
		
		public boolean getClicked() {
			return clicked;
		}
		
		// �]���w���U����
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
				// ���U�ƹ�����
				case MouseEvent.BUTTON1:
					// �X�i�ϰ�
					Minesweeper.Spread(x,y);
					
					// �p�G��쬵�u, �⩳��令����
					if (this.getBombState()==9 && Minesweeper.GameOver==false) {
						Minesweeper.GameOver = true;
						Minesweeper.showAllBombs();
						this.setBackground(Color.red);
					}
					break;
				// ���U�ƹ��k��
				case MouseEvent.BUTTON3:
					System.err.println("right");break;
			}
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}