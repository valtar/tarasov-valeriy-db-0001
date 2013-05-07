package com.deutschebank.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.monitor.Monitor;

import com.deutschebank.model.Model;
import com.deutschebank.view.View;
import com.deutschebank.view.frames.LoginFrame;
public class Controller {
	private View view;
	private Model model;
	private Object montor = new Object();
	
	public Controller(View view, Model model) {
		super();
		this.view = view;
		this.model = model;
	}

	public static void main(String[] args) {
		View view = new View();
		Model model = new Model();
		
		Controller c = new Controller(view,model);
		c.start();
	}

	class LoginButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = view.getLogin();
			model.loginAdded(s);
			view.disposeLoginFrame();
			synchronized (montor) {
				montor.notify();
			}
			
		}
		
	}
	LoginFrame lf = new LoginFrame();
	
	private void start() {
		model.connectServer();
		
		view.startLoginFrame(new LoginButtonListener());
		try {
			synchronized (montor) {
				montor.wait();
			}
		} catch (InterruptedException e) {}
		System.out.println("here");

		view.startMainFrame();
	
	}

}
