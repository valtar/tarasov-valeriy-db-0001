package com.deutschebank.client;

import java.util.ArrayList;

import com.deutschebank.order.Order;
import com.deutschebank.server.Acceptor;
import com.deutschebank.server.ServerConnector;

public class Client {
	private static int clientIdCount = 0;
	
	private String name;
	public String getName() {
		return name;
	}

	private ArrayList<Order> orders = new ArrayList<>();
	private int id;
	private Acceptor acceptor;
	
	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Client other = (Client) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Client(String name, Acceptor acceptor) {
		this.name = name;
		this.acceptor = acceptor;
		id = clientIdCount++;
	}
	
	public synchronized void matchNotify(Order order){
		removeOrder(order);
		acceptor.matchNotify(order);
	}
	
	public synchronized boolean addOrder(Order order){
		return orders.add(order);
	}
	
	public synchronized boolean removeOrder(Order order){
		return orders.remove(order);
	}

	
}
