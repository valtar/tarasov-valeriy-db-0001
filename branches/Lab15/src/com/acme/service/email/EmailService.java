package com.acme.service.email;

import com.acme.domain.email.Email;
import com.acme.domain.email.Queue;
import com.acme.domain.email.impl.AddToClosedQueueException;
import com.acme.domain.email.impl.QueueImpl;

public class EmailService implements Runnable {

	private static EmailService instance = new EmailService();

	private Queue queue = new QueueImpl();

	private void sendEmail(Email email) {
		System.out.println(email);
	}

	private boolean serviceClosed = false;

	private EmailService() {
		new Thread(){

			@Override
			public void run() {
				try {
					while (!serviceClosed) {
						sendEmail(queue.getEmail());
					}
					while (!queue.isEmpty()) {
						sendEmail(queue.getEmail());
					}
				} catch (InterruptedException e) {
				}
			}
			
		}.start();
	}

	public static EmailService getEmailService() {
		return instance;
	}

	public void sendNotificationEmail(Email email) {
		try {
			queue.addEmail(email);
		} catch (AddToClosedQueueException e) {
			sendEmail(email);
		}
	}

	public void close() {
		serviceClosed = true;
		queue.close();
	}

	public boolean isClosed() {
		return serviceClosed;
	}

	@Override
	public void run() {
		

	}

}
