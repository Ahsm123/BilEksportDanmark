package model.api;
import java.util.HashMap;

import model.Copy;

public class CarServiceAPI {
	
	private HashMap<String, Copy> carList;
	
	public CarServiceAPI() {
		carList = new HashMap<>();
		
		Copy copy1 = new Copy(17, "Porsche", "911", "Benzin", 300.00, 360, 4.5, 300, "Manuel", 6);
		copy1.setId(1);
		copy1.setVin("11");
		copy1.setPurchasePrice(150000);
		copy1.setState(5);
		copy1.setModification("Standard");
		copy1.setKilometer(200000);
		copy1.setColor("White");
		copy1.setTaxReturn(false);
		copy1.setIsInspected(false);
		copy1.setYear(2018);
		copy1.setRegistrationFee(75000);
		
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
