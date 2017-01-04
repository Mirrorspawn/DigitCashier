package se.humanus.DigitCashier;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class AdminWindow {
	private Text textItemCategory;
	private Text textVaruID;
	private Text textPrice;
	private Text textMoms;
	private Text textCategoryName;
	private Text textItemName;
	
	public static ItemCategory currentCategory;
	public static Item currentItem;

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void ativateAdminWindow() {
		try {
			AdminWindow window = new AdminWindow();
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
		Shell shlAdministration = new Shell();
		shlAdministration.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		shlAdministration.setSize(575, 345);
		shlAdministration.setText("Administration");
		
		TabFolder tabFolder = new TabFolder(shlAdministration, SWT.NONE);
		tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		tabFolder.setBounds(0, 10, 559, 274);
		
		TabItem tbtmVarugrupper = new TabItem(tabFolder, SWT.NONE);
		tbtmVarugrupper.setText("Item Categories");
		
		Composite composite = new Composite(tabFolder, SWT.EMBEDDED);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		tbtmVarugrupper.setControl(composite);
		
		Label lblMomsSatsLabel = new Label(composite, SWT.NONE);
		lblMomsSatsLabel.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblMomsSatsLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		lblMomsSatsLabel.setText("VAT in percent:");
		lblMomsSatsLabel.setBounds(242, 60, 107, 19);
		
		Label lblMeasuredText = new Label(composite, SWT.NONE);
		lblMeasuredText.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblMeasuredText.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		lblMeasuredText.setText("Measured in weight?");
		lblMeasuredText.setBounds(208, 85, 137, 21);
		
		Button buttonMeasure = new Button(composite, SWT.CHECK);
		buttonMeasure.setEnabled(false);
		buttonMeasure.setBounds(351, 88, 13, 16);
		
		textCategoryName = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		textCategoryName.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		textCategoryName.setBounds(350, 30, 150, 25);
		
		textMoms = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		textMoms.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		textMoms.setBounds(350, 60, 150, 25);
		
		Combo CatCombo = new Combo(composite, SWT.READ_ONLY);
		CatCombo.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		CatCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Gets the information for the selected Category and displays its attributes.
				currentCategory = CashRegister.categoryList.get(CatCombo.getSelectionIndex());
				textCategoryName.setText(currentCategory.getCategoryName());
				textMoms.setText(String.valueOf(currentCategory.getSalesTax()*100));
				buttonMeasure.setSelection(currentCategory.getMeasuredInWeight());			
			}
		});
		CatCombo.setItems(Administration.getCategoryList()); //Gets the selection items.
		CatCombo.setBounds(21, 29, 176, 23);
		
		Label lblNamn = new Label(composite, SWT.NONE);
		lblNamn.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblNamn.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		lblNamn.setBounds(296, 32, 49, 21);
		lblNamn.setText("Name:");
		
		Button btnCatEdit = new Button(composite, SWT.NONE);
		btnCatEdit.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnCatEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//Opens a new edit window for Categories and sets the selected Category as the Category to be edited
				Administration.setCurrentCategory(currentCategory);
				CatEditWindow.createNewWindow();
			}
		});
		btnCatEdit.setText("Edit Category");
		btnCatEdit.setBounds(21, 193, 107, 35);
		
		TabItem tbtmVaror = new TabItem(tabFolder, SWT.NONE);
		tbtmVaror.setText("Items");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		tbtmVaror.setControl(composite_1);
		
		Label lblVaruIDText = new Label(composite_1, SWT.NONE);
		lblVaruIDText.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblVaruIDText.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		lblVaruIDText.setBounds(228, 30, 55, 19);
		lblVaruIDText.setText("Item ID:");
		
		Label lblItemName = new Label(composite_1, SWT.NONE);
		lblItemName.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblItemName.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		lblItemName.setBounds(228, 57, 55, 20);
		lblItemName.setText("Name:");
		
		Label lblPriceText = new Label(composite_1, SWT.NONE);
		lblPriceText.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblPriceText.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		lblPriceText.setBounds(228, 83, 55, 21);
		lblPriceText.setText("Price:");
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		lblNewLabel_2.setBounds(228, 108, 68, 20);
		lblNewLabel_2.setText("Category:");
		
		textVaruID = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		textVaruID.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		textVaruID.setBounds(300, 28, 140, 24);
		
		textItemName = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		textItemName.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		textItemName.setBounds(300, 55, 140, 24);
		
		textPrice = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		textPrice.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		textPrice.setBounds(300, 82, 140, 24);
		
		textItemCategory = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		textItemCategory.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		textItemCategory.setBounds(300, 109, 140, 23);
		
		Combo comboVara = new Combo(composite_1, SWT.READ_ONLY);
		comboVara.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		comboVara.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Gets the information for the selected Item and displays its attributes.
				currentItem = CashRegister.itemList.get(comboVara.getSelectionIndex());
				textVaruID.setText(String.valueOf(currentItem.getId()));
				textItemName.setText(currentItem.getName());
				textItemCategory.setText((currentItem.getMyCategory()).getCategoryName());
				textPrice.setText(String.valueOf(currentItem.getPrice()));
			}
		});
		comboVara.setBounds(34, 28, 157, 23);
		comboVara.setItems(Administration.getItemList());
		
		Button btnItemEdit = new Button(composite_1, SWT.NONE);
		btnItemEdit.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnItemEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//Opens a new edit window for Items and sets the selected Item as the Item to be edited
				Administration.setCurrentItem(currentItem);
				ItemEditWindow.createNewWindow();
			}
		});
		btnItemEdit.setBounds(34, 197, 99, 28);
		btnItemEdit.setText("Edit Item");
		
		Menu menu = new Menu(shlAdministration, SWT.BAR);
		shlAdministration.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("Menu");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmSaveCurrentCategories = new MenuItem(menu_1, SWT.NONE);
		mntmSaveCurrentCategories.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Saves the current data of all Categories and Items to file. 
				try {
					DataManagement.saveData();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmSaveCurrentCategories.setText("Save Current Categories and Items");

		shlAdministration.open();
		shlAdministration.layout();
		while (!shlAdministration.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
			
		
	}
}
