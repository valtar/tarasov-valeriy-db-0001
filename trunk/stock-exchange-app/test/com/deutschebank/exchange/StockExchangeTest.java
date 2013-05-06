package com.deutschebank.exchange;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.BeforeClass;
import org.junit.Test;

import com.deutschebank.client.Client;
import com.deutschebank.exceptions.IllegalPriceException;
import com.deutschebank.order.Order;
import com.deutschebank.order.OrderType;
import com.deutschebank.stock.StockType;

public class StockExchangeTest {

	@Test
	public void shouldNotifyRightClientsWhenOrdersMatch() throws IllegalPriceException {
		StockExchange se = new StockExchange();
		Client buyer = mock(Client.class);
		Client seller = mock(Client.class);
		Client some = mock(Client.class);
		
		OrderType s = OrderType.SELL;
		OrderType b = OrderType.BUY;
		StockType type = StockType.GOLD;
		
		Order sell = new Order(1,s,1,1,type);
		Order buy = new Order(1,b,1,1,type);
		Order someOrder = new Order(1,b,1,1,type);
		
		se.add(seller, sell);
		se.add(buyer, buy);
		se.add(some, someOrder);
		
		verify(seller).matchNotify(any(Order.class));
		verify(buyer).matchNotify(any(Order.class));
		verify(some,times(0)).matchNotify(any(Order.class));
	}
	

}
