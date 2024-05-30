package model;

import java.util.Random;

public class Invoice {
	private int id;
	private int orderId;
	private double total;
	private double euroValue;
	
	private String paymentDate;
	
	public Invoice(String paymentDate, double total, int orderId) {
		this.euroValue = euroRandomizer();
		this.paymentDate = paymentDate;
		this.total = total/euroValue;
		this.orderId = orderId;
	}
	
	private double euroRandomizer() {
		Random random = new Random();
		double randomNumber = 7.4 + random.nextDouble() * 0.1;
		return randomNumber;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public int getOrderId() {
		return orderId;
	}

	public double getEuroValue() {
		return euroValue;
	}
	
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public String getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
}