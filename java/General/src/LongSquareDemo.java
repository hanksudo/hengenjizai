/**
 * ―long程9223372036854775807キよ
 * 2008-12-17
 * ノ计厩衡猭衡 ЧΘㄢ计
 */
public class LongSquareDemo {

	public static void main(String[] args) {
		new LongSquare("99");
	}
}

class LongSquare {
	private int num[] = new int[99999];
	
	public LongSquare(String s) {
		// 锣计皚
		int i;
		for(i=0; i<s.length(); i++) {
			num[i] = Character.getNumericValue(s.charAt(s.length()-i-1));
		}
		Square(num, i);
	}
	
	public void Square(int[] n, int digit) {
		int sum; //羆㎝
		int c=0;	//秈
		int temp = n[0]*n[0];
		int m = n[1];

		// 场 
		int i=0;
		c=n[0];
		while(c!=0) {
			sum=n[i]+c;
			n[i]=sum%10;
			c=sum/10;
			i++;
		}

		// 场
		c=0;
		for(i=0;i<3;i++) {
			sum=n[i]*m;
			n[i]=sum%10+c;
			c=sum/10;	
		}
				
		// 秈
		int j=i;
		for(i=j;i>0;i--) {
			n[i]=n[i-1];
		}
		n[0]=0;
			
		// 计
		i=0;
		c=temp;
		while(c!=0) {
			n[i]+=c;
			c=n[i]/10;
			n[i]%=10;
			i++;
		}
		
		boolean findNum=false;
		for(int k=(digit*2)+1; k>=0; k--) {
			if(findNum||n[k]!=0) {
				System.err.print(n[k]);
				findNum=true;
			}
		}		
	}
}