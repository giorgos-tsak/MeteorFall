import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener{

	Player player = new Player();
	Frame frame;
	LoginPanel loginPanel = new LoginPanel();
	RegisterPanel registerPanel = new RegisterPanel();
	OptionsPanel optionsPanel = new OptionsPanel();
	GamePanel gamePanel = new GamePanel(player);
	StatsPanel statsPanel = new StatsPanel(player);
	LeaderBoardPanel leaderBoardPanel = new LeaderBoardPanel();
	
	MainPanel(Frame frame)
	{
		
		
		gamePanel.setBounds(0, 0, GamePanel.Width, GamePanel.Height);
		loginPanel.setBounds(0,0,LoginPanel.Width,LoginPanel.Height);
		registerPanel.setBounds(0,0,RegisterPanel.Width,RegisterPanel.Height);
		optionsPanel.setBounds(0,0,OptionsPanel.Width,OptionsPanel.Height);
		statsPanel.setBounds(0,0,StatsPanel.Width,StatsPanel.Height);
		leaderBoardPanel.setBounds(0,0,LeaderBoardPanel.Width,LeaderBoardPanel.Height);
		
		loginPanel.getLoginButton().addActionListener(this);
		loginPanel.getSignupButton().addActionListener(this);
		registerPanel.getBackButton().addActionListener(this);
		registerPanel.getSignButton().addActionListener(this);
		optionsPanel.getStatsButton().addActionListener(this);
		optionsPanel.getPlayButton().addActionListener(this);
		optionsPanel.getLeaderBoardButton().addActionListener(this);
		gamePanel.getPauseButton().addActionListener(this);
		gamePanel.getRestartButton().addActionListener(this);
		statsPanel.getBackButton().addActionListener(this);
		leaderBoardPanel.getBackButton().addActionListener(this);
		
		gamePanel.setOptionsPanel(optionsPanel);
		
		this.frame = frame;
		this.setLayout(null);
		this.setFocusable(false);
		this.setPreferredSize(loginPanel.getPreferredSize());
		this.add(gamePanel);
		this.add(loginPanel);
		this.add(registerPanel);
		this.add(optionsPanel);
		this.add(statsPanel);
		this.add(leaderBoardPanel);
		
		registerPanel.setVisible(false);
		gamePanel.setVisible(false);
		statsPanel.setVisible(false);
		optionsPanel.setVisible(false);
		loginPanel.setVisible(true);
		leaderBoardPanel.setVisible(false);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource().equals(loginPanel.getLoginButton()))
		{
			if(loginPanel.login())
			{
				loginPanel.setVisible(false);
				optionsPanel.setVisible(true);
				this.setPreferredSize(optionsPanel.getPreferredSize());
				gamePanel.getPlayer().load(loginPanel.getAccount());
				frame.pack();
				frame.setLocationRelativeTo(null);
			}
			else {
				JOptionPane.showInternalMessageDialog(null, "Account doesn't exist.");
			}
			
		}
		if(e.getSource().equals(loginPanel.getSignupButton()))
		{
			loginPanel.setVisible(false);
			registerPanel.setVisible(true);
			this.setPreferredSize(registerPanel.getPreferredSize());
			frame.pack();
			frame.setLocationRelativeTo(null);
		}
		if(e.getSource().equals(registerPanel.getBackButton()))
		{
			registerPanel.setVisible(false);
			loginPanel.setVisible(true);
			this.setPreferredSize(loginPanel.getPreferredSize());
			frame.pack();
			frame.setLocationRelativeTo(null);
		}
		if(e.getSource().equals(registerPanel.getSignButton()))
		{
			if(registerPanel.signUp())
			{
				
				registerPanel.setVisible(false);
				optionsPanel.setVisible(true);
				gamePanel.getPlayer().load(registerPanel.getAccount());
				this.setPreferredSize(optionsPanel.getPreferredSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
			}
			
		}
		if(e.getSource().equals(gamePanel.getPauseButton()))
		{
			gamePanel.setVisible(false);
			gamePanel.pause = true;
			optionsPanel.setVisible(true);
			this.setPreferredSize(optionsPanel.getPreferredSize());
			frame.pack();
			frame.setLocationRelativeTo(null);
			
			
		}
		if(e.getSource().equals(optionsPanel.getPlayButton()))
		{
			gamePanel.setVisible(true);
			gamePanel.requestFocus();
			optionsPanel.setVisible(false);
			this.setPreferredSize(gamePanel.getPreferredSize());
			frame.pack();
			frame.setLocationRelativeTo(null);
		}
		if(e.getSource().equals(optionsPanel.getStatsButton()))
		{
			statsPanel.setVisible(true);
			optionsPanel.setVisible(false);
			this.setPreferredSize(statsPanel.getPreferredSize());
			frame.pack();
			frame.setLocationRelativeTo(null);
			
		}
		if(e.getSource().equals(optionsPanel.getLeaderBoardButton()))
		{
			optionsPanel.setVisible(false);
			leaderBoardPanel.setVisible(true);
			this.setPreferredSize(leaderBoardPanel.getPreferredSize());
			frame.pack();
			frame.setLocationRelativeTo(null);
		}
		if(e.getSource().equals(statsPanel.getBackButton()))
		{
			statsPanel.setVisible(false);
			optionsPanel.setVisible(true);
			this.setPreferredSize(optionsPanel.getPreferredSize());
			frame.pack();
			frame.setLocationRelativeTo(null);
		}
		if(e.getSource().equals(leaderBoardPanel.getBackButton()))
		{
			leaderBoardPanel.setVisible(false);
			optionsPanel.setVisible(true);
			this.setPreferredSize(optionsPanel.getPreferredSize());
			frame.pack();
			frame.setLocationRelativeTo(null);
		}

		
	}
	
	
}
