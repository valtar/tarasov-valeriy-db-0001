package com.acme.mock;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class BankClient {

	private Socket requestSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String message;

	public void startClient() {
		try {
			requestSocket = new Socket("localhost", 2004);
			System.out.println("Connected to localhost in port 2004");
			
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			
			try {
				message = (String) in.readObject();
				System.out.println("server>" + message);
				sendMessage("add accounttype=c;balance=100;overdraft=50;name=JohnDontExist;gender=m;");
				do {
					message = "bye";
					sendMessage(message);
					message = (String) in.readObject();
				} while (!message.equals("bye"));
			} catch (ClassNotFoundException classNot) {
				System.err.println("data received in unknown format");
			}
		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			
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
		BankClient bc = new BankClient();
		bc.startClient();
	}

}
