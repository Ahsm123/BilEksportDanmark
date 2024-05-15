package controller;
import model.database.*;
import model.*;


public class CustomerCtrl {
	private CustomerDBIF custDB;
	
	public CustomerCtrl() {
		try {
			this.custDB = new CustomerDB();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Customer findCustomer(String phoneNo) throws DataAccessException {
		return custDB.findCustomer(phoneNo);
	}
	
	public boolean doesCustomerExist(String phoneNo) throws DataAccessException {
		return findCustomer(phoneNo) != null;
	}
	
}
