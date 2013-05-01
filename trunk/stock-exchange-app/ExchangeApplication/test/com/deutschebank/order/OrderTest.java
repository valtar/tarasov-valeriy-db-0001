package com.deutschebank.order;

import static org.junit.Assert.*;

import org.junit.Test;

import com.deutschebank.exceptions.IllegalPriceException;

public class OrderTest {

	@Test
	public void shouldBeGreaterWhenPriceBigger() throws IllegalPriceException {
		OrderType type = OrderType.BUY;
		Order lowPrice = new Order(1, type, 1);
		Order highPrice = new Order(10, type, 1);

		int isRightOrdered = highPrice.compareTo(lowPrice);

		assertTrue(isRightOrdered > 0);

	}

	@Test
	public void shouldBeGreaterWhenCreatedEarlier() throws IllegalPriceException {
		OrderType type = OrderType.BUY;
		Order firstCreated = new Order(1, type, 1);
		Order lastCreated = new Order(1, type, 1);

		int isRightOrdered = firstCreated.compareTo(lastCreated);

		assertTrue(isRightOrdered > 0);
	}
}
