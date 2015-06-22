

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
		this();	// �]�m�ƹ�Listener
		
		// ���o�ثe�ϥΤ�DrawPanel
		cp = _drawing_panel;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		// ����Ϫ���u
		Stroke stroke = new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[]{5f, 5f}, 0f); //����Ϫ���u
        g2d.setStroke(stroke);
		g2d.setPaint(SELECT_COLOR); // ����϶��C��
		g2d.fill(selectRect);
		g2d.setPaint(SELECT_COLOR_OUTLINE); // // ����Ϯؽu�C��
		g2d.draw(selectRect);
	}
	
	// �}�ҿ���\��
	public void enableSelection() {
		if (!enabled) {
			enabled = true;
			cp.add(this);	// �N����ϥ[�JDrawPanel
			updateBounds();	// ��sSelection �P DrawPanel�@�ˤj
		}
	}
	
	// ��������\��
	public void disableSelection() {
		if (enabled) {
			cp.remove(this);	// �N����ϲ���
			enabled = false;
		}		
	}
	
	// ��������Ҧ�����
	public void clearSelection() {
		Component myObject[] = cp.getComponents();	

		for (int i=0; i<myObject.length; i++) {
			if (myObject[i] instanceof objModel) {
				((objModel)myObject[i]).select(false);
			}
		}
	}
	
	// ��s Selection�P DrawPanel�@�ˤj
	public void updateBounds() {
		if (enabled) {
			setBounds(0, 0, cp.getWidth(), cp.getHeight());
		}
	}
	
	// �즲�ɪ����m�B�z
	public void transSelection(int transX, int transY) {
		if (transX==0 && transY==0) return;
		
		Component myObject[] = cp.getComponents();
		Point p, topLeft=null;
		
		// �������L�k�W�L�W�P��
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
		
		// ���ʪ���
		for (int i=0; i<myObject.length; i++) {
	    	if (myObject[i] instanceof objDrawModel) { // ���ʪ���
	    		if (((objDrawModel)myObject[i]).isSelected()){
	    			((objDrawModel)myObject[i]).translate(transX,transY);
	    		}
	    	} else if (myObject[i] instanceof objArrow) {  // �u����m��۪���
	    		objArrow thisArrow = (objArrow)myObject[i];
	    		for (int j=0; j<thisArrow.getArrowPath().getEndIndex(); j++) {
	    			// �P�_�O�_���������A�קK��L����첾�줣������
	    			if (thisArrow.getArrowPath().isPointSelected(j)) {
	    				thisArrow.getArrowPath().translatePoint(j, transX, transY);
	    			}
	    			thisArrow.updateArrowPosition();
	    		}
	    	}
	    }
		// ���s�]�wDrawPanel���̾A�j�p
		cp.updatePreferredSize();
	}

	// �B�z����\��
	public void processSelection(MouseEvent evt) {
		if (!evt.isShiftDown()) {clearSelection();}
		
		Component myObject[] = cp.getComponents();
		for (int i=0; i<myObject.length; i++) {
			if (myObject[i] instanceof objModel) {
				// ����b����ؤ�������
				if (selectRect.intersects(myObject[i].getBounds())) {
					((objModel)myObject[i]).select(true);
				}
			}
		}
	}
	
	// �έp�ثe����������`��
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
	
	// �R�����������
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
	
	/** �B�z�ƹ��ƥ� **/
	public void mousePressed(MouseEvent arg0) {
		isSelecting = true;	// �Ҧ��]�� - �����
		cp.setLayer(this, 90);	// �]�m����Ϫ���� ø�Ϫ���W�h
		pointInit = arg0.getPoint();	// ���o�ثe�y��
		selectRect.setRect(pointInit.getX(), pointInit.getY(), 0, 0); // �]�m����خت�l��
		processSelection(arg0);
		repaint();
	}

	public void mouseReleased(MouseEvent arg0) {
		if (isSelecting) {
			isSelecting = false;
			cp.setLayer(this, 0);	// �N����϶���̤ܳU�h
			selectRect.setRect(-1, -1, 0, 0);	// ����϶�����
			repaint();
		}
	}

	public void mouseDragged(MouseEvent arg0) {
		if (isSelecting) {
			// ø�X�����
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
