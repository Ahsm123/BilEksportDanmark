package controller;

import model.Copy;

import java.util.LinkedList;

import model.Car;

public class CopyProvider {
	private Copy copy;
	private LinkedList<Copy> copies;
	
	public CopyProvider() {
		copies = new LinkedList<>();
		
	    Car car = new Car();
        car.setMilage(10000);
        car.setManufacturer("Toyota");
        car.setModel("Corolla");
        car.setGearType("Automatic");
        car.setFuelType("Petrol");
        car.setCo2Emission(120.5);
        car.setHp(150);
        car.setAcceleration(9.2);
        car.setTopSpeed(180);
        car.setNoOfGears(6);
        
        Copy copy = new Copy();
        copy.setId(1);
        copy.setVin("ABC123DEF456");
        copy.setPrice(25000.00);
        copy.setState("New");
        copy.setModification("Standard");
        copy.setKilometer(0);
        copy.setColor("Black");
        copy.setTaxReturn(false);
        copy.setIsInspected(false);
        
        copies.add(copy);
	}
	
	public Copy provideCopy() {
		return copies.getFirst();
	}
	
	

}
