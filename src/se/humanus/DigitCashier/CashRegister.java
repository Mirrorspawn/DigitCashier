package se.humanus.DigitCashier;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.*;

public class CashRegister {
	static String orgName="Acme Foods", orgNr="5560398800";
	static List<ItemCategory> categoryList = new ArrayList<>();
	static List<Item> itemList = new ArrayList<>();
	static List<Item> saleItemList = new ArrayList<>();
	static List<Float> amountOfItemList = new ArrayList<>();
	static String discount_20p = "20% rabatt";
	static String discount_50kr = "-50kr";
	static float discount = 0;
	static String change = "";
	static float total = 0;



	public static float getTotal() {
		return total;
	}

	public static void setTotal(float total) {
		CashRegister.total = total;
	}

	public static float getDiscount() {
		return discount;
	}

	public static void setDiscount(float discount) {
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
		setDiscount(sum - result);
		return result;
	}

	public static String getChange() {
		return change;
	}

	public static float changeCalculation(float paid, float total) {

		return paid - total;
	}


	public static boolean isNumeric(String str)   //code to check that input in change input field is a number
	{
		if (str.isEmpty()) return false;
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}



	public static void initializeCategories() {
		//Hardcoded creation of item categories. This is called from the Main method in the Login Class /JS

		categoryList.add(new ItemCategory("Mejeri", 0.12f, false));
		categoryList.add(new ItemCategory("Frukt & Grönt", 0.12f, true));
		categoryList.add(new ItemCategory("Chark & Pålägg", 0.12f, false));
		categoryList.add(new ItemCategory("Fisk", 0.12f, false));
		categoryList.add(new ItemCategory("Dryck", 0.12f, false));
		categoryList.add(new ItemCategory("Bröd & Bakning", 0.12f, false));
	}
	
	public static void createNewCategory(String categoryName, float moms, Boolean measuredInWeight ){
		categoryList.add(new ItemCategory(categoryName, moms, measuredInWeight));
	}

	public static void initializeItems() {
		//Hardcoded creation of items. This is called from the Main method in the Login Class /JS

		itemList.add(new Item(categoryList.get(0),"01","Standardmjölk, 1l", 9.10f));
		itemList.add(new Item(categoryList.get(0),"02","Creme Fraiche 34%, 5dl", 19.95f));
		itemList.add(new Item(categoryList.get(5),"03","Kronjäst Färsk, 50g", 2.50f));
		itemList.add(new Item(categoryList.get(2),"04","Rökt Skinka, 120g", 22.95f));
		itemList.add(new Item(categoryList.get(2),"05","Falukorv Ring Scan, 800g", 9.10f));
		itemList.add(new Item(categoryList.get(2),"06","Kalles Kaviar ABBA, 300g", 26.95f));
		itemList.add(new Item(categoryList.get(2),"07","Prinskorv, 300g", 28.95f));
		itemList.add(new Item(categoryList.get(2),"08","Kassler i bit, 1000g", 53.95f));
		itemList.add(new Item(categoryList.get(2),"09","Messmör original tub, 350g", 22.50f));
		itemList.add(new Item(categoryList.get(0),"10","Halloumi, 200g", 25.95f));
		itemList.add(new Item(categoryList.get(0),"11","Mozzarella Eko, 100g", 14.95f));
		itemList.add(new Item(categoryList.get(2),"12","Philadelphia Original, 300g", 22.95f));
		itemList.add(new Item(categoryList.get(3),"13","Rökt Lax i skivor, 200g", 53.50f));
		itemList.add(new Item(categoryList.get(3),"14","Rödspättafilé, 450g", 74.95f));
		itemList.add(new Item(categoryList.get(3),"15","Ishavsrom, 75g", 19.50f));
		itemList.add(new Item(categoryList.get(4),"16","Julmust Apotekarnes, 1.4l", 11.95f));
		itemList.add(new Item(categoryList.get(4),"17","Lättdryck Päron, 20cl", 5.70f));
		itemList.add(new Item(categoryList.get(4),"18","Kaffefilter 1x4 blekta, 80st", 18.50f));
		itemList.add(new Item(categoryList.get(4),"19","Godmorgon Apelsinjuice, 1L", 19.95f));
		itemList.add(new Item(categoryList.get(1),"20","Tomater Kvist, Holland", 26.90f));
		itemList.add(new Item(categoryList.get(1),"21","Paprika Röd, Sverige", 24.90f));
		itemList.add(new Item(categoryList.get(1),"22","Clementin, Spanien", 26.90f));
		itemList.add(new Item(categoryList.get(1),"23","Citron Eko, Spanien", 74.90f));
		itemList.add(new Item(categoryList.get(1),"24","Potatis, Sverige", 32.95f));
		itemList.add(new Item(categoryList.get(1),"25","Lime, Brasilien", 3.80f));

		//The call below is only used to check that the above list of objects have been created correctly.
		//It, and the method it calls, are commented out when I don't want to use them. Will be removed for the final
		//version of the application.
		//checkInitialize();

	}
	
