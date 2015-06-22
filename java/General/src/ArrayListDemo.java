import java.util.ArrayList;


public class ArrayListDemo {

	/**
	 * ArrayList Demo
	 * 2008/12/12
	 */
	public static void main(String[] args) {
		// <珹腹> 篶StringList - 把计て (parameterized)
		ArrayList<String> myList = new ArrayList<String>(); // 穝ArrayListン穦篶heap
		
		// じ
		String s1 = "Java";
		String s2 = "Ruby";
		myList.add(s1);
		myList.add(s2);
		
		// 琩高
		int listSize = myList.size();
		System.err.println(listSize);
		
		// 琩高疭﹚じ
		boolean isIn1 = myList.contains("PHP");
		boolean isIn2 = myList.contains("Java");
		System.err.println(isIn1);
		System.err.println(isIn2);
		
		// 琩高疭﹚じ竚
		int idx = myList.indexOf("Ruby");
		System.err.println(idx);
		
		// 耞栋琌
		boolean empty = myList.isEmpty();
		System.err.println(empty);
		
		// 埃じ
		myList.remove(s1);
		myList.remove(s2);
	}

}
