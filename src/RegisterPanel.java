import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterPanel extends JPanel{

	static final int Height=600,Width=600;
	
	private JLabel registerLabel = new JLabel("Sign up");
	private JLabel usernameLabel = new JLabel("Username");
	private JTextField usernameField = new JTextField();
	private JLabel passwordLabel = new JLabel("Password");
	private JPasswordField passwordField = new JPasswordField();
	private JPasswordField confirmPasswordField = new JPasswordField();
	private JLabel confirmPasswordLabel = new JLabel("Confirm password");
	private Image image = new ImageIcon("res\\meteorBackground.png").getImage();
	private JButton signButton = new JButton("Sign up");
	private ImageButton backButton = new ImageButton("res\\backButton.png");
	private Account account = new Account();
	
	RegisterPanel()
	{
		
		
		
		this.setPreferredSize(new Dimension(Width,Height));
		this.setLayout(null);
		this.add(registerLabel);
		this.add(usernameField);
		this.add(usernameLabel);
		this.add(passwordField);
		this.add(passwordLabel);
		this.add(confirmPasswordField);
		this.add(confirmPasswordLabel);
		this.add(signButton);
		this.add(backButton);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(image,0,0,null);
		
		registerLabel.setBounds(Width/2-55,50,110,40);
		registerLabel.setFont(new Font(null,Font.BOLD,30));
		registerLabel.setForeground(Color.white);
		
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
		
		confirmPasswordField.setBounds(Width/2-200/2,300,200,30);
		confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.black));
		confirmPasswordField.setFont(new Font(null, Font.PLAIN, 20));
		
		confirmPasswordLabel.setBounds(passwordField.getX(),confirmPasswordField.getY()-25,180,20);
		confirmPasswordLabel.setFont(new Font(null,Font.BOLD,20));
		confirmPasswordLabel.setForeground(Color.white);
		
		signButton.setFocusable(false);
		signButton.setBorderPainted(true);
		signButton.setBounds(usernameField.getX()+usernameField.getWidth()/2-75,350,150,30);
		signButton.setFont(new Font(null,Font.BOLD,20));
		signButton.setOpaque(false);
		signButton.setBackground(new Color(0, 0, 0, 255));
		signButton.setForeground(Color.white);
		
		backButton.setBounds(0,0,50,50);
		
		
	}
	
	
	public boolean signUp() {
		String username,password,confirmPassword;
		username = usernameField.getText();
		password = new String (passwordField.getPassword());
		confirmPassword = new String(confirmPasswordField.getPassword());
		boolean exist = false;
		HashMap<String, Account> accounts = new HashMap<>();
		try {
			
			FileInputStream filein = new FileInputStream("res\\accounts.ser");
			if(filein.available()!=0)
			{
				ObjectInputStream in = new ObjectInputStream(filein);
				accounts = (HashMap<String, Account>)in.readObject();
				
				in.close();
				filein.close();
				for (Map.Entry<String,Account> acc : accounts.entrySet()) {
					Account account = acc.getValue();
					if(username.equals(account.getUsername()))
					{
						
						exist = true;
						JOptionPane.showInternalMessageDialog(null, "username already exists");
					}
				}
			}
			if(!exist && password.equals(confirmPassword))
			{
				
				try {
					
					account.setUsername(username);
					account.setPassword(password);
					accounts.put(username,account);
					FileOutputStream fileout = new FileOutputStream("res\\accounts.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileout);
					out.writeObject(accounts);
					out.close();
					fileout.close();
			
				} catch (IOException e) {}
				return true;
			}
			
			
		} catch (IOException e) {} catch (ClassNotFoundException e) {e.printStackTrace();}
		
		return false;
	}
	
	public JButton getSignButton() {
		return signButton;
	}
	public ImageButton getBackButton() {
		return backButton;
	}
	public Account getAccount() {
		return account;
	}
	
}
