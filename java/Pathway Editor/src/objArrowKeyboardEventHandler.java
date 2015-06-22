
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class objArrowKeyboardEventHandler implements KeyListener {

	private objArrow thisArrow;
	
	public objArrowKeyboardEventHandler(objArrow _thisArrow) {
		thisArrow = _thisArrow;
	}

	public void keyPressed(KeyEvent e) {
		// 按下ESC or DELETE 則刪除未連結的線
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_DELETE) {
			DrawPanel getPanel = (DrawPanel)thisArrow.getParent();
			getPanel.arrow = null;
			thisArrow.delete();
			getPanel.repaint();
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
