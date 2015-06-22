import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;



/**
 * @comment: �B�z���qø�Ϫ���W���ƹ��ƥ�
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
		// �����ƹ��k��
		if (mev.getButton()==MouseEvent.BUTTON3) {
			if (cp.arrow != null) {
				cp.clearArrow();
			} else {
				((DrawPanel)cp).getSelectionObject().clearSelection(); // �M���Ҧ����
				myObject.select(true);
				JPopupMenu popMenu = getPopup(mev);
				if (popMenu != null) {popMenu.show(myObject, mev.getX(), mev.getY());}
			}
		}
		

		if (mev.getButton()==MouseEvent.BUTTON1) {
			objDrawModel currentObject  = (objDrawModel)myObject;
			switch (mainFrame.getMode()) {		
				// �b�Y�Ҧ�
				case ARROW:
					if (cp.arrow == null) {
						objArrow newArrow = new objArrow(currentObject);
						cp.add(newArrow);
						currentObject.addConnectFrom(newArrow);
						cp.arrow = newArrow;
						
						// �[�J�b�Y����Event
						keyhandler = new objArrowKeyboardEventHandler(newArrow);
						newArrow.addKeyListener(keyhandler);
						newArrow.requestFocusInWindow();	// �n�DFocus
					} else {
						objArrow currentArrow = cp.arrow;
						// �p�G�ثe���U������D�b�Y�_�l����
						if (!myObject.getClass().getName().equals(currentArrow.getSource().getClass().getName())) {
							currentArrow.setTarget(currentObject);
							currentObject.addConnectTo(currentArrow);
							cp.remove(currentArrow); // ������Arrow �קK�s�W�⦸
//							CreateGui.getModel().addArc(createArc);
							cp.addNewObject(currentArrow);
  					
							// �b�Y�wø�s����, ������L�ƥ�
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