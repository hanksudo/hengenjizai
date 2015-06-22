package collectionSample;

public class Foo2 {
	private String name;
	
	public Foo2(String name) {
		this.name = name;
	}
	
	public void showName() {
		System.out.println("foo2 name: " + name);
	}
	
	// 重新定義toString()
	public String toString() {
		return "foo2 name: " + name;
	}
}
