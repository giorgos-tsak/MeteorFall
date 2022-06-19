import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginPanel extends JPanel {

	static final int Width=600,Height=600;
	private JTextField usernameField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel usernameLabel = new JLabel("Username");
	private JLabel passwordLabel = new JLabel("Password");
	private JLabel loginLabel = new JLabel("Login");
	private JButton loginButton = new JButton("Login");
	private JButton signupButton = new JButton("Signup");
	


	private Account account = new Account();
	private Image image = new ImageIcon("res\\meteorBackground.png").getImage();
	
	LoginPanel()	
	{
	
		this.setPreferredSize(new Dimension(Width,Height));
		this.setLayout(null);
		this.add(usernameField);
		this.add(passwordField);
		this.add(usernameLabel);
		this.add(passwordLabel);
		this.add(loginLabel);
		this.add(loginButton);
		this.add(signupButton);

	}

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(image,0,0,null);
		

		
		loginLabel.setBounds(Width/2-85/2,50,85,40);
		loginLabel.setFont(new Font(null,Font.BOLD,30));
		loginLabel.setForeground(Color.white);
		
		usernameField.setBounds(Width/2-200/2,150,200,30);
		usernameField.setBorder(BorderFactory.createLineBorder(Color.black));
		usernameField.setFont(new Font(null, Font.PLAIN, 20));
		
		
		usernameLabel.setBounds(usernameField.getX(),usernameField.getY()-25,120,20);
		usernameLabel.setFont(new Font(null,Font.BOLD,20));
		usernameLabel.setForeground(Color.white);
		
		passwordField.setBounds(Width/2-200/2,225,200,30);
		passwordField.setBorder(BorderFactory.createLineBorder(Color.black));
		passwordField.setFont(new Font(null, Font.PLAIN, 20));
		
		
		
		passwordLabel.setBounds(passwordField.getX(),passwordField.getY()-25,120,20);
		passwordLabel.setFont(new Font(null,Font.BOLD,20));
		passwordLabel.setForeground(Color.white);
		
		loginButton.setFocusable(false);
		loginButton.setBorderPainted(true);
		loginButton.setBounds(usernameField.getX()+usernameField.getWidth()/2-75,275,150,30);
		loginButton.setFont(new Font(null,Font.BOLD,20));
		loginButton.setOpaque(false);
		loginButton.setBackground(new Color(0, 0, 0, 255));
		loginButton.setForeground(Color.white);	
		
		signupButton.setFocusable(false);
		signupButton.setBorderPainted(true);
		signupButton.setBounds(Width-100,400,100,30);
		signupButton.setFont(new Font(null,Font.BOLD,20));
		signupButton.setOpaque(false);
		signupButton.setBackground(new Color(0, 0, 0, 255));
		signupButton.setForeground(Color.white);
	}
	

	
	public boolean login() {
		String username,password;
		username = usernameField.getText().trim();
		password = new String (passwordField.getPassword());
		try {
			FileInputStream filein = new FileInputStream("res\\accounts.ser");
			if(filein.available()!=0)
			{
				
				ObjectInputStream in = new ObjectInputStream(filein);
				HashMap<String, Account> accounts = (HashMap<String, Account>)in.readObject();
				for (Map.Entry<String,Account> acc : accounts.entrySet()) {
					account = acc.getValue();
					if(username.equals(account.getUsername()) && password.equals(account.getPassword()))
					{
						return true;
					}
				}
				in.close();
				filein.close();
			}
			
			
			
			
		} catch (FileNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();} catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return false;
	}
	
	
	public Account getAccount() {
		return account;
	}
	public JButton getLoginButton() {
		return loginButton;
	}
	public JButton getSignupButton() {
		return signupButton;
	}

	
	
}
