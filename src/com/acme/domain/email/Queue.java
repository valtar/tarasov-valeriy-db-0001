package com.acme.domain.email;

import com.acme.domain.email.impl.AddToClosedQueueException;


public interface Queue {

	public Email getEmail() throws InterruptedException;

	public void addEmail(Email email) throws AddToClosedQueueException;

	public void close();
	
	public boolean isEmpty();
}
