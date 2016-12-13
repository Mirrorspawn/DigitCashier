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
		
		Label lblVarugruppensNamn = new Label(composite, SWT.NONE);
		lblVarugruppensNamn.setBounds(226, 59, 117, 15);
		lblVarugruppensNamn.setText("Varugruppens Namn:");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(349, 59, 55, 15);
		lblNewLabel.setText("New Label");
		
		Label lblMomssats = new Label(composite, SWT.NONE);
		lblMomssats.setBounds(226, 85, 73, 15);
		lblMomssats.setText("Moms-sats:");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBounds(349, 85, 55, 15);
		lblNewLabel_1.setText("New Label");
		
		Button btnMeasuredInWeight = new Button(composite, SWT.CHECK);
		btnMeasuredInWeight.setBounds(226, 110, 143, 16);
		btnMeasuredInWeight.setText("Measured in Weight");
		
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
