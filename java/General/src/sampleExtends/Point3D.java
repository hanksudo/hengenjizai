package sampleExtends;

public class Point3D extends Point2D {	// 擴充Point2D類別
	private int z;
	
	public Point3D() {
		super();
	}
	
	// 建構函式，同時指定呼叫父類別建構函式
	Point3D(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}

	public int getZ() {
		return z;
	}
	
	public void setZ(int z) {
		this.z = z;
	}
}
