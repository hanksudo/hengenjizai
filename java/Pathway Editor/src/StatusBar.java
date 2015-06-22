



import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @comment: ¸ËºA¦C
 * @author Han-Hong Wang
 * @date 2007/5/6
 */
public class StatusBar extends JPanel implements Constants {
	private JLabel label;
	
	public StatusBar() {
		label = new JLabel("OK! Let's start to use.");
	    this.setLayout(new BorderLayout(0,0));
	    this.add(label);
	}
	
	public void changeText(String newText) {
		label.setText(newText);
	}
	
	public void changeText(int typeID) {
		switch (typeID) {
			case SELECT:changeText("Select Mode: you can drag and edit object.");break;
			case ARROW:changeText("Arrow Mode");break;
			case CIRCLE:changeText("Draw Circle");break;
			case SQUARE:changeText("Draw Sqaure");break;
			case ELLIPSE:changeText("Draw Ellipse");break;
			case RECTANGLE:changeText("Draw Rectangle");break;
			case ROUNDRECTANGLE:changeText("Draw RoundRectangle");break;
			case TRIANGLE:changeText("Draw Triangle");break;
			case STAR:changeText("Draw Star");break;
			case SIXPOLYGON:changeText("Draw SixPolygon");break;
		}
	}
}
