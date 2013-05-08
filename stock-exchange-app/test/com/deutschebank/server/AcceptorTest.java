package com.deutschebank.server;

import static org.junit.Assert.*;

import org.junit.Test;

import com.deutschebank.exceptions.IllegalPriceException;
import com.deutschebank.order.Order;

public class AcceptorTest {

	@Test
	public void shouldParseRightFormatWhenMathNotifyCalled()
			throws IllegalPriceException {
		int id = 0;
		float price = 10.44F;
		int amount = 100;
		String counterparty = "Bill";
		
		String s = "MATCH;" + id + ";" + counterparty + ";" + price + ";"
				+ amount;
		Acceptor a = new Acceptor(null, null);
		Order orderOwner = new Order(price,null , amount, id, null);

		assertEquals(s, a.constructMatchMessage(orderOwner, counterparty));

	}

}
