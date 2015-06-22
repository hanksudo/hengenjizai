/*
 * Fibonacci ¶O¤ó¼Æ¦C - »¼°j
 * 2006/12/29
 */
public class Fibonacci_Recursive {
	public static void main(String[] args) {
		int i;
		for (i=0;i<=10;i++) {
			System.out.print(Fib(i)+" ");
		}
	}

	public static int Fib(int n) {
		if (n<=1) {
			return n;
		} else {
			return Fib(n-1)+Fib(n-2);
		}
	}
}
