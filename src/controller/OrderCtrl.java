package controller;

import model.Customer;
import model.Employee;
import model.EmptyOrderException;
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

	public void removeCopy(Copy copy) {
		currentOrder.removeCopy(copy);
	}
	
	public Order createOrder(String phoneNo) throws NullPointerException, DataAccessException {
		currentOrder = new Order(employee, findCustomer(phoneNo));
		return currentOrder;
	}

	public void addCopy(String vin) throws DataAccessException {
		Copy copy = carCtrl.findCopy(vin);

		if (copy != null) {
			currentOrder.addCopy(copy);
		}

	}

	public void confirmOrder() throws SQLException, DataAccessException, EmptyOrderException {
		if(currentOrder.getCopies().isEmpty()) {
			throw new EmptyOrderException("No cars in the order");
		}
		orderDB.saveOrder(currentOrder);
		invoiceCtrl.saveInvoiceInDB(currentOrder);
	}
	
	public Order getOrder() {
		return currentOrder;
	}
}