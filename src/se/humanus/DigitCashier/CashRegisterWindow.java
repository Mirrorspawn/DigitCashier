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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Menu;

public class CashRegisterWindow {

	protected Shell shlDigitcashierCashRegister;
	private Text antalText;
	private Text varuNrText;

	/**
	 * Launch the application.
	 * @param args
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
		shlDigitcashierCashRegister.setSize(546, 364);
		shlDigitcashierCashRegister.setText("DigitCashier Cash Register");
		
		Label lblDisplay = new Label(shlDigitcashierCashRegister, SWT.BORDER);
		lblDisplay.setAlignment(SWT.CENTER);
		lblDisplay.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
		lblDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblDisplay.setBounds(194, 43, 316, 25);
		
		antalText = new Text(shlDigitcashierCashRegister, SWT.BORDER);
		antalText.setBounds(11, 44, 35, 25);
		
		varuNrText = new Text(shlDigitcashierCashRegister, SWT.BORDER | SWT.RIGHT);
		varuNrText.setBounds(52, 44, 55, 25);
		
		Label lblAntal = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblAntal.setBounds(10, 23, 35, 15);
		lblAntal.setText("Antal");
		
		Label lblVarunr = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblVarunr.setBounds(52, 23, 55, 15);
		lblVarunr.setText("VaruNr");
		
		Button btnEnter = new Button(shlDigitcashierCashRegister, SWT.NONE);
		btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				System.out.println("Button Pressed."); //testline to see that buttonpress is detected. Remove from final code.
				String antalString = antalText.getText();  //gets the content of the inputwindow for antal/vikt.
				String antalMatch = "[0-9]{1,3}"; //Sets the regular expression for controlling what you can put into antal.
				String inputString = varuNrText.getText(); //gets the content of the inputwindow for varuNr
				
				//Check for #2# here
				
				
				if (!antalString.matches(antalMatch)) {
					lblDisplay.setText("Ogiltig mängd/vikt");
					return;
				}
				if (!inputString.matches("[0-9]{1,2}")) {
					lblDisplay.setText("Ogiltig input.");
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
			}
		});
		btnEnter.setBounds(113, 44, 75, 25);
		btnEnter.setText("Enter");
		
		Label lblNewLabel_1 = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblNewLabel_1.setBounds(194, 23, 84, 21);
		lblNewLabel_1.setText("Visningsf\u00E4lt");
		
		Combo betalningsmedel = new Combo(shlDigitcashierCashRegister, SWT.READ_ONLY); //drop-down menu to choose payment method SH
		betalningsmedel.setItems(new String[] {"Kort", "Kontant", "Present"});
		betalningsmedel.setBounds(11, 162, 91, 23);
		betalningsmedel.select(0);
		betalningsmedel.setText("Betalningsmedel");
		
		Label lblBetalningsmedel = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblBetalningsmedel.setText("Betalningsmedel");
		lblBetalningsmedel.setBounds(11, 141, 96, 15);
					
		Button btnKpKlart = new Button(shlDigitcashierCashRegister, SWT.NONE); //Pressed when confirming customer payment SH
		btnKpKlart.setBounds(113, 160, 75, 25);
		btnKpKlart.setText("K\u00F6p Klart");
		btnKpKlart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblDisplay.setText("Payment Confirmed: " + betalningsmedel.getText()); //displays confirmation with chosen method after pressing payment confirmation SH
				
				/*MessageBox paymentBox = new MessageBox(shlDigitcashierCashRegister); //Pop-up confirming payment succeeded - replaced with message in display, remove this if it works for everyone SH
				paymentBox.setText("Payment confirmation");
				paymentBox.setMessage("Payment confirmed!");
				paymentBox.open();*/
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
				float result = CashRegister.applyDiscount(discountlist.getText(), 1234f);  //1234f temporary value for sum of items, this is what the discount is applied to
				lblDisplay.setText("Discount applied:" + discountlist.getText()+ ". Result: "+result+"kr");
				
				
			}
		});
	}	
}
