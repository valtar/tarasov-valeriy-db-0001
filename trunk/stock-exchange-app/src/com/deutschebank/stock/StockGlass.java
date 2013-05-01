package com.deutschebank.stock;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.deutschebank.order.Order;
import com.deutschebank.order.OrderAnswer;
import com.deutschebank.order.OrderType;

public class StockGlass {
	private StockType type;
	private TreeSet<Order> sellOrders = new TreeSet<>();
	private TreeSet<Order> buyOrders = new TreeSet<>();

	public StockGlass(StockType type) {
		this.type = type;
	}

	public StockType getType() {
		return type;
	}

	public SortedSet<Order> getSellOrders() {
		return Collections.unmodifiableSortedSet(sellOrders);
	}

	public SortedSet<Order> getBuyOrders() {
		return Collections.unmodifiableSortedSet(buyOrders);
	}

	public OrderAnswer addOrder(Order order) {
		if (order.getType() == OrderType.BUY && sellOrders.isEmpty()) {
			buyOrders.add(order);
			return null;
		}
		if (order.getType() == OrderType.SELL && buyOrders.isEmpty()) {
			sellOrders.add(order);
			return null;
		}

		OrderAnswer ans = null;
		switch (order.getType()) {
		case SELL:
			ans = matchAndTryToAdd(order, buyOrders.descendingIterator(),
					buyOrders);
			break;
		case BUY:
			ans = matchAndTryToAdd(order, sellOrders.iterator(), sellOrders);
			break;
		default:
			System.err.println("Bad!!!");
		}

		return ans;

	}

	private boolean compareOrderPrices(Order currentOrder, Order order) {
		switch (order.getType()) {
		case SELL:
			return order.getPrice() <= currentOrder.getPrice();

		case BUY:
			return order.getPrice() >= currentOrder.getPrice();
		default:
			// TODO: LOG HERE
			System.out.println("Bad way!");
			return false;
		}
	}

	private OrderAnswer matchAndTryToAdd(Order order, Iterator<Order> it,
			TreeSet<Order> orders) {
		Order current;
		current = it.next();

		do {
			if (order.getAmount() == current.getAmount()) {
				orders.remove(current);
				Order buyer = current.getType() == OrderType.BUY ? current
						: order;
				Order seller = current.getType() == OrderType.SELL ? current
						: order;
				return new OrderAnswer(buyer, seller, current.getPrice());
			}

		} while (it.hasNext() && compareOrderPrices(current, order));

		orders.add(order);
		return null;
	}

}
