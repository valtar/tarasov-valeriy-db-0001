package com.db.httpserver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.Assert;

import org.junit.Test;

public class Tester {

	private int port = 8080;

	private int timeout = 1;

	private int size = 1000;

	private CountDownLatch latch = new CountDownLatch(size);

	private AtomicInteger nErrors = new AtomicInteger(0);

	private AtomicInteger nTimoutErrors = new AtomicInteger(0);

	private ExecutorService executor;

	public void failTest() {
		nErrors.incrementAndGet();
	}

	public class ServerThread implements Runnable {
		@Override
		public void run() {

			try {

				WorkerThread.startPool(port);
			} catch (IOException e) {

				System.out.println(e);
			}
		}
	}

	public class ClientThread implements Runnable {

		@Override
		public void run() {

			latch.countDown();
			try {

				latch.await();
			} catch (InterruptedException e1) {}

			try {

				Socket socket = new Socket("localhost", port);
				socket.setSoTimeout(timeout);
				
				String stringRequest = "GET from 0:0:0:0:0:0:0:1(63214): C:\\DB_0001\\workspace\\javaschool-io-->200";
				
				
				OutputStream out = socket.getOutputStream();
			
				
				for(int i = 0; i < 100; i++){
				
					out.write(stringRequest.getBytes());
					read(socket);
				}

				 socket.close();
			} catch (SocketTimeoutException e) {
				nTimoutErrors.incrementAndGet();
				failTest();
			} catch (UnknownHostException e) {
				
				failTest();
			} catch (IOException e) {

				failTest();
			}

		}

		protected void read(Socket socket) throws IOException {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));

			while (reader.ready())
						reader.readLine();
		}

	}

	@Test
	public void test() throws IOException, InterruptedException {
		new Thread(new ServerThread()).start();

		executor = Executors.newFixedThreadPool(size);

		for (int i = 0; i < size ; i++) {
	
			executor.execute(new ClientThread());
		}
		
		latch.await();
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.SECONDS);
		
		Assert.assertEquals("timeout error", 0, nTimoutErrors.get());
		Assert.assertEquals("all errors", 0, nErrors.get());
	}
}
