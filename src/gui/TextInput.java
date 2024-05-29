package gui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gui.exceptions.InvalidPhoneNumberException;
import gui.exceptions.InvalidVinException;

public class TextInput {

	String vinRegex = "^[A-HJ-NPR-Z0-9]{17}$";
	Pattern pattern = Pattern.compile(vinRegex);
	
	//Danske numre:
	//12345678
	//12 34 56 78
	//1234 5678
	String phoneNumberRegex = "^(\\d{8}|\\d{2} \\d{2} \\d{2} \\d{2}|\\d{4} \\d{4})$";
    Pattern phoneNumberPattern = Pattern.compile(phoneNumberRegex);

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
	
	 public boolean phoneNumberValidator(String phoneNumber) {
	        boolean result = false;
	        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);
	        if (matcher.matches()) {
	            result = true;
	        } else {
	            throw new InvalidPhoneNumberException("Phone number is invalid");
	        }
	        return result;
	    }
	}