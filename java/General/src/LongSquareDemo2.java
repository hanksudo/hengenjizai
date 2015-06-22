
public class LongSquareDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LongSquare2("1232");
	}

}

class LongSquare2 {
	public LongSquare2(String s) {
		int n[][] = new int[s.length()+1][s.length()*2+1];
		for(int i=0; i<s.length();i++) {
			for(int j=0; j<s.length(); j++) {
				n[i][j+i]=Character.getNumericValue(s.charAt(s.length()-1-j))*Character.getNumericValue(s.charAt(s.length()-1-i));
//				System.err.println("("+i+","+(j+i)+") = "+n[i][j+i]);
			}
			
			for(int k=0; k<n[0].length; k++) {
				n[s.length()][k]+=n[i][k];
//				System.out.println(n[s.length()][k]);
			}
		}
		
		for(int i=0; i<s.length();i++) {
			for(int j=n[i].length-1; j>=0; j--) {
				System.err.print(n[i][j]+" ");
			}
			System.err.println();
		}
		
		int c=0;
		String ans="";
		for(int m=0; m<n[0].length; m++) {
			n[s.length()][m]+=c;
			c=n[s.length()][m]/10;
			n[s.length()][m]%=10;
			ans = n[s.length()][m] +" "+ans;
		}

		boolean findNum=false;
		int i=0;
		while(!findNum) {
			if (ans.codePointAt(i)>'0') findNum=true;
			i++;
		}
//		System.err.println(ans.substring(i-1));
		System.err.println();
		System.err.println(ans);
	}
}
