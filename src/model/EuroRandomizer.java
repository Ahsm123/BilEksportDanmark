package model;
import java.text.DecimalFormat;
import java.util.Random;

public class EuroRandomizer {

	public static void Main(String args[]) {
		DecimalFormat numberFormat = new DecimalFormat("#.####");
		Random random = new Random();
		double randomNumber = 7.4 + random.nextDouble() * 0.1;
		System.out.println("Random number between 7.4 and 7.5: " + numberFormat.format(randomNumber));
	}
}
