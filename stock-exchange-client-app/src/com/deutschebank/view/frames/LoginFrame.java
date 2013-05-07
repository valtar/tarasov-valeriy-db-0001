package com.deutschebank.view.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.deutschebank.controller.Controller;

public class LoginFrame extends JFrame {
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel("Login:");
	private JButton button = new JButton("Ok");
	private JTextField field = new JTextField(10);
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public JTextField getField() {
		return field;
	}

	public void setField(JTextField field) {
		this.field = field;
	}


	public LoginFrame() {
		super();

		panel.setLayout(new GridBagLayout());

		int gridx = 0;
		int gridy = 0;
		panel.add(label, new GridBagConstraints(gridx, gridy, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 2, 0));
		gridx++;
		panel.add(field,new GridBagConstraints(gridx, gridy, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
		
		gridx ++;
		panel.add(button,new GridBagConstraints(gridx, gridy, 0, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
		
		add(panel);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(200, 100);
		setResizable(false);
		pack();
	}
	
	public void addLoginButtonListerner(ActionListener listener) {
		button.addActionListener(listener);
	}
	
	public static void main(String[] args) {
		JFrame f = new LoginFrame();
		f.setVisible(true);
	}

}
