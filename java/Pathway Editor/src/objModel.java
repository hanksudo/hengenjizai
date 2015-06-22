


import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

/**
 * @comment: ø�Ϫ���Ҳ�
 * @author Han-Hong Wang
 * @date 2007/5/7
 */

public class objModel extends JComponent implements Constants {
	protected final static int COMPONENT_DRAW_OFFSET = 5;
	protected objLabel Label;
	protected boolean selected = false;
	protected Rectangle rect = new Rectangle();
	
	public objModel() {}
	
	// �]�w����Τ����
	public void select(boolean state) {
		selected = state;
		repaint();
	}
	
	// �O�_�w���
	public boolean isSelected() {
		return selected;
	}
	
	// �s�W����
	public void addLabel(String text) {
		Label.setLabel(text);
		getParent().add(Label);
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void delete() {
		((DrawPanel)this.getParent()).removeObject(this);
		removeFromContainer();
		removeAll();
	}
	
	// �qDrawPanel�������� �P ����Label
	public void removeFromContainer() {
		Container c = getParent();
		if (c!=null) {
			c.remove(this);
			c.repaint();
		}
	}
}
