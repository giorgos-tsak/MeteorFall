import javax.swing.ImageIcon;
//Μετεωρίτης που παγώνει τον παίκτη και δεν τον αφήνει να κινηθεί ή να πυροβολήσει
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
