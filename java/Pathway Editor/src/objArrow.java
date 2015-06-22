

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


/**
 * @comment: 畫箭頭元件 - Arrow
 * @author Han-Hong Wang
 * @date 2007/5/5
 */

public class objArrow extends objModel implements Constants {
	private objDrawModel source = null;
	private objDrawModel target = null;
	private ArrowHead arrow = new ArrowHead();
	public objArrowPath myPath = new objArrowPath(this);
	
	
	static double thisWidth = 200;
	static double thisHeight = 200;
	

	public objArrow(objDrawModel _source) {
		source = _source;
		myPath.addPoint();
		myPath.addPoint();
		myPath.createPath();
	}

	// 繪製圖形
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// 設置格線
		g2.setStroke(new BasicStroke(1.6f));
		
		// 抗鋸齒
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		
		g2.translate(COMPONENT_DRAW_OFFSET-myPath.getBounds().getX(),
				COMPONENT_DRAW_OFFSET-myPath.getBounds().getY());

//		if (selected && !ignoreSelection)
		
		if (selected) {
			g2.setPaint(SELECT_COLOR_OUTLINE);
		} else {
			g2.setPaint(Color.BLACK);
		}
		
		
		g2.fill(arrow);
		g2.draw(myPath);
//		drawArrow(g2, myPath.getPoint(myPath.getEndIndex()).getX(), myPath.getPoint(myPath.getEndIndex()).getY(), Math.PI/4, 100, SIDE_LEAD);	
		
	}

	public objDrawModel getSource() {
		return source;
	}
	
	public objDrawModel getTarget() {
		return target;
	}
	
	public void setTarget(objDrawModel targetObj) {
		target = targetObj;
		updateArrowPosition();
	}
	
	public objArrowPath getArrowPath() {
		return myPath;
	}
	
	public void updateArrowPosition() {
		if (source != null) {
			source.updateEndPoint(this);
		}
	
		if (target != null) {
			target.updateEndPoint(this);
		}
		
		myPath.createPath();
	}
	
	public void delete() {
		myPath.delete();
		super.delete();
	}

	public void setEndPoint(double x, double y, boolean type) {
		myPath.setPointLocation(myPath.getEndIndex(), x, y);
		myPath.setPointType(myPath.getEndIndex(), type);
		updateArrowPosition();
	}
	
	public void setTargetLocation(double x, double y) {
		myPath.setPointLocation(myPath.getEndIndex(), x, y);
		updateArrowHead();
		myPath.createPath();
		updateBounds();
		repaint();
	}
	
	public void setSourceLocation(double x, double y) {
		myPath.setPointLocation(0,x,y);
		updateArrowHead();
		myPath.createPath();
		updateBounds();
		repaint();
	}
	
	public void updateArrowHead() {
		if(myPath.getEndIndex()!=-1) arrow.setLocation(myPath.getPoint(myPath.getEndIndex()).getX(), myPath.getPoint(myPath.getEndIndex()).getY(), myPath.getEndAngle()+Math.PI);
	}
	
	public void updateBounds() {
		rect = myPath.getBounds();
		rect.grow(COMPONENT_DRAW_OFFSET,COMPONENT_DRAW_OFFSET);
		setBounds(rect);
	}
	
	public final int SIDE_LEAD = 0, SIDE_TRAIL = 1, SIDE_BOTH = 2, SIDE_NONE = 3;
	public final double pi = Math.PI;
	
	public void drawArrow(Graphics2D g2, int x, int y, double theta, int length, int side) {
		if (length < 0) {
			theta += pi;
			length *= -1;
		}
		
		int x1, y1;
		x1 = (int) Math.ceil(x + length * Math.cos(theta));
		y1 = (int) Math.ceil(y - length * Math.sin(theta));
		g2.drawLine(x, y, x1, y1);

		switch (side) {
		// 起始箭頭
		case SIDE_LEAD:
			drawArrow(g2, x1, y1, theta + 5 * pi / 4, 5, SIDE_NONE);
			drawArrow(g2, x1, y1, theta + 3 * pi / 4, 5, SIDE_NONE);
			break;
		// 尾端箭頭
		case SIDE_TRAIL:
			drawArrow(g2, x, y, theta - pi / 4, 5, SIDE_NONE);
			drawArrow(g2, x, y, theta + pi / 4, 5, SIDE_NONE);
			break;
		// 雙向箭頭	
		case SIDE_BOTH:
			drawArrow(g2, x, y, theta - pi / 4, 5, SIDE_NONE);
			drawArrow(g2, x, y, theta + pi / 4, 5, SIDE_NONE);
			drawArrow(g2, x1, y1, theta + 5 * pi / 4, 5, SIDE_NONE);
			drawArrow(g2, x1, y1, theta + 3 * pi / 4, 5, SIDE_NONE);
			break;
		// 無箭頭
		case SIDE_NONE:
			break;
		}
	}
}
