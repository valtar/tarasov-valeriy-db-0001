package com.deutschebank.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.deutschebank.exceptions.IllegalPriceException;
import com.deutschebank.order.Order;
import com.deutschebank.order.OrderAnswer;
import com.deutschebank.order.OrderType;
import com.deutschebank.stock.StockGlass;
import com.deutschebank.stock.StockType;

public class StockGlassTest {
	StockType type;

	@Before
	public void setUp() {
		type = StockType.GOLD;
	}

	@Test
	public void shouldNotMatchWhenFirstTimeAdd() throws IllegalPriceException {
		StockGlass sg1 = new StockGlass(type);
		StockGlass sg2 = new StockGlass(type);
		Order sell = new Order(1, OrderType.SELL, 1);
		Order buy = new Order(1, OrderType.BUY, 1);

		OrderAnswer ansSell = sg1.addOrder(sell);
		OrderAnswer ansBuy = sg2.addOrder(buy);

		assertNull(ansSell);
		assertNull(ansBuy);
	}

	@Test
	public void shouldBuyAtBestPriceWhenSeveralSellersWithDifferentPrices()
			throws IllegalPriceException {
		StockGlass sg = new StockGlass(type);

		OrderType b = OrderType.BUY;
		OrderType s = OrderType.SELL;

		Order sell1 = new Order(1, s, 1);
		Order sell2 = new Order(2, s, 1);
		Order sell3 = new Order(3, s, 1);
		Order buy = new Order(2, b, 1);
		sg.addOrder(sell1);
		sg.addOrder(sell2);
		sg.addOrder(sell3);

		OrderAnswer ans = sg.addOrder(buy);

		assertEquals(sell1, ans.seller);
	}

	@Test
	public void shouldSellAtBestPriceWhenSeveralBuyersWithDifferentPrices()
			throws IllegalPriceException {
		StockGlass sg = new StockGlass(type);
		
		OrderType b = OrderType.BUY;
		OrderType s = OrderType.SELL;
		
		Order buy1 = new Order(1, b, 1);
		Order buy2 = new Order(2, b, 1);
		Order buy3 = new Order(3, b, 1);
		
		Order sell = new Order(2, s, 1);
		
		sg.addOrder(buy1);
		sg.addOrder(buy2);
		sg.addOrder(buy3);

		OrderAnswer ans = sg.addOrder(sell);

		assertEquals(buy3, ans.buyer);
	}

}
