package sampleClass;

public class Ball {
	private double radius;	//�b�|
	private String name;	//�W��
	
	//�L�Ѽƫغc��k
	public Ball() {
		this(0.0,"no name");
		System.err.println("Ball()");
	}
	
	public Ball(double radius, String name)  {
		this.radius = radius;
		this.name = name;
		System.err.println("Ball(r,n)");
	}
	
	public double getRadius() {
		System.err.println("getRadius()");
		return radius;
	}
	
	public String getName() {
		System.err.println("getName()");
		return name;
	}
	
	public void setRadius(double radius) {
		System.err.println("setRadius()");
		this.radius = radius;
	}
	
	public void setName(String name) {
		System.err.println("setName()");
		this.name = name;
	}
}
