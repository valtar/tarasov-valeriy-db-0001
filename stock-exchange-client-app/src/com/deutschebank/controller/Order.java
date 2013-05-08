package com.deutschebank.controller;

import com.deutschebank.view.OrderType;
import com.deutschebank.view.StockType;

public class Order {
	private static int orderIdCount = 0;

	private int id;
	private OrderType orderType;
	private StockType stockType;
	private float price;
	private int amount;

	public static int getOrderIdCount() {
		return orderIdCount;
	}

	public int getId() {
		return id;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public StockType getStockType() {
		return stockType;
	}

	public float getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}

	public Order(OrderType orderType, StockType stockType,
			float price, int amount) {
		super();
		this.orderType = orderType;
		this.stockType = stockType;
		this.price = price;
		this.amount = amount;
		this.id = orderIdCount++;
	}

	public String getOrderMessage() {
		return ClientMessageType.ORDER + ";" + getId() + ";" + getOrderType() + ";"
			+ getStockType() + ";" 	+ getPrice() + ";" + getAmount();

	}

	public static void main(String[] args) {
		System.out.println("vdsfvdfsv");
		
		System.out.println(new Order(OrderType.BUY, StockType.CRYSTALS, 110.4F,
				12).getOrderMessage());
	}
}
