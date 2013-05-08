package com.deutschebank.server;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.deutschebank.client.Client;
import com.deutschebank.exceptions.ParseException;
import com.deutschebank.order.Order;
import com.deutschebank.order.OrderType;
import com.deutschebank.stock.StockType;

public class ParserTest {

	@Test
	public void shouldParseClientName() throws ParseException {
		String name = "BILL";
		String message = "LOGIN;"+ name;
		
		Parser parser = new Parser();
		Client client = parser.getClientFromString(message, null);
		
		assertEquals(name, client.getName());
	}
	
	@Test
	public void shouldParseBuyOrder() throws ParseException{
		String message = "ORDER;1;ASK;GOLD;95.1;10";
		
		Parser parser = new Parser();
		Order order = parser.getOrderFromString(message);
		
		assertEquals(OrderType.BUY, order.getOrderType());
		assertEquals(StockType.GOLD, order.getStockType());
		assertEquals(10,order.getAmount());
		assertEquals(95.1,order.getPrice(),5);
		assertEquals(1,order.getClientId());
	}
	
	@Test
	public void shouldParseSellOrder() throws ParseException{
		String message = "ORDER;2;BID;GEMS;10.5;100";
		
		Parser parser = new Parser();
		Order order = parser.getOrderFromString(message);
		
		assertEquals(OrderType.SELL, order.getOrderType());
		assertEquals(StockType.GEMS, order.getStockType());
		assertEquals(100,order.getAmount());
		assertEquals(10.5,order.getPrice(),5);
		assertEquals(2,order.getClientId());
	}
	

	
}


