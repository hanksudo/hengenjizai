package sampleInterface;

public class Test {
	public static void main(String[] args) {
		for (int i=0; i<10 ; i++) {
			int n = (int) (Math.random() * 10) % 2;
			switch (n) {
				case 0:
					doRequest(new HelloRequest("Han-Hong"));
					break;
				case 1:
					doRequest(new WelcomeRequest("JAVA Planet"));
					break;
			}
		}
	}
	
	public static void doRequest(IRequest request) {
		request.execute();
	}
}
