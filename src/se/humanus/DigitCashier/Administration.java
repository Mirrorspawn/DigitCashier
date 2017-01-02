package se.humanus.DigitCashier;

import java.util.*;

public class Administration {
	
	private static ItemCategory editCategory;
	private static Item editItem;

	
	public static String[] getCategoryList(){
		ArrayList<String> tempList = new ArrayList<String>();
		for (ItemCategory cat:CashRegister.categoryList){
			tempList.add(cat.getCategoryName());
		}
		String[] categoryChoices=tempList.toArray(new String[tempList.size()]);
		return categoryChoices;	
	}
	
	public static String[] getItemList(){
		ArrayList<String> tempList = new ArrayList<String>();
		for (Item item:CashRegister.itemList){
			tempList.add(item.getName());
		}
		String[] itemChoices=tempList.toArray(new String[tempList.size()]);
		return itemChoices;
		}
	
	public static void setCurrentCategory(ItemCategory ic){
		editCategory = ic;		
	}
	
	public static void setCurrentItem(Item it){
		editItem = it;
	}
	
	public static boolean isIdNrTaken(String idNr){
		boolean taken = false;
		for (int i=0; i<CashRegister.getLengthOfItemList(); i++){
			if (editItem.getId().equals(idNr)){
				taken = true;
				}
			}
			return taken;
	}
	
	public static void updateCategory(String catName, double catMoms, boolean measureWeight) {
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
