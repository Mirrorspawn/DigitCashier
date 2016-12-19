package se.humanus.DigitCashier;

import java.text.DecimalFormat;

public class Item {
	//Class to handle items for purchase. /JS
	private String idNr; // unique identifying two-digit number
	private String name; // the name of the item
	private float price; // the price of the item
	private double discountModifier=1.0; //The number you have to multiply the price by to get the discounted price
	private ItemCategory myCategory; //The category this item belongs to
	
	public void assignToCategory(ItemCategory ic) { //Not yet tested, but should work. Not needed until we do adminstuff
		myCategory.removeItemFromCategory(this);
		ic.addItemToCategory(this);
		myCategory = ic;
		}
	
	public float getPrice() {
		return price;
		}
	
	public String getId() {
		return idNr;
		}
	
	public String getName() {
		return name;
		}
	
	public double getDiscount() {
		return discountModifier;
		}
	
	public ItemCategory getMyCategory() {
		return myCategory;
		}
	
	public void setPrice(float newPrice){
		price = newPrice;
	}
	
	public void setId(String newId) {
		idNr = newId;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	@Override
	public String toString() {
		//returns information about the item in String format.
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		return name + " á " + df.format(price)+"kr";
		}
	
	public Item (ItemCategory ic, String idNr, String name, float price) {
		//Standard constructor method, does not set discount modifier
		this.myCategory = ic;
		this.idNr = idNr;
		this.name = name;
		this.price = price;
		}

}
