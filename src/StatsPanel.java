import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsPanel extends JPanel implements ActionListener{
	
	private ImageButton healthButton = new ImageButton("res\\plusButton.png");
	private ImageButton damageButton = new ImageButton("res\\plusButton.png");
	private ImageButton attackSpeedButton = new ImageButton("res\\plusButton.png");
	private ImageButton backButton = new ImageButton("res\\backButton.png");
	static final int Width=600,Height=500;
	private JLabel healthLabel = new JLabel("Health: 0");
	private JLabel damageLabel = new JLabel("Damage: 0");
	private JLabel attackSpeedLabel = new JLabel("Atc. Speed: 0");
	private JLabel experiencePointsLabel = new JLabel("Experience points: 0");
	private Player player = new Player();
	static int damage;
	static int attackSpeed;
	static int health;
	
	StatsPanel(Player player)
	{
		this.player = player;	
		
		damageButton.setBounds(Width/2+100, 175, 50, 30);
		damageButton.addActionListener(this);
		damageLabel.setBounds(damageButton.getX()-165,170,180,40);
		damageLabel.setFont(new Font(null,Font.PLAIN,25));
		healthButton.setBounds(Width/2+100, 105, 50, 30);
		healthButton.addActionListener(this);
		healthLabel.setBounds(healthButton.getX()-145,100,180,40);
		healthLabel.setFont(new Font(null,Font.PLAIN,25));
		attackSpeedButton.setBounds(Width/2+100, 245, 50, 30);
		attackSpeedButton.addActionListener(this);
		attackSpeedLabel.setBounds(attackSpeedButton.getX()-195,240,230,40);
		attackSpeedLabel.setFont(new Font(null,Font.PLAIN,25));
		
		this.setLayout(null);
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(Width,Height));
		this.add(healthLabel);
		this.add(damageLabel);
		this.add(attackSpeedLabel);
		this.add(experiencePointsLabel);
		this.add(healthButton);
		this.add(damageButton);
		this.add(attackSpeedButton);
		this.add(backButton);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(player.getImage(),10,100,200,200,null);
		experiencePointsLabel.setText("Experience points: "+player.getUpgradePoints());
		experiencePointsLabel.setBounds(Width/2-145,350,280,40);
		experiencePointsLabel.setFont(new Font(null,Font.PLAIN,25));
		
		damageLabel.setText("Damage: "+damage);
		healthLabel.setText("Health: "+health);
		attackSpeedLabel.setText("Atc. Speed: "+attackSpeed);
		
		backButton.setBounds(0,0,50,50);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(player.getUpgradePoints()!=0)
		{
			if(e.getSource().equals(healthButton) && health!=10)
			{
				increaseHealth();
				player.setUpgradePoints(player.getUpgradePoints()-1);
			}
			if(e.getSource().equals(damageButton) && damage!=10)
			{
				
				increaseDamage();
				player.setUpgradePoints(player.getUpgradePoints()-1);
			}
			if(e.getSource().equals(attackSpeedButton) && attackSpeed!=10)
			{
				increaseAttackSpeed();
				player.setUpgradePoints(player.getUpgradePoints()-1);
			}	
		}
		
	}
	
	public void increaseDamage() {
		
		damage++;
		player.setDamage(player.getDamage()+damage);
		if(damage==10)
		{
			damageLabel.setText("Damage: MAX");
		}
	}
	
	public void increaseHealth() {
			health++;
			player.setMaxHealth(player.getMaxHealth()+10);
			player.getHealthBar().setMaximum(player.getMaxHealth());
		if(health==10)
		{
			healthLabel.setText("Health: MAX");
		}
	}
	public void increaseAttackSpeed() {
			attackSpeed++;
			player.setAttackSpeed(player.getAttackSpeed()-0.02);
		if(attackSpeed==10)
		{
			attackSpeedLabel.setText("Atc. Speed: MAX");
		}
	}
	
	public ImageButton getBackButton() {
		return backButton;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	

}
