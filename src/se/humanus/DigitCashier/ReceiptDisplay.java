package se.humanus.DigitCashier;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

//import com.sun.xml.internal.ws.util.StringUtils;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.RowData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class ReceiptDisplay {
	private Text text;

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
		shlKvitto.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shlKvitto.setSize(553, 615);
		shlKvitto.setText("Receipt");
		shlKvitto.setLayout(new FormLayout());

		Composite composite = new Composite(shlKvitto, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0);
		fd_composite.bottom = new FormAttachment(0, 577);
		fd_composite.right = new FormAttachment(0, 537);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(105, 105, 105));
		composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_1.setLayoutData(new RowData(532, 43));

		Label lblOrgName = new Label(composite_1, SWT.NONE); //org name
		lblOrgName.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblOrgName.setAlignment(SWT.CENTER);
		lblOrgName.setFont(SWTResourceManager.getFont("Bodoni MT", 15, SWT.BOLD));
		lblOrgName.setLayoutData(new RowData(260, 37));
		lblOrgName.setText(CashRegister.orgName);

		Label lblOrgNr = new Label(composite_1, SWT.NONE); //org number
		lblOrgNr.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblOrgNr.setFont(SWTResourceManager.getFont("Bodoni MT", 15, SWT.NORMAL));
		lblOrgNr.setAlignment(SWT.CENTER);
		lblOrgNr.setLayoutData(new RowData(260, 37));
		lblOrgNr.setText(CashRegister.orgNr);

		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		composite_2.setForeground(SWTResourceManager.getColor(176, 196, 222));
		composite_2.setLayoutData(new RowData(530, 453));

		Label lbldate = new Label(composite_2, SWT.NONE);
		lbldate.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lbldate.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lbldate.setAlignment(SWT.CENTER);
		lbldate.setBounds(2, 0, 531, 30);
		Receipt receipt = new Receipt();
		lbldate.setText(receipt.getDate()); // shows date on receipt.

		text = new Text(composite_2, SWT.V_SCROLL);  //scroll function with info from cash register input, replaces previous static window
		text.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		text.setBounds(2, 30, 525, 423);
		text.setText(CashRegister.getReceiptSaleInfo(CashRegister.saleItemList));



		Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		composite_3.setLayoutData(new RowData(529, 68));
		//discount, total and VAT box start
		Label lblSum = new Label(composite_3, SWT.RIGHT);
		lblSum.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblSum.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblSum.setBounds(265, 0, 254, 83);
		lblSum.setText("Discount applied:" +CashRegister.getDiscount()+"\nTotal Sum: " +CashRegister.getTotal()+ "SEK\nVAT: "+CashRegister.getTotalVAT(CashRegister.saleItemList)+"SEK");
		//discount, total and VAT box end

		//Amount paid, payment method and unique voucher nr start
		Label lblPaidXSek = new Label(composite_3, SWT.NONE);
		lblPaidXSek.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblPaidXSek.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblPaidXSek.setText("Paid: " +CashRegister.getChange()+ "SEK with " +CashRegister.getBetalningsmedel()+ "\nChange: " +(CashRegister.getChange() - CashRegister.getTotal())+ "\nVer.NR.: " +CashRegister.getVoucherNr());
		lblPaidXSek.setBounds(10, 0, 232, 83);
		//Amount paid, payment method and unique voucher nr end


		shlKvitto.open();
		shlKvitto.layout();
		while (!shlKvitto.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
