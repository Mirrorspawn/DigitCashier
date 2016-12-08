package se.humanus.DigitCashier;

import java.time.LocalDate;
import java.time.LocalTime;

public class Receipt {
	protected static String orgName;
	protected static String orgNr;
	private String dateTime; //Maybe this should use the special variable for date and time, but it is going to be printed, so...
	private int voucherNr;
	private int vat;
	private String receiptMainText;
	private String itemCategory;
	private int itemDiscount;
	
	public static void setOrganization(String organizationName, String organizationNr){
		orgName=organizationName;
		orgNr=organizationNr;
	}
	
	public void addLineToReceipt(String newTextLine){ //Collects String variables passed into it, \n makes sure the new string is on it's own line.
		receiptMainText = receiptMainText + "\n" + newTextLine;		
	}
	
	public void showReceipt() {
		ReceiptDisplay.startReceipt();
	}
	
	public void Receipt() {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		dateTime = currentDate + " " + currentTime;
	}
	
	
	
}
