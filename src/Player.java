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
	private int health;
	private int damage;
	private double attackSpeed;
	private int baseHealth = 100;
	private int score;
	private int maxLvL;
	private double immunityDuration;
	private boolean up=false,down=false,left=false,right=false,shoot=false;
	private boolean frozen = false;
	private boolean penetration=false;
	private ArrayList<Meteor> meteors = new ArrayList<>();
	private Account account = new Account();
	private MyKeyListener myKeyListener = new MyKeyListener();
	private JProgressBar healthBar = new JProgressBar();
	private ArrayList<ArrayList<Bullet>> bullets = new ArrayList<>();
	
	
	Player()
	{
		
		image = new ImageIcon("res\\spaceship.png").getImage();
		
		x=300;
		y=300;
		health=baseHealth;
		damage = 25;
		attackSpeed=0.3;
		width=100;
		height=100;
		levelPoints=0;
		speed=10.5;
		immunityDuration=1;
		maxLvL = 30;
		healthBar.setValue(health);
		healthBar.setForeground(Color.green);
		healthBar.setBackground(Color.black);
		healthBar.setStringPainted(true);
		healthBar.setString(Integer.toString(health));
		
		
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
		for (Meteor meteor : meteors) {
			if(meteor.getHealth()<=0)
			{
				score+=meteor.getScoreValue();
			}
		}
	
		
	}
	
	public void stop() {
		up=false;
		down=false;
		left=false;
		right=false;
		shoot=false;
	}
	
	private Bullet bullet = new Bullet(this);
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
		
		if(this.isShooting())
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
	

	public ArrayList<ArrayList<Bullet>> getBullets() {
		return bullets;
	}
	public Bullet getBullet() {
		return bullet;
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
	public boolean Penetration() {
		return penetration;
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
