package com.acme.task4;

public class Task4Arrays {
	public static void main(String[] args) {
		final int SIZE = 10;
		int array[] = new int[SIZE];
		for (int i = 0; i < array.length; i++) {
			switch ((int) ((Math.random() * 10) % 3)) {
			case 0:
				array[i] = 3;
				break;
			case 1:
				array[i] = 7;
				break;
			case 2:
				array[i] = 9;
				break;

			default:
				break;
			}
		}

		int i3 = 0, i7 = 0, i9 = 0;
		for (int i : array) {
			switch (i) {
			case 3:
				i3++;
				break;
			case 7:
				i7++;
				break;
			case 9:
				i9++;
				break;
			default:
				System.out.println("illegal massive: " + i);
			}
		}

		System.out.println("Number of 3: " + i3);
		System.out.println("Number of 7: " + i7);
		System.out.println("Number of 9: " + i9);
	}

}
