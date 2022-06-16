import javax.swing.ImageIcon;

public class PiercePowerup extends Powerup{

	static double pierceDuration = 5;
	
	PiercePowerup()
	{
		image = new ImageIcon("res\\pierce.png").getImage();
	}
	
	
	private static long pierceTime=System.currentTimeMillis();
	@Override
	public void applyEffect(Player player) {
		
//		long currentTime = System.currentTimeMillis();
//		if(this.collides(player))
//		{
//			pierceTime = System.currentTimeMillis();
//			player.setPenetration(true);
//		}
//		double elapsedTime = (currentTime-pierceTime)/1000.0;
//		System.out.println(elapsedTime);
//		if(elapsedTime>=pierceDuration)
//		{
//			player.setPenetration(false);
//		}
		if(this.collides(player))
		{
			player.setPenetration(true);
			player.setPenetrationTime(System.currentTimeMillis());
		}

	}
	
	
	
}
