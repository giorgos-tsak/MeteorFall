import java.util.Random;
import javax.swing.ImageIcon;
//Η κλάση αυτη αναλαμβάνει την δημιουργία μετεωριτών. Οι μετεωρίτες γίνονται δυνατότεροι όσο αυξάνεται το σκορ 
public class Meteor extends GameObject {
	
	private static Player player = new Player();
	private int damage;
	private int health;
	private int maxHealth=700;
	private int experienceValue;
	protected int scoreValue;
	private static double spawnTime;
	private Random random = new Random();
	private int maxSpeed=15;
	private int maxExperienceValue=35;
	private int maxScoreValue=100;
	private double maxSpawnTime=0.2;
	
	Meteor()
	{
		image = new ImageIcon("res\\meteor.png").getImage();
		width = 50;
		height = 50;
		speed = 3;		
		speed += player.getScore()/600;
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
		health+=player.getScore()/100;
		if(health>=maxHealth)
		{
			health=maxHealth;
		}
		spawnTime = 0.5;
		spawnTime-=player.getScore()/10000;
		if(spawnTime<=maxSpawnTime)
		{
			spawnTime=maxSpawnTime;
		}
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
			if(x>=80 && x<=100)
			{
				meteor = new TrackingMeteor();	
			}
			else if(x>=30 && x<80) {
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
		Meteor.player = player;
	}
	public int getScoreValue() {
		return scoreValue;
	}
	public void setExperienceValue(int experienceValue) {
		this.experienceValue = experienceValue;
	}
}
