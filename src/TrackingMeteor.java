import javax.swing.ImageIcon;
//Μετεωρίτης που κατευθύνεται προς το παίκτη
public class TrackingMeteor extends Meteor{

	
	TrackingMeteor()
	{
		image = new ImageIcon("res\\trackingMeteor.png").getImage();
		speed = (int)(super.speed*1.3);
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
