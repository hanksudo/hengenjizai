/**
 * @comment 用遞迴取得最大值
 *  		
 * 			1. 不准改寫 max() 
 * 			2. main() 不准有 if/switch statement, 只能使用 max(int, int)
 * 
 * @author Han-Hong Wang
 * @date 2007-03-10
 */

public class getMax {
	public static int max(int x, int y) {
		if (x>y) return x;
			else return y;
	}
	 
	public static void main(String[] args) {
		int a[] = {9, 5, 2, 13, 10, 14};

		int max_num=0;
		for (int i=0; i<a.length; i++) {
			max_num  = max(max_num, a[i]);
			System.out.println(max_num);
		}
	}
}