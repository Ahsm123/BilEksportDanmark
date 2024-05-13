package model;

import java.util.ArrayList;

public class Order {
	
	private int orderId;
	private String date;
	private double totalPrice;
	private String deliveryAddress;
	private boolean isDelivered;
	private Employee employee;
	private Customer customer;
	private ArrayList<Copy> copies;
	
	public Order(Employee employee, Customer customer) {
		
		this.date = java.time.LocalDate.now().toString();
		this.totalPrice = 0;
		this.deliveryAddress = null;
		this.isDelivered = false;
		this.employee = employee;
		this.customer = customer;
		this.copies = new ArrayList<>();
	}
	
	public void addCopy(Copy copy) {
		copies.add(copy);
	}
	
	public void setId(int orderId) {
		this.orderId = orderId;
	}
	public int getId() {
		return orderId;
	}
	
	public void setTotal(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTotal() {
		double result = 0;
		for(Copy copy : copies) {
			result += copy.getPrice();
		}
		return result;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<Copy> getCopies() {
		return copies;
	}

	public void setCopies(ArrayList<Copy> copies) {
		this.copies = copies;
	}
}
