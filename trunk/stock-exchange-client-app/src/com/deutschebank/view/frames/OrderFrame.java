package com.deutschebank.view.frames;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.deutschebank.view.OrderType;
import com.deutschebank.view.StockType;

public class OrderFrame extends JFrame {

	private JTextField priceField = new JTextField("1",5);
	private JTextField nSharesField = new JTextField("1",5);
	private JLabel stockNameLabel = new JLabel("stock name: ");
	private JLabel operationLabel = new JLabel("operation: ");
	private JLabel nSharesLabel = new JLabel("number of shares: ");
	private JLabel priceLabel = new JLabel("price: ");
	private JPanel panel = new JPanel();
	private JButton button = new JButton("send order");

	private boolean isCorrectOrder = true;

	public OrderFrame() {
		JComboBox<OrderType> operationBox = new JComboBox<>(OrderType.values());
		JComboBox<StockType> stockNamePopup = new JComboBox<>(
				StockType.values());

		panel.setLayout(new GridBagLayout());

		int x = 0;
		int y = 0;

		panel.add(stockNameLabel, new GridBagConstraints(x, y, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		y++;
		panel.add(operationLabel, new GridBagConstraints(x, y, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		y++;
		panel.add(nSharesLabel, new GridBagConstraints(x, y, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		y++;
		panel.add(priceLabel, new GridBagConstraints(x, y, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		y++;

		x = 1;
		y = 0;
		panel.add(stockNamePopup, new GridBagConstraints(x, y, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		y++;
		panel.add(operationBox, new GridBagConstraints(x, y, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		y++;
		panel.add(nSharesField, new GridBagConstraints(x, y, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		y++;
		panel.add(priceField, new GridBagConstraints(x, y, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		y++;
		x = 0;
		panel.add(button, new GridBagConstraints(x, y, 2, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(3, 3, 3, 3), 0, 0));

		add(panel);

		addFieldListeners();

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

	}

	private void addFieldListeners() {
		nSharesField.addCaretListener(new CaretListener() {
			Border defaultBoarder = nSharesField.getBorder();
			Border alertBoarder = BorderFactory.createLineBorder(Color.red);

			@Override
			public void caretUpdate(CaretEvent e) {
				try {
					Integer.parseInt(nSharesField.getText());
				} catch (NumberFormatException exception) {
					nSharesField.setBorder(alertBoarder);
					block();
					return;
				}
				nSharesField.setBorder(defaultBoarder);
				unblock();
			}

		});

		priceField.addCaretListener(new CaretListener() {
			Border defaultBoarder = nSharesField.getBorder();
			Border alertBoarder = BorderFactory.createLineBorder(Color.red);

			@Override
			public void caretUpdate(CaretEvent e) {
				try {
					Float.parseFloat(priceField.getText());
				} catch (NumberFormatException exception) {
					priceField.setBorder(alertBoarder);
					block();
					return;
				}
				priceField.setBorder(defaultBoarder);
				unblock();
			}
		});
	}
	private void unblock() {
		button.setEnabled(true);
	}

	private void block() {
		button.setEnabled(false);
	}
	public static void main(String[] args) {
		JFrame f = new OrderFrame();
		f.setVisible(true);
	}
}
