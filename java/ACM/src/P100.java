import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * 
 * @author Hank Wang(drapho@gmail.com)
 * @problem "Volume I - 100 The 3n+1 Problem"
 * @date 2010/07/06
 * 
 */

public class P100 {
	public static void main(String[] args) {
		int max=0, circle=1, n=0, io, jo;		
		Scanner in = new Scanner(new BufferedInputStream(System.in));

		while (in.hasNextInt()) {
			max = 0;
			int i = in.nextInt();
			int j = in.nextInt();
			if (i>j) {io=j; jo=i;} else {io=i; jo=j;}
			
			for(int k=io; k<=jo ;k++) {
				n = k;
				circle=1;
				while (n!=1) {
					circle++;
					if ((n&1)!=0) {
						n = n * 3 + 1;
					} else {
						n /= 2;
					}
				}
				if (circle>max) {max=circle;}
			}	
			System.out.println(i+" "+j+" "+max);
		}
	}
}