	public static void createNewItem (ItemCategory itemCat,String itemID, String itemName, float itemPrice) {
		//Method to create a new Item
		itemList.add(new Item(itemCat, itemID, itemName, itemPrice));
	}

	public static void initializeReceipt(){ 
		//Method for setting any info on the receipt that is the same every time. I've done orgName and orgNr to show what I mean.
		Receipt.setOrganization(orgName,orgNr);
		

	}

	//private static void checkInitialize() {
	//	for(int i=0;i<itemList.size();i++) {
	//		System.out.println(itemList.get(i).toString());
	//		}
	//	}


	public static float calculateSum(){     //summation + amount of items calculation
		   float totalPrice = 0;
		   float amount = 1f;

		   for(int i = 0; i < getLengthOfSaleItemList(); i++) {
		   amount = amountOfItemList.get(i);{
		totalPrice = totalPrice + (amount*saleItemList.get(i).getPrice());
		   }
		   }
		return roundCash(totalPrice);
		}

	/*public static float calculateSum() { // Code for summation of items. Not sure if it's correct. Was not able to test it for some reason /AF
		float totalPrice = 0;

		for(Item item : saleItemList){ 
			totalPrice += item.getPrice();
		}

		return roundCash(totalPrice);
		
		
	}*/



	public static int getLengthOfItemList() {
		//Returns the number of items in itemList (the list of products that can be sold) /JS
		int i = itemList.size();
		return i;
	}

	public static int getLengthOfSaleItemList() {
		int i = saleItemList.size();
		return i;
	}

	public static Boolean checkIfWeight(int itemID) {
		Item item=itemList.get(itemID-1);
		ItemCategory itemCat=item.getMyCategory();
		return itemCat.getMeasuredInWeight();		
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

	public static float getTotalVAT(List<Item> saleItemList) {	
		float vat = 0;
		for (int i = 0; i < saleItemList.size(); i++) {
			
			vat = vat + saleItemList.get(i).getMyCategory().getSalesTax() * getSaleItemPrice(i+1) * getSaleItemAmount(i+1) ; 
		}
		// Calculates VAT based on each item's individual sales tax, i+1 to keep away out of bounds error from being set to -1 in previous functions
		
		
		return vat;
	}

	private static float roundCash(float f) {
		int a = (int) (f * 100); // Code to limit the decimal numbers to 2. Code by my brother
		f = a / 100.0f;

		return f;
	}

	public static String getReceiptSaleInfo(List<Item> saleItemList) {                  //shows a list of purchased items and item amount on receipt with a linebreak for each item
		String receiptitemlist = "";
		for(int i = 0; i < saleItemList.size(); i++) {

			receiptitemlist = receiptitemlist + getSaleItemAmount(i+1) + " x " +  saleItemList.get(i) + "\n";

		}

		
		return receiptitemlist;
	}



}


