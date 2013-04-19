package com.acme;

import com.acme.domain.bank.Client;

public class PrintClientListener implements ClientRegistrationListener {

	@Override
	public void onClientAdded(Client client) {
		System.out.println(client);
	}

}
