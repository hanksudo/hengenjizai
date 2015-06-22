/************************************************
*  Comment: 輸入一數字n，計算1/1 + 1/2 + ... + 1/n
*  Author: Han-Hong Wang						
*  Website: http://www.whh.idv.tw				
*  E-mail: drapho@ms51.url.com.tw			
*  Develop Environment-Compiler: J2SE 1.6.0	
*		      -Editor: Eclipse 3.2.0			
*		      -OS: Windows XP SP2			
*  Creation Date: 2006/12/26					
*  Last Update: 2006/12/26					
*************************************************/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solve1 {
	public static void main(String[] args) throws IOException {
		double ans = 0;
		int n,i;
		System.out.print("Please input a number n:");
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(buf.readLine());
		for (i=1;i<=n;i++) {
			ans+=(double)1/i;
		}
		System.out.println("Ansewer = "+ans);
	}

}
