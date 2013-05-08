package com.deutschebank.order;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.deutschebank.exceptions.IllegalPriceException;
import com.deutschebank.stock.StockType;

public class Order implements Comparable<Order> {
	private static int serverOrderIdCount = 0;

	private int serverId;
	private int clientId;
	private OrderType orderType;
	private StockType stockType;
	private float price;
	private int amount;

	public StockType getStockType() {
		return stockType;
	}

	public int getClientId() {
		return clientId;
	}

	public int getAmount() {
		return amount;
	}

	public int getServerId() {
		return serverId;
	}

	public float getPrice() {
		return price;
	}

	@Override
	public int compareTo(Order o) {
		if (this.price == o.price) {
			if (this.serverId == o.serverId) {
				return 0;
			}
			if(OrderType.BUY == orderType){
				return this.serverId < o.serverId ? 1 : -1;
			}
			return this.serverId < o.serverId ? -1 : 1;
		}

		return this.price > o.price ? 1 : -1;
	}

	public Order(float price, OrderType orderType, int amount, int clientId,
			StockType stockType) throws IllegalPriceException {
		if (price < 0) {
			throw new IllegalPriceException("negative price:" + price);
		}
		this.price = new BigDecimal(price).setScale(3, RoundingMode.HALF_DOWN)
				.floatValue();
		this.orderType = orderType;
		this.amount = amount;
		this.serverId = Order.serverOrderIdCount++;
		this.clientId = clientId;
		this.stockType = stockType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (serverId ^ (serverId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (serverId != other.serverId)
			return false;
		return true;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

}
