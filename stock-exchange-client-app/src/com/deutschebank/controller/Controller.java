package com.deutschebank.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.deutschebank.connection.MatchAnswer;
import com.deutschebank.model.HistoryTableModelData;
import com.deutschebank.model.Model;
import com.deutschebank.view.View;

public class Controller {
	private View view;
	private Model model;
	private Object monitor = new Object();
	private HistoryTableModelData data = new HistoryTableModelData();

	public Controller() {
		this.view = new View();
		this.model = new Model(this,data);
	}

	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
	}

	class LoginButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = view.getLogin();
			model.loginAdded(s);
			view.disposeLoginFrame();
			synchronized (monitor) {
				monitor.notify();
			}

		}

	}
	class OrderButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Order order = view.getOrder();
			model.orderAdded(order);
			view.disposeOrderFrame();
			
		}
		
	}
	
	class MainWindowExitListener {
		
	}

	private void start() {
		try {

			model.connectServer();

			view.startLoginFrame(new LoginButtonListener());
			try {
				synchronized (monitor) {
					monitor.wait();
				}
			} catch (InterruptedException e) {
			}

			view.startMainFrame(new OrderButtonListener(), data);
		} catch (Exception e) {
			
		}
	}

	public void mainWidowClosed() {
		model.closeConnection();
		
	}

	public void matchNotify(MatchAnswer ans) {
		model.matchNotify(ans);
	}

	public void dataChanged() {
		view.dataChanged();
		
	}

}
