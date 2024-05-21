package model;

public class Car {
	private int milage;
	private String manufacturer;
	private String model;
	private String gearType;
	private String fuelType;
	private double co2Emission;
	private int hp;
	private double acceleration;
	private int topSpeed;
	private int noOfGears;
	
	public Car(int milage,String manufacturer,String model,String fuelType,double co2Emission,
			int hp,double acceleration,int topSpeed,String gearType,int noOfGears){
	
		this.milage=milage;
		this.manufacturer=manufacturer;
		this.model=model;
		this.fuelType=fuelType;
		this.co2Emission=co2Emission;
		this.hp=hp;
		this.acceleration=acceleration;
		this.topSpeed=topSpeed;
		this.gearType=gearType;
		this.noOfGears=noOfGears;
	}

	public int getMilage() {
		return milage;
	}

	public void setMilage(int milage) {
		this.milage = milage;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public double getCo2Emission() {
		return co2Emission;
	}

	public void setCo2Emission(double co2Emission) {
		this.co2Emission = co2Emission;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public int getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}

	public String getGearType() {
		return gearType;
	}

	public void setGearType(String gearType) {
		this.gearType = gearType;
	}

	public int getNoOfGears() {
		return noOfGears;
	}

	public void setNoOfGears(int noOfGears) {
		this.noOfGears = noOfGears;
	}
}