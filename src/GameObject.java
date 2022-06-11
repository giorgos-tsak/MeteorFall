import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;




public abstract class GameObject {
	
	protected int x,y;
	protected double speed;
	protected int width,height;
	protected Image image;
	
	

	
	
	public abstract void update();
	public abstract void move();
	public void paint(Graphics g)
	{
		g.drawImage(image, x, y, width, height, null);
	}
	
	
	public boolean collides(GameObject gameObject) {
	
		if(this.x<gameObject.x+gameObject.width && this.x+this.width>gameObject.x
		&& this.y<gameObject.y+gameObject.height && this.y+this.height>gameObject.y)
		{
			return true;
		}
		
		return false;
	}
	

	public int getX() {
		return x;
	}
	public int getHeight() {
		return height;
	}
	public Image getImage() {
		return image;
	}
	public double getSpeed() {
		return speed;
	}
	public int getWidth() {
		return width;
	}
	public int getY() {
		return y;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public int getCenter() {
		return this.x+this.width/2;
	}
	
}
