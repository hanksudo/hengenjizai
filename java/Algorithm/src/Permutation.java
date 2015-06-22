/**
 * 
 * Permutation 排列組合
 *
 */

public class Permutation {

	static int MAX = 11;
	static int solution[] = new int[MAX];
	static boolean used[] = new boolean[MAX];
	
	public static void main(String[] args) {
		// 列出 0~9的排列組合
		// Permutation(0, 10);
		// 列出 0~4的排列組合
		 Permutation(1, 11);
	}
	
	public static void Permutation(int k, int n) {
		int i;
		if(k==n){
			for (i=0; i < n; i++)
				System.out.print(solution[i]);
			System.out.println();
		} else {
			for (i=0; i < n; i++) {
				if (!used[i]) {
					used[i] = true;
					solution[k] = i;
					Permutation(k+1, n);
					used[i] = false;
				}
			}
		}
	}
}
