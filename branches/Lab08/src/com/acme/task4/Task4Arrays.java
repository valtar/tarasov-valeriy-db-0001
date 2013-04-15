package com.acme.task4;

public class Task4Arrays {
	public static void main(String[] args) {

		int array[] = new int[3];
		array[0] = 3;
		array[1] = 7;
		array[2] = 3;
		// ...
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

		// TODO: print the following
		System.out.println("Number of 3: " + i3);
		System.out.println("Number of 7: " + i7);
		System.out.println("Number of 9: " + i9);
	}
}
