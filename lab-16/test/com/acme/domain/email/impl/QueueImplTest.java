package com.acme.domain.email.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

}
