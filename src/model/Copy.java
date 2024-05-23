package model;

public class Copy extends Car{
	private int id;
	private String vin;
	private double price;
	private CarState state;
	private String modification;
	private int kilometer;
	private String color;
	private boolean taxReturn;
	private boolean isInspected;
	
	
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
			break;
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
}