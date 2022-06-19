import java.util.ArrayList;
import javax.swing.ImageIcon;
//Σφαίρες οι οποίες δημιουργούνται όταν ο παίκτης πατήσει space. Όταν ο παίκτης φτάσει επίπεδο 15 και 30 
//αποκτά μία επιπλέον σφαίρα
public class Bullet extends GameObject {
	
	private int damage;
	private ArrayList<Meteor> meteorsHit = new ArrayList<>();
	
	Bullet(Player player)
	{
		image = new ImageIcon("res\\bullet.png").getImage();
		
		width=10;
		height=50;
		speed=20;
		x = player.getCenter()-this.width/2;
		y = player.y;
		damage = player.getDamage();
		
	}
	


	public void update() {
		
		this.move();
		
	}

	private static long bulletTime = 0;
	static double elapsedTime;
	public static ArrayList<Bullet> generateBullets(Player player) {
		
		long currentTime = System.currentTimeMillis();
		elapsedTime = (currentTime-bulletTime)/1000.0;
		ArrayList<Bullet> tempBullets = new ArrayList<>();
		
		if(elapsedTime>=player.getAttackSpeed())
		{	
			bulletTime = System.currentTimeMillis();
			
			for (int i = 0; i < (player.getLevelPoints()/15)+1; i++) {
				Bullet bullet = new Bullet(player);
				bullet.width = bullet.width+player.getLevelPoints();
				bullet.damage = bullet.damage+player.getLevelPoints()/3*5;
				tempBullets.add(bullet);
				if(i==1)
				{
					
					tempBullets.get(i).x = player.getCenter();
					tempBullets.get(i-1).x = player.getCenter()-tempBullets.get(i-1).width;
				}	
				if(i==2)
				{
					tempBullets.get(i-1).x = player.getCenter()+tempBullets.get(i-1).width/2;
					tempBullets.get(i-2).x = player.getCenter()-tempBullets.get(i-2).width-tempBullets.get(i-2).width/2;
					tempBullets.get(i).x = player.getCenter()-tempBullets.get(i).width/2;
				}
				
			}
			
			return tempBullets;
		}
		return null;
		
	}
	
	
	public boolean collides(ArrayList<Meteor> meteors) {
		for (Meteor meteor : meteors) {
			boolean alreadyHit = false;
			if(super.collides(meteor))
			{
				
				for (Meteor meteor2 : meteorsHit) {
					if(meteor.equals(meteor2))
					{
						alreadyHit = true;
					}
				}
				if(!alreadyHit)
				{
					meteor.setHealth(meteor.getHealth()-damage);
					meteorsHit.add(meteor);
				}
				return true;
			}
		}
		return false;
	}
	
	
	
	@Override
	public void move() {
		y-=speed;
		
	}


	
	
}
