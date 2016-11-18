package se.humanus.DigitCashier;

public class Item {
	//Class to handle items for purchase. /JS
	private int idNr; // unique identifying two-digit number
	private String name; // the name of the item
	private double price; // the price of the iteem
	private double discountModifier=1.0; //The number you have to multiply the price by to get the discounted price
	
	public double getPrice () {
		return price;
		}
	
	public int getId () {
		return idNr;
		}
	
	public String getName () {
		return name;
		}
	
	public double getDiscount () {
		return discountModifier;
		}
	
	@Override
	public String toString() {
		//returns information about the item in String format
		return idNr + " " + name + " " + price;
		}
	
	public Item (int idNr, String name, double price) {
		//Standard constructor method, does not set discount modifier
		this.idNr = idNr;
		this.name = name;
		this.price = price;
		}

}
