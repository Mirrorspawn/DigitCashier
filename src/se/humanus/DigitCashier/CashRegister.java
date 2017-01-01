package se.humanus.DigitCashier;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.*;

public class CashRegister {
	private static final int itemID = 0;
	static String orgName="Acme Foods", orgNr="5560398800";
	static List<ItemCategory> categoryList = new ArrayList<>();
	static List<Item> itemList = new ArrayList<>();
	static List<Item> saleItemList = new ArrayList<>();
	static List<Double> amountOfItemList = new ArrayList<>();
	static String discount_20p = "20% rabatt";
	static String discount_50kr = "-50kr";
	static double discount = 0;
	static double change = 0;
	static double total = 0;
	static String betalningsmedel="";
	static String voucherNr = ""; //this and all variables below it are part of the voucher number creation /SH
	static int voucherCount = 00000;
	static Calendar cal = Calendar.getInstance();
	static int date = cal.get(Calendar.DATE);
	static int month = cal.get(Calendar.MONTH);
	static int year = cal.get(Calendar.YEAR);


	public static String getVoucherNr() {
		return voucherNr;

	}

	public static void createVoucherNr() {
		voucherCount++;
		CashRegister.voucherNr = String.format("%d%d%d%05d", year, month, date, voucherCount);

	}

	public static String getBetalningsmedel() {
		return betalningsmedel;
	}

	public static void setBetalningsmedel(String betalningsmedel) {
		CashRegister.betalningsmedel = betalningsmedel;
	}

	public static double getTotal() {
		return roundCash(total);

	}

	public static void setTotal(double total) {
		CashRegister.total = total;
	}

	public static double getDiscount() {
		return roundCash(discount);
	}

	public static void setDiscount(double discount) {
		CashRegister.discount = discount;
	}

	public static void setChange(double change) {
		CashRegister.change = change;
	}

	public static double applyDiscount(String discount, double sum) {
		double result = sum;
		if(discount.equals(discount_50kr)) {
			result = sum -50;
		}
		if(discount.equals(discount_20p)) {
			result = (double) (sum *0.8);
		}
		setDiscount(sum - result);
		return roundCash(result);
	}
	//calculation for discount, one for a flat amount and one for a percentage /SH

	public static double getChange() {
		return roundCash(change);
	}

	public static double changeCalculation(double paid, double total) {

		setChange(paid);
		return roundCash(paid - total);
	} //calculates change owed to customer after purchase and rounds the result to 2 decimals max /SH



	public static boolean isNumeric(String str)   //code to check that input in change input field is a number
	{
		if (str.isEmpty()) return false;
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}



	public static void initializeCategories() {
		//Hardcoded creation of default item categories. This is called if load from data-file, fails at any point. /JS
		categoryList.clear();
		categoryList.add(new ItemCategory("Mejeri", 0.12d, false));
		categoryList.add(new ItemCategory("Frukt och Grönt", 0.12d, true));
		categoryList.add(new ItemCategory("Chark och Pålägg", 0.12d, false));
		categoryList.add(new ItemCategory("Fisk", 0.12d, false));
		categoryList.add(new ItemCategory("Dryck", 0.12d, false));
		categoryList.add(new ItemCategory("Bröd och Bakning", 0.12d, false));
	}

	public static void createNewCategory(String categoryName, double moms, Boolean measuredInWeight ){
		categoryList.add(new ItemCategory(categoryName, moms, measuredInWeight));
	}

	public static void initializeItems() {
		//Hardcoded creation of default items. This is called if the load from data-file, fails at any point. /JS

		itemList.add(new Item(categoryList.get(0),"01","Standardmjölk, 1l", 9.10d));
		itemList.add(new Item(categoryList.get(0),"02","Creme Fraiche 34%, 5dl", 19.95d));
		itemList.add(new Item(categoryList.get(5),"03","Kronjäst Färsk, 50g", 2.50d));
		itemList.add(new Item(categoryList.get(2),"04","Rökt Skinka, 120g", 22.95d));
		itemList.add(new Item(categoryList.get(2),"05","Falukorv Ring Scan, 800g", 9.10d));
		itemList.add(new Item(categoryList.get(2),"06","Kalles Kaviar ABBA, 300g", 26.95d));
		itemList.add(new Item(categoryList.get(2),"07","Prinskorv, 300g", 28.95d));
		itemList.add(new Item(categoryList.get(2),"08","Kassler i bit, 1000g", 53.95d));
		itemList.add(new Item(categoryList.get(2),"09","Messmör original tub, 350g", 22.50d));
		itemList.add(new Item(categoryList.get(0),"10","Halloumi, 200g", 25.95d));
		itemList.add(new Item(categoryList.get(0),"11","Mozzarella Eko, 100g", 14.95d));
		itemList.add(new Item(categoryList.get(2),"12","Philadelphia Original, 300g", 22.95d));
		itemList.add(new Item(categoryList.get(3),"13","Rökt Lax i skivor, 200g", 53.50d));
		itemList.add(new Item(categoryList.get(3),"14","Rödspättafilé, 450g", 74.95d));
		itemList.add(new Item(categoryList.get(3),"15","Ishavsrom, 75g", 19.50d));
		itemList.add(new Item(categoryList.get(4),"16","Julmust Apotekarnes, 1.4l", 11.95d));
		itemList.add(new Item(categoryList.get(4),"17","Lättdryck Päron, 20cl", 5.70d));
		itemList.add(new Item(categoryList.get(4),"18","Kaffefilter 1x4 blekta, 80st", 18.50d));
		itemList.add(new Item(categoryList.get(4),"19","Godmorgon Apelsinjuice, 1L", 19.95d));
		itemList.add(new Item(categoryList.get(1),"20","Tomater Kvist, Holland", 26.90d));
		itemList.add(new Item(categoryList.get(1),"21","Paprika Röd, Sverige", 24.90d));
		itemList.add(new Item(categoryList.get(1),"22","Clementin, Spanien", 26.90d));
		itemList.add(new Item(categoryList.get(1),"23","Citron Eko, Spanien", 74.90d));
		itemList.add(new Item(categoryList.get(1),"24","Potatis, Sverige", 32.95d));
		itemList.add(new Item(categoryList.get(1),"25","Lime, Brasilien", 3.80d));

		//The call below is only used to check that the above list of objects have been created correctly.
		//It, and the method it calls, are commented out when I don't want to use them. Will be removed for the final
		//version of the application.
		//checkInitialize();

	}

