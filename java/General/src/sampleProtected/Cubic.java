package sampleProtected;

public class Cubic extends Rectangle {
	protected int z;
	protected int length;
	
	public Cubic() {
		z = 0;
		length = 0;
	}
	
	public Cubic(int x, int y, int z, int length, int width, int height) {
		super(x, y, width, height);
		this.z = z;
		this.length = length;
	}
	
	public int getLength() { return length; }
	public int getVolume() { return length*width*height; }
}
