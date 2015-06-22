

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;


public class objSelection extends JComponent implements MouseListener, MouseMotionListener, Constants {
	private DrawPanel cp;
	private Point pointInit;
	private Rectangle selectRect = new Rectangle();

	boolean enabled = false;
	boolean isSelecting = false;
	
	public objSelection() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public objSelection(DrawPanel _drawing_panel) {
		this();	// 設置滑鼠Listener
		
		// 取得目前使用之DrawPanel
		cp = _drawing_panel;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		// 選取區的虛線
		Stroke stroke = new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[]{5f, 5f}, 0f); //選取區的虛線
        g2d.setStroke(stroke);
		g2d.setPaint(SELECT_COLOR); // 選取區填滿顏色
		g2d.fill(selectRect);
		g2d.setPaint(SELECT_COLOR_OUTLINE); // // 選取區框線顏色
		g2d.draw(selectRect);
	}
	
	// 開啟選取功能
	public void enableSelection() {
		if (!enabled) {
			enabled = true;
			cp.add(this);	// 將選取區加入DrawPanel
			updateBounds();	// 更新Selection 與 DrawPanel一樣大
		}
	}
	
	// 關閉選取功能
	public void disableSelection() {
		if (enabled) {
			cp.remove(this);	// 將選取區移除
			enabled = false;
		}		
	}
	
	// 取消選取所有物件
	public void clearSelection() {
		Component myObject[] = cp.getComponents();	

		for (int i=0; i<myObject.length; i++) {
			if (myObject[i] instanceof objModel) {
				((objModel)myObject[i]).select(false);
			}
		}
	}
	
	// 更新 Selection與 DrawPanel一樣大
	public void updateBounds() {
		if (enabled) {
			setBounds(0, 0, cp.getWidth(), cp.getHeight());
		}
	}
	
	// 拖曳時物件位置處理
	public void transSelection(int transX, int transY) {
		if (transX==0 && transY==0) return;
		
		Component myObject[] = cp.getComponents();
		Point p, topLeft=null;
		
		// 限制選取無法超過上與左
		for (int i=0; i<myObject.length; i++) {
			if (myObject[i] instanceof objModel) {
				if (((objModel)myObject[i]).isSelected()) {
					p = ((objModel)myObject[i]).getLocation();
					if (topLeft==null) {
						topLeft = p;
					} else {
						if (p.x<topLeft.x) {topLeft.x = p.x;}
						if (p.y<topLeft.y) {topLeft.y = p.y;}
					}
				}
			}
		}
		
		if (topLeft!=null) {
			topLeft.translate(transX, transY);
			if (topLeft.x<0) {transX -= topLeft.x;}
			if (topLeft.y<0) {transY -= topLeft.y;}
			if (transX==0 && transY==0) {return;}
		}
		
		// 移動物件
		for (int i=0; i<myObject.length; i++) {
	    	if (myObject[i] instanceof objDrawModel) { // 移動物件
	    		if (((objDrawModel)myObject[i]).isSelected()){
	    			((objDrawModel)myObject[i]).translate(transX,transY);
	    		}
	    	} else if (myObject[i] instanceof objArrow) {  // 線的位置跟著物件
	    		objArrow thisArrow = (objArrow)myObject[i];
	    		for (int j=0; j<thisArrow.getArrowPath().getEndIndex(); j++) {
	    			// 判斷是否為選取物件，避免其他物件拖移到不相關的
	    			if (thisArrow.getArrowPath().isPointSelected(j)) {
	    				thisArrow.getArrowPath().translatePoint(j, transX, transY);
	    			}
	    			thisArrow.updateArrowPosition();
	    		}
	    	}
	    }
		// 重新設定DrawPanel為最適大小
		cp.updatePreferredSize();
	}

	// 處理選取功能
	public void processSelection(MouseEvent evt) {
		if (!evt.isShiftDown()) {clearSelection();}
		
		Component myObject[] = cp.getComponents();
		for (int i=0; i<myObject.length; i++) {
			if (myObject[i] instanceof objModel) {
				// 選取在選取框中之物件
				if (selectRect.intersects(myObject[i].getBounds())) {
					((objModel)myObject[i]).select(true);
				}
			}
		}
	}
	
	// 統計目前選取之物件總數
	public int getSelectionCount() {
		Component myObject[] = cp.getComponents();
		int selectionCount = 0;
		
		for (int i=0; i<myObject.length; i++) {
			if ((myObject[i] instanceof objModel))
				if (((objModel)myObject[i]).isSelected()) {
					selectionCount++;
				}
		}					
		return selectionCount;
	}
	
	// 刪除選取的物件
	public void deleteSelection() {
		Component netObj[] = cp.getComponents();
		for (int i=0; i<netObj.length; i++)
		{
			if ((netObj[i] instanceof objModel) && (((objModel)netObj[i]).isSelected())) {
				((objModel)netObj[i]).delete();
			}
		}
		cp.updatePreferredSize();
	}
	
	/** 處理滑鼠事件 **/
	public void mousePressed(MouseEvent arg0) {
		isSelecting = true;	// 模式設為 - 選取中
		cp.setLayer(this, 90);	// 設置選取區物件於 繪圖物件上層
		pointInit = arg0.getPoint();	// 取得目前座標
		selectRect.setRect(pointInit.getX(), pointInit.getY(), 0, 0); // 設置選取框框初始值
		processSelection(arg0);
		repaint();
	}

	public void mouseReleased(MouseEvent arg0) {
		if (isSelecting) {
			isSelecting = false;
			cp.setLayer(this, 0);	// 將選取區塊放至最下層
			selectRect.setRect(-1, -1, 0, 0);	// 選取區塊消失
			repaint();
		}
	}

	public void mouseDragged(MouseEvent arg0) {
		if (isSelecting) {
			// 繪出選取框
			selectRect.setSize((int)Math.abs(arg0.getX()-pointInit.getX()), (int)Math.abs(arg0.getY()-pointInit.getY()));
			selectRect.setLocation((int)Math.min(arg0.getX(), pointInit.getX()), (int)Math.min(arg0.getY(), pointInit.getY()));
			processSelection(arg0);
			repaint();
		}
	}

	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}

}
