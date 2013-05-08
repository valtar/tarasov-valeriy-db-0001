package com.deutschebank.model;

import com.deutschebank.connection.Connector;
import com.deutschebank.connection.MatchAnswer;
import com.deutschebank.controller.Controller;
import com.deutschebank.controller.Order;
import com.deutschebank.view.frames.HistoryTableModel;

public class Model {
	private final String DELIMETER = ";";
	private Connector connector;
	private Controller controller;
	HistoryTableModelData data ;
	
	public Model(Controller controller, HistoryTableModelData data) {
		this.controller = controller;
		this.data = data;
	}

	public boolean loginAdded(String s) {
		connector.sendMessage(MessageType.LOGIN.toString() + DELIMETER + s);
		return true;
	}

	public void connectServer() {
		if (connector == null) {
			connector = new Connector("localhost", 5555,controller);
			connector.connect();
		}
	}


	public void orderAdded(Order order) {
		data.addItem(new HistoryTableModelDataEntry(order));
		controller.dataChanged();
		connector.sendMessage(order.getOrderMessage());
	}

	public void closeConnection() {
		connector.closeConnection();
	}

	public void matchNotify(MatchAnswer ans) {
		synchronized (data) {
			data.changeItem(ans);			
		}

		
		controller.dataChanged();
	}

}
