package com.acme.task3;

public class Task3Arrays {
	public static void main(String[] args) {
		for (int i = 1; i <= 50; i++) {
			if (i % 3 == 0) {
				System.out.println("Число " + i + " кратно трем");
			}
		}

		int n = 50;
		int[] mass = new int[n];
		for (int i = 0; i < mass.length; i++) {
			mass[i] = i + 1;
		}

		double avg = avg(mass);
		System.out.println("avg = " + avg);

	}

	private static double avg(int[] mass) {
		double avg = 0.0;
		for (int i : mass) {
			avg += i;
		}
		return avg / mass.length;
	}
}
