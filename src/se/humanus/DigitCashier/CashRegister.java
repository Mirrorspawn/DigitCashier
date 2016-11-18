package se.humanus.DigitCashier;
import java.util.*;

public class CashRegister {
	List<Item> itemList = new ArrayList<>();
	Scanner scan = new Scanner(System.in);
	
	public CashRegister() {
		//Hardcoded creation of items when the CashRegister object is created
		
		itemList.add(new Item(1,"Standardmjölk, 1l", 9.10f));
		itemList.add(new Item(2,"Creme Fraiche 34%, 5dl", 19.95f));
		itemList.add(new Item(3,"Kronjäst Färsk, 50g", 2.50f));
		itemList.add(new Item(4,"Rökt Skinka, 120g", 22.95f));
		itemList.add(new Item(5,"Falukorv Ring Scan, 800g", 9.10f));
		itemList.add(new Item(6,"Kalles Kaviar ABBA, 300g", 26.95f));
		itemList.add(new Item(7,"Prinskorv, 300g", 28.95f));
		itemList.add(new Item(8,"Kassler i bit, 1000g", 53.95f));
		itemList.add(new Item(9,"Messmör original tub, 350g", 22.50f));
		itemList.add(new Item(10,"Halloumi, 200g", 25.95f));
		itemList.add(new Item(11,"Mozzarella Eko, 100g", 14.95f));
		itemList.add(new Item(12,"Philadelphia Original, 300g", 22.95f));
		itemList.add(new Item(13,"Rökt Lax i skivor, 200g", 53.50f));
		itemList.add(new Item(14,"Rödspättafilé, 450g", 74.95f));
		itemList.add(new Item(15,"Ishavsrom, 75g", 19.50f));
		itemList.add(new Item(16,"Julmust Apotekarnes, 1.4l", 11.95f));
		itemList.add(new Item(17,"Lättdryck Päron, 20cl", 5.70f));
		itemList.add(new Item(18,"Kaffefilter 1x4 blekta, 80st", 18.50f));
		itemList.add(new Item(19,"Godmorgon Apelsinjuice, 1L", 19.95f));
		itemList.add(new Item(20,"Kaffe Skånerost, 450g", 34.50f));
				
	}

}
