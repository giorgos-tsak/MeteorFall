import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;


public class Player extends GameObject {

	
	private int levelPoints;
	private int experiencePoints;
	private int health;
	private int baseHealth = 100;
	private int maxHealth = 100;
	private int damage;
	private int baseDamage=25;
	private double attackSpeed;
	private double baseAttackSpeed=0.3;
	private int score;
	private int upgradePoints;
	private int maxLvL;
	private double immunityDuration;
	private boolean up=false,down=false,left=false,right=false,shoot=false;
	private boolean frozen = false;
	private boolean penetration=false;
	private ArrayList<Meteor> meteors = new ArrayList<>();
	private Account account = new Account();
	private MyKeyListener myKeyListener = new MyKeyListener();
	private ArrayList<ArrayList<Bullet>> bullets = new ArrayList<>();
	private JProgressBar healthBar = new JProgressBar();
	private JProgressBar experienceBar = new JProgressBar();
	
	Player()
	{
		
		image = new ImageIcon("res\\spaceship.png").getImage();
		
		x=GamePanel.Width/2;
		y=GamePanel.Height/2+GamePanel.Height/6;
		health=baseHealth;
		damage = 25;
		attackSpeed=0.3;
		width=100;
		height=100;
		levelPoints=1;
		speed=10;
		immunityDuration=1;
		maxLvL = 30;
		experiencePoints = 0;
		upgradePoints=1;
		healthBar.setValue(health);
		healthBar.setForeground(Color.green);
		healthBar.setBackground(Color.black);
		healthBar.setStringPainted(true);
		healthBar.setString(Integer.toString(health));
		experienceBar.setMaximum(levelPoints*100);
		experienceBar.setForeground(new Color(0,188,255));
		experienceBar.setBackground(Color.black);
		experienceBar.setString(Integer.toString(experiencePoints)+"/"+Integer.toString(levelPoints*100));
		experienceBar.setStringPainted(true);
		
		
	}

