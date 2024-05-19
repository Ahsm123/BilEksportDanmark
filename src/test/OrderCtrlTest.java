package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import controller.OrderCtrl;
import model.CopyAlreadyInOrderException;
import model.Customer;
import model.EmptyOrderException;
import model.Order;
import model.database.CarAlreadySoldException;
import model.database.DataAccessException;

public class OrderCtrlTest {

	private OrderCtrl orderCtrl;

	@Before
	public void setup() {
		CustomerDBStub customerDBStub = new CustomerDBStub();
		CarDBStub carDBStub = new CarDBStub();
		InvoiceDBStub invoiceDBStub = new InvoiceDBStub();
		OrderDBStub orderDBStub = new OrderDBStub();

		try {
			orderCtrl = new OrderCtrl(orderDBStub, customerDBStub, carDBStub, invoiceDBStub);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TC_01_testCreateOrderWithPhoneNumber() throws DataAccessException, EmptyOrderException, SQLException {
		// Arrange
		Customer customer = orderCtrl.findCustomer("12345678");
		Order order = orderCtrl.createOrder("12345678", 1);

		// Act
		orderCtrl.addCopy("abcdefgh1234");
		orderCtrl.confirmOrder();

		// Assert
		assertNotNull("Customer should be found", customer);
		assertNotNull("Order should be created", order);
		assertEquals("Order should have one car", 1, order.getCopies().size());
	}

	@Test
	public void TC_02_testCreateOrderWithoutPhoneNumber() throws DataAccessException {
		// Arrange
		Customer customer = orderCtrl.findCustomer("");

		// Assert
		assertNull("Customer should not be found", customer);
	}

	@Test
	public void TC_03_testCreateOrderWithPhoneNumberAndMultiplyCars()
			throws DataAccessException, EmptyOrderException, SQLException {

		// Arrange
		Customer customer = orderCtrl.findCustomer("12345678");
		Order order = orderCtrl.createOrder("12345678", 1);

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
		Customer customer = orderCtrl.findCustomer("12345678");
		Order order = orderCtrl.createOrder("12345678", 1);

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
		Customer customer = orderCtrl.findCustomer("12345678");
		Order order = orderCtrl.createOrder("12345678", 1);

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