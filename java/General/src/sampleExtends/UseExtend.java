package sampleExtends;

public class UseExtend {
	public static void main(String[] args) {
		Point3D p1 = new Point3D(1, 3, 4);
		Point3D p2 = new Point3D();
		
		System.out.println("p1: (" + p1.getX() + "," + p1.getY() + "," + p1.getZ() + ")");
		System.out.println("p2: (" + p2.getX() + "," + p2.getY() + "," + p2.getZ() + ")");
		
		Point3D p3 = new Point3D();
		p3.setX(2);
		p3.setY(4);
		p3.setZ(6);
		System.out.println("p3: (" + p3.getX() + "," + p3.getY() + "," + p3.getZ() + ")");
	}
}
