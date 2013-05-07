package com.deutschebank.view;

public enum OrderType {
	BUY("ASK"), SELL("BID");
	
	private String enumName;
	
	OrderType(String enumName){
		this.enumName = enumName;
	}
	@Override
	public String toString(){
		return enumName;
	}
}
