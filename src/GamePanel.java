import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{

	static final int Width=600,Height=800;
	private GameThread gameThread = new GameThread();
	private Player player = new Player();
	private ArrayList<Meteor> meteors = new ArrayList<>();
	private ArrayList<Powerup> powerups = new ArrayList<>();
	private JLabel scoreJLabel = new JLabel();
	private JLabel bestScoreLabel = new JLabel();	
	private ImageButton pauseButton = new ImageButton("res\\pause.png");
	private ImageButton restartButton = new ImageButton("res\\restart.png");
	private OptionsPanel optionsPanel;
	private AnimatedBackground animatedBackground = new AnimatedBackground();
	private JLabel gameoverLabel = new JLabel("Game Over");
	
	GamePanel(Player player)
	{
		
		this.player = player;

		pauseButton.setBounds(Width-50,0,50,50);
		pauseButton.setFocusable(false);
		
		restartButton.setBounds(Width-50,0,50,50);
		restartButton.setFocusable(false);
		restartButton.addActionListener(this);
		restartButton.setVisible(false);
		
		scoreJLabel.setForeground(Color.green);
		scoreJLabel.setFont(new Font(null,Font.BOLD,25));
		scoreJLabel.setBounds(0,0,150,20);
		
		bestScoreLabel.setForeground(Color.green);
		bestScoreLabel.setFont(new Font(null,Font.BOLD,20));
		bestScoreLabel.setBounds(0,50,200,20);
		 
		gameoverLabel.setForeground(Color.green);
		gameoverLabel.setFont(new Font(null,Font.BOLD,50));
		gameoverLabel.setBounds(Width/2-110,Height/2-200,270,40);
		gameoverLabel.setVisible(false);
		
		
		this.setFocusable(true);
		this.addKeyListener(player.getMyKeyListener());
		this.setPreferredSize(new Dimension(Width,Height));
		this.setLayout(null);
		this.setBackground(Color.black);
		this.add(player.getHealthBar());
		this.add(player.getExperienceBar());
		this.add(scoreJLabel);
		this.add(pauseButton);
		this.add(restartButton);
		this.add(bestScoreLabel);
		this.add(gameoverLabel);
	}
	boolean pause = false;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(optionsPanel.getPlayButton()))
		{
			player.stop();
			gameThread = new GameThread();
			gameThread.start();
			pause = false;
		}
		if(e.getSource().equals(restartButton))
		{
			gameoverLabel.setVisible(false);
			restartButton.setVisible(false);
			pauseButton.setVisible(true);
			player.setHealth(player.getBaseHealth());
			player.getHealthBar().setMaximum(player.getBaseHealth());
			player.setDamage(player.getBaseDamage());
			player.setAttackSpeed(player.getBaseAttackSpeed());
			player.setX(Width/2);
			player.setY(500);
			player.setFrozen(false);
			player.setPenetration(false);
			player.setScore(0);
			player.setExperiencePoints(0);
			player.setLevelPoints(1);
			player.setUpgradePoints(1);
			player.setBullets(new ArrayList<>());
			meteors = new ArrayList<>();
			powerups = new ArrayList<>();
			StatsPanel.health=0;
			StatsPanel.damage=0;
			StatsPanel.attackSpeed=0;
			gameThread = new GameThread();
			gameThread.start();
		}
		 
	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		for (AnimatedBackground animatedBackground : animatedBackground.getBackgrounds()) {
			animatedBackground.paint(g);
		}
		player.paint(g);
		for (ArrayList<Bullet> arrayList: player.getBullets()) {
			for (Bullet bullet : arrayList) {
				bullet.paint(g);
			}
		}
		for (Meteor meteor : meteors) {
			meteor.paint(g);
		}
		for (Powerup powerup : powerups) {
			powerup.paint(g);
		}
	}
		
	
	
	
	private Meteor tempMeteor = new Meteor();
	private Powerup tempPowerup=new Powerup();
	class GameThread extends Thread
	{
		public void run() {
			while(!pause && player.isAlive())
			{
				
				animatedBackground.update();
				scoreJLabel.setText("Score:"+player.getScore());
				bestScoreLabel.setText("Best:"+player.getAccount().getBestScore());
				
				tempPowerup = Powerup.generatePowerup();
				if(tempPowerup!=null)
				{
					tempPowerup.setPlayer(player);
					powerups.add(tempPowerup);
				}
				Iterator<Powerup> iterator = powerups.iterator();
				while (iterator.hasNext()) {
					Powerup powerup = (Powerup) iterator.next();
					powerup.update();
					if(powerup.collides(player) || powerup.y>GamePanel.Height)
					{
						iterator.remove();
					}
				}
				
				tempMeteor = Meteor.generateMeteor();
				if(tempMeteor!=null)
				{
					tempMeteor.setPlayer(player);
					meteors.add(tempMeteor);
				}
				Iterator<Meteor> iterator2 = meteors.iterator();
				while (iterator2.hasNext()) {
					Meteor meteor = (Meteor) iterator2.next();
					meteor.update();
					if(meteor.y>GamePanel.Height || meteor.getHealth()<=0 || meteor.collides(player))
					{
						iterator2.remove();	
					}
				}
				
				player.setMeteors(meteors);
				player.update();
				

				repaint();				
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {e.printStackTrace();}	
			}
			if(!player.isAlive())
			{
				restartButton.setVisible(true);
				pauseButton.setVisible(false);
				gameoverLabel.setVisible(true);
			}
		}
	}
	public GameThread getGameThread() {
		return gameThread;
	}
	public Player getPlayer() {
		return player;
	}	
	public void setOptionsPanel(OptionsPanel optionsPanel) {
		this.optionsPanel = optionsPanel;
		this.optionsPanel.getPlayButton().addActionListener(this);
	}
	public JButton getPauseButton() {
		return pauseButton;
	}
	public JButton getRestartButton() {
		return restartButton;
	}
}
