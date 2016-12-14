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

public class AdminWindow {

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
		lblMomsSatsLabel.setBounds(227, 32, 87, 15);
		
		Label lblMoms = new Label(composite, SWT.BORDER);
		lblMoms.setBackground(SWTResourceManager.getColor(192, 192, 192));
		lblMoms.setBounds(320, 32, 49, 15);
		
		Label lblMeasuredText = new Label(composite, SWT.NONE);
		lblMeasuredText.setText("Measured in:");
		lblMeasuredText.setBounds(227, 53, 73, 15);
		
		Label lblMeasureText = new Label(composite, SWT.BORDER);
		lblMeasureText.setBackground(SWTResourceManager.getColor(192, 192, 192));
		lblMeasureText.setBounds(320, 53, 49, 15);
		
		Combo CatCombo = new Combo(composite, SWT.NONE);
		CatCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ItemCategory ic = CashRegister.categoryList.get(CatCombo.getSelectionIndex());
				lblMoms.setText(String.valueOf(ic.getSalesTax()*100));
				lblMeasureText.setText(ic.getMeasuredInWeight().toString());				
			}
		});
		CatCombo.setItems(Administration.getCategoryList());
		CatCombo.setBounds(31, 29, 176, 23);
		
		TabItem tbtmVaror = new TabItem(tabFolder, SWT.NONE);
		tbtmVaror.setText("Varor");
		
		Menu menu = new Menu(shlAdministration, SWT.BAR);
		shlAdministration.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
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
