package controller;

import model.Customer;

public class CustomerProvider {
	private Customer customer;

	public CustomerProvider() {
		customer.setId(1);
		customer.setSsn(87654321);
		customer.setAge(28);
		customer.setEmail("Anders@lonewolf.dk");
		customer.setPhoneNo("1111111");

	}

	public Customer provideCustomer() {
		return customer;
	}
}