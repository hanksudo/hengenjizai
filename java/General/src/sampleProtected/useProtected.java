package sampleProtected;
public class useProtected {
	public static void main(String[] args) {
		Cubic c1 = new Cubic(0, 0, 0, 10, 20, 30);
		System.out.println("c1 volume: " + c1.getVolume());
	}
}
