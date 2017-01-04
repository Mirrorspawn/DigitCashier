package se.humanus.DigitCashier;

import java.util.ArrayList;
import java.util.List;

public class ItemCategory {
	String categoryName;
	Double salesTaxMod=0.12d;
	Boolean measuredInWeight;
	private List<Item> itemsInCategory = new ArrayList<>(); 
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String newName){
		categoryName = newName;
	}
	
	public double getSalesTax() {
		return salesTaxMod;
	}
	
	public void setSalesTax(double newSalesTax) {
		salesTaxMod = newSalesTax;
	}
	
	public Boolean getMeasuredInWeight() {
		return measuredInWeight;
	}
	
	public void setMeasuredInWeight(boolean newMeasure){
		measuredInWeight = newMeasure;
	}
	
	public void addItemToCategory(Item item) {
		itemsInCategory.add(item);		
	}
	
	public void removeItemFromCategory(Item item) { 
		//Removes an item from the category's list of items.
		if(itemsInCategory.contains(item)) {
			itemsInCategory.remove(itemsInCategory.indexOf(item));
		}
	}
	
	public boolean isItemInCategory(Item item) {
		//returns True if the passed Item is present in the category
		boolean itemPresent=false;
		for (Item i:itemsInCategory){
			if (i==item){
				itemPresent=true;
			}
		}
		return itemPresent;
	}
	
	public ItemCategory(String name, double taxMod, Boolean weight) {
		//Standard Constructor
		this.categoryName=name;
		this.salesTaxMod=taxMod;
		this.measuredInWeight=weight;
	}

}
