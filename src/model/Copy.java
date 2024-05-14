package model;

public class Copy {
	private int id;
	private String vin;
	private double price;
	private String state;
	private String modification;
	private int kilometer;
	private String color;
	private boolean taxReturn;
	private boolean isInspected;
	
	public Copy(){}
	
	public Copy(int id,String vin,double price,String state,String modification,
			int kilometer,String color,boolean taxReturn,boolean isInspected) {
		
		this.id=id;
		this.vin=vin;
		this.price=price;
		this.state=state;
		this.modification=modification;
		this.kilometer=kilometer;
		this.color=color;
		this.taxReturn=taxReturn;
		this.isInspected=isInspected;
		
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
