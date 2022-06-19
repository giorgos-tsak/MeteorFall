import javax.swing.ImageIcon;

public class PiercePowerup extends Powerup{

	static double pierceDuration = 5;
	
	PiercePowerup()
	{
		image = new ImageIcon("res\\pierce.png").getImage();
	}
	
	
	
	@Override
	public void applyEffect(Player player) {
		
		if(this.collides(player))
		{
			player.setPenetration(true);
			player.setPenetrationTime(System.currentTimeMillis());
		}

	}
	
	
	
}
