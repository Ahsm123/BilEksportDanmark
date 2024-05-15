package controller;
import model.database.*;
import model.*;


public class CustomerCtrl {
	private CustomerDBIF customerDB;
	
	public CustomerCtrl() {
		try {
			this.customerDB = new CustomerDB();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	public Customer findCustomer(String phoneNo) throws DataAccessException {
		return customerDB.findCustomer(phoneNo);
	}
	
}
