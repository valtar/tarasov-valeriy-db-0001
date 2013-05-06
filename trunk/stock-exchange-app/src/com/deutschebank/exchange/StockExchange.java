package com.deutschebank.exchange;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
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
	public synchronized void  add(Client client, Order order) {
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
		Client notifyClient = null;
		notifyClient = orderOwners.remove(ans.getBuyer());
		if(notifyClient == null){
			log.severe("user doesn't exist");
		}else{
			notifyClient.matchNotify(ans.getBuyer());
		}
		
		notifyClient = orderOwners.remove(ans.getSeller());
		if(notifyClient == null){
			log.severe("user doesn't exist");
		}else{
			notifyClient.matchNotify(ans.getSeller());
		}
	}

}
