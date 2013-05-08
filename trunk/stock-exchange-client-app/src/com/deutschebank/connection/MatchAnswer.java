package com.deutschebank.connection;


public class MatchAnswer {
	public int id;
	public String counterparty;
	public float price;
	public int nShares;
	private static final String DELIMETR = ";";

	public MatchAnswer(int id, String counterparty, float price, int nShares) {
		super();
		this.id = id;
		this.counterparty = counterparty;
		this.price = price;
		this.nShares = nShares;
	}

	enum MatchAnswerOrder {
		MESSAGE_TYPE, ID, COUNTERPARTY, PRICE, N_SHARES
	}
//	MATCH;0;name;1.0;1
	public static MatchAnswer parseMatchAnswer(String s) {
		String[] ss = s.split(DELIMETR);
		MatchAnswer answer = null;
		int id = 0;
		String counterparty = null;
		float price = 0;
		int nShares = 0;

		try {
			id = Integer.parseInt(ss[MatchAnswerOrder.ID.ordinal()]);
			counterparty = ss[MatchAnswerOrder.COUNTERPARTY.ordinal()];
			price = Float.parseFloat(ss[MatchAnswerOrder.PRICE.ordinal()]);
			nShares = Integer.parseInt(ss[MatchAnswerOrder.N_SHARES.ordinal()]);
			answer = new MatchAnswer(id, counterparty, price, nShares);
		} catch (NumberFormatException e) {
			answer = null;
		} 

		return answer;
	}
}
