package com.deutschebank.model;

import java.util.List;

import com.deutschebank.controller.Order;
import com.deutschebank.view.OrderType;
import com.deutschebank.view.StockType;
import com.deutschebank.view.frames.OrderStatus;

public class HistoryTableModelDataEntry {
	public Integer id;
	public Integer nShare;
	public Float price;
	public StockType stockName;
	public OrderType orderName;
	public OrderStatus status;
	public Float dealPrice;
	public String counterparty;

	public HistoryTableModelDataEntry(Integer id, Integer nShare, Float price,
			StockType stockName, OrderType orderName, OrderStatus statuse,
			Float dealPrice, String counterparty) {
		super();
		this.id = id;
		this.nShare = nShare;
		this.price = price;
		this.stockName = stockName;
		this.orderName = orderName;
		this.status = statuse;
		this.dealPrice = dealPrice;
		this.counterparty = counterparty;
	}

	public HistoryTableModelDataEntry(Order order) {
		this(order.getId(), order.getAmount(), order.getPrice(), order
				.getStockType(), order.getOrderType(), OrderStatus.NEW, 0F,
				"UKNOWN");

	}

}
