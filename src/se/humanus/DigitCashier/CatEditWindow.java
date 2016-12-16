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
		shlEditCategory.setSize(297, 202);
		shlEditCategory.setText("Edit Category");
		
		Label lblNewLabel = new Label(shlEditCategory, SWT.NONE);
		lblNewLabel.setBounds(16, 15, 95, 15);
		lblNewLabel.setText("Category Name:");
		
		Label lblNewLabel_1 = new Label(shlEditCategory, SWT.NONE);
		lblNewLabel_1.setBounds(16, 46, 112, 15);
		lblNewLabel_1.setText("Moms-sats procent:");
		
		Label lblNewLabel_2 = new Label(shlEditCategory, SWT.NONE);
		lblNewLabel_2.setBounds(16, 78, 55, 15);
		lblNewLabel_2.setText("Pris per:");
		
		textName = new Text(shlEditCategory, SWT.BORDER);
		textName.setBounds(149, 15, 112, 21);
		textName.setText(AdminWindow.currentCategory.getCategoryName());
		
		textMoms = new Text(shlEditCategory, SWT.BORDER);
		textMoms.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
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
		textMoms.setBounds(149, 45, 112, 21);
		textMoms.setText(String.valueOf((Math.round(AdminWindow.currentCategory.getSalesTax()*100))));
		
		CCombo comboMeasure = new CCombo(shlEditCategory, SWT.BORDER);
		comboMeasure.setItems(new String[] {"Vara", "Vikt (Kg)"});
		comboMeasure.setBounds(149, 78, 75, 21);
		comboMeasure.select((AdminWindow.currentCategory.getMeasuredInWeight()==false)?0:1);
		
		Button btnConfirm = new Button(shlEditCategory, SWT.NONE);
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				boolean measureWeight;
				String catName = textName.getText();
				int tempMoms = Integer.parseInt(textMoms.getText());
				System.out.println(tempMoms);
				float catMoms = (tempMoms/100.0f);
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
		btnConfirm.setBounds(16, 123, 103, 25);
		btnConfirm.setText("G\u00F6r \u00C4ndringar");
		
		Button btnCancel = new Button(shlEditCategory, SWT.NONE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shlEditCategory.close();
			}
		});
		btnCancel.setBounds(186, 123, 75, 25);
		btnCancel.setText("Avbryt");

		shlEditCategory.open();
		shlEditCategory.layout();
		while (!shlEditCategory.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
