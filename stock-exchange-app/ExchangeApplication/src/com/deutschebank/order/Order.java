package com.deutschebank.order;

import com.deutschebank.exceptions.IllegalPriceException;

public class Order implements Comparable<Order> {
	private static int orderIdCount = 0;

	private int id;
	private OrderType type = OrderType.BUY;
	private float price = 0;
	private int amount = 1;

	public int getAmount() {
		return amount;
	}

	public int getId() {
		return id;
	}

	public float getPrice() {
		return price;
	}

	@Override
	public int compareTo(Order o) {
		if (this.price == o.price) {
			if (this.id == o.id) {
				return 0;
			}
			return this.id < o.id ? 1 : -1;
		}

		return this.price > o.price ? 1 : -1;
	}

	public Order(float price, OrderType type, int amount)
			throws IllegalPriceException {
		if (price < 0) {
			throw new IllegalPriceException("negative price:" + price);
		}
		this.price = price;
		this.type = type;
		this.amount = amount;
		this.id = Order.orderIdCount++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (id != other.id)
			return false;
		return true;
	}

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}

}
