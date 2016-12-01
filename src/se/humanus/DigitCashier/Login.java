package se.humanus.DigitCashier;

import java.util.Scanner;

public class Login {

	public static void main(String[] args) {
		
		/*Scanner input = new Scanner(System.in);
		
		String user="", pass="", EmployeeID="";
		
		
		
      
		boolean validName=false;
		while(!validName){
		System.out.print("Input username: ");
		user = input.nextLine();
		
		if (user.equals("Kassör") || (user.equals("Admin") || (user.equals("Chef"))))
			validName=true;
		
		else {
			
			System.out.println("Invalid user");
			
		}
		}
		
		boolean ValidEmployeeID=false;
		
		while(!ValidEmployeeID){
		System.out.print("Input Employee ID: ");
		EmployeeID = input.nextLine();
		
		if (EmployeeID.equals("01") && (user.equals("Kassör"))){ //(EmployeeID.equals("02") || (EmployeeID.equals("03"))))
			ValidEmployeeID=true;
		}
		
		else if (EmployeeID.equals("02") && (user.equals("Admin"))){
			ValidEmployeeID=true;
		}
		
		else if (EmployeeID.equals("03") && (user.equals("Chef"))){
			ValidEmployeeID=true;
		}
		
			
			else 
			{
			
			System.out.println("Invalid Employee ID");
		}
		}
		
		boolean ValidPassword=false;
		while(!ValidPassword){
		System.out.print("Input Password: ");
		pass = input.nextLine();
		
		if (pass.equals("201") && (EmployeeID.equals("01"))) {
			ValidPassword=true;
		}
		else if (pass.equals("302") && (EmployeeID.equals("02"))) {
			ValidPassword=true;
		}
		
		else if (pass.equals("303") && (EmployeeID.equals("03"))) {
			ValidPassword=true;
		}
		else {
				
				System.out.println("Invalid Password");
			}
		System.out.println("Du har blivit inloggad.");
			
		}
	input.close();
		
		/*Method-calls below initialize and starts things to do with CashRegister. 
		 * Any login code should probably be before that call. Any call to a method that needs to use the 
		 *the hardcode-created Items should be put after it /JS 		
		 */
		CashRegister.initializeCategories(); // This is a call to a method in Cashregister that creates some objects of the class ItemCategory./JS
		CashRegister.initializeItems(); //This is a call to a method in CashRegister that creates twenty objects of the class Item./JS
		CashRegisterWindow.activateCashRegister(); //Starts up the CashRegister interface.

	}

}
