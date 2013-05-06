package com.deutschebank.order;

public class OrderAnswer {
	public Order buyer;
	public Order seller;
	public float price;

	public Order getBuyer() {
		return buyer;
	}

	public void setBuyer(Order buyer) {
		this.buyer = buyer;
	}

	public Order getSeller() {
		return seller;
	}

	public void setSeller(Order seller) {
		this.seller = seller;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float amount) {
		this.price = amount;
	}


	public OrderAnswer(Order buyer, Order seller, float price) {
		this.buyer = buyer;
		this.seller = seller;
		this.price = price;
	}

}