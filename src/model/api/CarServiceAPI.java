package model.api;
import java.util.HashMap;

import model.Copy;

public class CarServiceAPI {
	
	private HashMap<String, Copy> carList;
	
	public CarServiceAPI() {
		carList = new HashMap<>();
		
		Copy copy0 = new Copy(20, "Volvo", "XC60", "Diesel", 124.00, 210, 8.1, 190, "Automatisk", 8);
		copy0.setId(10);
		copy0.setVin("5NPEB4AC5CH354720");
		copy0.setPurchasePrice(249000);
		copy0.setState(5);
		copy0.setModification("Standard");
		copy0.setKilometer(118000);
		copy0.setColor("Black");
		copy0.setTaxReturn(false);
		copy0.setIsInspected(false);
		copy0.setYear(2004);
		copy0.setRegistrationFee(416000);
		
		
		
		Copy copy1 = new Copy(17, "Peugeot", "407", "Benzin", 186.00, 213, 9.1, 140, "Manuel", 6);
		copy1.setId(11);
		copy1.setVin("JN8AZ08W64W393996");
		copy1.setPurchasePrice(38900);
		copy1.setState(5);
		copy1.setModification("Standard");
		copy1.setKilometer(150000);
		copy1.setColor("Grå");
		copy1.setTaxReturn(false);
		copy1.setIsInspected(false);
		copy1.setYear(2018);
		copy1.setRegistrationFee(194000);
		
		Copy copy2 = new Copy(40, "Lada", "Riva", "Benzin", 150.00, 65, 20, 140, "Manuel", 4);
		copy2.setId(12);
		copy2.setVin("2G4WF5518Y1188740");
		copy2.setPurchasePrice(20000);
		copy2.setState(5);
		copy2.setModification("Standard");
		copy2.setKilometer(50000);
		copy2.setColor("Red");
		copy2.setTaxReturn(false);
		copy2.setIsInspected(false);
		copy2.setYear(2010);
		copy2.setRegistrationFee(5000);
		
		Copy copy3 = new Copy(18, "Opel", "Corsa", "Benzin", 150.00, 90, 12, 189, "Manuel", 5);
		copy3.setId(13);
		copy3.setVin("JF1GE6B68AH507144");
		copy3.setPurchasePrice(20000);
		copy3.setState(5);
		copy3.setModification("Standard");
		copy3.setKilometer(55000);
		copy3.setColor("Red");
		copy3.setTaxReturn(false);
		copy3.setIsInspected(false);
		copy3.setYear(2003);
		copy3.setRegistrationFee(100000);
		
		Copy copy4 = new Copy(24, "BMW", "320", "Diesel", 250.00, 240, 6, 249, "Automatic", 8);
		copy4.setId(14);
		copy4.setVin("3B7HC13ZXTG143211");
		copy4.setPurchasePrice(349000);
		copy4.setState(5);
		copy4.setModification("Standard");
		copy4.setKilometer(59000);
		copy4.setColor("Red");
		copy4.setTaxReturn(false);
		copy4.setIsInspected(false);
		copy4.setYear(2019);
		copy4.setRegistrationFee(340000);
		
		Copy copy5 = new Copy(24, "BMW", "320", "Diesel", 250.00, 240, 6, 249, "Automatic", 8);
		copy5.setId(14);
		copy5.setVin("ML32A4HJ5EH043996");
		copy5.setPurchasePrice(349000);
		copy5.setState(5);
		copy5.setModification("Standard");
		copy5.setKilometer(49999);
		copy5.setColor("Red");
		copy5.setTaxReturn(false);
		copy5.setIsInspected(false);
		copy5.setYear(2010);
		copy5.setRegistrationFee(340000);
		
		Copy copy6 = new Copy(24, "BMW", "320", "Diesel", 250.00, 240, 6, 249, "Automatic", 8);
		copy6.setId(14);
		copy6.setVin("xxx");
		copy6.setPurchasePrice(349000);
		copy6.setState(5);
		copy6.setModification("Standard");
		copy6.setKilometer(49999);
		copy6.setColor("Red");
		copy6.setTaxReturn(false);
		copy6.setIsInspected(false);
		copy6.setYear(2010);
		copy6.setRegistrationFee(340000);
		
		carList.put("5NPEB4AC5CH354720", copy0);
		carList.put("JN8AZ08W64W393996", copy1);
		carList.put("2G4WF5518Y1188740", copy2);
		carList.put("JF1GE6B68AH507144", copy3);
		carList.put("3B7HC13ZXTG143211", copy4);
		carList.put("ML32A4HJ5EH043996", copy5);
		
		
		
		
	}
	
	public Copy importCopy(String vin) {
		Copy copy = carList.get(vin);
		return copy;
	}
		
	
	public HashMap<String, Copy> getCarList() {
		return carList;
	}

	public void setCarList(HashMap<String, Copy> carList) {
		this.carList = carList;
	}
	
	

}
