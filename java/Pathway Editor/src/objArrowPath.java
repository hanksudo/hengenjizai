
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;


public class objArrowPath implements Shape, Cloneable, Constants {
	private GeneralPath path = new GeneralPath();
	private objArrow myArrow;
//	private int transitionAngle;
	private List pathPoints = new ArrayList();
	private objArrowPathPoint currentPoint;
	private Shape shape;
	
	public objArrowPath(objArrow a) {
		myArrow = a;
//		transitionAngle = 0;
	}
	
	public void createPath() {
		// setControlPoints();
		
		setStraightControlPoints();
		
		currentPoint = null;
		path = new GeneralPath();
		currentPoint = (objArrowPathPoint)pathPoints.get(0);
		path.moveTo(currentPoint.getPoint().x, currentPoint.getPoint().y);
		
//		currentPoint.setPointType(objArrowPathPoint.STRAIGHT);
		currentPoint.setPointType(false);
		
		for (int i=1; i<=getEndIndex(); i++) {
			currentPoint = (objArrowPathPoint)pathPoints.get(i);
			
//			if (currentPoint.getPointType() == ArcPathPoint.STRAIGHT){
				path.lineTo(currentPoint.getPoint().x, currentPoint.getPoint().y); 
//			}
//			else if (currentPoint.getPointType() == ArcPathPoint.CURVED){
//				if (showControlPoints){//draw control lines for illustrative purposes
//					path.lineTo(currentPoint.getControl1().x,currentPoint.getControl1().y);
//					path.lineTo(currentPoint.getControl2().x,currentPoint.getControl2().y);
//					path.lineTo(currentPoint.getPoint().x, currentPoint.getPoint().y);
//					path.moveTo(((ArcPathPoint)pathPoints.get(i-1)).getPoint().x, ((ArcPathPoint)pathPoints.get(i-1)).getPoint().y);
//				}
//				path.curveTo(currentPoint.getControl1().x,currentPoint.getControl1().y,currentPoint.getControl2().x,currentPoint.getControl2().y,currentPoint.getPoint().x,currentPoint.getPoint().y);
//			}

		}
	}
	
	public GeneralPath getArrowPath() {
		return path;
	}
	
	public void translatePoint(int index, float x, float y) {
		objArrowPathPoint point = (objArrowPathPoint)pathPoints.get(index);
		point.setPointLocation(point.getPoint().x+x,point.getPoint().y+y);
	}
	
	public void setPointLocation(int index, double x, double y) {
		if (index<pathPoints.size() && index >=0) {
			((objArrowPathPoint)pathPoints.get(index)).setPointLocation((float)x, (float)y);
		}
	}
	
	public void setPointType(int index, boolean type) {
		((objArrowPathPoint)pathPoints.get(index)).setPointType(type);
	}

	public void addPoint(double x, double y, boolean type) {
		pathPoints.add(new objArrowPathPoint((float)x, (float)y, type, this));
	}
	
	public void addPoint() {
		pathPoints.add(new objArrowPathPoint(this));
	}
	
	// 取得目前最後一個pathPoint
	public int getEndIndex() {
		return pathPoints.size()-1;
	}
	
	public double getStartAngle() {
		if (getEndIndex()>0) {
			return ((objArrowPathPoint)pathPoints.get(0)).getAngle(((objArrowPathPoint)(pathPoints.get(1))).getControl2());
		}
		return 0;
	}
	
	public double getEndAngle() {
		if (getEndIndex()>0){
//			if (getArrow().getTarget() instanceof Transition)
//				return ((objArrowPathPoint)pathPoints.get(getEndIndex())).getAngle(((objArrowPathPoint)(pathPoints.get(getEndIndex()))).getControl2());
//			else
				return ((objArrowPathPoint)pathPoints.get(getEndIndex())).getAngle(((objArrowPathPoint)(pathPoints.get(getEndIndex()))).getControl1());
		}
		return 0;
	}
	
