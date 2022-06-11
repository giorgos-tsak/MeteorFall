import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import javax.swing.JLabel;
import javax.swing.JPanel;


public class LeaderBoardPanel extends JPanel{

	static final int Width=600,Height=600;
	private JLabel leaderboardLabel = new JLabel("Leaderboard");
	HashMap<String, Account> accounts = new HashMap<String, Account>();
	private ImageButton backButton = new ImageButton("res\\backButton.png");
	
	LeaderBoardPanel()
	{
		leaderboardLabel.setBounds(Width/2-100,10,200,35);
		leaderboardLabel.setFont(new Font(null,Font.BOLD,30));
		
		backButton.setBounds(0,0,50,50);
		
	
		try {
			FileInputStream fileInputStream = new FileInputStream("res\\test.ser");
			ObjectInputStream in = new ObjectInputStream(fileInputStream);
			accounts = (HashMap<String, Account>)in.readObject();
			
			in.close();
			fileInputStream.close();
		} catch (IOException | ClassNotFoundException e) {}
		
		ArrayList<Account> sortedAccounts = new ArrayList<>();
		for (Map.Entry<String, Account> entry : accounts.entrySet()) {
			
			Account acc = entry.getValue();
			sortedAccounts.add(acc);

		}
		Collections.sort(sortedAccounts);
		Collections.reverse(sortedAccounts);
		for (int i = 0; i < sortedAccounts.size(); i++) {
			if(i>=10)
			{
				sortedAccounts.remove(i);
			}
			
		}
		int posy=100;
		int count=1;
		for (Account account : sortedAccounts) {
			
			JLabel usernameLabel = new JLabel(count+". "+account.getUsername());
			JLabel scoreLabel = new JLabel(""+account.getBestScore());
			usernameLabel.setBounds(Width/2-130,posy,200,25);
			usernameLabel.setFont(new Font(null,Font.BOLD,25));
			scoreLabel.setBounds(usernameLabel.getX()+180,posy,100,25);
			scoreLabel.setFont(new Font(null,Font.BOLD,25));
//			scoreLabel.setBorder(BorderFactory.createLineBorder(Color.black));
			posy+=50;
			count++;
			this.add(usernameLabel);
			this.add(scoreLabel);
			
		}
		
		this.setLayout(null);
		this.setPreferredSize(new Dimension(Width,Height));
		this.setBackground(Color.pink);
		this.add(leaderboardLabel);
		this.add(backButton);
		
	}
	
	public ImageButton getBackButton() {
		return backButton;
	}
}
