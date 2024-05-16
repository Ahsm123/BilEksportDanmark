package controller;

import model.Customer;
import model.Employee;
import model.EmptyOrderException;
import model.Order;
import model.database.CarDBIF;
import model.database.CustomerDBIF;
import model.database.DataAccessException;
import model.database.InvoiceDBIF;
import model.database.OrderDB;
import model.database.OrderDBIF;
import java.sql.SQLException;
import model.Copy;
import model.CopyAlreadyInOrderException;

public class OrderCtrl {
	private OrderDBIF orderDB;
	private CustomerCtrl customerCtrl;
	private CarCtrl carCtrl;
	private InvoiceCtrl invoiceCtrl;
	private Order currentOrder;
	private Employee employee;

	public OrderCtrl(OrderDBIF orderDB, CustomerDBIF customerDB, CarDBIF carDB, InvoiceDBIF invoiceDB) throws DataAccessException, SQLException {
	
		this.orderDB = new OrderDB();
		this.customerCtrl = new CustomerCtrl();
		this.carCtrl = new CarCtrl();
		this.invoiceCtrl = new InvoiceCtrl();
	}

	public OrderCtrl() throws SQLException {
		this.orderDB = new OrderDB();
		this.customerCtrl = new CustomerCtrl();
		this.carCtrl = new CarCtrl();
		this.invoiceCtrl = new InvoiceCtrl();
	}

	public Customer findCustomer(String phoneNo) throws DataAccessException {
		return customerCtrl.findCustomer(phoneNo);
		
	}

	public void removeCopy(String copyVin) {	
		currentOrder.removeCopy(copyVin);
	}
	
	public Order createOrder(String phoneNo) throws NullPointerException, DataAccessException {
		currentOrder = new Order(employee, findCustomer(phoneNo));
		return currentOrder;
	}

	public void addCopy(String vin) throws DataAccessException {
		Copy copy = carCtrl.findCopy(vin);
		
		if(!currentOrder.hasCopy(copy)) {
			currentOrder.addCopy(copy);
		}
		else {
			throw new CopyAlreadyInOrderException("Bil med vin: " + vin + " er allerede tilføjet");
		}
	}

	public void confirmOrder() throws SQLException, DataAccessException, EmptyOrderException {
		if(currentOrder.getCopies().isEmpty()) {
			throw new EmptyOrderException("No cars in the order");
		}
		orderDB.saveOrder(currentOrder);
		invoiceCtrl.saveInvoiceInDB(currentOrder);
	}
	
	public boolean isCopyInAnOrder(String input) throws SQLException {
		return orderDB.isCopyInAnOrder(input);
	}
	
	public Order getOrder() {
		return currentOrder;
	}
}