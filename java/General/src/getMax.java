/**
 * @comment �λ��j���o�̤j��
 *  		
 * 			1. �����g max() 
 * 			2. main() ���㦳 if/switch statement, �u��ϥ� max(int, int)
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