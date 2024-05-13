package controller;

import model.Customer;
import model.Employee;
import model.Order;
import model.database.OrderDB;
import model.database.OrderDBIF;
import java.sql.SQLException;

public class OrderCtrl {
	private OrderDBIF orderDB;
	private CustomerCtrl customerCtrl;
	private CarCtrl carCtrl;
	private InvoiceCtrl invoiceCtrl;
	private Order currentOrder;
	private Customer customer;
	private Employee employee;

	public OrderCtrl() throws DataAccessException {
	
		this.orderDB = new OrderDB();
		this.customerCtrl = new CustomerCtrl();
		this.carCtrl = new CarCtrl();
		this.invoiceCtrl = new InvoiceCtrl();
	}

	private Customer findCustomer(String phoneNo) throws DataAccessException {
		return customerCtrl.findCustomer(phoneNo);
		
	}

	private Order createOrder(String phoneNo) throws NullPointerException {
		Customer customer = findCustomer(phoneNo);

		if (customer != null) {
			currentOrder = new Order(employee, customer);
		} else {
			throw new NullPointerException("No customer found");
		}
		return currentOrder;
	}

	private Copy findCopy(String vin) throws DataAccessException {
		return carCtrl.findCopy(vin);
	}

	public void addCopy(String vin) throws DataAccessException {
		Copy copy = findCopy(vin);

		if (copy != null) {
			currentOrder.addCopy(copy);
		}

	}

	public void confirmOrder() throws SQLException {
		
	}
}
