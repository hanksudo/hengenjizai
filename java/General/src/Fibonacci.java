/*
 * Fibonacci ¶O¤ó¼Æ¦C - «D»¼°j
 * 2006/12/29
 */
public class Fibonacci {
	public static void main(String[] args) {
		int n = 10;
		int i,ans=0;
		int prev1=0, prev2=1;
		for (i=0;i<=n;i++) {
			if (i<=1) {
				ans = i;
			} else {
				ans = prev1 + prev2;
				prev1 = prev2;
				prev2 = ans;
			}
			System.out.print(ans+" ");
		}
	}

}
