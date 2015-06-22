/*
 * Factorial ¶¥¼h - ¨Ï¥Î»¼°j
 * 2006/12/27
 */
public class Factorial_Recursive {
	public static void main(String[] args) {
		System.out.println(Fac(6));
	}

	public static long Fac(int n) {
		if (n==1) {
			return 1;
		} else {
			return n*Fac(n-1); 
		}
	}
}
