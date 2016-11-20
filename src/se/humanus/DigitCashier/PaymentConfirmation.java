package se.humanus.DigitCashier;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class PaymentConfirmation extends Dialog {

/*	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		PaymentConfirmation confirm = new PaymentConfirmation(shell, SWT.None);
		confirm.open();
	}*/
	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PaymentConfirmation(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setSize(450, 300);
		shell.setText(getText());

		// The Button and popup confirmation, tested and works - SH
		Button btnConfirmPayment = new Button(shell, SWT.NONE);
		btnConfirmPayment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				MessageBox paymentBox = new MessageBox(shell);
				paymentBox.setText("Payment confirmation");
				paymentBox.setMessage("Payment confirmed!");
				paymentBox.open();
			}
		});
		btnConfirmPayment.setBounds(65, 42, 124, 35);
		btnConfirmPayment.setText("Confirm Payment?");

	}

}
