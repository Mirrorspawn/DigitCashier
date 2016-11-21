package se.humanus.DigitCashier;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

public class CashRegisterWindow {

	protected Shell shlDigitcashierCashRegister;
	private Text text;
	private Text text_1;

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
		
		text = new Text(shlDigitcashierCashRegister, SWT.BORDER);
		text.setBounds(11, 44, 35, 25);
		
		text_1 = new Text(shlDigitcashierCashRegister, SWT.BORDER);
		text_1.setBounds(52, 44, 55, 25);
		
		Label lblAntal = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblAntal.setBounds(10, 23, 35, 15);
		lblAntal.setText("Antal");
		
		Label lblVarunr = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblVarunr.setBounds(52, 23, 55, 15);
		lblVarunr.setText("VaruNr");
		
		Button btnEnter = new Button(shlDigitcashierCashRegister, SWT.NONE);
		btnEnter.setBounds(113, 44, 75, 25);
		btnEnter.setText("Enter");
		
		Label lblDisplay = new Label(shlDigitcashierCashRegister, SWT.BORDER);
		lblDisplay.setAlignment(SWT.CENTER);
		lblDisplay.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
		lblDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblDisplay.setBounds(194, 43, 316, 25);
		
		Label lblNewLabel_1 = new Label(shlDigitcashierCashRegister, SWT.NONE);
		lblNewLabel_1.setBounds(194, 23, 84, 21);
		lblNewLabel_1.setText("Visningsf\u00E4lt");
		
		Button btnKpKlart = new Button(shlDigitcashierCashRegister, SWT.NONE);
		btnKpKlart.setBounds(113, 75, 75, 25);
		btnKpKlart.setText("K\u00F6p Klart");

	}
}
