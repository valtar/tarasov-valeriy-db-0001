package com.deutschebank.connection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
	private Socket requestSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String message;
	private boolean b = true;

	public void startClient() {
		try {
			// 1. creating a socket to connect to the server
			requestSocket = new Socket("localhost", 2004);
			System.out.println("Connected to localhost in port 2004");
			// 2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			// 3: Communicating with the server
			String m = "";

			Thread t = new Thread() {
				String s = "";

				@Override
				public void run() {
					try {
						while (b) {
							s = (String) in.readObject();
							System.out.println(s);
						}
					} catch (ClassNotFoundException | IOException e) {
//						e.printStackTrace();
						System.out.println("read thread exit");
					}
				}

			};
			t.start();

			try {
				Scanner scanner = new Scanner(System.in);
				do {
					m = scanner.next();
					out.writeObject(m);
				} while (!"LOGOUT".equals(m));
				b = true;
			} catch (IOException e) {
				System.err.println("data received in unknown format");
			}
		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 4: Closing connection
			try {
				in.close();
				out.close();
				requestSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	void sendMessage(final String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.startClient();
	}
}