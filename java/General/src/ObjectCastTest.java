class Base{}
class Sub extends Base {}
class Sub2 extends Base {}

public class ObjectCastTest {
	public static void main(String[] args) {
		Base b = new Base();
		Sub s = new Sub();
		s = (Sub)b;

	}

}
