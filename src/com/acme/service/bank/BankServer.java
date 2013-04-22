package com.acme.service.bank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.acme.domain.bank.Bank;
import com.acme.domain.bank.Client;

public class BankServer {
	private ServerSocket providerSocket = null;

	private static class BankReport {
		public static boolean addClientFromString(String line, Bank bank) {
			Client client = BankDataLoader.addClientFromString(bank, line);
			return client != null ? true : false;
		}
	}

	public void startServer(final Bank bank) {
		// this.bank = bank;
		try {
			// 1. creating a server socket
			providerSocket = new ServerSocket(2004, 10);
			while (true) {
				// 2. Wait for connection
				System.out.println("Waiting for connection");
				final Socket connection = providerSocket.accept();

				runSession(connection, bank);

			}

		} catch (Exception e) {
			System.out.println(e);
			try {
				providerSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private synchronized void runSession(Socket connection, Bank bank) {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try {
			String message = "";
			System.out.println("Connection received from "
					+ connection.getInetAddress().getHostName());
			// 3. get Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			sendMessage("Connection successful", out);
			// 4. The two parts communicate via the input and output streams
			do {
				try {
					message = (String) in.readObject();
					System.out.println("client>" + message);
					if (message.equals("bye"))
						sendMessage("bye", out);
					else if (message.contains("add ")) {
						boolean success = BankReport.addClientFromString(
								message.substring("add ".length()), bank);
						if (success) {
							message = "Client added successfull";
						} else {
							message = "Client adding fails";
						}

						System.out.println("server>" + message);
					} else {
						message = "Illegal command";
						System.out.println("client>" + message);
					}
				} catch (ClassNotFoundException classnot) {
					System.err.println("Data received in unknown format");
				}
			} while (!message.equals("bye"));
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 4: Closing connection
			try {
				in.close();
				out.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	void sendMessage(final String msg, ObjectOutputStream out) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("server>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Bank bank = new Bank();
		System.out.println(BankReport.addClientFromString(
				"accounttype=c;balance=100;overdraft=50;name=John;gender=m;",
				bank));
		System.out.println(BankReport.addClientFromString(
				"accounttype=c;balance=100;overdraft=50;name=John;gender=m;",
				bank));
		System.out.println(BankReport.addClientFromString(
				"accounttype=c;balance=100;overdraft=50;name=John;gender=f;",
				bank));
		System.out.println(BankReport.addClientFromString(
				"accounttype=c;balance=100;overdraft=50;name=Jo;gender=m;",
				bank));

	}

}
