package se.humanus.DigitCashier;

import java.util.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataManagement {
	private static Path savePath=Paths.get("DigitCashierSaveData.txt");
	private static String saveFileName = "DigitCashierSaveData.txt";
	
	public static void initialize() throws FileNotFoundException, IOException{
		try{
			Files.createFile(savePath);	
		}
		catch (FileAlreadyExistsException e) {
			System.out.println("New Savefile not created. Data-file already exists.");			
		}
		
//		loadData();
	}
	
	private static void loadDefaults(){
		CashRegister.initializeCategories();
		CashRegister.initializeItems();
	}
	
	public static void saveData() throws IOException{
		int nrOfCategories= CashRegister.getLengthOfCategoryList();
		int nrOfItems = CashRegister.getLengthOfItemList();
		
		PrintWriter utFil = new PrintWriter(new BufferedWriter(new FileWriter(saveFileName)));
		
		utFil.println("CategoryListStart");
		utFil.println(nrOfCategories);
		for (int i=0;i<nrOfCategories;i++){
			ItemCategory currentCat = CashRegister.getCategory(i);
			utFil.println(currentCat.getCategoryName());
			utFil.println(currentCat.getSalesTax());
			utFil.println(currentCat.getMeasuredInWeight());
		}
		utFil.println("CategoryListEnd");
		
		utFil.println("ItemListStart");
		utFil.println(nrOfItems);
		for (int i=0;i<nrOfItems;i++) {
			Item currentItem = CashRegister.getItem(i);
			utFil.println(CashRegister.getNrOfCategory(currentItem.getMyCategory()));
//			utFil.print(" ");
			utFil.println(currentItem.getId());
//			utFil.print(" ");
			utFil.println(currentItem.getName());
//			utFil.println(" ");
			utFil.println(currentItem.getPrice());
//			utFil.print("\r\n");
		}
		utFil.println("ItemListEnd");
		utFil.close();	
	}
	
	public static void LoadData() throws FileNotFoundException{
		//This method loads data from the saveDatafile
		String categoryName;
		String tempString; //String to test stuff, remove from final code
		Float salesTax;
		boolean weight;
		int categoryNr;
		String idNr, itemName;
		float price;
		
		Scanner scan = new Scanner(new File(saveFileName));
		if (!scan.hasNext()) {//Checks if there is any data in the file, and if there isn't loads the hard-coded defaults.
			System.out.println("No previous save file. Loading default Categories and Items.");
			loadDefaults();
			scan.close();
			return;
		}
		if (scan.nextLine().equals("CategoryListStart")){
			//Checks that there is a CategoryList in the savefile, and if there isn't, loads hard-coded defaults.
			int nrOfCategories = Integer.parseInt(scan.nextLine());
			for (int i=0;i<nrOfCategories;i++){
				categoryName = scan.nextLine();
				System.out.println(categoryName);
				tempString = scan.nextLine();
				//System.out.println("Before parseFloat: "+tempString); //Testline. Remove for final code.
				salesTax = Float.parseFloat(tempString);
				System.out.print(" Sales Tax: "+salesTax);
				tempString = scan.nextLine();
				weight = Boolean.parseBoolean(tempString);
				System.out.println(" Measured in weight? - "+weight);
				CashRegister.createNewCategory(categoryName,salesTax,weight);
			}
		}
		else {
			System.out.println("Categories not loaded because of incorrect save file. Using default Categories and Items.");
			loadDefaults();
			scan.close();
			return;
		}
		if (!scan.nextLine().equals("CategoryListEnd")){
			//Aborts load if there is not a proper end-marker. Loads default Categories and Items instead.
			System.out.println("Categorylist not ended properly in save file. Load aborted");
			scan.close();
			return;
		}
		
		if (scan.nextLine().equals("ItemListStart")){
			int nrOfItems = Integer.parseInt(scan.nextLine());
			for (int i=0;i<nrOfItems;i++){
				tempString= scan.nextLine();
				categoryNr = Integer.parseInt(tempString);
				System.out.print("categoryNr: "+categoryNr);
				idNr = scan.nextLine();
				System.out.print("  itemId: "+idNr);
				itemName = scan.nextLine();
				System.out.print("  itemName: "+itemName);
				tempString = scan.nextLine();
				price = Float.parseFloat(tempString);
				System.out.println("  Price: "+price);
				ItemCategory itemCategory = CashRegister.getCategory(categoryNr);
				CashRegister.createNewItem(itemCategory, idNr, itemName, price);				
			}
		}
		else {
			System.out.println("Items not loaded because of incorrect save file.");
		}
		if (!scan.nextLine().equals("ItemListEnd")){
			System.out.println("ItemList not ended properly in save file. Load aborted");
			scan.close();
			return; 
		}
		scan.close();
	}

}
