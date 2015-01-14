package guessGame.frontend;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn extends JFrame {

	private JTextField userText;
	private JTextField passwordText;
	private JLabel userLbl;
	private JLabel passwordLbl;
	private JButton register;
	private JButton logIn;

	private final Client client;

	@SuppressWarnings("deprecation")
	public LogIn(Client client) {
		this.client = client;

		setTitle("Log In");
		setSize(200, 120);
		setLocationRelativeTo(this.client);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 2));

		userText = new JTextField();
		passwordText = new JPasswordField();
		userLbl = new JLabel("Username: ");
		passwordLbl = new JLabel("Password: ");
		register = new JButton("I'm New");
		logIn = new JButton("Log In");

		register.addActionListener(new LogInListener());
		logIn.addActionListener(new LogInListener());

		add(userLbl);
		add(userText);
		add(passwordLbl);
		add(passwordText);
		add(register);
		add(logIn);

		this.client.disable();
		this.setAlwaysOnTop(true);

	}

	class LogInListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = userText.getText();
			String password = passwordText.getText();
			String typeOfRequest = "";
			if (!name.equals("") && !password.equals("")) {
				System.out.println(name + " " + password);
				client.setUserName(name);
				client.setPassword(password);
				if (register.equals(e.getSource())) {
					typeOfRequest = "register";
				} else {
					if (logIn.equals(e.getSource())) {
						typeOfRequest = "login";
					}
				}
				setVisible(false);
				client.enable();
				
				try {
					client.readInTask(client.getHttpClient(), typeOfRequest);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TimeoutException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}
}
