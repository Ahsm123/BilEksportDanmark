package model;

public class Copy extends Car{
	private int id;
	private String vin;
	private double salesPrice;
	private double purchasePrice;
	private CarState state;
	private String modification;
	private int kilometer;
	private String color;
	private boolean taxReturn;
	private boolean isInspected;
	private int year;
	private double registrationFee;
	
	
	public Copy(int milage,String manufacturer,String model,String fuelType,double co2Emission,
			int hp,double acceleration,int topSpeed,String gearType,int noOfGears) {
		super(milage, manufacturer, model, fuelType, co2Emission,
				hp, acceleration, topSpeed, gearType, noOfGears);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}


	public CarState getState() {
		return state;
	}

	public void setState(int state) {
		CarState stateToSet = null;
		switch(state) {
		case 1: 
			stateToSet = CarState.AWAITING_OFFER;
			break;
		case 2: 
			stateToSet = CarState.IN_STORAGE;
			break;
		case 3:
			stateToSet = CarState.IN_TRANSIT;
			break;
		case 4:
			stateToSet = CarState.SOLD;
			break;
		case 5:
			stateToSet = CarState.OFFER_SENT;
			break;
		default:
			stateToSet = CarState.IN_STORAGE;
		}
		this.state = stateToSet;
		
	}

	public String getModification() {
		return modification;
	}

	public void setModification(String modification) {
		this.modification = modification;
	}

	public int getKilometer() {
		return kilometer;
	}

	public void setKilometer(int kilometer) {
		this.kilometer = kilometer;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isTaxReturn() {
		return taxReturn;
	}

	public void setTaxReturn(boolean taxReturn) {
		this.taxReturn = taxReturn;
	}

	public boolean isInspected() {
		return isInspected;
	}

	public void setIsInspected(boolean isInspected) {
		this.isInspected = isInspected;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
		
	}

	public double getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(double registrationFee) {
		this.registrationFee = registrationFee;
	}

	public double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
}