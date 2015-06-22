

public class isOdd {

	/**
	 * @param args
	 * 用&(And)判斷某一數字是否為單數
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.err.println(isOdd(99));
	}
	
	public static boolean isOdd(int n) {
		return (n&1)!=0;
	}

}
