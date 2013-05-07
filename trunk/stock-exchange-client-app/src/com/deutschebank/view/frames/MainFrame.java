package com.deutschebank.view.frames;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	JButton orderButton = new JButton("new order");
	
	public MainFrame() {
		add(orderButton);
		setLocationRelativeTo(null);
		pack();
	}
	
	public void addOrderButtonListener(ActionListener listener){
		orderButton.addActionListener(listener);
	}
}
