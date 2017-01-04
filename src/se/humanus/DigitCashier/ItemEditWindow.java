package se.humanus.DigitCashier;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class ItemEditWindow {

	protected Shell shlRedigeraVara;
	private Text textItemName;
	private Text textIDnr;
	private Text textPris;

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void createNewWindow() {
		try {
			ItemEditWindow window = new ItemEditWindow();
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
		shlRedigeraVara.open();
		shlRedigeraVara.layout();
		while (!shlRedigeraVara.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlRedigeraVara = new Shell();
		shlRedigeraVara.setSize(344, 300);
		shlRedigeraVara.setText("Redigera Vara");
		
		Label lblVaruNamn = new Label(shlRedigeraVara, SWT.NONE);
		lblVaruNamn.setBounds(37, 35, 55, 15);
		lblVaruNamn.setText("Namn");
		
		Label lblKategori = new Label(shlRedigeraVara, SWT.NONE);
		lblKategori.setBounds(37, 69, 55, 15);
		lblKategori.setText("Kategori");
		
		Label lblIDnr = new Label(shlRedigeraVara, SWT.NONE);
		lblIDnr.setBounds(37, 105, 55, 15);
		lblIDnr.setText("IDnr");
		
		Label lblPrice = new Label(shlRedigeraVara, SWT.NONE);
		lblPrice.setBounds(37, 145, 55, 15);
		lblPrice.setText("Pris");
		
		Label lblWarning = new Label(shlRedigeraVara, SWT.NONE);
		lblWarning.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblWarning.setBounds(191, 110, 93, 15);
		
		CCombo combo = new CCombo(shlRedigeraVara, SWT.BORDER);
		combo.setBounds(106, 68, 178, 21);
		combo.setItems(Administration.getCategoryList());
		combo.select(combo.indexOf(AdminWindow.currentItem.getMyCategory().getCategoryName()));
		
		
		textItemName = new Text(shlRedigeraVara, SWT.BORDER); //Displays the name of the Item
		textItemName.setBounds(106, 32, 178, 21);
		textItemName.setText(AdminWindow.currentItem.getName());
		
		textIDnr = new Text(shlRedigeraVara, SWT.BORDER); //Displays the IDnr of the item
		textIDnr.setBounds(106, 105, 76, 21);
		textIDnr.setText(AdminWindow.currentItem.getId());
		
		textPris = new Text(shlRedigeraVara, SWT.BORDER); //Displays the Price of the item
		textPris.setBounds(106, 142, 76, 21);
		textPris.setText(String.valueOf(AdminWindow.currentItem.getPrice()));
		
		Button btnConfirm = new Button(shlRedigeraVara, SWT.NONE);
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (!(textIDnr.getText().equals(AdminWindow.currentItem.getId()) && Administration.isIdNrTaken(textIDnr.getText()))){
					textIDnr.setText(AdminWindow.currentItem.getId());
					lblWarning.setText("ID finns redan!");
					return;					
				}
				else{
					Administration.updateItem(textItemName.getText(), textIDnr.getText(), CashRegister.getCategory(combo.getSelectionIndex()), Double.parseDouble(textPris.getText()));
					shlRedigeraVara.close();
				}
			}
		});
		btnConfirm.setBounds(37, 195, 108, 25);
		btnConfirm.setText("Confirm Changes");
		
		Button btnCancel = new Button(shlRedigeraVara, SWT.NONE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shlRedigeraVara.close();
			}
		});
		btnCancel.setBounds(225, 195, 75, 25);
		btnCancel.setText("Cancel");

	}
}
