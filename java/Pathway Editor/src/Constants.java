import java.awt.Color;

/**
 * @comment: 儲存常數
 * @author Han-Hong Wang
 * @date 2007/5/7
 */
public interface Constants {
	int DEFAULT_LaF = 1;
	int DEFAULT_GRID_SIZE = 2;
	
	// Function Constatns
	int SELECT = 100;
	int ARROW = 101;
	int CIRCLE = 102;
	int SQUARE = 103;
	int ELLIPSE = 104;
	int RECTANGLE = 105;
	int ROUNDRECTANGLE = 106;
	int TRIANGLE = 107;
	int STAR = 108;
	int SIXPOLYGON = 109;
	
	// Draw Object Color
	Color SELECT_COLOR = new Color(0, 0, 255, 30);
	Color SELECT_COLOR_OUTLINE = new Color(0, 0, 192); 
	Color CIRCLE_COLOR = new Color(255, 51, 0);
	Color ELLIPSE_COLOR = new Color(0, 204, 0);
	Color SQUARE_COLOR = new Color(128, 0, 255);
	Color RECTANGLE_COLOR = new Color(0, 0, 255);
	Color ROUNDRECTANGLE_COLOR = new Color(255, 255, 255);
	Color TRIANGLE_COLOR = new Color(255, 128, 64);
	Color STAR_COLOR = new Color(255, 255, 0);
	Color SIXPOLYGON_COLOR = new Color(128, 128, 255);
	
	// File type
	int XML_FILETYPE = 0;
	int JPEG_FILETYPE = 1;

	
	// Define Object Label (mode 預設值-101)
	public String DEFINES[] = {"", "Compound", "Object", "Other", "Enzyme", "Pathway", "Undefine", "Undefine", "Undefine"};
	
	int ARC_CONTROL_POINT_CONSTANT = 3;
	int DRAWMODEL_PROXIMITY_RADIUS = 25;
	

}
