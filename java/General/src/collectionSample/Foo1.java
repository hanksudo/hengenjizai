package collectionSample;

public class Foo1 {
	private String name;
	
	public Foo1(String name) {
		this.name = name;
	}
	
	public void showName() {
		System.out.println("foo1 name: " + name);
	}
	
	// ���s�w�qtoString()
	public String toString() {
		return "foo1 name: " + name;
	}
}
