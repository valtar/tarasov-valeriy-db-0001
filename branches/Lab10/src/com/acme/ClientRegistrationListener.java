package com.acme;

import com.acme.domain.bank.Client;


public interface ClientRegistrationListener {
	public void onClientAdded(Client client);
}
