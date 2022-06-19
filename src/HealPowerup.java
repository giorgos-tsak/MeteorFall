import javax.swing.ImageIcon;
//Ενισχυτικό το οποίο θεραπεύει τον παίκτη
public class HealPowerup extends Powerup{
	
	private int healValue = 25;
	
	
	HealPowerup()
	{
		image = new ImageIcon("res\\heal.png").getImage();
	}


	@Override
	public void applyEffect(Player player) {
		if(this.collides(player))
		{
			if(player.getHealth()<player.getMaxHealth())
			{
				player.setHealth(player.getHealth()+healValue);
				if(player.getHealth()>player.getMaxHealth())
				{
					player.setHealth(player.getMaxHealth());
				}	
			}	
		}
		
	}

}
