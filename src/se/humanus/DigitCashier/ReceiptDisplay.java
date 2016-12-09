package se.humanus.DigitCashier;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.RowData;

public class ReceiptDisplay {

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void startReceipt() {
		try {
			ReceiptDisplay window = new ReceiptDisplay();
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
		Shell shlKvitto = new Shell();
		shlKvitto.setSize(450, 423);
		shlKvitto.setText("Kvitto");
		shlKvitto.setLayout(new FormLayout());
		
		Composite composite = new Composite(shlKvitto, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 384);
		fd_composite.right = new FormAttachment(0, 434);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_1.setLayoutData(new RowData(428, SWT.DEFAULT));
		
		Label lblOrgName = new Label(composite_1, SWT.NONE);
		lblOrgName.setLayoutData(new RowData(215, SWT.DEFAULT));
		lblOrgName.setText("Organization Name Here");
		
		Label lblOrgNr = new Label(composite_1, SWT.NONE);
		lblOrgNr.setAlignment(SWT.RIGHT);
		lblOrgNr.setLayoutData(new RowData(200, SWT.DEFAULT));
		lblOrgNr.setText("XXXXXXXXX");
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayoutData(new RowData(427, 303));
		
		Label lblSaleInfo = new Label(composite_2, SWT.NONE);
		lblSaleInfo.setBounds(10, 10, 407, 283);
		lblSaleInfo.setText("Sale Information goes here\n" +CashRegister.getReceiptSaleInfo(CashRegister.saleItemList));
		
		Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setLayoutData(new RowData(428, 50));
		
		Label lblSum = new Label(composite_3, SWT.NONE);
		lblSum.setBounds(255, 10, 143, 30);
		lblSum.setText("Total Sum: " +CashRegister.getTotal()+ "SEK\nVarav Moms: "+CashRegister.getTotalVAT(CashRegister.saleItemList)+"SEK");

		shlKvitto.open();
		shlKvitto.layout();
		while (!shlKvitto.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
