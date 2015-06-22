


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * @comment: �e����Τ��� - Square
 * @author Han-Hong Wang
 * @date 2007/5/5
 */

public class objSquare extends objDrawModel {
	// �]�m���󪺪��|
	static double thisWidth = 30;
	static double thisHeight = 30;
	
	private static Rectangle2D.Double obj = new Rectangle2D.Double(0, 0, thisWidth, thisHeight);
	private static Shape proximityObj = (new BasicStroke(DRAWMODEL_PROXIMITY_RADIUS)).createStrokedShape(obj);

	public objSquare(double positionXinput, double positionYinput) {
		super(positionXinput, positionYinput, thisWidth, thisHeight);
	}

	// ø�s�ϧ�
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// �N�ثe�y�в��ʦܤ���
		g2.translate(5, 5); 

		// �]�m��u
		g2.setStroke(new BasicStroke(1.0f));	
		
		// ��������
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// �����C��
		g2.setColor(SQUARE_COLOR);
		if (selected) {g2.setColor(SELECT_COLOR);}
		g2.fill(obj);
		g2.setPaint(Color.black);
		g2.draw(obj);
	}

	// �ۭq�ϧ� �è��o�b�Y�J�I 2007-05-27
	public boolean contains(int x, int y) {
		thisArrow = ((DrawPanel)this.getParent()).arrow;
		if (thisArrow != null) {
			if ((proximityObj.contains(x-COMPONENT_DRAW_OFFSET, y-COMPONENT_DRAW_OFFSET) || obj.contains(x-COMPONENT_DRAW_OFFSET, y-COMPONENT_DRAW_OFFSET)) && areNotSameType(thisArrow.getSource())) {
				if (thisArrow.getTarget() != this) {
					thisArrow.setTarget(this);
					thisArrow.updateArrowPosition();
				}
				
				return true;
			} else {
				if (thisArrow.getTarget() == this) {
					thisArrow.setTarget(null);
//					updateConnected();
				}
				return false;
			}
		} else {
			return obj.contains(x-COMPONENT_DRAW_OFFSET, y-COMPONENT_DRAW_OFFSET);
		}
	}
	
	public void updateEndPoint(objArrow arrow) {
		if (arrow.getSource()==this) {
			arrow.setSourceLocation(this.positionX+thisWidth/2,this.positionY+thisHeight/2);
			double angle = arrow.getArrowPath().getStartAngle();
			arrow.setSourceLocation(0.5*thisWidth*(Math.sin(-angle))+positionX+centreOffsetLeft(), 0.5*thisHeight*(Math.cos(-angle))+positionY+centreOffsetTop());
		} else {
			arrow.setTargetLocation(this.positionX+thisWidth/2,this.positionY+thisHeight/2);
			double angle = arrow.getArrowPath().getEndAngle();
			arrow.setTargetLocation(0.5*thisWidth*(Math.sin(-angle))+positionX+centreOffsetLeft(), 0.5*thisHeight*(Math.cos(-angle))+positionY+centreOffsetTop());			
		}
	}
}
