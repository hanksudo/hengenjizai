
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class objArrowKeyboardEventHandler implements KeyListener {

	private objArrow thisArrow;
	
	public objArrowKeyboardEventHandler(objArrow _thisArrow) {
		thisArrow = _thisArrow;
	}

	public void keyPressed(KeyEvent e) {
		// ���UESC or DELETE �h�R�����s�����u
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
