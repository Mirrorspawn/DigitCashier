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
		//Initializes values and calls the loadfunction, then starts up the login-window
		DataManagement.initialize();
		DataManagement.LoadData();
		LoginGUI.activateLoginWindow();
	}

	
	public static void startApplication(String mode){
		if (mode.equals("Cashier")){
			CashRegisterWindow.activateCashRegister(); //Starts up the CashRegister interface.
		}
		else if (mode.equals("Admin")){
			AdminWindow.ativateAdminWindow(); //Starts up the Administration Window
		}
		else if (mode.equals("Chef")){
			System.out.println("Not implemented yet.");
		}

	}

}
