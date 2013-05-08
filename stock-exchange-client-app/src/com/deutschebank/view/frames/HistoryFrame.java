package com.deutschebank.view.frames;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import com.deutschebank.model.HistoryTableModelData;

public class HistoryFrame extends JFrame{
	private JScrollPane pane;
	private JTable table;
	public AbstractTableModel model;
	
	public HistoryFrame(HistoryTableModelData data,AbstractTableModel model) {
		this.model = model;
		table = new JTable(model);
		pane = new JScrollPane(table);
		
		add(pane);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}
	
	
}
