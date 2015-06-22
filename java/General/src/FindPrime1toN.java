/**
 * 列出1~N的質數 By 篩選法
 * Date: 2008/12/24
 */

import java.util.Arrays;

public class FindPrime1toN {
	public static void main(String[] args) {
		int n = 100;
		boolean prime[] = new boolean[n+1];
		Arrays.fill(prime, true);
		
		for (int i=2;i<n/2;i++) {
			int j=2;
			do {
				prime[i*j] = false;
				j++;
			} while(i*j<=n);
		}
		
		for (int k=2;k<=n;k++) {
			if(prime[k]) {
				System.err.print(k+" ");
			}
		}
	}
}
