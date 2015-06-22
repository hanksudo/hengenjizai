import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;



/**
 * @comment: 處理普通繪圖物件上的滑鼠事件
 * @author Han-Hong Wang
 * @date 2007/5/7
 */

public class objDrawHandler extends objHandlerModel implements Constants {
	objArrowKeyboardEventHandler keyhandler = null;
	
	public objDrawHandler(DrawPanel contentPane, objDrawModel obj) {
		super(contentPane, obj);
	}
	
	public JPopupMenu getPopup(MouseEvent e) {
		JPopupMenu popup = super.getPopup(e);

		JMenuItem editItem = new JMenuItem();
		JMenuItem cutItem = new JMenuItem();
		JMenuItem copyItem = new JMenuItem();
		JMenuItem searchItem = new JMenuItem();
		JMenuItem detailItem = new JMenuItem();
		
		editItem.setText("Edit Label");
		cutItem.setText("Cut");
		copyItem.setText("Copy");
		searchItem.setText("Search");
		detailItem.setText("Details");
		
		popup.add(searchItem);
		popup.add(editItem);
		popup.add(cutItem);
		popup.add(copyItem);
		popup.add(detailItem);

		return popup;
	}
	
	public void mousePressed(MouseEvent mev) {
		super.mousePressed(mev);
		// 偵測滑鼠右鍵
		if (mev.getButton()==MouseEvent.BUTTON3) {
			if (cp.arrow != null) {
				cp.clearArrow();
			} else {
				((DrawPanel)cp).getSelectionObject().clearSelection(); // 清除所有選取
				myObject.select(true);
				JPopupMenu popMenu = getPopup(mev);
				if (popMenu != null) {popMenu.show(myObject, mev.getX(), mev.getY());}
			}
		}
		

		if (mev.getButton()==MouseEvent.BUTTON1) {
			objDrawModel currentObject  = (objDrawModel)myObject;
			switch (mainFrame.getMode()) {		
				// 箭頭模式
				case ARROW:
					if (cp.arrow == null) {
						objArrow newArrow = new objArrow(currentObject);
						cp.add(newArrow);
						currentObject.addConnectFrom(newArrow);
						cp.arrow = newArrow;
						
						// 加入箭頭按鍵Event
						keyhandler = new objArrowKeyboardEventHandler(newArrow);
						newArrow.addKeyListener(keyhandler);
						newArrow.requestFocusInWindow();	// 要求Focus
					} else {
						objArrow currentArrow = cp.arrow;
						// 如果目前按下的物件非箭頭起始物件
						if (!myObject.getClass().getName().equals(currentArrow.getSource().getClass().getName())) {
							currentArrow.setTarget(currentObject);
							currentObject.addConnectTo(currentArrow);
							cp.remove(currentArrow); // 先移除Arrow 避免新增兩次
//							CreateGui.getModel().addArc(createArc);
							cp.addNewObject(currentArrow);
  					
							// 箭頭已繪製完畢, 移除鍵盤事件
							currentArrow.removeKeyListener(keyhandler);
							keyhandler = null;
							cp.arrow = null;
//							CreateGui.getView().setShiftDown(false);
						}
					}
					break;
			}
		}
	
	}
}