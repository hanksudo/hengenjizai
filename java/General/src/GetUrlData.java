import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class GetUrlData {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		java.net.URL srcUrl = new URL("http://www.kuas.edu.tw");
		BufferedReader in = new BufferedReader(new InputStreamReader(srcUrl.openStream()));
		String tmp;
		
		tmp = in.readLine();
		while(null != tmp) {
			System.out.println(tmp);
			tmp = in.readLine();
		}
		in.close();
	}

}
