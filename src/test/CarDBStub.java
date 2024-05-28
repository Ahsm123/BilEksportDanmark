package test;

import model.Copy;
import model.database.CarDBIF;
import model.exceptions.DataAccessException;

public class CarDBStub implements CarDBIF {

	@Override
	public Copy findCopy(String vin) throws DataAccessException {
		switch (vin) {
		case "1G4HR57Y18U165590":
			Copy copy0 = new Copy(18, "Ford", "multipla", "Benzin", 10, 80, 12,180, "manuel", 6);
			copy0.setVin("1G4HR57Y18U165590");
			copy0.setSalesPrice(20000);
			copy0.setState(2);
			copy0.setModification(	"");
			copy0.setKilometer(200000);
			copy0.setColor("Blue");
			copy0.setTaxReturn(true);
			copy0.setIsInspected(true);
			return copy0;

		case "KNDJP3A55E7006855":
			Copy copy1 = new Copy(7, "Ferrari", "Spyder", "Benzin", 30, 480, 3.5, 320, "manuel", 7);
			copy1.setVin("KNDJP3A55E7006855");
			copy1.setSalesPrice(20000);
			copy1.setState(2);
			copy1.setModification("");
			copy1.setKilometer(60000);
			copy1.setColor("Blue");
			copy1.setTaxReturn(true);
			copy1.setIsInspected(true);
			return copy1;
			
			//Solgt bil
		case "5NPEB4AC7CH325431":
			Copy copy2 = new Copy(20, "Suzuki", "Swift", "Benzin", 10, 120, 9, 220, "automatic", 6);
			copy2.setVin("5NPEB4AC7CH325431");
			copy2.setSalesPrice(20000);
			copy2.setState(2);
			copy2.setModification("");
			copy2.setKilometer(70000);
			copy2.setColor("Blue");
			copy2.setTaxReturn(true);
			copy2.setIsInspected(true);
			return copy2;
			
		case "1HGCT1B73EA082703":
			Copy copy3 = new Copy(15, "Volve", "V60", "Diesel", 20, 140, 7, 210, "automatic", 7);
			copy3.setVin("1HGCT1B73EA082703");
			copy3.setSalesPrice(20000);
			copy3.setState(2);
			copy3.setModification("");
			copy3.setKilometer(70000);
			copy3.setColor("Blue");
			copy3.setTaxReturn(true);
			copy3.setIsInspected(false);
			return copy3;
			
		default:
            return null;
		}
	}
}