package se.humanus.DigitCashier;

import java.text.DecimalFormat;

public class Item {
	//Class to handle items for purchase. /JS
	private int idNr; // unique identifying two-digit number
	private String name; // the name of the item
	private float price; // the price of the iteem
	private double discountModifier=1.0; //The number you have to multiply the price by to get the discounted price
	
	public float getPrice() {
		return this.price;
		}
	
	public int getId() {
		return this.idNr;
		}
	
	public String getName() {
		return this.name;
		}
	
	public double getDiscount() {
		return this.discountModifier;
		}
	
	@Override
	public String toString() {
		//returns information about the item in String format. Needs to be changed to give the price with double decimals.
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		return name + " á " + df.format(price)+"kr";
		}
	
	public Item (int idNr, String name, float price) {
		//Standard constructor method, does not set discount modifier
		this.idNr = idNr;
		this.name = name;
		this.price = price;
		}

}
