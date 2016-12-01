package se.humanus.DigitCashier;

import java.util.ArrayList;
import java.util.List;

public class ItemCategory {
	String categoryName;
	Float salesTaxMod=1.12f;
	Boolean measuredInWeight;
	private List<Item> itemsInCategory = new ArrayList<>(); 
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public float getSalesTax() {
		return salesTaxMod;
	}
	
	public void addItemToCategory(Item item) {
		itemsInCategory.add(item);		
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
