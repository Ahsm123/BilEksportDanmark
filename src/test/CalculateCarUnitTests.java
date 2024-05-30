package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import controller.CalculateCarCtrl;
import gui.TextInput;
import gui.exceptions.InvalidVinException;
import model.BuyInfo;
import model.Copy;
import model.database.BuyInfoDB;
import model.database.CarDB;
import model.database.SellerDB;
import model.exceptions.CarDoesNotMeetRequirementsException;
import model.exceptions.DataAccessException;

public class CalculateCarUnitTests {
	private CalculateCarCtrl calculateCarCtrl;
	private BuyInfoDBStub buyInfoDBStub;
	private TextInput textInput;
	
	public CalculateCarUnitTests() throws SQLException{
		
		
	}
	
	@Before public void setUp() throws SQLException, DataAccessException{
		calculateCarCtrl = new CalculateCarCtrl(new CarDB(), new BuyInfoDB(), new SellerDB());
		textInput = new TextInput();
		buyInfoDBStub = new BuyInfoDBStub();
	}
	
	@Test
	public void TC_01_calculateOfferTest() throws CarDoesNotMeetRequirementsException, SQLException, DataAccessException {
		
		Copy copy = calculateCarCtrl.importCopy("5NPEB4AC5CH354720");
		calculateCarCtrl.calculateOffer(copy, 125000);
		
		BuyInfo latestBuyInfo = calculateCarCtrl.getLatestBuyInfo();
		buyInfoDBStub.saveBuyInfo(latestBuyInfo);
		
		LinkedList<BuyInfo> buyOrders = buyInfoDBStub.getBuyOrders();
		
		assertFalse("Buy orders list should not be empty", buyOrders.isEmpty()) ;
		
	}
	
	@Test
	public void TC_02_invalidVinNumber() {
		
		assertThrows(InvalidVinException.class, ()-> textInput.VINValidator("xxx"));
		
		
		
	}
	
	@Test
	public void TC_03_importCopyFrom2004() throws CarDoesNotMeetRequirementsException {
		
		Copy copy = calculateCarCtrl.importCopy("5NPEB4AC5CH354720");
		assertNotNull("Copy should be imported", copy);
		
	}
	
	@Test
	public void TC_04_importCopyFrom2018() throws CarDoesNotMeetRequirementsException {
		
		Copy copy = calculateCarCtrl.importCopy("JN8AZ08W64W393996");
		assertNotNull("Copy should be imported", copy);
		
	}
	@Test
	public void TC_05_importCopyWith50K() throws CarDoesNotMeetRequirementsException {
		
		Copy copy = calculateCarCtrl.importCopy("2G4WF5518Y1188740");
		assertNotNull("Copy should be imported", copy);
		
	}

	@Test
	public void TC_06_importCopyFrom2003() {
		assertThrows(CarDoesNotMeetRequirementsException.class, ()-> calculateCarCtrl.importCopy("JF1GE6B68AH507144"));

	}
	
	@Test
	public void TC_07_importCopyFrom2019() {
		assertThrows(CarDoesNotMeetRequirementsException.class, ()-> calculateCarCtrl.importCopy("3B7HC13ZXTG143211"));

	}
	
	
	@Test
	public void TC_08_importCopyWithSub50K() {
		assertThrows(CarDoesNotMeetRequirementsException.class, ()-> calculateCarCtrl.importCopy("ML32A4HJ5EH043996"));
	}
	
	
	
	

}
