package se.humanus.DigitCashier;
import java.util.*;

public class CashRegister {
	static List<Item> itemList = new ArrayList<>();
	static List<Item> saleItemList = new ArrayList<>();
	static List<Float> amountOfItemList = new ArrayList<>();
	static String discount_20p = "20% rabatt";
	static String discount_50kr = "-50kr";
	static String discount = "";
		
	
	public static String getDiscount() {
		return discount;
	}

	public static void setDiscount(String discount) {
		CashRegister.discount = discount;
	}

	public static float applyDiscount(String discount, float sum) {
		float result = sum;
		if(discount.equals(discount_50kr)) {
			result = sum -50;
		}
		if(discount.equals(discount_20p)) {
			result = (float) (sum *0.8);
		}
		
		return result;
	}
	public static void initializeItems() {
		//Hardcoded creation of items. This is called from the Main method in the Login Class /JS
		
		itemList.add(new Item(1,"Standardmj�lk, 1l", 9.10f));
		itemList.add(new Item(2,"Creme Fraiche 34%, 5dl", 19.95f));
		itemList.add(new Item(3,"Kronj�st F�rsk, 50g", 2.50f));
		itemList.add(new Item(4,"R�kt Skinka, 120g", 22.95f));
		itemList.add(new Item(5,"Falukorv Ring Scan, 800g", 9.10f));
		itemList.add(new Item(6,"Kalles Kaviar ABBA, 300g", 26.95f));
		itemList.add(new Item(7,"Prinskorv, 300g", 28.95f));
		itemList.add(new Item(8,"Kassler i bit, 1000g", 53.95f));
		itemList.add(new Item(9,"Messm�r original tub, 350g", 22.50f));
		itemList.add(new Item(10,"Halloumi, 200g", 25.95f));
		itemList.add(new Item(11,"Mozzarella Eko, 100g", 14.95f));
		itemList.add(new Item(12,"Philadelphia Original, 300g", 22.95f));
		itemList.add(new Item(13,"R�kt Lax i skivor, 200g", 53.50f));
		itemList.add(new Item(14,"R�dsp�ttafil�, 450g", 74.95f));
		itemList.add(new Item(15,"Ishavsrom, 75g", 19.50f));
		itemList.add(new Item(16,"Julmust Apotekarnes, 1.4l", 11.95f));
		itemList.add(new Item(17,"L�ttdryck P�ron, 20cl", 5.70f));
		itemList.add(new Item(18,"Kaffefilter 1x4 blekta, 80st", 18.50f));
		itemList.add(new Item(19,"Godmorgon Apelsinjuice, 1L", 19.95f));
		itemList.add(new Item(20,"Kaffe Sk�nerost, 450g", 34.50f));
		
		//The call below is only used to check that the above list of objects have been created correctly.
		//It, and the method it calls, are commented out when I don't want to use them. Will be removed for the final
		//version of the application.
		//checkInitialize();
				
	}

	//private static void checkInitialize() {
	//	for(int i=0;i<itemList.size();i++) {
	//		System.out.println(itemList.get(i).toString());
	//		}
	//	}
	
	
	

	public static float CashRegister;{ // Code for summation of items. Not sure if it's correct. Was not able to test it for some reason /AF
	}

	float totalPrice = 0; {
	for(Item item : itemList)
		totalPrice += item.getPrice();
	}
	
	
	
	public static int getLengthOfItemList() {
		//Returns the number of items in itemList (the list of products that can be sold) /JS
		int i = itemList.size();
		return i;
	}
	
	public static int getLengthOfSaleItemList() {
		int i = saleItemList.size();
		return i;
	}
	
	public static Item getLatestItem() {
		return saleItemList.get(getLengthOfSaleItemList()-1);
	}
	
	public static void addItemToSale(float amountOfItem, int itemID){
		//adds an item from the itemList to the saleItemList which tracks references to products in the current sale. //JS
		saleItemList.add(itemList.get(itemID-1));
		amountOfItemList.add(amountOfItem);
	}
	
	public static String getSaleItemName(int nrInSale) {
		//returns the name of a specific item registered to the current sale. nrInSale marks where in the order of the sale the 
		//item was added. If you want the name of the first item registered during the current sale, nrInSale should be 1. /JS
		String name = saleItemList.get(nrInSale-1).getName();
		return name;
	}
	
	public static float getSaleItemPrice(int nrInSale) {
		//returns the price of a specific item registered to the current sale. nrInSale marks where in the order of the sale the
		//item was added. If you want the price of the first item registered during the current sale, nrInSale should be 1. /JS
		float price = saleItemList.get(nrInSale-1).getPrice();
		return price;
	}
	
	public static float getSaleItemAmount (int nrInSale) {
		//returns the amount of a specific item registered to the current sale. nrInSale marks where in the order of the sale the
		//item was added. If you want the amount of the first item registered during the current sale, nrInSale should be 1. /JS
		float amount = amountOfItemList.get(nrInSale-1);
		return amount;
	}
	
	
	
	}


