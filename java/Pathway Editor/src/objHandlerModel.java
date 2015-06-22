
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.MouseInputAdapter;


/**
 * @comment: 處理物件上的滑鼠事件
 * @author Han-Hong Wang
 * @date 2007/5/7
 */

public class objHandlerModel extends MouseInputAdapter implements Constants {
	DrawPanel cp;
	objModel myObject = null;
	Point dragInit = new Point();
	
	boolean isSelecting = false;
	boolean isDragging = false;
	
	public objHandlerModel(DrawPanel contentPane, objModel obj) {
		cp = contentPane;
		myObject = obj; 
	}
	
	public JPopupMenu getPopup(MouseEvent e) {
		JPopupMenu popup = new JPopupMenu();
		JMenuItem deleteItem = new JMenuItem(new deleteAction(myObject));
		deleteItem.setText("Delete");
		popup.add(deleteItem);
		return popup;
	}

	public void mousePressed(MouseEvent mev) {
		if (mev.getButton()==MouseEvent.BUTTON1) {
			switch (mainFrame.getMode()) {
				// 選取模式
				case SELECT:
					if (!myObject.isSelected()) {
						if (!mev.isShiftDown()) {
							((DrawPanel)cp).getSelectionObject().clearSelection(); // 清除所有選取
						}
						myObject.select(true);
						isSelecting = true;
					}
					dragInit = mev.getPoint();
					break;
			}
		}
	}
	
	public void mouseReleased(MouseEvent mev) {
		if (mev.getButton()==MouseEvent.BUTTON1) {
			switch (mainFrame.getMode()) {
				case SELECT:
					if (isDragging) {
						isDragging = false;
					} else {
						if (!isSelecting) {
							if (mev.isShiftDown()) {
								myObject.select(false);
							} else {
								((DrawPanel)cp).getSelectionObject().clearSelection();
								myObject.select(true);
							}
						}
					}
					break;
			}
			
			isSelecting = false;
		}
	}

	public void mouseDragged(MouseEvent mev) {
		if (mainFrame.getMode()==SELECT) {
			
			// 物件對齊格線
	  		if (!isDragging && ((DrawPanel)cp).getSelectionObject().getSelectionCount()==1) {
	  			if (myObject instanceof objDrawModel) {
	  				((objDrawModel)myObject).setCentre(Grid.getModifiedX(((objDrawModel)myObject).getCentre().getX()), Grid.getModifiedY(((objDrawModel)myObject).getCentre().getY()));
	  			}
	  		}
			
			isDragging = true;
			
			// 計算拖曳座標
			int transX = Grid.getModifiedX(mev.getX() - dragInit.getX());
			int transY = Grid.getModifiedY(mev.getY() - dragInit.getY());
			((DrawPanel)cp).getSelectionObject().transSelection(transX, transY);
		}
	}

	// PopMenu的Action
	class deleteAction extends AbstractAction {
		objModel selectObj;
		public deleteAction(objModel _selectObj) {
			selectObj = _selectObj;
		}
		public void actionPerformed(ActionEvent e) {
			selectObj.delete();
		}
		
	}
}
