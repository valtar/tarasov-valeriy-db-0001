package com.deutschebank.view.frames;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.deutschebank.model.HistoryTableModelData;
import com.deutschebank.view.OrderType;
import com.deutschebank.view.StockType;

public class HistoryTableModel extends AbstractTableModel {
	private int columnCount = HistoryTableColumns.values().length;
	private int rowCount = 0;
	HistoryTableModelData data = new HistoryTableModelData(
			new ArrayList<Integer>(), new ArrayList<Integer>(),
			new ArrayList<Float>(), new ArrayList<StockType>(),
			new ArrayList<OrderType>(), new ArrayList<OrderStatus>());

	@Override
	public String getColumnName(int column) {
		return HistoryTableColumns.values()[column].toString();
	}

	public HistoryTableModel(HistoryTableModelData data) {
		this.data = data;
		rowCount = data.getIds().size();
	}

	@Override
	public void fireTableDataChanged() {
		rowCount = data.getIds().size();
		super.fireTableDataChanged();
	}

	public enum HistoryTableColumns {
		ID, PRICE, STOCKNAME, OPERATION, N_SHARES, STATUS
	}


	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		HistoryTableColumns type = null;

		type = HistoryTableColumns.values()[columnIndex];

		switch (type) {
		case ID:
			return data.getIds().get(rowIndex);
		case N_SHARES:
			return data.getnShares().get(rowIndex);
		case OPERATION:
			return data.getOrderNames().get(rowIndex);
		case PRICE:
			return data.getPrices().get(rowIndex);
		case STATUS:
			return data.getStatuses().get(rowIndex);
		case STOCKNAME:
			return data.getStockNames().get(rowIndex);
		default:
			return 1;
		}
		// return 1;
	}

	public static void main(String[] args) {
		HistoryTableColumns[] y = HistoryTableColumns.values();
		for (int i = 0; i < y.length; i++) {
			System.out.println(HistoryTableColumns.values()[i]);
		}

		JFrame f = new JFrame();
		TableModel model = new HistoryTableModel(null);
		
		JTable t = new JTable(model);
		JScrollPane pane = new JScrollPane(t);
		// pane.add(t);

		f.add(pane);
		// f.add(t);
		// pane.setSize(500, 100);
		// f.setSize(500,100);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
//		((AbstractTableModel) model).fireTableDataChanged();

	}

}
