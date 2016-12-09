package se.humanus.DigitCashier;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

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
		
		TabItem tbtmVaror = new TabItem(tabFolder, SWT.NONE);
		tbtmVaror.setText("Varor");

		shlAdministration.open();
		shlAdministration.layout();
		while (!shlAdministration.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
