package collectionSample;

public class Test {
	public static void main(String[] args) {
		SimpleCollection objs = new SimpleCollection();
		
		objs.add(new Foo1("f1 number 1"));
		objs.add(new Foo2("f2 number 1"));
		
		Foo1 f1 = (Foo1) objs.get(0);
		f1.showName();
		
		Foo2 f2 = (Foo2) objs.get(1);
		f2.showName();
		
		System.out.println();
		System.out.println("f1.toString(): " + f1.toString());
		System.out.println("f2.toString(): " + f2.toString());
	}
}
