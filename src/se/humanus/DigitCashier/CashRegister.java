package se.humanus.DigitCashier;
import java.util.*;

public class CashRegister {
	static List<ItemCategory> categoryList = new ArrayList<>();
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
	
	public static void initializeCategories() {
		//Hardcoded creation of item categories. This is called from the Main method in teh Login Class /JS
		
		categoryList.add(new ItemCategory("Mejeri", 1.12f, false));
		categoryList.add(new ItemCategory("Frukt & Grönt", 1.12f, false));
		categoryList.add(new ItemCategory("Chark & Pålägg", 1.12f, false));
		categoryList.add(new ItemCategory("Fisk", 1.12f, false));
		categoryList.add(new ItemCategory("Dryck", 1.12f, false));
		categoryList.add(new ItemCategory("Bröd & Bakning", 1.12f, false));
	}
	
	public static void initializeItems() {
		//Hardcoded creation of items. This is called from the Main method in the Login Class /JS
		
		itemList.add(new Item("01","Standardmjölk, 1l", 9.10f));
		//categoryList.get(0).addItemToCategory(itemList.get(0));
		itemList.add(new Item("02","Creme Fraiche 34%, 5dl", 19.95f));
		//categoryList.get(0).addItemToCategory(itemList.get(1));
		itemList.add(new Item("03","Kronjäst Färsk, 50g", 2.50f));
		//categoryList.get(5).addItemToCategory(itemList.get(2));
		itemList.add(new Item("04","Rökt Skinka, 120g", 22.95f));
		//categoryList.get(2).addItemToCategory(itemList.get(3));
		itemList.add(new Item("05","Falukorv Ring Scan, 800g", 9.10f));
		//categoryList.get(2).addItemToCategory(itemList.get(4));
		itemList.add(new Item("06","Kalles Kaviar ABBA, 300g", 26.95f));
		itemList.add(new Item("07","Prinskorv, 300g", 28.95f));
		itemList.add(new Item("08","Kassler i bit, 1000g", 53.95f));
		itemList.add(new Item("09","Messmör original tub, 350g", 22.50f));
		itemList.add(new Item("10","Halloumi, 200g", 25.95f));
		itemList.add(new Item("11","Mozzarella Eko, 100g", 14.95f));
		itemList.add(new Item("12","Philadelphia Original, 300g", 22.95f));
		itemList.add(new Item("13","Rökt Lax i skivor, 200g", 53.50f));
		itemList.add(new Item("14","Rödspättafilé, 450g", 74.95f));
		itemList.add(new Item("15","Ishavsrom, 75g", 19.50f));
		itemList.add(new Item("16","Julmust Apotekarnes, 1.4l", 11.95f));
		itemList.add(new Item("17","Lättdryck Päron, 20cl", 5.70f));
		itemList.add(new Item("18","Kaffefilter 1x4 blekta, 80st", 18.50f));
		itemList.add(new Item("19","Godmorgon Apelsinjuice, 1L", 19.95f));
		itemList.add(new Item("20","Kaffe Skånerost, 450g", 34.50f));
		
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
	
	
	

	public static float calculateSum() { // Code for summation of items. Not sure if it's correct. Was not able to test it for some reason /AF
		float totalPrice = 0;
		for(Item item : itemList){ 
		totalPrice += item.getPrice();
		}
		return totalPrice;
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


