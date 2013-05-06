package com.deutschebank.exchange;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.deutschebank.client.Client;
import com.deutschebank.order.Order;
import com.deutschebank.stock.StockType;
import com.sun.istack.internal.logging.Logger;

public class ExchangeService {
	private StockExchange exchange = new StockExchange();
	private BlockingQueue<OrderGroup> queue = new LinkedBlockingQueue<OrderGroup>();
	private Logger log = Logger.getLogger(ExchangeService.class);

	private static class OrderGroup {
		Client client;
		Order order;
		StockType type;

		public OrderGroup(Client client, Order order) {
			this.client = client;
			this.order = order;
		}
	}

	public ExchangeService() {
		Thread thread = new Thread() {

			@Override
			public void run() {
				while (true) {
					try {
						OrderGroup tmp = queue.take();
						exchange.add(tmp.client, tmp.order);
					} catch (InterruptedException e) {
						log.warning("interupt: ", e);
					}
				}
			}

		};

		thread.setDaemon(true);
		thread.start();
	}

	public void add(Client client, Order order) {
		try {
			queue.put(new OrderGroup(client, order));
		} catch (InterruptedException e) {
			log.warning("interupted", e);
		}
	}

}
