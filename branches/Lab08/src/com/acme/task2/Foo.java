package com.acme.task2;

public class Foo {
	static long iteration = 0L;
	public Foo() {
		super();
		iteration++;
	}
	
	@Override
	protected void finalize() throws Throwable {
		//TODO: print to console that finalize() was called	
		System.out.println("finalize() was called in iteration " + iteration);
		super.finalize();
	}
}
