import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class P146 {
	static int p[] = new int[50];
	static int old_p[] = new int[50];
	
	private static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public static boolean nextPerm(int[] p) {
		int i;
		for (i = p.length-1; i-->0 && p[i]>p[i + 1];)
			;

		if (i < 0)
			return false;

		int j;

		for (j = p.length; --j > i && p[j] < p[i];)
			;

		swap(p, i, j);

		for (j = p.length; --j > ++i; swap(p, i, j))
			;

		if (Arrays.equals(old_p, p)) return false;
		old_p = Arrays.copyOf(p, p.length);
	
		return true;
	}

	private static void print(int[] p) {
		for (int i = 0; i < p.length; ++i)
			System.out.print((char)p[i]);
		System.out.println();
	}

	public static void main(String args[]) {
		while(getInput()) {
			if(nextPerm(p))
				print(p);
			else
				System.out.println("No Successor");
		}

// list all result
//		getInput();
//		do
//			print(p);
//		while (nextPerm(p));
	}
	
	private static boolean getInput() {
		Scanner in = new Scanner(new BufferedInputStream(System.in));
		String cin;
		try {
			cin = in.next();
		} catch (Exception e) {
			return false;
		}
		
		if (cin.equals("#") || cin == null)
			return false;

		p = new int[cin.length()];

		for(int i=0;i<cin.length();i++)
			p[i] = cin.charAt(i);
				
		old_p = Arrays.copyOf(p, p.length);
		return true;
	}
}