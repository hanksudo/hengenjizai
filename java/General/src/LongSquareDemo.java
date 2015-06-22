/**
 * �Dlong���O���̤j��9223372036854775807������
 * 2008-12-17
 * �ΦL�׼ƾǺ�k�� �u��������
 */
public class LongSquareDemo {

	public static void main(String[] args) {
		new LongSquare("99");
	}
}

class LongSquare {
	private int num[] = new int[99999];
	
	public LongSquare(String s) {
		// �ର�Ʀr�}�C
		int i;
		for(i=0; i<s.length(); i++) {
			num[i] = Character.getNumericValue(s.charAt(s.length()-i-1));
		}
		Square(num, i);
	}
	
	public void Square(int[] n, int digit) {
		int sum; //�`�M
		int c=0;	//�i��
		int temp = n[0]*n[0];
		int m = n[1];

		// �ۥ[���� 
		int i=0;
		c=n[0];
		while(c!=0) {
			sum=n[i]+c;
			n[i]=sum%10;
			c=sum/10;
			i++;
		}

		// �ۭ�����
		c=0;
		for(i=0;i<3;i++) {
			sum=n[i]*m;
			n[i]=sum%10+c;
			c=sum/10;	
		}
				
		// �i��
		int j=i;
		for(i=j;i>0;i--) {
			n[i]=n[i-1];
		}
		n[0]=0;
			
		// �[�Ӧ��
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