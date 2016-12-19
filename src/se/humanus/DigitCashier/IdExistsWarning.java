package se.humanus.DigitCashier;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

public class IdExistsWarning extends Dialog {

	protected Object result;
	protected Shell shlFailed;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public IdExistsWarning(Shell parent, int style) {
		super(parent, style);
		setText("Misslyckat");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlFailed.open();
		shlFailed.layout();
		Display display = getParent().getDisplay();
		while (!shlFailed.isDisposed()) {
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
		shlFailed = new Shell(getParent(), getStyle());
		shlFailed.setSize(277, 189);
		shlFailed.setText("F\u00F6rs\u00F6k Misslyckat");
		
		Label lblNewLabel = new Label(shlFailed, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblNewLabel.setBounds(43, 38, 183, 32);
		lblNewLabel.setText("Varu ID redan upptaget!");
		
		Button btnOk = new Button(shlFailed, SWT.NONE);
		btnOk.setBounds(94, 87, 75, 25);
		btnOk.setText("Ok");

	}

}
