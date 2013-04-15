package com.acme.task3;

import java.util.Arrays;

public class Task3Arrays {
	public static void main(String[] args) {
//		for (int i = 1; i <= 50; i++) {
//			if(i%3 == 0){
//				System.out.println("Число " + i + " кратно трем");
//			}
//		}
		int n = 50;
		int[] mass = new int[n];
		for (int i = 0; i < mass.length; i++) {
			mass[i] = i + 1;
		}
		
		double avg = 0.0;
		for (int i : mass) {
			avg += i;
		}
		System.out.println(avg/mass.length);

		
	}
}
