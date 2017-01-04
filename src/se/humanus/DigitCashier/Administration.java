package se.humanus.DigitCashier;

import java.util.*;

public class Administration {
	
	private static ItemCategory editCategory; //The Category that is currently chosen in the Admin-window
	private static Item editItem; //The Item that is currently chosen in the Admin-Window

	
	public static String[] getCategoryList(){   
		//Returns the names of the Categories in Category list as an Array of strings for use with Combo-box
		ArrayList<String> tempList = new ArrayList<String>();
		for (ItemCategory cat:CashRegister.categoryList){
			tempList.add(cat.getCategoryName());
		}
		String[] categoryChoices=tempList.toArray(new String[tempList.size()]);
		return categoryChoices;	
	}
	
	public static String[] getItemList(){
		//Returns the names of the Items in Item list as an Array of strings for use with Combo-box
		ArrayList<String> tempList = new ArrayList<String>();
		for (Item item:CashRegister.itemList){
			tempList.add(item.getName());
		}
		String[] itemChoices=tempList.toArray(new String[tempList.size()]);
		return itemChoices;
		}
	
	public static void setCurrentCategory(ItemCategory ic){
		//sets editCategory to the Category passed
		editCategory = ic;		
	}
	
	public static void setCurrentItem(Item it){
		//sets editItem to the Item passed
		editItem = it;
	}
	
	public static boolean isIdNrTaken(String idNr){
		//Checks if a specific Item IdNr is already in use and returns true if it is, and false if it isn't
		boolean taken = false;
		for (int i=0; i<CashRegister.getLengthOfItemList(); i++){
			if (editItem.getId().equals(idNr)){
				taken = true;
				}
			}
			return taken;
	}
	
	public static void updateCategory(String catName, double catMoms, boolean measureWeight) {
		//Checks if the Arguments provided are different from the attributes of the current editCategory,
		//and if so, changes the attribute of the category.
		if (!(editCategory.getCategoryName().equals(catName))){
			editCategory.setCategoryName(catName);
		}
		if (!(editCategory.getSalesTax()==catMoms)){
			editCategory.setSalesTax(catMoms);
		}
		if (!(editCategory.getMeasuredInWeight()==measureWeight)){
			editCategory.setMeasuredInWeight(measureWeight);
		}
	}
	
	public static void updateItem(String itemName, String idNr, ItemCategory ic, double itemPrice){
		//Checks if the Arguments provided are different from the attributes of the current editItem,
		//and if so, changes the attribute of the item.
		if (!(editItem.getId().equals(idNr))) {
			editItem.setId(idNr);				
		}
		if (!(editItem.getName().equals(itemName))){
			editItem.setName(itemName);
		}
		if (!(editItem.getPrice()==itemPrice)){
			editItem.setPrice(itemPrice);
		}
		if (!(editItem.getMyCategory() == ic)){
			editItem.assignToCategory(ic);
		}
		
	}

}
