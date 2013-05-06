package com.deutschebank.server;

import com.deutschebank.exceptions.MessageTypeFormatException;
import com.deutschebank.exceptions.OrderTypeFormatException;
import com.deutschebank.order.OrderType;

public enum MessageType {
	NEW_CLIENT("LOGIN"),
	NEW_ORDER("order"),//BID || ASK
	CANCEL_ORDER("CANCEL"),
	CLOSE_CONNECTION("CLOSE"), 
	UNKNOWN("unknown");
	
	String typeString;
	
	private MessageType(String typeString) {
		this.typeString = typeString;
	}
	
	public static MessageType parseMessageType (String s)throws MessageTypeFormatException{
		if(s == null) throw new MessageTypeFormatException("null string");
		
		for(MessageType mType : MessageType.values()){
			if(mType.typeString.equals(s)){
				return mType;
			}
		}
		
		OrderType orderType = null;
		try{
			orderType = OrderType.parseOrderType(s);
			if(orderType != null){
				return MessageType.NEW_ORDER;
			}
		}catch(OrderTypeFormatException e){}
		
		throw new MessageTypeFormatException("MessageType '" + s + "' doesn't exist");		
	}
}
