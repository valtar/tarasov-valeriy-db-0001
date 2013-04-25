package com.acme.domain.email.impl;

import java.util.ArrayList;
import java.util.List;

import com.acme.domain.email.Email;
import com.acme.domain.email.Queue;

public class QueueImpl<T> implements Queue<T> {

	private List<T> items = new ArrayList<T>(MAX_EMAILS);
	private boolean isClosed = false;
	private static final int MAX_EMAILS = 10;
	

	public synchronized void addItem(T item)
			throws AddToClosedQueueException {
		if (!isClosed) {
			items.add(item);
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

	public synchronized T getItem() throws InterruptedException {
		while (isEmpty()) {
			wait();
			if(isEmpty() && isClosed){
				throw new InterruptedException();
			}
		}
		return items.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}

	

}
