package com.deutschebank.exchange;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.deutschebank.client.Client;
import com.deutschebank.order.Order;
import com.deutschebank.order.OrderAnswer;
import com.deutschebank.stock.StockGlass;
import com.deutschebank.stock.StockType;

public class StockExchange {
	private LinkedList<StockType> listOfStocks = new LinkedList<>();
	private HashMap<StockType, StockGlass> glasses = new HashMap<>();
	private HashMap<Order, Client> orderOwners = new HashMap<>();

	private Logger log = Logger.getLogger(StockExchange.class.getName());

	public StockExchange() {
		for (StockType type : StockType.values()) {
			glasses.put(type, new StockGlass(type));
			listOfStocks.add(type);
		}
	}

	/**
	 * 
	 * Adds order in StockGlass. If matches, order's owners (clients) are
	 * notified by calling matchNotify() .
	 * 
	 */
	public synchronized void add(Client client, Order order) {
		orderOwners.put(order, client);
		StockType type = order.getStockType();

		StockGlass sg = glasses.get(type);
		OrderAnswer ans = sg.addOrder(order);
		if (ans == null) {
			return;
		}

		notifyClients(ans);
	}

	private void notifyClients(OrderAnswer ans) {
		Client seller = orderOwners.remove(ans.getBuyer());
		Client buyer = orderOwners.remove(ans.getSeller());

		String sellerCounterparty = "unknown";
		String buyerCounterparty = "unknown";
		
		if(buyer == null) {
			log.severe("user " + ans.getBuyer() + "doesn't exist");
		}else{
			sellerCounterparty = buyer.getName();
		}

		if(seller == null) {
			log.severe("user " + ans.getSeller() + "doesn't exist");
		}else{
			buyerCounterparty = seller.getName();
		}
		
		if(buyer != null){
			buyer.matchNotify(ans.getBuyer(), buyerCounterparty);
		}
		
		if(seller != null){
			seller.matchNotify(ans.getSeller(), sellerCounterparty);
		}
		
	}

	public synchronized void deleteClientOrders(Client client) {
		if (client == null) {
			return;
		}
		if (!orderOwners.containsValue(client)) {
			return;
		}

		Order removedOrder = null;
		Client removedClient = null;
		StockGlass glass = null;
		for (Entry<Order, Client> entry : orderOwners.entrySet()) {
			removedClient = entry.getValue();
			removedOrder = entry.getKey();
			if (client.equals(removedClient)) {
				orderOwners.remove(removedOrder);

				glass = glasses.get(removedOrder.getStockType());
				glass.removeOrder(removedOrder);
			}
		}
	}

}
