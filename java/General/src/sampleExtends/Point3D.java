package sampleExtends;

public class Point3D extends Point2D {	// �X�RPoint2D���O
	private int z;
	
	public Point3D() {
		super();
	}
	
	// �غc�禡�A�P�ɫ��w�I�s�����O�غc�禡
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
