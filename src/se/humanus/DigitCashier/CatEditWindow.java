package se.humanus.DigitCashier;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class CatEditWindow {
	private Text textName;
	private Text textMoms;

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void createNewWindow() {
		try {
			CatEditWindow window = new CatEditWindow();
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
		Shell shlEditCategory = new Shell();
		shlEditCategory.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		shlEditCategory.setSize(364, 238);
		shlEditCategory.setText("Redigera Varugrupp");
		
		Label lblNewLabel = new Label(shlEditCategory, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		lblNewLabel.setBounds(16, 15, 116, 22);
		lblNewLabel.setText("Category Name:");
		
		Label lblNewLabel_1 = new Label(shlEditCategory, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		lblNewLabel_1.setBounds(16, 46, 132, 21);
		lblNewLabel_1.setText("Moms-sats procent:");
		
		Label lblNewLabel_2 = new Label(shlEditCategory, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		lblNewLabel_2.setBounds(16, 78, 55, 21);
		lblNewLabel_2.setText("Pris per:");
		
		textName = new Text(shlEditCategory, SWT.BORDER); //Display for the name of the chosen category
		textName.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		textName.setBounds(150, 15, 112, 25);
		textName.setText(AdminWindow.currentCategory.getCategoryName());
		
		textMoms = new Text(shlEditCategory, SWT.BORDER);//Display for the VAT percentage of the chosen category
		textMoms.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		textMoms.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//Checks that only numbers are entered in this textbox
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0,  chars.length, chars, 0);
				for (int i=0; i<chars.length;i++){
					if (!('0' <= chars[i] && chars[i] <= '9')){
						e.doit=false;
					}
				}
			}
		});
		textMoms.setBounds(150, 45, 112, 25);
		textMoms.setText(String.valueOf((Math.round(AdminWindow.currentCategory.getSalesTax()*100))));
		
		CCombo comboMeasure = new CCombo(shlEditCategory, SWT.BORDER); //ComboBox to display and choose if measured in weight or amount
		comboMeasure.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		comboMeasure.setItems(new String[] {"Amount", "Weight (Kg)"});
		comboMeasure.setBounds(150, 74, 112, 25);
		comboMeasure.select((AdminWindow.currentCategory.getMeasuredInWeight()==false)?0:1);
		
		Button btnConfirm = new Button(shlEditCategory, SWT.NONE);
		btnConfirm.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//Collects the info from the textboxes and sends it to the updateCategory method
				boolean measureWeight;
				String catName = textName.getText();
				int tempMoms = Integer.parseInt(textMoms.getText());
				System.out.println(tempMoms);
				double catMoms = (tempMoms/100.0d);
				System.out.println(catMoms);
				int measureTemp = comboMeasure.getSelectionIndex();
				if (measureTemp == 0){
					measureWeight = false;
				}
				else {
					measureWeight = true;
				}				
				Administration.updateCategory(catName, catMoms, measureWeight);
				//AdminWindow.updateCategoryView(); //Have to work out how to do that.
				shlEditCategory.close();				
			}
		});
		btnConfirm.setBounds(14, 145, 125, 30);
		btnConfirm.setText("Confirm Changes");
		
		Button btnCancel = new Button(shlEditCategory, SWT.NONE);
		btnCancel.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shlEditCategory.close();
			}
		});
		btnCancel.setBounds(236, 145, 75, 30);
		btnCancel.setText("Cancel");

		shlEditCategory.open();
		shlEditCategory.layout();
		while (!shlEditCategory.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
