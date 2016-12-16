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
	
	public static void updateCategory(String catName, float catMoms, boolean measureWeight) {
		// TODO Auto-generated method stub
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

}
