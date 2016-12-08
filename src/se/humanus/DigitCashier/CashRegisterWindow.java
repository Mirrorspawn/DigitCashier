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
	private float total = 1234f; //Temporary total amount of selected wares, replace with Awe's sum code later


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
		shlDigitcashierCashRegister.setSize(610, 364);
		shlDigitcashierCashRegister.setText("DigitCashier Cash Register");

		Label lblDisplay = new Label(shlDigitcashierCashRegister, SWT.BORDER);
		lblDisplay.setAlignment(SWT.CENTER);
		lblDisplay.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
		lblDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblDisplay.setBounds(220, 43, 316, 25);

		Label lblAntal = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblAntal.setBounds(10, 23, 62, 15);
		lblAntal.setText("Antal/Vikt");

		Label lblVarunr = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblVarunr.setBounds(78, 23, 55, 15);
		lblVarunr.setText("VaruNr");

		antalText = new Text(shlDigitcashierCashRegister, SWT.BORDER);
		antalText.setBounds(11, 44, 35, 25);
		antalText.setText("1");

		varuNrText = new Text(shlDigitcashierCashRegister, SWT.BORDER | SWT.RIGHT);
		varuNrText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String testString = varuNrText.getText();

				if (testString.matches("[0-9]{2}")) { //Kollar om det är exakt två siffror i VaruNr
					int itemNr = Integer.parseInt(testString); //Omvandlar strängen från VaruNr till Integer
					if(CashRegister.checkIfWeight(itemNr)) { //Frågar om den inslagna koden motsvarar en vara som skall mätas i vikt
						lblAntal.setText("Vikt");// Sätter texten ovanför Antal/Vikt fönstret
					}
					else {
						lblAntal.setText("Antal");// Sätter texten ovanför Antal/Vikt fönstret						
					}
				}
				else {
					lblAntal.setText("Antal/Vikt");// Sätter texten ovanför Antal/Vikt fönstret
				}	
			}


		});
		varuNrText.setBounds(78, 44, 55, 25);

		Button btnEnter = new Button(shlDigitcashierCashRegister, SWT.NONE);
		btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {

				System.out.println("Button Pressed."); //testline to see that buttonpress is detected. Remove from final code.
				String antalString = antalText.getText();  //gets the content of the inputwindow for antal/vikt.
				String antalMatch = "[0-9]{1,3}"; //Sets the regular expression for controlling what you can put into antal.
				String inputString = varuNrText.getText(); //gets the content of the inputwindow for varuNr
				
		
				
				if(inputString.equals("#2#")) { // Code for the #2# function. AF
					System.out.println("show total items here..");
					double sum = CashRegister.calculateSum();
					int a = (int) (sum * 100); // Code to limit the decimal numbers to 2. Code by my brother
					sum = a / 100.0;
					lblDisplay.setText("total sum = " + sum);
					return;
				
				
				}
				if (!antalString.matches(antalMatch)) {
					lblDisplay.setText("Ogiltig mängd/vikt");
					return;
				}
				if (!inputString.matches("[0-9]{2}")) {
					lblDisplay.setText("Ogiltig input. VaruNr skall bestå av två siffror.");
					return;
				}

				int itemNr = Integer.parseInt(inputString); //converts itemNr to an Integer
				float amountOfItem = Float.parseFloat(antalString);//converts amountOfItem to a float value
				System.out.println(itemNr); //testline to see what number has been detected. Remove from final code.
				int upperBounds = CashRegister.getLengthOfItemList(); //Sets upperBounds to the number of products in the productlist
				if (itemNr<1||itemNr>upperBounds) { //checks if the text in varuNr is at least 1 and no greater than upperBounds.
					lblDisplay.setText("Varunummer finns ej.");					
				}
				else {
					lblDisplay.setText("");
					CashRegister.addItemToSale(amountOfItem,itemNr);
					System.out.println("Added " + amountOfItem + " of item " + itemNr);//Testline to see that this has worked. Remove from final code.
					String itemAdded = (CashRegister.getLatestItem()).toString();
					System.out.println(itemAdded);//testline. Remove from final code
					lblDisplay.setText(amountOfItem +" x "+ itemAdded);
				}
				antalText.setText(defaultAntal);
				varuNrText.setText("");
				lblAntal.setText("Antal/Viktb");
			}
		});
		btnEnter.setBounds(139, 44, 75, 25);
		btnEnter.setText("Enter");

		Label lblNewLabel_1 = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblNewLabel_1.setBounds(220, 23, 84, 21);
		lblNewLabel_1.setText("Visningsf\u00E4lt");

		Combo betalningsmedel = new Combo(shlDigitcashierCashRegister, SWT.READ_ONLY); //drop-down menu to choose payment method SH
		betalningsmedel.setItems(new String[] {"Kort", "Kontant", "Present"});
		betalningsmedel.setBounds(10, 208, 91, 23);
		betalningsmedel.select(0);
		betalningsmedel.setText("Betalningsmedel");

		Label lblBetalningsmedel = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblBetalningsmedel.setText("Betalningsmedel");
		lblBetalningsmedel.setBounds(10, 187, 96, 15);

		Button btnKpKlart = new Button(shlDigitcashierCashRegister, SWT.NONE); //Pressed when confirming customer payment SH
		btnKpKlart.setBounds(113, 206, 75, 25);
		btnKpKlart.setText("K\u00F6p Klart");
		btnKpKlart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblDisplay.setText("Payment Confirmed: " + betalningsmedel.getText()); //displays confirmation with chosen method after pressing payment confirmation SH

				/*MessageBox paymentBox = new MessageBox(shlDigitcashierCashRegister); //Pop-up confirming payment succeeded - replaced with message in display, remove this if it works for everyone SH
				paymentBox.setText("Payment confirmation");
				paymentBox.setMessage("Payment confirmed!");
				paymentBox.open();*/
				Receipt currentReceipt = new Receipt(); //created a new Receipt object
				currentReceipt.showReceipt(); //shows the receipt window
			}
		});



		Label lblRabbater = new Label(shlDigitcashierCashRegister, SWT.NONE);           //start of discount
		lblRabbater.setText("Rabatter");
		lblRabbater.setBounds(11, 87, 96, 15);

		Combo discountlist = new Combo(shlDigitcashierCashRegister, SWT.READ_ONLY);
		discountlist.setItems(new String[] {CashRegister.discount_20p, CashRegister.discount_50kr});
		discountlist.setBounds(11, 108, 91, 23);
		discountlist.setText("Rabatter");
		discountlist.select(0);

		Button rabbutton = new Button(shlDigitcashierCashRegister, SWT.NONE);
		rabbutton.setBounds(113, 108, 75, 25);
		rabbutton.setText("Apply");

		rabbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				float result = CashRegister.applyDiscount(discountlist.getText(), total);  
				lblDisplay.setText("Discount applied:" + discountlist.getText()+ ". Result: "+result+"kr"); // end discount
			}});
		
		//start change calculation
		changeInput = new Text(shlDigitcashierCashRegister, SWT.BORDER); //input field for payment received from customer
		changeInput.setBounds(10, 160, 76, 21);

		Label lblVxel = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblVxel.setText("V\u00E4xel");
		lblVxel.setBounds(10, 139, 96, 15);

		Button btncalculate = new Button(shlDigitcashierCashRegister, SWT.NONE);
		btncalculate.setBounds(113, 158, 75, 25);
		btncalculate.setText("Calculate");



		btncalculate.addMouseListener(new MouseAdapter() {     //when calculate button is pressed, if statement is run to check if input field received a numeric value at or above total cost of purchase
			@Override
			public void mouseDown(MouseEvent e) {
				Float paid = 0f;
				if (CashRegister.isNumeric(changeInput.getText())) {
					paid = Float.parseFloat(changeInput.getText());
					float change = CashRegister.changeCalculation(paid, total);  
					if (paid < total) {lblDisplay.setText("Insuficient funds");}
					else {lblDisplay.setText("Växel: "+change+"kr");}
				} else { 
					lblDisplay.setText("Is not a number");  //end change calculation
				}


			}
		});
	}	
}
