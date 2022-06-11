import java.util.Random;

public class Powerup extends GameObject{

	
	private Random random = new Random();
	private static double spawnTime;
	private Player player;
	
	Powerup()
	{
		width = 50;
		height = 50;
		speed = 5;
		x = random.nextInt(GamePanel.Width/width)*width;
		y=-height;
		spawnTime = 0.5;
	}

	
	
	public void update() {
		this.move();
		this.applyEffect(player);
		
	}
	
	private static long powerupTime = System.currentTimeMillis();
	public static Powerup generatePowerup() {
		long currentTime = System.currentTimeMillis();
		double elapsedTime = (currentTime-powerupTime)/1000.0;
		if(elapsedTime>=spawnTime)
		{

			powerupTime = System.currentTimeMillis();
			Random randomPowerup = new  Random();
			int x = randomPowerup.nextInt(3);
			x=2;
			Powerup powerup = null;
			if(x==0)
			{
				powerup = new HealPowerup();
			}
			if(x==1)
			{
				powerup = new PiercePowerup();
			}
			if(x==2)
			{
				powerup = new ExperiencePowerup();
			}
			return powerup;
		}
		
		
		
		return null;
	}
	
	public void applyEffect(Player player)
	{}
	




	@Override
	public void move() {
		y+=speed;
		
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
