import javax.swing.ImageIcon;

public class HealPowerup extends Powerup{
	
	private int healValue = 10;
	
	
	HealPowerup()
	{
		image = new ImageIcon("res\\heal.png").getImage();
	}


	@Override
	public void applyEffect(Player player) {
		if(this.collides(player))
		{
			if(player.getHealth()<100)
			{
				player.setHealth(player.getHealth()+healValue);
				if(player.getHealth()>100)
				{
					player.setHealth(100);
				}	
			}	
		}
		
	}

}
