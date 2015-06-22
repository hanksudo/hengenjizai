import java.math.BigInteger;

public class demo {
	public static void main(String[] args) {
		BigInteger bigInteger = BigInteger.ZERO;
		int end = 100;
		for (int i = 1; i <= end; i++) {
			bigInteger = bigInteger.add(BigInteger.ONE);
			if (bigInteger.isProbablePrime(10)) {
				System.out.println(i);
			}
		}
	}
}