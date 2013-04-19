package com.acme.task2;

public class Foo {
	@SuppressWarnings("unused")
	private byte array[] = new byte[1024];
	private int iteration = 0;

	public Foo(int iteration) {
		super();
		this.iteration = iteration;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalize() was called in iteration " + iteration);
		super.finalize();
	}
}
