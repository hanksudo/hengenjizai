/**
 * @comment: 羅馬與阿拉伯數字互轉
 * @author Han-Hong Wang (Hank)
 * @date 2008/6/6
 */

public class RomanAndNumeralConveter {
	public static void main(String[] args) {
		int num = 1998;	//MCMXCVIII
		System.out.println(NumeralToRoman(num));
		System.out.println(RomanToNumeral("MCMXCVIII"));
	}
	
	
	static int RomanToNumeral(String str) {
		int ans=0;
		
		for (int i=0; i<str.length(); i++) {
			int num = 0;
			int x=0, y=0;
			
			x = getNumeral(str.charAt(i));
			if (i<str.length()-1) {y=getNumeral(str.charAt(i+1));}
			
			if (x<y) {
				num = y-x; i++;
			} else if (x==y) {
				num = y+x; i++;
			} else {
				num = x;
			}
			ans+=num;
		}
		return ans;
	}
	
	static int getNumeral(char c) {
		int r=0;
		switch (c) {
			case 'M':r=1000; break;
			case 'D':r=500; break;
			case 'C':r=100; break;
			case 'L':r=50; break;
			case 'X':r=10; break;
			case 'V':r=5; break;
			case 'I':r=1; break;
		}
		return r;
	}
	
	static String NumeralToRoman(int n) {
		String out="";
		String numeral = String.valueOf(n);
		for (int i=0; i<numeral.length(); i++) {
			out+=getRoman(numeral.charAt(i)-'0',numeral.length()-1-i);
		}
		
		return out;
	}
	
	static String getRoman(int n, int pow) {
		String r="";
		String romanWord[] = {"I","X","C","M","V","L","D"};
		switch (n) {
			case 0: break;
			case 1: r=romanWord[pow]; break;
			case 2: r=romanWord[pow]+romanWord[pow]; break;
			case 3: r=romanWord[pow]+romanWord[pow]+romanWord[pow]; break;
			case 4: r=romanWord[pow]+romanWord[pow+4]; break;
			case 5: r=romanWord[pow+4]; break;
			case 6: r=romanWord[pow+4]+romanWord[pow]; break;
			case 7: r=romanWord[pow+4]+romanWord[pow]+romanWord[pow]; break;
			case 8: r=romanWord[pow+4]+romanWord[pow]+romanWord[pow]+romanWord[pow]; break;
			case 9: r=romanWord[pow]+romanWord[pow+1]; break;
		}

		return r;
	}
}
