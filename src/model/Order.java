package model;

import java.util.LinkedList;
import java.util.stream.Collectors;

import model.exceptions.CopyNotInOrderException;

public class Order {
	private int orderId;
	private String date;
	private String deliveryAddress;
	
	private boolean isDelivered;
	private int employeeId;
	private Customer customer;
	private LinkedList<Copy> copies;

	public Order(int employeeId, Customer customer) {

		this.date = java.time.LocalDate.now().toString();
		this.deliveryAddress = null;
		this.isDelivered = false;
		this.employeeId = employeeId;
		this.customer = customer;
		this.copies = new LinkedList<>();
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
	
	public boolean hasCopy(Copy copy) {
		return copies.contains(copy);
	}

	private boolean removeCopyByCopyObject(Copy copy) {
		boolean found = false;
		int i = -1;
		while(!found && ++i < copies.size()) {
			Copy currentCopy = copies.get(i);
			if(currentCopy.getVin().equals(copy.getVin())) {
				copies.remove(i);
				found = true;
			}
		}
		return found;
	}
	
	public void removeCopyByVin(String copyVin) {
		Copy copy = getCopyFromId(copyVin);
		
		if(!removeCopyByCopyObject(copy)) {
			throw new CopyNotInOrderException("Kan ikke fjerne bilen da den ikke er i ordren");
		}
	}
	
	public Copy getCopyFromId(String copyVin) {
		Copy result = null;
		boolean found = false;
		int i = -1;
		while(!found && ++i < copies.size()) {
			Copy currentCopy = copies.get(i);
			if(currentCopy.getVin().equals(copyVin)) {
				found = true;
				result = currentCopy;
			}
		}
		return result;
	}
	
	public double getTotalPrice() {
		return copies.stream()
                .mapToDouble(Copy::getSalesPrice)
                .sum();
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

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployee(int employeeId) {
		this.employeeId = employeeId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LinkedList<Copy> getCopies() {
		return copies;
	}

	public void setCopies(LinkedList<Copy> copies) {
		this.copies = copies;
	}
}
