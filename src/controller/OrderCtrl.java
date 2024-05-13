//package controller;
//import model.Order;
//import model.database.OrderDBIF;
//import java.sql.SQLException;
//
//public class OrderCtrl {
//	private OrderDBIF orderDB;
//	private Order currentORder;
//	private CustomerCtrl customerCtrl;
////	private CopyCtrl copyCtrl;
////	private InvoiceCtrl invoiceCtrl;
////	
////	public OrderCtrl() throws DataAccessException {
////		this.orderDB = new OrderDB();
////		this.customerCtrl = new CustomerCtrl;
////		this.productCtrl = new ProductCtrl;
////		this.invoiceCtrl = new InvoiceCtrl;
//	}
//
//	private Customer findCustomer(String phoneNo) throws new DataAccessException {
//		return customerCtrl.findCustomer(phoneNo);
//
//	private Order createOrder(String phoneNo) throws DataAccessException {
//		Customer customer = findCustomer(phoneNo);
//		
//		if (customer != null) {
//			currentOrder = new Order(customer);
//		} else {
//			throw new NullPointerException("No customer found");
//		}
//		return currentOrder;
//	}
//	
//	private findCopy(String vin) throws DataAccessException {
//		return copyCtrl.findCopy(vin);
//	}
//	
//	public void addCopy(String vin) throws DataAccessException {
//		Copy copy = findCopy(vin);
//		
//		if(copy != null) {
//			currentOrder.addCopy(copy);
//		}
//		
//	}
//	public void confirmOrder() throws SQLException {
//		orderDB.saveOrderInDB(currentOrder) && invoiceController.saveInvoiceInDB(invoiceCtrl.createInvoice(currentOrder));
//	}
//}
