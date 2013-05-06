package com.deutschebank.order;

import com.deutschebank.exceptions.OrderTypeFormatException;

public enum OrderType {
	BUY("ASK"), SELL("BID");
	
	private String enumName;
	
	OrderType(String enumName){
		this.enumName = enumName;
	}
	
	public static OrderType parseOrderType(String s)
			throws OrderTypeFormatException {
		if (s == null)
			throw new OrderTypeFormatException("null string");

		for (OrderType mType : OrderType.values()) {
			if (mType.toString().equals(s)) {
				return mType;
			}
		}

		throw new OrderTypeFormatException("StockType '" + s
				+ "' doesn't exist");
	}
	
	@Override 
	public String toString(){
		return enumName;
	}
}
