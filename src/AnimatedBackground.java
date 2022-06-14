import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

public class AnimatedBackground extends GameObject{

	private ArrayList<AnimatedBackground> backgrounds = new ArrayList<>();
	AnimatedBackground()
	{
		speed = 5;
		width=GamePanel.Width;
		height=GamePanel.Height;
		image = new ImageIcon("res\\background.png").getImage();	
	}
	
	Iterator<AnimatedBackground> iterator = backgrounds.iterator();
	@Override
	public void update() {
		if (backgrounds.size()==0) {
			
			backgrounds.add(new AnimatedBackground());
			backgrounds.add(new AnimatedBackground());
			backgrounds.get(0).y=0;
			backgrounds.get(1).y=-GamePanel.Height;
		}
		
		for (Iterator iterator = backgrounds.iterator(); iterator.hasNext();) {
			AnimatedBackground animatedBackground = (AnimatedBackground) iterator.next();
			animatedBackground.move();
			if(animatedBackground.y>=GamePanel.Height)
			{
				animatedBackground.y=-GamePanel.Height;
			}
			
		}
	}

	@Override
	public void move() {
		y+=speed;
	}
	public ArrayList<AnimatedBackground> getBackgrounds() {
		return backgrounds;
	}
	
}
