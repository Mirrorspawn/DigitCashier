package se.humanus.DigitCashier;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

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
		shlDigitcashierCashRegister.setSize(546, 183);
		shlDigitcashierCashRegister.setText("DigitCashier Cash Register");
		
		Label lblDisplay = new Label(shlDigitcashierCashRegister, SWT.BORDER);
		lblDisplay.setAlignment(SWT.CENTER);
		lblDisplay.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
		lblDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblDisplay.setBounds(194, 43, 316, 25);
		
		antalText = new Text(shlDigitcashierCashRegister, SWT.BORDER);
		antalText.setBounds(11, 44, 35, 25);
		
		varuNrText = new Text(shlDigitcashierCashRegister, SWT.BORDER);
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
				System.out.println("Button Pressed.");
				String inputString = varuNrText.getText();
				int itemNr = Integer.parseInt(inputString);
				System.out.println(itemNr);
				if (itemNr<1||itemNr>99) {
					lblDisplay.setText("Ogiltigt Varunummer.");					
				}
				else {
					CashRegister.addItemToSale(itemNr);
					System.out.println("Added item " + itemNr);
				}
			}
		});
		btnEnter.setBounds(113, 44, 75, 25);
		btnEnter.setText("Enter");
		
		Label lblNewLabel_1 = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblNewLabel_1.setBounds(194, 23, 84, 21);
		lblNewLabel_1.setText("Visningsf\u00E4lt");
		
		Button btnKpKlart = new Button(shlDigitcashierCashRegister, SWT.NONE);
		btnKpKlart.setBounds(113, 75, 75, 25);
		btnKpKlart.setText("K\u00F6p Klart");

	}
}
