import javax.swing.ImageIcon;

public class IceMeteor extends Meteor{

	static double freezeDuration = 1;

	IceMeteor()
	{
		image = new ImageIcon("res\\iceMeteor.png").getImage();
		setExperienceValue(getExperienceValue()*3);
	}
	
	@Override
	public void applyEffect(Player player) {
		if(super.collides(player))
		{
			player.setFrozen(true);
			player.setFreezeTime(System.currentTimeMillis());
			
		}
	}
	

	
	
}
