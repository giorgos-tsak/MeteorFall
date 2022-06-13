import javax.swing.JFrame;


public class Frame extends JFrame{
	
	MainPanel mainPanel = new MainPanel(this);
	
	Frame()
	{
		this.add(mainPanel);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
}
