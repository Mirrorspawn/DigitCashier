package se.humanus.DigitCashier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Label;

public class LoginGUI {

	protected Shell LoginWindow;
	private Text usernameField;
	private Text passwordField;
	
	CashRegisterWindow cashRegisterGUI = new CashRegisterWindow();
	

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	//public static void main(String[] args) {
	public static void activateLoginWindow(){
		try {
			LoginGUI window = new LoginGUI();
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
		LoginWindow.open();
		LoginWindow.layout();
		while (!LoginWindow.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		LoginWindow = new Shell();
		LoginWindow.setSize(450, 300);
		LoginWindow.setText("SWT Application");
		
		Label InvalidUsername = new Label(LoginWindow, SWT.NONE);
		InvalidUsername.setBounds(201, 53, 97, 15);
		InvalidUsername.setText("Username");
		
		Label invalidPassword = new Label(LoginWindow, SWT.NONE);
		invalidPassword.setBounds(201, 105, 97, 15);
		invalidPassword.setText("Password");
		
		usernameField = new Text(LoginWindow, SWT.BORDER);
		usernameField.setBounds(54, 43, 141, 32);
		
		passwordField = new Text(LoginWindow, SWT.BORDER);
		passwordField.setBounds(54, 95, 141, 32);
		
		Button btnLogin = new Button(LoginWindow, SWT.NONE);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				if (username.equals("Kassör")){
					if (password.equals("201")){
						
						LoginWindow.close();
						Login.startApplication("Cashier");						
					}
					else{
						invalidPassword.setText("Invalid Password");	
					}
				}
				else if (username.equals("Admin")){
					if (password.equals("302")){
						
						LoginWindow.close();
						Login.startApplication("Admin");
						
					}
					else{
						invalidPassword.setText("Invalid login-code");
					}
				
				}
				else if (username.equals("Chef")){
					if (password.equals("503")){
						
						LoginWindow.close();
						Login.startApplication("Chef");
						
					}	
					else{
						invalidPassword.setText("Invalid login-code");
					}
					
				}
				else{
					InvalidUsername.setText("Invalid Username");
					
				}
			}
		});
		btnLogin.setBounds(75, 156, 75, 25);
		btnLogin.setText("Login");

	}

}
