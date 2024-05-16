package model;

import java.time.LocalDate;
import java.util.Random;

public class Invoice {
	private int id;
	private LocalDate paymentDate;
	private double total;
	private int orderId;

	
	public Invoice( LocalDate paymentDate, int id, double total, int orderId) {
		super();
		this.paymentDate = paymentDate;
		this.id = id;
		this.total = total;
		this.orderId = orderId;
	}
	
	public Invoice() {
		
		
	}
	
	public double euroRandomizer() {
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


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public LocalDate getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	



}
