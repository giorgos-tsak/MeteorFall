import javax.swing.ImageIcon;

public class IceMeteor extends Meteor{

	static double freezeDuration = 2;

	IceMeteor()
	{
		image = new ImageIcon("res\\iceMeteor.png").getImage();
	}
	
//	@Override
//	public boolean collides(Player player) {
//		if(super.collides(player))
//		{
//			
//			player.setFrozen(true);
//			player.setFreezeTime(System.currentTimeMillis());
//			return true;
//		}
//		return false;
//
//	}
	
	@Override
	public void applyEffect(Player player) {
		if(super.collides(player))
		{
			player.setFrozen(true);
			player.setFreezeTime(System.currentTimeMillis());
			
		}
	}
	

	
	
}
