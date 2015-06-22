
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

/**
 * @comment: 畫六邊形元件
 * @author Han-Hong Wang
 * @date 2007/6/1
 */

public class objSixPolygon extends objDrawModel {
	// 設置元件的長和寬
	static double thisWidth = 40;
	static double thisHeight = 35;
	
	private int xPoints[] = {7, 0, 7, 23, 30, 23};
	private int yPoints[] = {0, 15, 30, 30, 15, 0};
	
	private Polygon obj = new Polygon(xPoints, yPoints, 6);
	private Shape proximityObj = (new BasicStroke(DRAWMODEL_PROXIMITY_RADIUS)).createStrokedShape(obj);
	
	public objSixPolygon(double inputX, double inputY) {
		super(inputX, inputY, thisWidth, thisHeight);
	}

	// 繪製圖形
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// 物件座標比例轉換
		convertRate();
		
		// 將目前座標移動至中心
		g2.translate(5, 5); 
		
		// 設置格線
		g2.setStroke(new BasicStroke(1.4f));
		
		// 消除鋸齒
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
		// 元件顏色
		g2.setColor(SIXPOLYGON_COLOR);
		if (selected) {g2.setColor(SELECT_COLOR);}
		g2.fill(obj);
		g2.setPaint(Color.black);
		
		// 繪製內圈三條線
		g2.drawLine(11, 4, 28, 4);
		g2.drawLine(4, 17, 11, 31);
		g2.drawLine(28, 31, 35, 17);
		
		g2.draw(obj);
	}
	
	// 物件比例轉換
	public void convertRate() {
		// 取得比例 (100%=30/30, 150%=45/30, 200%=60/30)
		double xrate = thisWidth/30;
		double yrate = thisHeight/30;

		// 轉換成新的座標比例
		int newX[] = new int[xPoints.length];
		int newY[] = new int[xPoints.length];
		
		for(int i=0; i<xPoints.length; i++) {
			newX[i] = (int)(xPoints[i]*xrate);
			newY[i] = (int)(yPoints[i]*yrate);
		}
		
		obj = new Polygon(newX, newY, xPoints.length);
	}

	// 自訂圖形 並取得箭頭焦點 2007-05-27
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
			arrow.setSourceLocation(this.getCentre().getX(), this.getCentre().getY());
//			arrow.setSourceLocation(this.positionX+thisWidth/2,this.positionY+thisHeight/2);
			double angle = arrow.getArrowPath().getStartAngle();
//			arrow.setSourceLocation(0.5*thisWidth*(Math.sin(-angle))+positionX+centreOffsetLeft(), 0.5*thisHeight*(Math.cos(-angle))+positionY+centreOffsetTop());
			
		} else {
			arrow.setTargetLocation(this.getCentre().getX(), this.getCentre().getY());
//			arrow.setTargetLocation(this.positionX+thisWidth/2,this.positionY+thisHeight/2);
			double angle = arrow.getArrowPath().getEndAngle();
//			arrow.setTargetLocation(0.5*thisWidth*(Math.sin(-angle))+positionX+centreOffsetLeft(), 0.5*thisHeight*(Math.cos(-angle))+positionY+centreOffsetTop());			
		}
	}
}
