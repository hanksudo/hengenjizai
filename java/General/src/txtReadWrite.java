import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 找出特定字開頭然後輸出
 * @author hank
 *
 */

public class txtReadWrite {
	public static void main(String[] args) {
		try {
			BufferedReader inputStream = new BufferedReader(new FileReader("C:\\test.txt"));
			PrintStream outputStream = new PrintStream(new FileOutputStream("C:\\b.txt"));
			String in;
			while ((in=inputStream.readLine()) != null) {
				if (in.charAt(0)=='#') {
					int endIndex=0;
					if (in.indexOf(' ')>0) {
					 	endIndex=in.indexOf(' ');
					} else {
						endIndex=in.length();
					}
//					System.err.println(in.substring(1, endIndex));
					outputStream.println(in.substring(1, endIndex));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
