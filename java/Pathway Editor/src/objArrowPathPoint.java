
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;



public class objArrowPathPoint extends objModel implements Constants {
	private objArrowPath myArrowPath;
	private static RectangularShape shape;
	private Point2D.Float point = new Point2D.Float();
	private Point2D.Float control1 = new Point2D.Float();
	private Point2D.Float control2 = new Point2D.Float();
	
	private int SIZE = 4;
	private int SIZE_OFFSET = 4;
	
	private boolean pointType;
	
	public objArrowPathPoint(objArrowPath a) {
		myArrowPath = a;
		setPointLocation(0, 0);
	}
	
	public objArrowPathPoint(float x, float y, boolean _pointType, objArrowPath a) {
		this(a);
		point = new Point2D.Float(x, y);
		pointType = _pointType;
	}
	
	public void setPointLocation(float x, float y) {
		point.setLocation(x, y);
		setBounds((int)x - SIZE, (int)y - SIZE, 2*SIZE + SIZE_OFFSET, 2*SIZE + SIZE_OFFSET);
	}
	
	public void setPointType(boolean type) {
		if (pointType != type) {
			pointType = type;
			myArrowPath.createPath();
			myArrowPath.getArrow().updateArrowPosition();
		}
	}
	
	public boolean getPointType() {
		return pointType;
	}	
	
	public Point2D.Float getPoint() {
		return point;
	}
	
	public Point2D.Float getControl1() {
		return control1;
	}
	
	public Point2D.Float getControl2() {
		return control2;
	}
	
	public void setControl1(Point2D.Float p) {
		control1.x = p.x;
		control1.y = p.y;
	}
	
	public void setControl2(Point2D.Float p) {
		control2.x = p.x;
		control2.y = p.y;
	}
	
	public double getAngle(Point2D.Float p2) {
		double angle;
		if (point.y <= p2.y)
			angle = Math.atan((point.x - p2.x) / (p2.y - point.y));
		else
			angle = Math.atan((point.x - p2.x) / (p2.y - point.y))+Math.PI;
		
		// Needed to eliminate an exception on Windows
		if (point.equals(p2))
			angle = 0;
		
		return angle;
	}
	
	public void kill() {
		super.removeFromContainer();
		myArrowPath.deletePoint(this);		
	}

}
