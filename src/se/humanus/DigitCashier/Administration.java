package se.humanus.DigitCashier;

import java.util.*;

public class Administration {

	
	public static String[] getCategoryList(){
		ArrayList<String> tempList = new ArrayList<String>();
		for (ItemCategory cat:CashRegister.categoryList){
			tempList.add(cat.getCategoryName());
		}
		String[] categoryChoices=tempList.toArray(new String[tempList.size()]);
		return categoryChoices;	
	}

}
