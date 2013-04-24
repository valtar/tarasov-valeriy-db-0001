package com.acme.service.email;

import java.util.Random;

import com.acme.domain.email.Email;
import com.acme.domain.email.impl.EmailImpl;

public class EmailServiceTester {

	public static void main(final String[] args) {
		new EmailServiceTester().go();
	}

	private void go() {
		EmailService service = EmailService.getEmailService();

		for (int i = 0; i < 10; i++) {
			Email email = new EmailImpl();
			email.setFrom("User_" + new Random().nextDouble());
			email.setTo("To_" + new Random().nextDouble());
			email.setSubject("Subject_" + new Random().nextDouble());
			email.setMessage("Message_" + new Random().nextDouble());

			service.sendNotificationEmail(email);
		}

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		service.close();
	}
}
