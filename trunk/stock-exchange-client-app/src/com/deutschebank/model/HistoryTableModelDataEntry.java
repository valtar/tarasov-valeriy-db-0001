package com.deutschebank.model;

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
	public HistoryTableModelDataEntry(Integer id, Integer nShare, Float price,
			StockType stockName, OrderType orderName, OrderStatus statuse) {
		super();
		this.id = id;
		this.nShare = nShare;
		this.price = price;
		this.stockName = stockName;
		this.orderName = orderName;
		this.status = statuse;
	}
	
	public HistoryTableModelDataEntry(Order order){
		this(order.getId(), order.getAmount(), order.getPrice(), order.getStockType(), order.getOrderType(), OrderStatus.NEW);
		
		
	}

}
