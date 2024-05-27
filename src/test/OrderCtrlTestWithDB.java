package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import controller.OrderCtrl;
import model.Customer;
import model.Order;
import model.database.CarDB;
import model.database.CustomerDB;
import model.database.InvoiceDB;
import model.database.OrderDB;
import model.exceptions.CarAlreadySoldException;
import model.exceptions.CopyAlreadyInOrderException;
import model.exceptions.DataAccessException;
import model.exceptions.EmptyOrderException;

public class OrderCtrlTestWithDB {

	private OrderCtrl orderCtrl;

	@Before
	public void setup() throws DataAccessException, SQLException {
		CustomerDB customerDB = new CustomerDB();
		CarDB carDB = new CarDB();
		InvoiceDB invoiceDB = new InvoiceDB();
		OrderDB orderDB = new OrderDB();

		try {
			orderCtrl = new OrderCtrl(orderDB, customerDB, carDB, invoiceDB);
		} catch (DataAccessException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TC_01_testCreateOrderWithPhoneNumber() throws DataAccessException, EmptyOrderException, SQLException {
		// Arrange
		Order order = orderCtrl.createOrder("12345678", 1);
		Customer customer = order.getCustomer();

		// Act
		orderCtrl.addCopy("abcdefgh1234");
		orderCtrl.confirmOrder();

		// Assert
		assertEquals("Customer should be found", customer);
		assertNotNull("Order should be created", order);
		assertEquals("Order should have one car", 1, order.getCopies().size());
	}

	@Test
	public void TC_02_testCreateOrderWithoutPhoneNumber() throws DataAccessException {
		// Arrange
		Order order = orderCtrl.createOrder(" ", 1);
		Customer customer = order.getCustomer();

		// Assert
		assertNull("Customer should not be found", customer);
	}

	@Test
	public void TC_03_testCreateOrderWithPhoneNumberAndMultiplyCars()
			throws DataAccessException, EmptyOrderException, SQLException {

		// Arrange
		Order order = orderCtrl.createOrder("12345678", 1);
		Customer customer = order.getCustomer();
		// Act
		orderCtrl.addCopy("abcdefgh1234");
		orderCtrl.addCopy("abcdefgh11235");
		orderCtrl.confirmOrder();

		// Assert
		assertNotNull("Customer should be found", customer);
		assertNotNull("Order should be created", order);
		assertEquals("Order should have two cars", 2, order.getCopies().size());

	}

	@Test
	public void TC_04_testCreateOrderWithPhoneNumberAndSoldCar()
			throws DataAccessException, EmptyOrderException, SQLException {

		// Arrange
		Order order = orderCtrl.createOrder("12345678", 1);
		Customer customer = order.getCustomer();
		// Act
		assertThrows(NullPointerException.class, ()->orderCtrl.addCopy("null_value"));

		// Assert
		assertNotNull("Customer should be found", customer);
		assertNotNull("Order should be created", order);
		assertEquals("Order should have one car", 0, order.getCopies().size());
	}

	@Test
	public void TC_05_testCreateOrderWithPhoneNumberAndSoldCarPlusOne()
			throws DataAccessException, EmptyOrderException, SQLException {

		// Arrange

		Order order = orderCtrl.createOrder("12345678", 1);
		Customer customer = order.getCustomer();
		// Act
		orderCtrl.addCopy("abcdefgh1234");
		assertThrows(CarAlreadySoldException.class, ()-> orderCtrl.addCopy("bbcdefgh1234"));
		
		orderCtrl.confirmOrder();

		// Assert
		assertNotNull("Customer should be found", customer);
		assertNotNull("Order should be created", order);
		assertEquals("Order should have one car", 1, order.getCopies().size());
	}

}