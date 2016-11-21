package se.humanus.DigitCashier;

public class Login {

	public static void main(String[] args) {
		
		/*Method-calls below initialize and starts things to do with CashRegister. 
		 * Any login code should probably be before that call. Any call to a method that needs to use the 
		 *the hardcode-created Items should be put after it /JS 		
		 */
		CashRegister.initializeItems(); //This is a call to a method in CashRegister that creates twenty objects of the class Item./JS
		CashRegisterWindow.activateCashRegister(); //Starts up the CashRegister interface.

	}

}
