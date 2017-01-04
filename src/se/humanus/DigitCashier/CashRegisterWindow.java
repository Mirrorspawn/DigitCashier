package se.humanus.DigitCashier;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;

import java.util.regex.Pattern;
import java.util.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

//import com.sun.xml.internal.ws.util.StringUtils;  //This import gave me an error message. The application works without it. What is it used for?

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class CashRegisterWindow {

	protected Shell shlDigitcashierCashRegister;
	private Text antalText;
	private Text varuNrText;
	private String defaultAntal="1";
	private Text changeInput;
	private Text text;



	/**
	 * @wbp.parser.entryPoint
	 */
	public static void activateCashRegister() {
		try {
			CashRegisterWindow window = new CashRegisterWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlDigitcashierCashRegister.open();
		shlDigitcashierCashRegister.layout();
		while (!shlDigitcashierCashRegister.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDigitcashierCashRegister = new Shell();
		shlDigitcashierCashRegister.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		shlDigitcashierCashRegister.setSize(679, 569);
		shlDigitcashierCashRegister.setText("DigitCashier Cash Register");

		Label lblDisplay = new Label(shlDigitcashierCashRegister, SWT.BORDER);
		lblDisplay.setAlignment(SWT.CENTER);
		lblDisplay.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
		lblDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblDisplay.setBounds(255, 43, 373, 25);
		
		Text lblSaleDisplay = new Text(shlDigitcashierCashRegister, SWT.READ_ONLY | SWT.V_SCROLL | SWT.MULTI);
		lblSaleDisplay.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblSaleDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblSaleDisplay.setBounds(255, 87, 373, 411);

		Label lblAntal = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblAntal.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblAntal.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblAntal.setBounds(16, 18, 62, 22);
		lblAntal.setText(" Amount");

		Label lblVarunr = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblVarunr.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblVarunr.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblVarunr.setBounds(85, 18, 55, 22);
		lblVarunr.setText(" ItemNr");

		antalText = new Text(shlDigitcashierCashRegister, SWT.BORDER);
		antalText.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		antalText.setBounds(16, 43, 62, 31);

		varuNrText = new Text(shlDigitcashierCashRegister, SWT.BORDER | SWT.RIGHT);
		varuNrText.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		varuNrText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String testString = varuNrText.getText();

				if (testString.matches("[0-9]{2}")) { //Checks if there are exactly two digits in the input-field
					int itemNr = Integer.parseInt(testString);
					if (itemNr < CashRegister.getLengthOfItemList()){//Checks so that the number isn't higher than the highest known item ID.
						if(CashRegister.checkIfWeight(itemNr)) { //Asks checkIfWeight if the item is measured in weight
							lblAntal.setText("Weight");// Changes text above Amount/Weight window appropriately
						}
						else {
							lblAntal.setText("Amount");// Changes text above Amount/Weight window appropriately
						}
						
					}
				}
				else 
				{
					lblAntal.setText("Amount");// Changes text above Amount/Weight window appropriately
				}

			}


		});
		varuNrText.setBounds(85, 43, 55, 31);

		Button btnEnter = new Button(shlDigitcashierCashRegister, SWT.NONE);
		btnEnter.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {

				String antalString = antalText.getText();  //gets the content of the input window for Amount/Weight.
				String antalMatch; //Sets the regular expression for controlling what you can put into Amount.
				String inputString = varuNrText.getText(); //gets the content of the input window for varuNr
				
				if (lblAntal.getText() == "Amount"){
					antalMatch = "[0-9]{1,3}";
				}
				else {
					antalMatch = "[0-9]*?(\\.[0-9]{1,2})?";
				}

				if(inputString.equals("#2#")) { // Code for the #2# function. AF
					System.out.println("show total items here..");
					double sum = CashRegister.calculateSum();					
					lblDisplay.setText("total sum = " + sum);
					CashRegister.setTotal(sum);
					return;
				}
				if (!antalString.matches(antalMatch)) {
					//Displays warning if the amount does not match the regular expression for amount
					lblDisplay.setText("Illegal amount/weight");
					return;
				}
				if (!inputString.matches("[0-9]{2}")) {
					//Displays warning if the amount is more than to digits
					lblDisplay.setText("Illegal value. ItemNr has to be two digits.");
					return;
				}
				
				//The following code, converts variables to the appropriate types and checks if the itemNr is valid
				int itemNr = Integer.parseInt(inputString);
				double amountOfItem = Double.parseDouble(antalString);
				int upperBounds = CashRegister.getLengthOfItemList();
				if (itemNr<1||itemNr>upperBounds) {
					//Displays error Message if itemNr is less than one or larger than the highest ItemNr
					lblDisplay.setText("No such ItemNr.");					
				}
				else {
					//Adds Item to sale if it has passed all the checks above.
					lblDisplay.setText("");
					CashRegister.addItemToSale(amountOfItem,itemNr);
					System.out.println("Added " + amountOfItem + " of item " + itemNr);//Testline to see that this has worked. Remove from final code.
					String itemAdded = (CashRegister.getLatestItem()).toString();
					String itemLine = amountOfItem + " x " + itemAdded;
					lblDisplay.setText(amountOfItem +" x "+ itemAdded);
					lblSaleDisplay.append(itemLine + "\n");
				}
				antalText.setText(defaultAntal);
				varuNrText.setText("");
				lblAntal.setText("Antal/Viktb");
				double sum = CashRegister.calculateSum();
				CashRegister.setTotal(sum);
			}
		});
		btnEnter.setBounds(160, 43, 75, 31);
		btnEnter.setText("Enter");

		Label lblNewLabel_1 = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(0, 0, 0));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblNewLabel_1.setBounds(265, 10, 69, 29);
		lblNewLabel_1.setText("Display");

		Combo betalningsmedel = new Combo(shlDigitcashierCashRegister, SWT.READ_ONLY); //drop-down menu to choose payment method SH
		betalningsmedel.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		betalningsmedel.setItems(new String[] {"Creditcard", "Cash", "Giftcard"});
		betalningsmedel.setBounds(16, 357, 112, 31);
		betalningsmedel.select(0);
		betalningsmedel.setText("Payment Method");

		Label lblBetalningsmedel = new Label(shlDigitcashierCashRegister, SWT.NONE); //label above drop-down menu
		lblBetalningsmedel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblBetalningsmedel.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblBetalningsmedel.setText(" Choose Payment Method");
		lblBetalningsmedel.setBounds(16, 326, 172, 23);

		Button btnKpKlart = new Button(shlDigitcashierCashRegister, SWT.NONE); //Pressed when confirming customer payment SH
		btnKpKlart.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.BOLD));
		btnKpKlart.setBounds(16, 430, 219, 68);
		btnKpKlart.setText("Finalize \r\nSale");
		btnKpKlart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				double sum = CashRegister.calculateSum();
				boolean paymentReceived = (!changeInput.getText().isEmpty()); 
				if (!paymentReceived){
					switch(betalningsmedel.getText()){
					case "Cash":
						lblDisplay.setText("No amount registered for Cash payment.");
						break;
						
					case "Giftcard":
						lblDisplay.setText("No amount registered for coupon.");
						break;
						
					case "Creditcard":
						changeInput.setText(String.valueOf(sum));
						CashRegister.setChange(sum);
						paymentReceived = true;
						break;					
					}		
				}
				if (paymentReceived){
					lblDisplay.setText("Payment Confirmed: " + betalningsmedel.getText()); //displays confirmation with chosen method after pressing payment confirmation SH
					CashRegister.setTotal(sum);
					CashRegister.setBetalningsmedel(betalningsmedel.getText());
					CashRegister.createVoucherNr(); //generates new voucher number each time button is pressed (resets when closing app window)
					Receipt currentReceipt = new Receipt(); //created a new Receipt object
					currentReceipt.showReceipt(); //shows the receipt window
					CashRegister.clearSale();
					lblSaleDisplay.setText("");
					changeInput.setText("");
					}
			}
		});



		Label lblRabbater = new Label(shlDigitcashierCashRegister, SWT.NONE);           //start of discount /SH
		lblRabbater.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblRabbater.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblRabbater.setText(" Discount");
		lblRabbater.setBounds(16, 122, 65, 23);

		Combo discountlist = new Combo(shlDigitcashierCashRegister, SWT.READ_ONLY);
		discountlist.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		discountlist.setItems(new String[] {"20% discount", "-50kr"});
		discountlist.setBounds(16, 151, 131, 31);
		discountlist.setText("Rabatter");
		discountlist.select(0);

		Button rabbutton = new Button(shlDigitcashierCashRegister, SWT.NONE);
		rabbutton.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		rabbutton.setBounds(160, 151, 75, 31);
		rabbutton.setText("Apply");

		rabbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				double result = CashRegister.applyDiscount(discountlist.getText(), CashRegister.getTotal());  
				lblDisplay.setText("Discount applied:" + discountlist.getText()+ ". Result: "+result+"kr");
				CashRegister.setTotal(result);// end discount /SH
			}});

		//start change calculation /SH
		changeInput = new Text(shlDigitcashierCashRegister, SWT.BORDER); //input field for payment received from customer
		changeInput.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		changeInput.setBounds(16, 254, 112, 31);

		Label lblVxel = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblVxel.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblVxel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblVxel.setText(" Amount Paid");
		lblVxel.setBounds(16, 225, 96, 23);

		Button btncalculate = new Button(shlDigitcashierCashRegister, SWT.NONE);
		btncalculate.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		btncalculate.setBounds(160, 254, 75, 31);
		btncalculate.setText("Calculate");
		
		

		btncalculate.addMouseListener(new MouseAdapter() {     //when calculate button is pressed, if statement is run to check if input field received a numeric value at or above total cost of purchase
			@Override
			public void mouseDown(MouseEvent e) {
				double paid = 0d;
				if (CashRegister.isNumeric(changeInput.getText())) {
					paid = Double.parseDouble(changeInput.getText());
					double change = CashRegister.changeCalculation(paid, CashRegister.getTotal());  
					if (paid < CashRegister.getTotal()) {lblDisplay.setText("Insuficient funds");}
					else {lblDisplay.setText("Change: "+change+"kr");}
				} else { 
					lblDisplay.setText("Is not a number");  //end change calculation
				}


			}
		});
	}	
}
