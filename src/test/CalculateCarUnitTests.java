package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import model.BuyInfo;
import model.Copy;
import model.api.*;
import model.database.BuyInfoDB;
import controller.BuyInfoCtrl;
import controller.CalculateCarCtrl;
import model.exceptions.CarDoesNotMeetRequirementsException;
import gui.TextInput;
import guiExceptions.InvalidVinException;

public class CalculateCarUnitTests {
	private CalculateCarCtrl calculateCarCtrl;
	private TextInput textInput;
	
	public CalculateCarUnitTests() throws SQLException{
		
		
	}
	
	@Before public void setUp() throws SQLException{
		calculateCarCtrl = new CalculateCarCtrl();
		textInput = new TextInput();
	}
	
	@Test
	public void TC_01_calculateOfferTest() throws CarDoesNotMeetRequirementsException {
		
		Copy copy = calculateCarCtrl.importCopy("5NPEB4AC5CH354720");
		int actual = (int) calculateCarCtrl.calculateOffer(copy, 100000);
		int expected = 154544;
		
		assertEquals(expected, actual);
		
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
	public void TC_06_importCopyWith50K() throws CarDoesNotMeetRequirementsException {
		
		Copy copy = calculateCarCtrl.importCopy("2G4WF5518Y1188740");
		assertNotNull("Copy should be imported", copy);
		
	}

	@Test
	public void TC_07_importCopyFrom2003() {
		assertThrows(CarDoesNotMeetRequirementsException.class, ()-> calculateCarCtrl.importCopy("JF1GE6B68AH507144"));

	}
	
	@Test
	public void TC_08_importCopyFrom2019() {
		assertThrows(CarDoesNotMeetRequirementsException.class, ()-> calculateCarCtrl.importCopy("3B7HC13ZXTG143211"));

	}
	
	
	@Test
	public void TC_09_importCopyWithSub50K() {
		assertThrows(CarDoesNotMeetRequirementsException.class, ()-> calculateCarCtrl.importCopy("ML32A4HJ5EH043996"));
	}
	
	
	
	

}
