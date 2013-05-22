package com.db.httpserver;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;


public class Tester {
	private int port = 8080;
	int size = 10000;
	private CountDownLatch latch = new CountDownLatch(size);
	@Test
	public void foo() throws IOException, InterruptedException{
		new Thread(){

			@Override
			public void run() {
		
				try {
					WorkerThread.startPool(port);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(size);
		class Foo implements Runnable{

			@Override
			public void run() {
				latch.countDown();
				try {
					latch.await();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Socket socket = new Socket("localhost", port);
					socket.getOutputStream().write(new byte[] {1,2} );
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		for (int i = 0; i < size; i++) {
			executor.execute(new Foo());
		}
		
		executor.shutdown();
		executor.awaitTermination(1000, TimeUnit.SECONDS);
	}
}
