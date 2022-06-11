import javax.swing.ImageIcon;

public class TrackingMeteor extends Meteor{

	
	TrackingMeteor()
	{
		image = new ImageIcon("res\\trackingMeteor.png").getImage();
		
		speed = (int)(super.speed*1.5);
		setHealth((int)super.getHealth()/2);
	}
	
	
	
	@Override
	public void applyEffect(Player player) {
		if(this.y<player.y+player.height)
		{			
			if(this.getCenter()>player.getCenter())
			{
				x-=speed;
			}
			if(this.getCenter()<player.getCenter())
			{
				x+=speed;
			}
		}
	}
	
}
