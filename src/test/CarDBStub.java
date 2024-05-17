package test;

import model.Copy;
import model.database.CarDBIF;
import model.database.DataAccessException;

public class CarDBStub implements CarDBIF {

	@Override
	public Copy findCopy(String vin) throws DataAccessException {
		switch (vin) {
		case "abcdefgh1234":
			Copy copy = new Copy(0, "", "", "", 0, 0, 0, 0, "", 0);
			copy.setVin("abcdefgh1234");
			copy.setPrice(20000);
			copy.setState("");
			copy.setModification("");
			copy.setKilometer(2000);
			copy.setColor("Blue");
			copy.setTaxReturn(true);
			copy.setIsInspected(true);
			return copy;

		case "abcdefgh11235":
			Copy copy2 = new Copy(0, "", "", "", 0, 0, 0, 0, "", 0);
			copy2.setVin("abcdefgh11235");
			copy2.setPrice(20000);
			copy2.setState("");
			copy2.setModification("");
			copy2.setKilometer(2000);
			copy2.setColor("Blue");
			copy2.setTaxReturn(true);
			copy2.setIsInspected(true);
			return copy2;

		case "bbcdefgh1234":
			Copy copy3 = new Copy(0, "", "", "", 0, 0, 0, 0, "", 0);
			copy3.setVin("bbcdefgh1234");
			copy3.setPrice(20000);
			copy3.setState("");
			copy3.setModification("");
			copy3.setKilometer(2000);
			copy3.setColor("Blue");
			copy3.setTaxReturn(true);
			copy3.setIsInspected(true);
			return copy3;
			
		default:
            return null;
		}
	}
}