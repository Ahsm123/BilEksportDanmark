package gui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextInput {

    String vinRegex = "^[A-HJ-NPR-Z0-9]{17}$";
    Pattern pattern = Pattern.compile(vinRegex);

    public void VINValidator(String vin) {
        
        Matcher matcher = pattern.matcher(vin);
        if (matcher.matches()) {
            System.out.println("The VIN is valid.");
        } else {
            System.out.println("The VIN is invalid.");
        }
    }

}
