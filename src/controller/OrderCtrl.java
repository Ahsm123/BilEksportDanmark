package controller;

import model.Order;
import model.database.CarDB;
import model.database.CarDBIF;
import model.database.CustomerDB;
import model.database.CustomerDBIF;
import model.database.InvoiceDB;
import model.database.InvoiceDBIF;
import model.database.OrderDB;
import model.database.OrderDBIF;
import model.exceptions.CarAlreadySoldException;
import model.exceptions.CopyAlreadyInOrderException;
import model.exceptions.CopyNotReady;
import model.exceptions.DataAccessException;
import model.exceptions.EmptyOrderException;

import java.sql.SQLException;
import model.Copy;

public class OrderCtrl {
	private OrderDBIF orderDB;
	private CustomerCtrl customerCtrl;
	private CarCtrl carCtrl;
	private InvoiceCtrl invoiceCtrl;
	private Order currentOrder;

	public OrderCtrl(OrderDBIF orderDB, CustomerDBIF customerDB, CarDBIF carDB, InvoiceDBIF invoiceDB) throws DataAccessException, SQLException {
		this.orderDB = orderDB;
		this.customerCtrl = new CustomerCtrl(customerDB);
		this.carCtrl = new CarCtrl(carDB);
		this.invoiceCtrl = new InvoiceCtrl(invoiceDB);
	}

	public OrderCtrl() throws SQLException, DataAccessException {
		this.orderDB = new OrderDB();
		this.customerCtrl = new CustomerCtrl(new CustomerDB());
		this.carCtrl = new CarCtrl(new CarDB());
		this.invoiceCtrl = new InvoiceCtrl(new InvoiceDB());
	}

	public void removeCopy(String copyVin) {	
		currentOrder.removeCopyByVin(copyVin);
	}
	
	public Order createOrder(String phoneNo, int employeeId) throws NullPointerException, DataAccessException {
		currentOrder = new Order(employeeId, customerCtrl.findCustomer(phoneNo));
		return currentOrder;
	}

	public void addCopy(String vin) throws DataAccessException, SQLException, CopyNotReady {
		Copy copy = carCtrl.findCopy(vin);
		if(copy == null) {
			throw new NullPointerException();
		}
		else if(isCopyInAnOrder(vin)) {
			throw new CarAlreadySoldException("Bil allerede solgt");
		}
		else if(!copy.isInspected()) {
			throw new CopyNotReady("Bilen har ikke gyldige dokumenter");
		}
		else if(!currentOrder.hasCopy(copy)) {
			currentOrder.addCopy(copy);
		}
		else {
			throw new CopyAlreadyInOrderException("Bil med vin: " + vin + " er allerede tilføjet");
		}
	}
	
	public void deleteOrder(int orderId) throws SQLException, DataAccessException {
		orderDB.deleteOrder(orderId);
	}

	public void confirmOrder() throws SQLException, DataAccessException, EmptyOrderException {
		if(currentOrder.getCopies().isEmpty()) {
			throw new EmptyOrderException("No cars in the order");
		}
		else {
			orderDB.saveOrder(currentOrder);
			invoiceCtrl.saveInvoiceInDB(currentOrder);
		}
	}
	
	public boolean isCopyInAnOrder(String input) throws SQLException {
		return orderDB.isCopyInAnOrder(input);
	}
	
	public Order getOrder() {
		return currentOrder;
	}
}