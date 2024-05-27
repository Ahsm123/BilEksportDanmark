package model.api;
import java.util.HashMap;

import model.Copy;

public class CarServiceAPI {
	
	private HashMap<String, Copy> carList;
	
	public CarServiceAPI() {
		carList = new HashMap<>();
		
		Copy copy0 = new Copy(20, "Volve", "XC60", "Diesel", 124.00, 210, 8.1, 190, "Automatisk", 8);
		copy0.setId(3);
		copy0.setVin("00");
		copy0.setPurchasePrice(249000);
		copy0.setState(5);
		copy0.setModification("Standard");
		copy0.setKilometer(118000);
		copy0.setColor("Black");
		copy0.setTaxReturn(false);
		copy0.setIsInspected(false);
		copy0.setYear(2017);
		copy0.setRegistrationFee(416000);
		
		
		
		Copy copy1 = new Copy(17, "Peugeot", "407", "Benzin", 186.00, 213, 9.1, 140, "Manuel", 6);
		copy1.setId(1);
		copy1.setVin("11");
		copy1.setPurchasePrice(38900);
		copy1.setState(5);
		copy1.setModification("Standard");
		copy1.setKilometer(154523);
		copy1.setColor("Grå");
		copy1.setTaxReturn(false);
		copy1.setIsInspected(false);
		copy1.setYear(2007);
		copy1.setRegistrationFee(194000);
		
		Copy copy2 = new Copy(40, "Lada", "Riva", "Benzin", 150.00, 65, 20, 140, "Manuel", 4);
		copy2.setId(2);
		copy2.setVin("22");
		copy2.setPurchasePrice(20000);
		copy2.setState(5);
		copy2.setModification("Standard");
		copy2.setKilometer(400000);
		copy2.setColor("Red");
		copy2.setTaxReturn(false);
		copy2.setIsInspected(false);
		copy2.setYear(1990);
		copy2.setRegistrationFee(5000);
		
		carList.put("00", copy0);
		carList.put("11", copy1);
		carList.put("22", copy2);
		
		
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
