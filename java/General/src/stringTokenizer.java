import java.util.StringTokenizer;

/**
 * @comment: java.util.StringTokenizer ¦r¦ê¤À³Î½d¨Ò
 * @author Han-Hong Wang
 * @date 2007/3/11
 */
public class stringTokenizer {
	public static void main(String[] args) {
		// Sample 1 (Delimiter is " ")
		StringTokenizer st = new StringTokenizer("Welcome to JAVA planet!!");
		while(st.hasMoreTokens()) {
			  System.out.println(st.nextToken());
		}
		
		// Sample 2 (Delimiter is ",")
		StringTokenizer st2= new StringTokenizer("Red,Orange,Blue,Green", ",");
		while(st2.hasMoreTokens()) {
			System.out.println(st2.nextToken());
		}
		
		// Sample 3 (Delimiter is "+" and "=")
		StringTokenizer st3= new StringTokenizer("1+3=4", "+=");
		while(st3.hasMoreTokens()) {
			System.out.println(st3.nextToken());
		}
	}
}
