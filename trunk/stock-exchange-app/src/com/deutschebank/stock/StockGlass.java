package com.deutschebank.stock;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.deutschebank.order.Order;
import com.deutschebank.order.OrderAnswer;
import com.deutschebank.order.OrderType;

public class StockGlass {
	private StockType type;
	private TreeSet<Order> sellOrders = new TreeSet<>();
	private TreeSet<Order> buyOrders = new TreeSet<>();
	private Logger log = Logger.getLogger(StockGlass.class.getName());

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
		if (order.getOrderType() == OrderType.BUY && sellOrders.isEmpty()) {
			buyOrders.add(order);
			log.info("buy order added: " + order);
			return null;
		}
		if (order.getOrderType() == OrderType.SELL && buyOrders.isEmpty()) {
			sellOrders.add(order);
			log.info("sell order added: " + order);
			return null;
		}

		OrderAnswer ans = null;
		switch (order.getOrderType()) {
		case SELL:
			ans = matchAndTryToAdd(order, buyOrders.descendingIterator(),
					buyOrders);
			break;
		case BUY:
			ans = matchAndTryToAdd(order, sellOrders.iterator(), sellOrders);
			break;
		default:
			log.warning("Illegal order type: " + order.getOrderType());
		}

		log.info("order mathes: buy_order>" + ans.getBuyer() + " ;sell_order>"
				+ ans.getSeller() + " ;price = " + ans.price);
		return ans;

	}

	private boolean compareOrderPrices(Order currentOrder, Order order) {
		switch (order.getOrderType()) {
		case SELL:
			return order.getPrice() <= currentOrder.getPrice();

		case BUY:
			return order.getPrice() >= currentOrder.getPrice();
		default:
			log.warning("Illegal order type: " + order.getOrderType());
			return false;
		}
	}

	public boolean removeOrder(Order order) {
		boolean isRemoved = false;
		switch (order.getOrderType()) {
		case BUY:
			isRemoved = buyOrders.remove(order);
			break;
		case SELL:
			isRemoved = sellOrders.remove(order);
			break;
		default:
		}

		return isRemoved;
	}

	private OrderAnswer matchAndTryToAdd(Order order, Iterator<Order> it,
			TreeSet<Order> orders) {
		Order current;
		current = it.next();

		do {
			if (order.getAmount() == current.getAmount()) {
				orders.remove(current);
				Order buyer = current.getOrderType() == OrderType.BUY ? current
						: order;
				Order seller = current.getOrderType() == OrderType.SELL ? current
						: order;
				return new OrderAnswer(buyer, seller, current.getPrice());
			}

		} while (it.hasNext() && compareOrderPrices(current, order));

		orders.add(order);
		return null;
	}

}
