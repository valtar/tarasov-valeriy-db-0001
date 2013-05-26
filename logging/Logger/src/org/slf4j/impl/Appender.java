package org.slf4j.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Appender {

	private BlockingQueue<String> queue = new LinkedBlockingQueue<>();
	private File file = new File("log.info");
	private BufferedWriter writer;
	
	private class AppendWorker implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					String msg = queue.take();
					writer.write(msg);
					writer.newLine();
					writer.flush();
					System.out.println(msg);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupted();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	public Appender() {
		new Thread(new AppendWorker()).start();
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		} catch (FileNotFoundException e) {
			System.err.println("Ooops");
		}
	}

	public void append(String str) {
		try {
			queue.put(str);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupted();
		}
	}
}
