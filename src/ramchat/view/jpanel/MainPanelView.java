package ramchat.view.jpanel;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainPanelView extends JPanel{

	String imagePath = "src/resource/main.png";
	ImageIcon icon= new ImageIcon(imagePath);
	
	public MainPanelView() {

		setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawImage(icon.getImage(), 0, 0,getWidth(),getHeight(),null);
		setOpaque(false);
		super.paintComponents(g);
	}

	
}
