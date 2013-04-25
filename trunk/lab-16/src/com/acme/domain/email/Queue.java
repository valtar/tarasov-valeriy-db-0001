package com.acme.domain.email;

import com.acme.domain.email.impl.AddToClosedQueueException;


public interface Queue<T> {

	public T getItem() throws InterruptedException;

	public void addItem(T item) throws AddToClosedQueueException;

	public void close();
	
	public boolean isClosed();
	
	public boolean isEmpty();
}
