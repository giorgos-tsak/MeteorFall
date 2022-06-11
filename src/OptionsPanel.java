import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JPanel;

public class OptionsPanel extends JPanel {

	static final int Width=(int)(GamePanel.Width*0.7),Height=(int)(GamePanel.Height*0.7);
	private JButton playButton = new JButton("Play");
	private JButton statsButton = new JButton("Stats");
	private JButton leaderBoardButton = new JButton("Leaderboard");


	
	OptionsPanel()
	{
		playButton.setBounds(Width-(int)(Width*0.5)-100,Height-(int)(Height*0.9), 200, 40);
		playButton.setFont(new Font(null,Font.BOLD,25));
		playButton.setBorderPainted(false);
		playButton.setFocusable(false);

		
		statsButton.setBounds(Width-(int)(Width*0.5)-100,Height-(int)(Height*0.75), 200, 40);
		statsButton.setFont(new Font(null,Font.BOLD,25));
		statsButton.setBorderPainted(false);
		statsButton.setFocusable(false);
		
		
		leaderBoardButton.setBounds(Width-(int)(Width*0.5)-100,Height-(int)(Height*0.6), 200, 40);
		leaderBoardButton.setFont(new Font(null,Font.BOLD,25));
		leaderBoardButton.setBorderPainted(false);
		leaderBoardButton.setFocusable(false);
		
		
		this.setLayout(null);
		this.setPreferredSize(new Dimension(Width,Height));
		this.setBackground(new Color(10, 10, 10, 200));
		this.add(playButton);
		this.add(statsButton);
		this.add(leaderBoardButton);
	}

	public JButton getPlayButton() {
		return playButton;
	}
	public JButton getStatsButton() {
		return statsButton;
	}
	public JButton getLeaderBoardButton() {
		return leaderBoardButton;
	}

}
