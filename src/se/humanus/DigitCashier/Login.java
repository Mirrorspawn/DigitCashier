package se.humanus.DigitCashier;

import java.util.Scanner;

public class Login {
	
	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */

	public static void main(String[] args) {
		LoginGUI.activateLoginWindow();
	}
		
		/*Method-calls below initialize and starts things to do with CashRegister. 
		 * Any login code should probably be before that call. Any call to a method that needs to use the 
		 *the hardcode-created Items should be put after it /JS 		
		 */
	public static void startApplication(){ 	
		CashRegister.initializeCategories(); // This is a call to a method in Cashregister that creates some objects of the class ItemCategory./JS
		CashRegister.initializeItems(); //This is a call to a method in CashRegister that creates objects of the class Item./JS
		CashRegisterWindow.activateCashRegister(); //Starts up the CashRegister interface.

	}

}
