/*
 * 使用 Scanner 讀取使用者輸入文字
 * 不可讀入空白字元輸入
 * 2006/12/26
 */
import java.util.Scanner;

public class GetInput_Scanner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please input your name: ");
        String getStr = scan.next();	//取得字串
        System.out.println("Hello! "+getStr+".");
        
        System.out.print("How old are you?");
        int age = scan.nextInt();	//取得數字
        System.out.println("You are "+age+" years old.");
    }
}