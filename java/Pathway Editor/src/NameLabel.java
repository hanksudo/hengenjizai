

import javax.swing.JLabel;

public class NameLabel extends JLabel{

  public int positionX;
  public int positionY;
  private double xCoord;
  private double yCoord;
  public int arcboundsLeft;
  public int arcboundsTop;
  private String id = null;

  public NameLabel(){
  	super();
  }

  public NameLabel(String text){
    super(text);
    setHorizontalAlignment(LEFT);
    setVerticalAlignment(TOP);
  }

  public NameLabel(double positionXInput, double positionYInput, String text, String idInput) {
  	super (text);
  	id = idInput;
  	xCoord = positionXInput;
  	yCoord = positionYInput;
  }
  
  public void setPosition(int x, int y) {
  	positionX = x;
  	positionY = y;
  	updatePosition();
  }
  
  public void updateSize() {
    // To get round Java bug #4352983 I have to expand the size a bit 
  	setSize((int)(getPreferredSize().width*1.2),(int)(getPreferredSize().height*1.2));
  	updatePosition();
  }
  
  public void updatePosition() {
  	setLocation(positionX-getPreferredSize().width,positionY-getPreferredSize().height);
  }
  
  public void translate(int x, int y) {
  	setPosition(positionX+x, positionY+y);
  }
  
  public double getYPosition() {
  	return yCoord;
  }
  
  public double getXPosition() {
  	return xCoord;
  }
  
  public String getID() {
  	return id;
  }
  public void setID(String idInput) {
  	id = idInput;
  }
}

