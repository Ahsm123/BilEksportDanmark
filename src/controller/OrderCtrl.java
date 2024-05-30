package controller;

import java.sql.SQLException;

import model.CarState;
import model.Copy;
import model.Invoice;
import model.Order;
import model.database.CarDBIF;
import model.database.CustomerDBIF;
import model.database.InvoiceDBIF;
import model.database.OrderDBIF;
import model.exceptions.CarAlreadySoldException;
import model.exceptions.CopyAlreadyInOrderException;
import model.exceptions.CopyNotFoundException;
import model.exceptions.CopyNotReady;
import model.exceptions.CustomerNotFound;
import model.exceptions.DataAccessException;
import model.exceptions.EmptyOrderException;

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

	public void removeCopy(String copyVin) {	
		currentOrder.removeCopyByVin(copyVin);
	}
	
	public Order createOrder(String phoneNo, int employeeId) throws NullPointerException, DataAccessException, CustomerNotFound {
		currentOrder = new Order(employeeId, customerCtrl.findCustomer(phoneNo));
		return currentOrder;
	}

	public Copy addCopy(String vin) throws DataAccessException, SQLException, CopyNotReady, CopyNotFoundException, CopyAlreadyInOrderException, CarAlreadySoldException {
		Copy copy = carCtrl.findCopy(vin);
		if(isCopyInAnOrder(vin)) {
			throw new CarAlreadySoldException("Bil allerede solgt");
		}
		else if(!copy.isInspected() || !copy.isTaxReturn() || copy.getState() != CarState.IN_STORAGE) {
			throw new CopyNotReady("Bilen har ikke gyldige dokumenter eller er ikke på lager");
		}
		else if(!currentOrder.hasCopy(copy)) {
			currentOrder.addCopy(copy);
		}
		else {
			throw new CopyAlreadyInOrderException("Bil med vin: " + vin + " er allerede tilføjet");
		}
		return copy;
	}
	
	public void deleteOrder(int orderId) throws SQLException, DataAccessException {
		orderDB.deleteOrder(orderId);
	}

	public Order confirmOrder() throws SQLException, DataAccessException, EmptyOrderException {
		if(currentOrder.getCopies().isEmpty()) {
			throw new EmptyOrderException("Ingen biler i ordren");
		}
		else {
			orderDB.saveOrder(currentOrder);
			Invoice invoice = invoiceCtrl.createInvoice(currentOrder);
			invoiceCtrl.saveInvoiceInDB(invoice);
		}
		return currentOrder;
	}
	
	public boolean isCopyInAnOrder(String input) throws SQLException {
		return orderDB.isCopyInAnOrder(input);
	}
	
	public Order getOrder() {
		return currentOrder;
	}
}