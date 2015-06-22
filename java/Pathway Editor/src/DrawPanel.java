

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLayeredPane;


/**
 * @comment: 繪製元件的畫板
 * @author Han-Hong Wang
 * @date 2007/5/5
 */

public class DrawPanel extends JLayeredPane implements MouseListener, MouseMotionListener, Constants {
	objSelection selection;
	public objArrow arrow;
	private DrawFrame currentFrame;
	private boolean shiftDown = false;
	ArrayList objCircleIndex = new ArrayList();
	ArrayList objSquareIndex = new ArrayList();
	ArrayList objEllipseIndex = new ArrayList();
	ArrayList objRectangleIndex = new ArrayList();
	ArrayList objRoundRectangleIndex = new ArrayList();
	ArrayList objTriangleIndex = new ArrayList();
	ArrayList objStarIndex = new ArrayList();
	ArrayList objSixPolygonIndex = new ArrayList();
	
	public DrawPanel() {}
	
	public DrawPanel(DrawFrame frame) {
		currentFrame = frame;
		setSize(800, 600);
		setLayout(null);
		setOpaque(true);	// 設置透明
		setDoubleBuffered(true);	// 設置緩衝區繪圖
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));	// 滑鼠樣式
				
		addMouseListener(this);
		addMouseMotionListener(this);
		
		// 畫板底色
		setBackground(Color.white);
		
		// 新增選取區
		selection = new objSelection(this);
	}
	
	// 取得目前選取區物件
	public objSelection getSelectionObject() {
		return selection;
	}
	
	// 調整繪板至適當大小 - 避免視窗大小截掉繪板
	public void updatePreferredSize() {
		Component[] objs = this.getComponents();
		Dimension d=new Dimension(0,0);
		
		for(int i=0; i<objs.length; i++) {
			// 判斷此物件是否含有選取區塊 objSelection
			if(objs[i] instanceof objSelection) continue;
			Rectangle r = objs[i].getBounds();
			int x = r.x + r.width;
			int y = r.y + r.height;
			if(x>d.width) { d.width = x+30;}
			if(y>d.height) { d.height= y+30;}
		}
		setPreferredSize(d);
		
		getParent().validate();
	}
	
	
	// 刪除箭頭
	public void clearArrow() {
		arrow.delete();
		this.arrow = null;
		this.getParent().repaint();	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (Grid.getGridEnabled()) {
			Grid.updateGrid(this.getWidth(), this.getHeight());
			Grid.drawGrid(g);
		}
		selection.updateBounds();
	}
	
	// 新增新物件
	public void addNewObject(objDrawModel newObj, int idNum) {
		if (newObj.getMouseListeners().length==0) {
			objDrawHandler handler = new objDrawHandler(this, newObj);
			newObj.addMouseListener(handler);
			newObj.addMouseMotionListener(handler);
			add(newObj);
			newObj.addLabel(DEFINES[mainFrame.getMode()-101]+" "+idNum);
		}
	}
	
	// 新增新物件 - 箭頭
	public void addNewObject(objModel newObj) {
		if (newObj.getMouseListeners().length==0) {
			if (newObj instanceof objArrow) {
				add(newObj);
				objArrowHandler arrowHandler = new objArrowHandler(this, (objArrow)newObj);
				newObj.addMouseListener(arrowHandler);
				newObj.addMouseMotionListener(arrowHandler);
			}
		}
		
	}
	
	// 移除物件
	public void removeObject(objModel obj) {
		ArrayList processArrayList = null;
		if (obj instanceof objCircle) {
			processArrayList = objCircleIndex;
		} else if (obj instanceof objSquare) {
			processArrayList = objSquareIndex;
		} else if (obj instanceof objEllipse) {
			processArrayList = objEllipseIndex;
		} else if (obj instanceof objRectangle) {
			processArrayList = objRectangleIndex;
		} else if (obj instanceof objRoundRectangle) {
			processArrayList = objRoundRectangleIndex;
		} else if (obj instanceof objTriangle) {
			processArrayList = objTriangleIndex;
		} else if (obj instanceof objStar) {
			processArrayList = objStarIndex;
		} else if (obj instanceof objSixPolygon) {
			processArrayList = objSixPolygonIndex;
		}

		if (processArrayList != null) {
			processArrayList.remove(obj);
		}
	}
	
	
	/** 滑鼠動作處理 **/

	public void mousePressed(MouseEvent e) {
		objDrawModel newObj = null;
		Point start = e.getPoint(); // 滑鼠目前點的座標
		
		// 當按下滑鼠左鍵
		if (e.getButton() == MouseEvent.BUTTON1) {
			// 將狀態設為已改變
			if (!currentFrame.changed && mainFrame.getMode()!=SELECT) {currentFrame.setChanged(true);}
			currentFrame.getDrawPanel().getSelectionObject().clearSelection();
			
			switch (mainFrame.getMode()) {
				case SELECT:break;
				case ARROW:
					if (arrow != null) {
						arrow.setEndPoint(Grid.getModifiedX(e.getX()), Grid.getModifiedY(e.getY()), shiftDown);
						arrow.getArrowPath().addPoint(Grid.getModifiedX(e.getX()), Grid.getModifiedY(e.getY()), shiftDown);
					}
					break;					
				case CIRCLE:
					newObj = new objCircle(Grid.getModifiedX(start.x), Grid.getModifiedY(start.y));
					objCircleIndex.add(newObj);
					addNewObject(newObj, objCircleIndex.size());break;
				case SQUARE:
					newObj = new objSquare(Grid.getModifiedX(start.x), Grid.getModifiedY(start.y));
					objSquareIndex.add(newObj);
					addNewObject(newObj, objSquareIndex.size());break;
				case ELLIPSE:
					newObj = new objEllipse(Grid.getModifiedX(start.x), Grid.getModifiedY(start.y));
					objEllipseIndex.add(newObj);
					addNewObject(newObj, objEllipseIndex.size());break;
				case RECTANGLE:
					newObj = new objRectangle(Grid.getModifiedX(start.x), Grid.getModifiedY(start.y));
					objRectangleIndex.add(newObj);
					addNewObject(newObj, objRectangleIndex.size());break;
				case ROUNDRECTANGLE:
					newObj = new objRoundRectangle(Grid.getModifiedX(start.x), Grid.getModifiedY(start.y));
					objRoundRectangleIndex.add(newObj);
					addNewObject(newObj, objRoundRectangleIndex.size());break;
				case TRIANGLE:
					newObj = new objTriangle(Grid.getModifiedX(start.x), Grid.getModifiedY(start.y));
					objTriangleIndex.add(newObj);
					addNewObject(newObj, objTriangleIndex.size());break;
				case STAR:
					newObj = new objStar(Grid.getModifiedX(start.x), Grid.getModifiedY(start.y));
					objStarIndex.add(newObj);
					addNewObject(newObj, objStarIndex.size());break;
				case SIXPOLYGON:
					newObj = new objSixPolygon(Grid.getModifiedX(start.x), Grid.getModifiedY(start.y));
					objSixPolygonIndex.add(newObj);
					addNewObject(newObj, objSixPolygonIndex.size());break;
			}
		}
		
		// 當按下滑鼠右鍵
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (this.arrow!=null) {clearArrow();}
		}
		
		updatePreferredSize();
	}
	
	public void mouseMoved(MouseEvent e) {
		if (arrow != null) {
			arrow.setEndPoint(Grid.getModifiedX(e.getX()), Grid.getModifiedY(e.getY()), shiftDown);
		}
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
}
