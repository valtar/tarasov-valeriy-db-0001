package com.acme.task1;

public class Calculator {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("illegal number of arguments");
			return;
		}

		double operand1 = 0.0;
		double operand2 = 0.0;
		char operation = '\u0000';

		try {
			operand1 = Double.parseDouble(args[0]);
			operand2 = Double.parseDouble(args[2]);
			operation = args[1].charAt(0);
		} catch (Exception ex) {
			System.out.println("illegal arguments");
			return;
		}

		if (operand2 == 0.0) {
			System.out.println("division by zero");
			return;
		}
		double result = 0;
		switch (operation) {
		case '+':
			result = operand1 + operand2;
			break;
		case '-':
			result = operand1 - operand2;
			break;
		case '*':
			result = operand1 * operand2;
			break;
		case '/':
			result = operand1 / operand2;
			break;
		default:
			System.out.println("illegal operation: " + operation);
			return;
		}

		System.out.println(operand1 + " " + operation + " " + operand2 + " = "
				+ result);
	}
}
