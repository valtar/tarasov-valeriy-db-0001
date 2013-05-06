package com.deutschebank.order;

import static org.junit.Assert.*;

import org.junit.Test;

import com.deutschebank.exceptions.IllegalPriceException;
import com.deutschebank.stock.StockType;

public class OrderTest {

	@Test
	public void shouldBeGreaterWhenPriceBigger() throws IllegalPriceException {
		OrderType orderType = OrderType.BUY;
		StockType stockType = StockType.GOLD;
		
		Order lowPrice = new Order(1, orderType, 1,1,stockType);
		Order highPrice = new Order(10, orderType, 1,1,stockType);

		int isRightOrdered = highPrice.compareTo(lowPrice);

		assertTrue(isRightOrdered > 0);

	}

	@Test
	public void shouldBeGreaterWhenCreatedEarlier() throws IllegalPriceException {
		OrderType orderType = OrderType.BUY;
		StockType stockType = StockType.GOLD;
		
		Order firstCreated = new Order(1, orderType, 1,1,stockType);
		Order lastCreated = new Order(1, orderType, 1,1,stockType);

		int isRightOrdered = firstCreated.compareTo(lastCreated);

		assertTrue(isRightOrdered > 0);
	}
}
