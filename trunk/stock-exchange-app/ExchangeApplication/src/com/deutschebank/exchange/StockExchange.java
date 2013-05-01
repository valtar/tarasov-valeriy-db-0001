package com.deutschebank.exchange;

import java.util.HashMap;
import java.util.LinkedList;

import com.deutschebank.order.Order;
import com.deutschebank.stock.StockGlass;
import com.deutschebank.stock.StockType;

public class StockExchange {

	private static StockExchange exchange = new StockExchange();
	
	private LinkedList<StockType> listOfStocks = new LinkedList<>();
	private HashMap<StockType,StockGlass> glasses = new HashMap<>();
	private StockExchange() {
		for(StockType type : StockType.values()){
			glasses.put(type,new StockGlass(type));
			listOfStocks.add(type);
		}
	}
	
	public static StockExchange getExchange(){
		return StockExchange.exchange;
	}
	
	public void add(Order order, StockType type){
		glasses.get(type).addOrder(order);
	}

	
	
}
