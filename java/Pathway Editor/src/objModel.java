


import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

/**
 * @comment: 繪圖物件模組
 * @author Han-Hong Wang
 * @date 2007/5/7
 */

public class objModel extends JComponent implements Constants {
	protected final static int COMPONENT_DRAW_OFFSET = 5;
	protected objLabel Label;
	protected boolean selected = false;
	protected Rectangle rect = new Rectangle();
	
	public objModel() {}
	
	// 設定選取或不選取
	public void select(boolean state) {
		selected = state;
		repaint();
	}
	
	// 是否已選取
	public boolean isSelected() {
		return selected;
	}
	
	// 新增標籤
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
	
	// 從DrawPanel移除物件 與 標籤Label
	public void removeFromContainer() {
		Container c = getParent();
		if (c!=null) {
			c.remove(this);
			c.repaint();
		}
	}
}
