import javax.swing.ImageIcon;

public class PiercePowerup extends Powerup{

	private double pierceDuration = 3;
	
	PiercePowerup()
	{
		image = new ImageIcon("res\\pierce.png").getImage();
	}
	
	
	private static long pierceTime;
	@Override
	public void applyEffect(Player player) {
		long currentTime = System.currentTimeMillis();
		if(this.collides(player))
		{
			
			pierceTime = System.currentTimeMillis();
			player.setPenetration(true);
		}
		double elapsedTime = (currentTime-pierceTime)/1000.0;
		if(elapsedTime>=pierceDuration)
		{
			player.setPenetration(false);
		}

	}
	
	
	
}
