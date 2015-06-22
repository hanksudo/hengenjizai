package sampleAbstract;

public class Play {
	public static void main(String[] args) {
//		AbstractGuessNumber guessNum = new ConcreteGuessNumber();
		ConcreteGuessNumber guessNum = new ConcreteGuessNumber();
		guessNum.setNumber(50);
		guessNum.start();
	}
}
