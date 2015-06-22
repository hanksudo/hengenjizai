/*
 * 判斷單數或雙數
 * 2006/12/28
 */

public class Odd_Even {
	public static void main(String[] args) {
		System.out.println(checkNum(21));
	}

	public static String checkNum(int n) {
		if ((n&1)==0) {
			return "Even";
		} else {
			return "Odd";
		}
	}
}
