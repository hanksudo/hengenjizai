

import javax.swing.JLabel;

public class objLabel extends JLabel {

	public int positionX;
	public int positionY;

	public objLabel() {
		super();
	}

	public objLabel(String text) {
		super(text);
		setHorizontalAlignment(LEFT);
		setVerticalAlignment(TOP);
	}
	
	public void setLabel(String text) {
		this.setText(text);
	}

	public void setPosition(int x, int y) {
		positionX = x;
		positionY = y;
		updatePosition();
	}

	public void updatePosition() {
		setLocation(positionX, positionY);
//		setLocation(positionX - getPreferredSize().width, positionY - getPreferredSize().height);
	}

	public void translate(int x, int y) {
		setPosition(positionX + x, positionY + y);
	}
	
	//	public void updateSize() {
	//	// To get round Java bug #4352983:Clipping of text when printing
	//     I have to expand the size a bit
	//	setSize((int) (getPreferredSize().width * 1.2),
	//			(int) (getPreferredSize().height * 1.2));
	//	updatePosition();
	//}
}
