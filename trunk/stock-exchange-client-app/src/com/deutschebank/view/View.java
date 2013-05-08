package com.deutschebank.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import com.deutschebank.controller.Controller;
import com.deutschebank.controller.Order;
import com.deutschebank.model.HistoryTableModelData;
import com.deutschebank.view.frames.HistoryFrame;
import com.deutschebank.view.frames.HistoryTableModel;
import com.deutschebank.view.frames.LoginFrame;
import com.deutschebank.view.frames.MainFrame;
import com.deutschebank.view.frames.OrderFrame;

public class View {

	private LoginFrame lf = new LoginFrame();
	private MainFrame mf = new MainFrame(this);
	private OrderFrame currentOrderFrame;
	private Controller controller;
	private HistoryFrame historyFrame;
	private AbstractTableModel model;

	public void startLoginFrame(ActionListener loginButtonListener) {
		lf.setVisible(true);
		lf.addLoginButtonListerner(loginButtonListener);		
	}

	public String getLogin() {
		return lf.getField().getText();
	}

	public void disposeLoginFrame() {
		lf.dispose();
	}

	
//	public void orderButtonPressed(ActionListener listener){
//		
//	}

	public Order getOrder() {
		return currentOrderFrame.getOrder();
	}

	public void disposeOrderFrame() {
		currentOrderFrame.dispose();
	}

	public void startMainFrame(final ActionListener orderButtonListener, final HistoryTableModelData data) {
	model = new HistoryTableModel(data);
		
		mf.addOrderButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentOrderFrame = new OrderFrame();
				currentOrderFrame.addOrderButtonListener(orderButtonListener);
				currentOrderFrame.setVisible(true);
			}
		});

	mf.addHistoryButtonListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			new HistoryFrame(data,model).setVisible(true);
		}
	});
		
	
	
	mf.setVisible(true);
	}

	public void dataChanged(){
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	model.fireTableDataChanged();
		    }
		});
	}
	
	
	public void mainWindowClosed() {
		controller.mainWidowClosed();
	}

	

}
