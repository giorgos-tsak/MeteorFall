import javax.swing.ImageIcon;

public class ExperiencePowerup extends Powerup{
	
	private int experienceValue;
	
	ExperiencePowerup()
	{
		experienceValue = 50;
		image = new ImageIcon("res\\experience.png").getImage();
	}
	

	@Override
	public void applyEffect(Player player) {

		if(this.collides(player) && player.getLevelPoints()<player.getMaxLvL())
		{
			
			player.setExperiencePoints(player.getExperiencePoints()+experienceValue);
		}
		
	}
	
	
}
