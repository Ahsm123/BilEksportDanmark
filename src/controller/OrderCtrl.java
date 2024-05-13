package controller;

import model.Customer;
import model.Employee;
import model.Order;
import model.database.DataAccessException;
import model.database.OrderDB;
import model.database.OrderDBIF;
import java.sql.SQLException;
import model.Copy;

public class OrderCtrl {
	private OrderDBIF orderDB;
	private CustomerCtrl customerCtrl;
	private CarCtrl carCtrl;
	private InvoiceCtrl invoiceCtrl;
	private Order currentOrder;
	private Employee employee;

	public OrderCtrl() throws DataAccessException, SQLException {
	
		this.orderDB = new OrderDB();
		this.customerCtrl = new CustomerCtrl();
		this.carCtrl = new CarCtrl();
		this.invoiceCtrl = new InvoiceCtrl();
	}

	public Customer findCustomer(String phoneNo) throws DataAccessException {
		return customerCtrl.findCustomer(phoneNo);
		
	}

	public Order createOrder(Customer customer) throws NullPointerException, DataAccessException {
		if (customer != null) {
			currentOrder = new Order(employee, customer);
		} else {
			throw new NullPointerException("No customer found");
		}
		return currentOrder;
	}

	public void addCopy(String vin) throws DataAccessException {
		Copy copy = carCtrl.findCopy(vin);

		if (copy != null) {
			currentOrder.addCopy(copy);
		}

	}

	public void confirmOrder() throws SQLException {
		
	}
}
