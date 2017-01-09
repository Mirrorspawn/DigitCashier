package se.humanus.DigitCashier;

import java.time.LocalDate;
import java.time.LocalTime;

public class Receipt {
	protected static String orgName;
	protected static String orgNr;
	private String dateTime; //This is a string because we are going to display it as a text..


	public static void setOrganization(String organizationName, String organizationNr){ 
		//Sets the orgName and orgNr
		orgName=organizationName;
		orgNr=organizationNr;
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
