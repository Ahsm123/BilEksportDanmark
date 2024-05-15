package test;

import model.Copy;
import model.database.CarDBIF;

public class CarDBStub implements CarDBIF {

	@Override
	public Copy findCopy(String vin) {
		if ("abcdefgh1234".equals(vin)) {
			return new Copy(110000, "Peugeot", "308", "Diesel", 50, 120, 2.0, 298, "Manual", 6);
		}
		if ("abcdefgh11235".equals(vin)) {
			return new Copy(300000, "Suzuki", "Swift", "Gasoline", 200, 80, 10.0, 100, "Manual", 5);
		}
		return null; // Car not found
	}
}