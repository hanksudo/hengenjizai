
public class Regex {
	public static void main(String[] args) {	
		String a= "</body bgcolor=black>abc</html>";
		String tagPattern = "</?[a-z][a-z0-9]*[^<>]*>";
        System.out.println("Before:" + a);
        System.out.println("After :" + a.replaceAll(tagPattern, ""));
	}

}
