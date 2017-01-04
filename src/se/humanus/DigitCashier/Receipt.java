package se.humanus.DigitCashier;

import java.time.LocalDate;
import java.time.LocalTime;

public class Receipt {
	protected static String orgName;
	protected static String orgNr;
	private String dateTime; //This is a string because we are going to display it as a text..
	private String receiptMainText;


	public static void setOrganization(String organizationName, String organizationNr){ 
		//Sets the orgName and orgNr
		orgName=organizationName;
		orgNr=organizationNr;
	}

	public void addLineToReceipt(String newTextLine){ 
		//Collects String variables passed into it, \n makes sure the new string is on it's own line.
		receiptMainText = receiptMainText + "\n" + newTextLine;		
	}

	public void showReceipt() { 
		//activates the receipt window
		ReceiptDisplay.startReceipt();
	}

	public Receipt() { 
		//Constructor class
		LocalDate currentDate = LocalDate.now();//Adds date into
		LocalTime currentTime = LocalTime.now();
		dateTime = currentDate + " " + currentTime;
	}


	public String getDate(){
		return dateTime;
	}



}
