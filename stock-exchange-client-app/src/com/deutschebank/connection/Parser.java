package com.deutschebank.connection;

import java.util.logging.Logger;

import com.deutschebank.controller.Controller;

public class Parser {
	private static final String DELIMETER = ";";
	private Controller controller;
	private Logger log = Logger.getLogger(Parser.class.getName());
	
	public Parser(Controller controller) {
		this.controller = controller;
	}

	public MatchAnswer getMatchAnswer(String s) {
		return MatchAnswer.parseMatchAnswer(s);
	}

	public ServerMessageType getServerMessageType(String s) {
		String[] ss = s.split(DELIMETER);
		if (ss.length < 1) {
			return null;
		}
		ServerMessageType type = null;
		try {
			type = ServerMessageType.valueOf(ss[0]);
		} catch (IllegalArgumentException e) {
			log.warning("illegal message type");
		}

		return type;
	}

	public void parse(String msg) {
		ServerMessageType type = null;
		type = getServerMessageType(msg);
		if(type == null){
			log.warning("sever sended illegal command type:" + msg );
			return;
		}
		
		switch(type){
		case MATCH:
			MatchAnswer ans = getMatchAnswer(msg);
			if(ans == null){
				log.warning("invalid message: " + msg);
				return;
			}
			controller.matchNotify(ans);
		}
		
	}

}
