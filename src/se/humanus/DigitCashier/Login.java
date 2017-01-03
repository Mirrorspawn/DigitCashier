package se.humanus.DigitCashier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Login {

	/**
	 * Launch the application.
	 * @param args
	 * @throws IOException 
	 * @wbp.parser.entryPoint
	 */

	public static void main(String[] args) throws FileNotFoundException, IOException {
		DataManagement.initialize();
		DataManagement.LoadData();
		LoginGUI.activateLoginWindow();
	}

	/*Method-calls below initialize and starts things to do with CashRegister. 
	 * Any login code should probably be before that call. Any call to a method that needs to use the 
	 *the hardcode-created Items should be put after it /JS 		
	 */
	public static void startApplication(String mode){
		//CashRegister.initializeCategories(); // This is a call to a method in Cashregister that creates some objects of the class ItemCategory./JS
		//CashRegister.initializeItems(); //This is a call to a method in CashRegister that creates objects of the class Item./JS
		//CashRegister.initializeReceipt();//This calls a method that initializes some values in the receipt class.
		if (mode.equals("Cashier")){
			CashRegisterWindow.activateCashRegister(); //Starts up the CashRegister interface.
		}
		else if (mode.equals("Admin")){
			AdminWindow.ativateAdminWindow();
		}
		else if (mode.equals("Chef")){
			System.out.println("Not implemented yet.");
		}

	}

}
