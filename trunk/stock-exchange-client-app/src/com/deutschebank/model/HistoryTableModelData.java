package com.deutschebank.model;

import java.util.ArrayList;
import java.util.List;

import com.deutschebank.connection.MatchAnswer;
import com.deutschebank.view.OrderType;
import com.deutschebank.view.StockType;
import com.deutschebank.view.frames.OrderStatus;

public class HistoryTableModelData {
	private List<Integer> ids;
	private List<Integer> nShares;
	private List<Float> prices;
	private List<StockType> stockNames;
	private List<OrderType> orderNames;
	private List<OrderStatus> statuses;
	private List<Float> dealPrices;
	private List<String> counterpartys;
	
	public List<Float> getDealPrices() {
		return dealPrices;
	}

	public void setDealPrices(List<Float> dealPrices) {
		this.dealPrices = dealPrices;
	}

	public List<String> getCounterpartys() {
		return counterpartys;
	}

	public void setCounterpartys(List<String> counterpartys) {
		this.counterpartys = counterpartys;
	}


	public void addItem(HistoryTableModelDataEntry entry) {
		ids.add(entry.id);
		nShares.add(entry.nShare);
		prices.add(entry.price);
		stockNames.add(entry.stockName);
		orderNames.add(entry.orderName);
		statuses.add(entry.status);
		dealPrices.add(entry.dealPrice);
		counterpartys.add(entry.counterparty);
	}

	public HistoryTableModelData() {
		this(new ArrayList<Integer>(), new ArrayList<Integer>(),
				new ArrayList<Float>(), new ArrayList<StockType>(),
				new ArrayList<OrderType>(), new ArrayList<OrderStatus>(),
				new ArrayList<Float>(), new ArrayList<String>());
	}

	public HistoryTableModelData(List<Integer> ids, List<Integer> nShares,
			List<Float> prices, List<StockType> stockNames,
			List<OrderType> orderNames, List<OrderStatus> statuses,
			List<Float> dealPrices, List<String> counterpartys) {
		this.ids = ids;
		this.nShares = nShares;
		this.prices = prices;
		this.stockNames = stockNames;
		this.orderNames = orderNames;
		this.statuses = statuses;
		this.dealPrices = dealPrices;
		this.counterpartys = counterpartys;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<Integer> getnShares() {
		return nShares;
	}

	public void setnShares(List<Integer> nShares) {
		this.nShares = nShares;
	}

	public List<Float> getPrices() {
		return prices;
	}

	public void setPrices(List<Float> prices) {
		this.prices = prices;
	}

	public List<StockType> getStockNames() {
		return stockNames;
	}

	public void setStockNames(List<StockType> stockNames) {
		this.stockNames = stockNames;
	}

	public List<OrderType> getOrderNames() {
		return orderNames;
	}

	public void setOrderNames(List<OrderType> orderNames) {
		this.orderNames = orderNames;
	}

	public List<OrderStatus> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<OrderStatus> statuses) {
		this.statuses = statuses;
	}

	public void changeItem(MatchAnswer ans) {
		int ind = 0;
		if (!ids.contains(ans.id))
			return;
		ind = ids.indexOf(ans.id);

		statuses.set(ind, OrderStatus.FILLED);
		counterpartys.set(ind, ans.counterparty);
		dealPrices.set(ind, ans.price);
	}
}