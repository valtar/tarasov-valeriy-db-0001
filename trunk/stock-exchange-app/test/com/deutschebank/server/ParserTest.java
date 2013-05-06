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
		String message = "ASK;GOLD;95;10;1";
		
		Parser parser = new Parser();
		Order order = parser.getOrderFromString(message);
		
		assertEquals(OrderType.BUY, order.getOrderType());
		assertEquals(StockType.GOLD, order.getStockType());
		assertEquals(95,order.getAmount());
		assertEquals(10,order.getPrice(),5);
		assertEquals(1,order.getClientId());
	}
	
	@Test
	public void shouldParseSellOrder() throws ParseException{
		String message = "BID;GEMS;100;10.5;2";
		
		Parser parser = new Parser();
		Order order = parser.getOrderFromString(message);
		
		assertEquals(OrderType.SELL, order.getOrderType());
		assertEquals(StockType.GEMS, order.getStockType());
		assertEquals(100,order.getAmount());
		assertEquals(10.5,order.getPrice(),5);
		assertEquals(2,order.getClientId());
	}
	

	
}