	public static void createNewItem (ItemCategory itemCat,String itemID, String itemName, double itemPrice) {
		//Method to create a new Item
		itemList.add(new Item(itemCat, itemID, itemName, itemPrice));
	}

	public static void initializeReceipt(){ 
		//Method for setting any info on the receipt that is the same every time. I've done orgName and orgNr to show what I mean.
		Receipt.setOrganization(orgName,orgNr);
	}

	public static double calculateSum(){     //summation + amount of items calculation
		double totalPrice = 0;
		double amount = 1f;

		for(int i = 0; i < getLengthOfSaleItemList(); i++) {
			amount = amountOfItemList.get(i);{
				totalPrice = totalPrice + (amount*saleItemList.get(i).getPrice());
			}
		}
		return roundCash(totalPrice);
	}

	public static int getLengthOfCategoryList() {
		//Returns the number of categories in categoryList
		int i= categoryList.size();
		return i;
	}

	public static ItemCategory getCategory (int nrInCategoryList){
		return categoryList.get(nrInCategoryList);
	}

	public static int getNrOfCategory (ItemCategory itemCat){
		return categoryList.indexOf(itemCat);
	}



	public static int getLengthOfItemList() {
		//Returns the number of items in itemList (the list of products that can be sold) /JS
		int i = itemList.size();
		return i;
	}

	public static Item getItem (int nrInItemList){
		return itemList.get(nrInItemList);
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

	public static void addItemToSale(double amountOfItem, int itemID){
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

	public static double getSaleItemPrice(int nrInSale) {
		//returns the price of a specific item registered to the current sale. nrInSale marks where in the order of the sale the
		//item was added. If you want the price of the first item registered during the current sale, nrInSale should be 1. /JS
		double price = saleItemList.get(nrInSale-1).getPrice();
		return price;
	}

	public static double getSaleItemAmount (int nrInSale) {
		//returns the amount of a specific item registered to the current sale. nrInSale marks where in the order of the sale the
		//item was added. If you want the amount of the first item registered during the current sale, nrInSale should be 1. /JS
		double amount = amountOfItemList.get(nrInSale-1);
		return amount;
	}
	
	public static void clearSale (){
		saleItemList.clear();
		amountOfItemList.clear();
		setTotal(0.0d);
		setBetalningsmedel("");
		
	}

	public static double getTotalVAT(List<Item> saleItemList) {	
		double vat = 0;
		for (int i = 0; i < saleItemList.size(); i++) {

			vat = vat + saleItemList.get(i).getMyCategory().getSalesTax() * getSaleItemPrice(i+1) * getSaleItemAmount(i+1) ; 
		}
		// Calculates VAT based on each item's individual sales tax, i+1 to keep away out of bounds error from being set to -1 in previous functions /SH


		return roundCash(vat);
	}

	private static double roundCash(double f) {
		int a = (int) (f * 100); // Code to limit the decimal numbers to 2
		f = a / 100.0d;

		return f;
	}

	public static String getReceiptSaleInfo(List<Item> saleItemList) {                  //shows a list of purchased items and item amount on receipt with a linebreak for each item /SH
		String receiptitemlist = "";
		for(int i = 0; i < saleItemList.size(); i++) {

			receiptitemlist = receiptitemlist + saleItemList.get(i).getMyCategory().getCategoryName() + "  -  " + getSaleItemAmount(i+1) + " x " +  saleItemList.get(i) + "\n";

		}


		return receiptitemlist;
	}



}


