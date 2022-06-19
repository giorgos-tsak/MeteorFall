import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//Κλάση για την δημιουργία κουμπιών με εικόνες
public class ImageButton extends JButton{


	private Image image;
	
	
	ImageButton(String path)
	{
		image = new ImageIcon(path).getImage();

		
		setContentAreaFilled(false);
		setFocusable(false);
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
	}
	
	
}
