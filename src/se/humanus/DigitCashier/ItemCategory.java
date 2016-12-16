package se.humanus.DigitCashier;

import java.util.ArrayList;
import java.util.List;

public class ItemCategory {
	String categoryName;
	Float salesTaxMod=0.12f;
	Boolean measuredInWeight;
	private List<Item> itemsInCategory = new ArrayList<>(); 
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String newName){
		categoryName = newName;
	}
	
	public float getSalesTax() {
		return salesTaxMod;
	}
	
	public void setSalesTax(float newSalesTax) {
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
	
	public void removeItemFromCategory(Item item) { //Not tested. Removes an item from the category's list of items.
		if(itemsInCategory.contains(item)) {
			itemsInCategory.remove(itemsInCategory.indexOf(item));
		}
	}
	
	public boolean isItemInCategory(Item item) {
		boolean itemPresent=false;
		for (Item i:itemsInCategory){
			if (i==item){
				itemPresent=true;
			}
		}
		return itemPresent;
	}
	
	public ItemCategory(String name, float taxMod, Boolean weight) {
		this.categoryName=name;
		this.salesTaxMod=taxMod;
		this.measuredInWeight=weight;
	}

}
