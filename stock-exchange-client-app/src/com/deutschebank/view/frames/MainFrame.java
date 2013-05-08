package com.deutschebank.view.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.deutschebank.view.View;

public class MainFrame extends JFrame implements WindowListener {
	View view;
	JButton orderButton = new JButton("new order");
	JButton historyButton = new JButton("show orders");
	JPanel panel = new JPanel();
	
	public MainFrame(View view) {
		this.view = view;
		panel.setLayout(new BorderLayout());
		
		add(orderButton,BorderLayout.WEST);
		add(historyButton,BorderLayout.EAST);
		setLocationRelativeTo(null);
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void addOrderButtonListener(ActionListener listener) {
		orderButton.addActionListener(listener);
	}
	public void addHistoryButtonListener(ActionListener listener) {
		historyButton.addActionListener(listener);
	}

	public static void main(String[] args) {
		JFrame f = new MainFrame(null);
		f.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
		view.mainWindowClosed();
	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

}
