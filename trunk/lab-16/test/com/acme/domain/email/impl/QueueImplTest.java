package com.acme.domain.email.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.acme.domain.email.Queue;

public class QueueImplTest {	
	@Test
	public void shouldAtFirstBeEmptyAndNotEmptyWhenAdd() throws AddToClosedQueueException {
		//given
		Queue<Object> queue = new QueueImpl<Object>();
		boolean beforeAdd = queue.isEmpty();
		
		//when
		queue.addItem(new Object());
		boolean afterAdd = queue.isEmpty();
		
		//then
		assertTrue(beforeAdd);
		assertFalse(afterAdd);
	}
	
	@Test
	public void shouldReturnFirstAdedItemWhenSeveralItemsAdded() throws AddToClosedQueueException, InterruptedException{
		//given
		Queue<Integer> queue = new QueueImpl<Integer>();
		Integer item1 = new Integer(1);
		Integer item2 = new Integer(2);
		Integer item3 = new Integer(3);
		queue.addItem(item1);
		queue.addItem(item2);
		queue.addItem(item3);

		//when
		Integer returnedItem = queue.getItem();
		
		//then
		assertEquals(item1, returnedItem);
		
	}
	
	@Test(timeout=2000)
	public void shouldReturnItemWhenTryToGetEmptyAndAnotherThreadAddes() throws InterruptedException{
		//given
		final Queue<Object> queue = new QueueImpl<Object>();
		
		//when
		new Thread(){
			@Override
			public void run(){
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {}
				try {
					queue.addItem(new Object());
				} catch (AddToClosedQueueException e) {
					fail(e.toString());
				}
				
			}
		}.start();
		Object obj = queue.getItem();
		
		//then
		assertNotNull(obj);
		
	}
	
	@Test
	public void shouldThrowExceptionWhenInClosedQueueAdds() {
		//given
		Queue<Object> queue = new QueueImpl<Object>();
		queue.close();

		//when
		try {
			queue.addItem(new Object());
		} catch (AddToClosedQueueException e) {
		
		//then
			assertTrue(true);
		}		
		
	}
	

}
