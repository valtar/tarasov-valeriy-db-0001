package com.acme;

import com.acme.domain.bank.Client;

public class EmailNotificatuonListener implements ClientRegistrationListener{

	@Override
	public void onClientAdded(Client client) {
		System.out.println("Notification email for client " + client + " to be sent");
	}

}
