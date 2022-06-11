import javax.swing.ImageIcon;

public class ExperiencePowerup extends Powerup{
	
	ExperiencePowerup()
	{
		image = new ImageIcon("res\\experience.png").getImage();
	}
	

	@Override
	public void applyEffect(Player player) {

		if(this.collides(player) && player.getLevelPoints()<player.getMaxLvL()-1)
		{
			player.setLevelPoints(player.getLevelPoints()+1);
		}
		
	}
	
	
}
