/**
 * @comment: �Q��Do While Loop�g�E�E���k�æ����b�\��
 * @author Han-Hong Wang (Hank)
 * @date 2007/3/13
 */


import java.util.Scanner;

public class dowhileMultiplication {	
	public static void main(String[] args) {
		int n;
		System.out.print("Please input a number(1-9): ");
		try {
			Scanner input = new Scanner(System.in);
			n = input.nextInt();
			
			if (n>0&&n<10) {
				int i=1;
				do {
					System.out.println(n+" * "+i+" = "+n*i);
				} while(++i<10);
			} else {
				main(null);
			}
		} catch (Exception e) {
			System.out.println("�榡���~!!");
			main(null);
		}
	}
}
