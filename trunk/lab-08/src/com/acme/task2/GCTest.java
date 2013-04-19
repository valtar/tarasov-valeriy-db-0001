package com.acme.task2;

public class GCTest {

	public static void main(String[] args) {
		int numberOfIterations = 10000000;

		Foo[] list = new Foo[numberOfIterations];
		for (int i = 0; i < numberOfIterations; i++) {
			Foo foo = new Foo(i);
			list[i] = foo;
		}
	}
}
