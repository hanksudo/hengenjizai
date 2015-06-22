package sampleAbstract;

import java.util.Scanner;

public class ConcreteGuessNumber extends AbstractGuessNumber {
	private Scanner scanner;
	
	public ConcreteGuessNumber() {
		scanner = new Scanner(System.in);
	}
	
	protected void showMessage(String message) {
		System.out.println(message + "!");
	}
	
	protected int getUserInput() {
		System.out.println("input a number: ");
		return scanner.nextInt();
	}
}
