/*
 * �ϥ� Scanner Ū���ϥΪ̿�J��r
 * ���iŪ�J�ťզr����J
 * 2006/12/26
 */
import java.util.Scanner;

public class GetInput_Scanner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please input your name: ");
        String getStr = scan.next();	//���o�r��
        System.out.println("Hello! "+getStr+".");
        
        System.out.print("How old are you?");
        int age = scan.nextInt();	//���o�Ʀr
        System.out.println("You are "+age+" years old.");
    }
}