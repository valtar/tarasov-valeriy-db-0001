package com.acme.domain.email.impl;

import java.util.ArrayList;
import java.util.List;

import com.acme.domain.email.Email;
import com.acme.domain.email.Queue;

public class QueueImpl implements Queue {

	private List<Email> mails = new ArrayList<Email>(MAX_EMAILS);
	private boolean isClosed = false;
	private static final int MAX_EMAILS = 10;
	

	public synchronized void addEmail(Email email)
			throws AddToClosedQueueException {
		if (!isClosed) {
			mails.add(email);
			notify();
		} else {
			throw new AddToClosedQueueException();
		}
	}

	public synchronized void close() {
		isClosed = true;
		notifyAll();
	}
	
	public boolean isClosed(){
		return isClosed;
	}

	public synchronized Email getEmailAndRemove() throws InterruptedException {
		while (isEmpty()) {
			wait();
			if(isEmpty() && isClosed){
				throw new InterruptedException();
			}
		}
		return mails.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return mails.isEmpty();
	}

}
