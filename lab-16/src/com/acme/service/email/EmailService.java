package com.acme.service.email;

import com.acme.domain.email.Email;
import com.acme.domain.email.Queue;
import com.acme.domain.email.impl.AddToClosedQueueException;
import com.acme.domain.email.impl.QueueImpl;

public class EmailService implements Runnable {

	private static EmailService instance;

	private static Object monitor = new Object();

	private Queue<Email> queue = new QueueImpl<Email>();


	private void sendEmail(Email email) {
		System.out.println(email);
	}

	private EmailService() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		{
			try {
				while (!queue.isClosed()) {
					sendEmail(queue.getItem());
				}
				while (!queue.isEmpty()) {
					sendEmail(queue.getItem());
				}
			} catch (InterruptedException e) {
			}
		}

	}

	public static EmailService getEmailService() {
		if (EmailService.instance == null) {
			synchronized (monitor) {
				if (EmailService.instance == null) {
					instance = new EmailService();
				}
			}
		}

		return instance;
	}

	public void sendNotificationEmail(Email email) {
		try {
			queue.addItem(email);
		} catch (AddToClosedQueueException e) {
			sendEmail(email);
		}
	}

	public void close() {
		queue.close();
	}

	public boolean isClosed() {
		return queue.isClosed();
	}

}
