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
	private Text textMeasure;
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
		shlAdministration.setSize(575, 417);
		shlAdministration.setText("Administration");
		
		TabFolder tabFolder = new TabFolder(shlAdministration, SWT.NONE);
		tabFolder.setBounds(0, 10, 549, 358);
		
		TabItem tbtmVarugrupper = new TabItem(tabFolder, SWT.NONE);
		tbtmVarugrupper.setText("Varugrupper");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmVarugrupper.setControl(composite);
		
		Label lblMomsSatsLabel = new Label(composite, SWT.NONE);
		lblMomsSatsLabel.setText("Moms-procent:");
		lblMomsSatsLabel.setBounds(226, 59, 87, 15);
		
		Label lblMeasuredText = new Label(composite, SWT.NONE);
		lblMeasuredText.setText("Measured in:");
		lblMeasuredText.setBounds(226, 83, 73, 15);
		
		textCategoryName = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		textCategoryName.setBounds(319, 29, 150, 21);
		
		textMoms = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		textMoms.setBounds(319, 56, 150, 21);
		
		textMeasure = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		textMeasure.setBounds(319, 81, 150, 21);
		
		Combo CatCombo = new Combo(composite, SWT.READ_ONLY);
		CatCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				currentCategory = CashRegister.categoryList.get(CatCombo.getSelectionIndex());
				textCategoryName.setText(currentCategory.getCategoryName());
				textMoms.setText(String.valueOf(currentCategory.getSalesTax()*100));
				textMeasure.setText(currentCategory.getMeasuredInWeight().toString());				
			}
		});
		CatCombo.setItems(Administration.getCategoryList());
		CatCombo.setBounds(31, 29, 176, 23);
		
		Label lblNamn = new Label(composite, SWT.NONE);
		lblNamn.setBounds(227, 36, 55, 15);
		lblNamn.setText("Namn:");
		
		Button btnCatEdit = new Button(composite, SWT.NONE);
		btnCatEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Administration.setCurrentCategory(currentCategory);
				CatEditWindow.createNewWindow();
			}
		});
		btnCatEdit.setText("Redigera Grupp");
		btnCatEdit.setBounds(31, 209, 99, 25);
		
		TabItem tbtmVaror = new TabItem(tabFolder, SWT.NONE);
		tbtmVaror.setText("Varor");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmVaror.setControl(composite_1);
		
		Label lblVaruIDText = new Label(composite_1, SWT.NONE);
		lblVaruIDText.setBounds(240, 31, 55, 15);
		lblVaruIDText.setText("Varu ID");
		
		Label lblItemName = new Label(composite_1, SWT.NONE);
		lblItemName.setBounds(239, 56, 55, 15);
		lblItemName.setText("Namn");
		
		Label lblPriceText = new Label(composite_1, SWT.NONE);
		lblPriceText.setBounds(240, 82, 55, 15);
		lblPriceText.setText("Price");
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setBounds(238, 109, 55, 15);
		lblNewLabel_2.setText("Category");
		
		textVaruID = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		textVaruID.setBounds(299, 28, 140, 21);
		
		textItemName = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		textItemName.setBounds(299, 54, 140, 21);
		
		textPrice = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		textPrice.setBounds(299, 79, 140, 21);
		
		textItemCategory = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		textItemCategory.setBounds(299, 106, 140, 21);
		
		Combo comboVara = new Combo(composite_1, SWT.READ_ONLY);
		comboVara.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				currentItem = CashRegister.itemList.get(comboVara.getSelectionIndex());
				textVaruID.setText(String.valueOf(currentItem.getId()));
				textItemName.setText(currentItem.getName());
				//System.out.println(it.getMyCategory().getCategoryName().toString()); //Testrad
				textItemCategory.setText((currentItem.getMyCategory()).getCategoryName());
				textPrice.setText(String.valueOf(currentItem.getPrice()));
			}
		});
		comboVara.setBounds(34, 28, 157, 23);
		comboVara.setItems(Administration.getItemList());
		
		Button btnItemEdit = new Button(composite_1, SWT.NONE);
		btnItemEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Administration.setCurrentItem(currentItem);
				ItemEditWindow.createNewWindow();
			}
		});
		btnItemEdit.setBounds(34, 197, 99, 25);
		btnItemEdit.setText("Redigera Vara");
		
		Menu menu = new Menu(shlAdministration, SWT.BAR);
		shlAdministration.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("Menu");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmCreateNewCategory = new MenuItem(menu_1, SWT.NONE);
		mntmCreateNewCategory.setText("Create New Category");
		
		MenuItem mntmCreateNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmCreateNewItem.setText("Create New Item");
		
		MenuItem mntmSaveCurrentCategories = new MenuItem(menu_1, SWT.NONE);
		mntmSaveCurrentCategories.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					DataManagement.saveData();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
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