	public void load(Account account) {
		this.account = account;
		 
	}
	public void save() {
		try {
			
			HashMap<String, Account> accounts = new HashMap<>();
			FileInputStream filein = new FileInputStream("res\\test.ser");
			if(filein.available()!=0)
			{
				
				ObjectInputStream in = new ObjectInputStream(filein);
				accounts = (HashMap<String, Account>)in.readObject();
				accounts.put(account.getUsername(), account);
				in.close();
				filein.close();
			}
			
				
				try {
					
					FileOutputStream fileout = new FileOutputStream("res\\test.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileout);
					out.writeObject(accounts);
					out.close();
					fileout.close();
				
				} catch (IOException e) {}	
		} catch (IOException e) {} catch (ClassNotFoundException e) {e.printStackTrace();}
		
	}
	
	public void update() {
	
		
		move();
		shoot();
		increaseScore();
		isFrozen();
		Penetration();
		lvlUp();
		if(health<0)
		{
			health = 0;
			save();
			
		}
		
		if(score>account.getBestScore())
		{
			account.setBestScore(score);
			
		}
		healthBar.setValue(health);
		healthBar.setString(Integer.toString(health));
		healthBar.setBounds(this.getCenter()-50,this.y-20,100,15);
		
		experienceBar.setString(Integer.toString(experiencePoints)+" / "+Integer.toString(levelPoints*100));
		experienceBar.setValue(experiencePoints);
		experienceBar.setBounds(this.getCenter()-50,this.y-5,100,10);
		for (Meteor meteor : meteors) {
			if(meteor.getHealth()<=0)
			{
				experiencePoints+=meteor.getExperienceValue();
				score+=meteor.getScoreValue();			
			}
		}
	
		
	}
	
	public void lvlUp() {
		if(experiencePoints>=levelPoints*100 && levelPoints<maxLvL)
		{
			
			if(levelPoints!=30)
			{
				experiencePoints=experiencePoints-levelPoints*100;				
			}
			levelPoints++;
			upgradePoints++;
			this.speed+=0.5;
			experienceBar.setMaximum(levelPoints*100);
		}
		
	}
	
	public void stop() {
		up=false;
		down=false;
		left=false;
		right=false;
		shoot=false;
	}
	
	private ArrayList<Bullet> tempBullets = new ArrayList<>();
	public void shoot() {
		
		Iterator<ArrayList<Bullet>> iterator = bullets.iterator();
		while (iterator.hasNext()) {
			ArrayList<Bullet> arrayList = (ArrayList<Bullet>) iterator.next();
			Iterator<Bullet> iterator2 = arrayList.iterator();
			while (iterator2.hasNext()) {
				Bullet bullet = (Bullet) iterator2.next();
				bullet.update();
				if((bullet.collides(meteors) && !penetration) || bullet.y<0)
				{
					iterator2.remove();
				}
			}
			
		}
		
		if(this.isShooting() && !frozen)
		{
			tempBullets = Bullet.generateBullets(this);	
			if(tempBullets!=null)
			{
				bullets.add(tempBullets);
			}
		}
		
	}
	
	@Override
	public void move() {
		if(!frozen)
		{
			if(up && y>0)
			{
				
				y-=speed;
			}
			if(down && y+height<GamePanel.Height)
			{
				y+=speed;
			}
			if(left && x>0)
			{
				x-=speed;
			}
			if(right && x+width<GamePanel.Width)
			{
				x+=speed;
			}
		}
		
		
	}
	
	private long scoreTime = 0;
	public void increaseScore() {
		long currentTime = System.currentTimeMillis();
		double elapsedTime = (currentTime-scoreTime)/1000.0;
		if(elapsedTime>=0.1)
		{
			score++;
			scoreTime = System.currentTimeMillis();
		}
		
	}
	
	
	private long freezeTime = 0;
	public boolean isFrozen() {
		
		long currenTime = System.currentTimeMillis();	
		double elapsedTime = (currenTime-freezeTime)/1000.0;
		if(elapsedTime>IceMeteor.freezeDuration)
		{
			frozen = false;		
		}
		return frozen;
	}
	
	private long penetrationTime = 0;
	public boolean Penetration() {
		long currentTime = System.currentTimeMillis();
		double elapsedTime = (currentTime-penetrationTime)/1000.0;
		if(elapsedTime>PiercePowerup.pierceDuration)
		{
			penetration=false;
		}
		return penetration;
	}

	public void setPenetrationTime(long penetrationTime) {
		this.penetrationTime = penetrationTime;
	}
	public ArrayList<ArrayList<Bullet>> getBullets() {
		return bullets;
	}
	public void setFreezeTime(long freezeTime) {
		this.freezeTime = freezeTime;
	}
	public boolean isShooting()
	{
		return shoot;
	}
	public boolean isAlive() {
		return health>0;
	}
	public void setMeteors(ArrayList<Meteor> meteors) {
		this.meteors = meteors;
	}
	public int getScore() {
		return score;
	}
	public int getLevelPoints() {
		return levelPoints;
	}
	public JProgressBar getHealthBar() {
		return healthBar;
	}
	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public double getAttackSpeed() {
		return attackSpeed;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getHealth() {
		return health;
	}
	public void setPenetration(boolean penetration) {
		this.penetration = penetration;
	}
	
	public void setLevelPoints(int levelPoints) {
		this.levelPoints = levelPoints;
	}
	public int getDamage() {
		return damage;
	}
	public void setAttackSpeed(double attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public double getImmunityDuration() {
		return immunityDuration;
	}
	public int getMaxLvL() {
		return maxLvL;
	}
	public Account getAccount() {
		return account;
	}
	public MyKeyListener getMyKeyListener() {
		return myKeyListener;
	}
	public int getBaseHealth() {
		return baseHealth;
	}
	public void setBaseHealth(int baseHealth) {
		this.baseHealth = baseHealth;
	}
	public JProgressBar getExperienceBar() {
		return experienceBar;
	}
	public void setBullets(ArrayList<ArrayList<Bullet>> bullets) {
		this.bullets = bullets;
	}
	public int getExperiencePoints() {
		return experiencePoints;
	}
	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}
	public int getUpgradePoints() {
		return upgradePoints;
	}
	public void setUpgradePoints(int upgradePoints) {
		this.upgradePoints = upgradePoints;
	}
	public double getBaseAttackSpeed() {
		return baseAttackSpeed;
	}
	public int getBaseDamage() {
		return baseDamage;
	}
	public void setMaxLvL(int maxLvL) {
		this.maxLvL = maxLvL;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	
	class MyKeyListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_W)
			{
				up = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_S)
			{
				down = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_A)
			{
				left = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_D)
			{
				right = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				shoot = true;
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_W)
			{
				up = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_S)
			{
				down = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_A)
			{
				left = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_D)
			{
				right = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				shoot = false;
			}
			
		}
		
	}

	
}
