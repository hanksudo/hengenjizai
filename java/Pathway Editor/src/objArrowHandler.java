import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

/**
 * @comment: 處理箭頭事件
 * @author Han-Hong Wang
 * @MSN phodra@hotmail.com
 * @website http://whh.idv.tw
 * @date 2007/6/17
 */
public class objArrowHandler extends objHandlerModel implements Constants {

	public objArrowHandler(DrawPanel cp, objArrow obj) {
		super(cp, obj);
	}
	
	public JPopupMenu getPopup(MouseEvent e) {
		JPopupMenu popup = new JPopupMenu();
		return popup;
	}
	
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		// 按兩下時選取與此箭頭相連的元件
		if (e.getClickCount() == 2) {
			((objArrow)myObject).getSource().select(true);
			((objArrow)myObject).getTarget().select(true);
			isSelecting = true;
		}
	}

}
