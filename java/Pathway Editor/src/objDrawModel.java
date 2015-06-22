
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @comment: 繪圖物件模組
 * @author Han-Hong Wang
 * @MSN phodra@hotmail.com
 * @website http://whh.idv.tw
 * @date 2007/5/23
 */
public abstract class objDrawModel extends objModel {

	// 物件座標
	protected double positionX;
	protected double positionY;
	
	// Label 標籤座標
	protected double labelPositionX;
	protected double labelPositionY;
	
	// 物件的大小
	protected double objWidth;
	protected double objHeight;
	
	private Collection connectTo = new LinkedList();
	private Collection connectFrom = new LinkedList();
	protected objArrow thisArrow;
	
	public objDrawModel(double positionXInput, double positionYInput, double widthInput, double heightInput) {
		Label = new objLabel();
		
		getSize(widthInput, heightInput);
		setCentre(positionXInput, positionYInput);
		updateBound();		
		
		Label.setSize(100, 50);
	}
	
	// 將元件位置設置於中心點對齊
	public void setCentre(double x, double y) {
		positionX = x - objWidth/2;
		positionY = y - objHeight/2;
		labelPositionX = positionX+objWidth/2;
		labelPositionY = positionY+objHeight/2+3;
		updateBound();
		updateLabelLocation();
	}
	
	// 取得元件中心點
	public Point2D.Double getCentre() { 
		return new Point2D.Double(positionX +objWidth/2, positionY + objHeight/2);
	}
	
	// 改變元件位置
	public void translate(int x, int y) {
		positionX += x;
		positionY += y;
		updateBound();
		Label.translate(x,y);
	}
	
	// 	設置大小位置
	private void updateBound() {
		rect.setBounds((int)positionX, (int)positionY, (int)objWidth, (int)objHeight);
		rect.grow(5, 5);
		setBounds(rect);
	}
	
	// 更新標籤位置
	public void updateLabelLocation() {
		Label.setPosition((int)labelPositionX,(int)labelPositionY);
	}
	
	public void addLabel(String label) {
		super.addLabel(label);
	}
	
	// 取得物件大小
	private void getSize(double w, double h) {
		objWidth = w;
		objHeight = h;	
	}
	
	public int centreOffsetTop() {
	  	return (int)objHeight/2;
	}
	
	public int centreOffsetLeft() {
		return (int)objWidth /2;
	}
	
	public void addConnectFrom(objArrow newArrow) {
		connectFrom.add(newArrow);
	}
	
	public void addConnectTo(objArrow newArrow) {
		connectTo.add(newArrow);
	}
	
	public boolean areNotSameType(objDrawModel obj) {
		return !(obj.getClass().getName().equals(this.getClass().getName()));
	}
	
	public void select(boolean state) {
		super.select(state);
		
		// 選取與此物件相關的箭頭
		Iterator arrowFrom = connectFrom.iterator();
		Iterator arrowTo = connectTo.iterator();
		while (arrowFrom.hasNext()) {((objArrow)arrowFrom.next()).select(state);}
		while (arrowTo.hasNext()) {((objArrow)arrowTo.next()).select(state);}
	}
	
	public void delete() {
		this.getParent().remove(Label);
		super.delete();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	abstract public void updateEndPoint(objArrow arrow);
}
