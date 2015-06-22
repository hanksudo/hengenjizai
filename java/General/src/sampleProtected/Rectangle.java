package sampleProtected;

public class Rectangle {
	// ¨ü«OÅ@ªºMember
	protected int x, y;
	protected int width, height;
	
	public Rectangle() {
		x = y = 0;
		width = height = 0;
	}
	
	public Rectangle(int x, int y, int width ,int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getArea() { return width*height; }
}
