import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;


public class Meteor extends GameObject {
	
	private static ArrayList<Meteor> meteors = new ArrayList<>();
	private static Player player = new Player();
	private int damage;
	private int health;
	private int experienceValue;
	private int scoreValue;
	private static double spawnTime;
	private Random random = new Random();
	private int maxSpeed=30;
	private int maxExperienceValue=35;
	private int maxScoreValue=100;
	
	
	
	Meteor()
	{
		image = new ImageIcon("res\\meteor.png").getImage();
		
		width = 50;
		height = 50;
		
		speed = 5;		
		speed += player.getScore()/100;
		if(speed>=maxSpeed)
		{
			speed = maxSpeed;
		}
		
		experienceValue=10;
		experienceValue+= player.getScore()/50;
		
		if(experienceValue>=maxExperienceValue)
		{
			experienceValue = maxExperienceValue;
		}
		
		scoreValue = 5;
		scoreValue += player.getScore()/100;
		if(scoreValue>=maxScoreValue)
		{
			scoreValue=maxScoreValue;
		}
		
		damage = 15;
		health = 100;
		spawnTime = 0.5;
		x = random.nextInt(GamePanel.Width/width)*width;
		
		y=-height;
		
	}
	
	public void update() {	
		
		this.move();
		this.applyEffect(player);
	}
	
	
	public void applyEffect(Player player) {}
	
	private static long generateTime = 0;
	public static  Meteor generateMeteor() {
		long currentTime = System.currentTimeMillis();
		Random randomMeteor = new Random();
		int x = randomMeteor.nextInt(100);
		double elapsedTime = (currentTime-generateTime)/1000.0;
		if(elapsedTime>spawnTime)
		{
			Meteor meteor;
			if(x>=70 && x<100)
			{
				meteor = new TrackingMeteor();	
			}
			else if(x>=30 && x<=100) {
				meteor = new IceMeteor();
			}
			else {
				meteor = new Meteor();
			}
			generateTime=System.currentTimeMillis();
			return meteor;
		}
		return null;
		
	}
	

	public boolean collides(Player player) {

		if(super.collides(player))
		{

				player.setHealth(player.getHealth()-damage);
				return true;	
		}
		
		return false;
	}
	
	
	
	@Override
	public void move() {
			y+=speed;
	}
	
	
	
	public int getExperienceValue() {
		return experienceValue;
	}
	public ArrayList<Meteor> getMeteors() {
		return meteors;
	}	
	public int getDamage() {
		return damage;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getHealth() {
		return health;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getScoreValue() {
		return scoreValue;
	}
}
