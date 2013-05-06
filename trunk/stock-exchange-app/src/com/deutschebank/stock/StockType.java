package com.deutschebank.stock;

import com.deutschebank.exceptions.StockTypeFormatException;

public enum StockType {
	GOLD,
	WOOD,
	ORE,
	GEMS,
	CRYSTALS,
	SULFUR,
	MERCURY;
	
	public static StockType parseStockType (String s)throws StockTypeFormatException{
		if(s == null) throw new StockTypeFormatException("null string");
		
		for(StockType mType : StockType.values()){
			if(mType.toString().equals(s)){
				return mType;
			}
		}
		
		throw new StockTypeFormatException("StockType '" + s + "' doesn't exist");		
	}
}
