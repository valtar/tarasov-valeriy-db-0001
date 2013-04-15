package com.acme.task2;

import java.util.ArrayList;
import java.util.List;


public class GCTest {
	byte array[] = new byte[1024];

	public static void main(String[] args) {
		//TODO: modify numberOfIterations to check that GC is called
		int numberOfIterations = 10000000;
		
		List<Foo> list = new ArrayList<Foo>(numberOfIterations);
		for (int i = 0; i < numberOfIterations; i++) {
			//TODO: collect references to foo at an array to prevent object being garbage collected
			Foo foo = new Foo();
			list.add(foo);
		}
	}
}
