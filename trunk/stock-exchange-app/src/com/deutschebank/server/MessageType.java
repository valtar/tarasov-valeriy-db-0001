package com.deutschebank.server;

import com.deutschebank.exceptions.MessageTypeFormatException;
import com.deutschebank.exceptions.OrderTypeFormatException;
import com.deutschebank.order.OrderType;

public enum MessageType {
	NEW_CLIENT("LOGIN"),
	NEW_ORDER("ORDER"),
	CANCEL_ORDER("CANCEL"),
	CLOSE_CONNECTION("LOGOUT"), 
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
		
		throw new MessageTypeFormatException("MessageType '" + s + "' doesn't exist");		
	}
}