	private void setStraightControlPoints() {
		objArrowPathPoint myCurrentPoint = (objArrowPathPoint)pathPoints.get(0);		
		objArrowPathPoint myPreviousButOnePoint = null;
		objArrowPathPoint myNextPoint = null;
		objArrowPathPoint myPreviousPoint = null;

		for (int c=1; c<=getEndIndex(); c++){
			myPreviousPoint = (objArrowPathPoint)pathPoints.get(c-1);
			myCurrentPoint = (objArrowPathPoint)pathPoints.get(c);
			
			if (myCurrentPoint.getPointType() == false){
				myCurrentPoint.setControl1(getControlPoint(myPreviousPoint.getPoint(),
						myCurrentPoint.getPoint(),
						myPreviousPoint.getPoint(),
						myCurrentPoint.getPoint()));
				myCurrentPoint.setControl2(getControlPoint(myCurrentPoint.getPoint(),
						myPreviousPoint.getPoint(),
						myCurrentPoint.getPoint(),
						myPreviousPoint.getPoint()));
			}
			
			else {
				if (c>1 && myPreviousPoint.getPointType() == false){
					
					myPreviousButOnePoint = (objArrowPathPoint)pathPoints.get(c-2);
					myCurrentPoint.setControl1(getControlPoint(myPreviousButOnePoint.getPoint(),
							myPreviousPoint.getPoint(),
							myPreviousPoint.getPoint(),
							myCurrentPoint.getPoint()));
				}
				if (c<getEndIndex()){
					myNextPoint = (objArrowPathPoint)pathPoints.get(c+1);
					if (myNextPoint.getPointType() == false){
						myCurrentPoint.setControl2(getControlPoint(myNextPoint.getPoint(),
								myCurrentPoint.getPoint(),
								myCurrentPoint.getPoint(),
								myPreviousPoint.getPoint()));
					}
				}
			}
		}
	}
	
	private Point2D.Float getControlPoint(Point2D.Float A, Point2D.Float B, Point2D.Float C, Point2D.Float D){
		Point2D.Float p = new Point2D.Float(0, 0);
		
		double modAB = getMod(A, B);
		double modCD = getMod(C, D);		
		
		double ABx = (B.x - A.x) / modAB; 
		double ABy = (B.y - A.y) / modAB;
		
		if (modAB < 7) 	// hack, stops division by zero, modAB can only be this low
						// if the points are virtually superimposed anyway
			p = (Point2D.Float)C.clone();	
		else{
			p.x = C.x + (float) (ABx * modCD / ARC_CONTROL_POINT_CONSTANT );
			p.y = C.y + (float) (ABy * modCD / ARC_CONTROL_POINT_CONSTANT );
		}
		return p;
	}
	
	private double getMod(Point2D.Float A, Point2D.Float B){
		double ABx = A.x - B.x; 
		double ABy = A.y - B.y; 
		
		return Math.sqrt(ABx*ABx + ABy*ABy);
	}
	
	public Point2D.Float getPoint(int index) {
		return ((objArrowPathPoint)pathPoints.get(index)).getPoint();
	}
	
	public objArrow getArrow() {
		return myArrow;
	}
	
	public void delete() {
		while (!pathPoints.isEmpty()) {
			// 刪除所有Point
			((objArrowPathPoint)pathPoints.get(0)).kill();
		}
	}
	
	public void deletePoint(objArrowPathPoint a) {
		pathPoints.remove(a);
	}
	
	public boolean isPointSelected(int index) {
		return ((objArrowPathPoint)pathPoints.get(index)).isSelected();
	}
	
	/** **/
	public boolean contains(Point2D p) {
		return shape.contains(p);
	}

	public boolean contains(Rectangle2D r) {
		return false;
	}

	public boolean contains(double x, double y) {
		return false;
	}

	public boolean contains(double x, double y, double w, double h) {
		return false;
	}

	public Rectangle getBounds() {
		return path.getBounds();
	}

	public Rectangle2D getBounds2D() {
		return null;
	}

	public PathIterator getPathIterator(AffineTransform at) {
		return path.getPathIterator(at);
	}

	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return path.getPathIterator(at, flatness);
	}

	public boolean intersects(Rectangle2D r) {
		return shape.intersects(r);
	}

	public boolean intersects(double x, double y, double w, double h) {
		return false;
	}

}
