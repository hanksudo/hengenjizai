package sampleAbstract;

/**
 * 
 * @author hank
 * ©â¶H¤Æ abstract ½d¨Ò
 *
 */
public abstract class AbstractGuessNumber {
	private int number;
	
	protected abstract void showMessage(String message);
	protected abstract int getUserInput();
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void start() {
		showMessage("Welcome");
		
		int guess;
		do {
			guess = getUserInput();
			if (guess > number) {
				showMessage("bigger than the goal number.");
			} else if (guess < number) {
				showMessage("smaller than the goal number.");
			} else {
				showMessage("you win");
			}
		} while (guess != number);
	}
	
}
