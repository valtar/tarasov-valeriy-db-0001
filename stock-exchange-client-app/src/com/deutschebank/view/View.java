package com.deutschebank.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.deutschebank.view.frames.LoginFrame;
import com.deutschebank.view.frames.MainFrame;

public class View {

	private LoginFrame lf = new LoginFrame();
	private MainFrame mf = new MainFrame();

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

	public void startMainFrame() {
		mf.addOrderButtonListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
