package gui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import guiExceptions.InvalidVinException;

public class TextInput {

	String vinRegex = "^[A-HJ-NPR-Z0-9]{17}$";
	Pattern pattern = Pattern.compile(vinRegex);

	public boolean VINValidator(String vin) {
		boolean result = false;
		Matcher matcher = pattern.matcher(vin);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new InvalidVinException("Vin number is invalid");
		}
		return result;
	}
}
