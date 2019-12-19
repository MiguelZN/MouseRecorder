import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class GlassFrame extends JFrame{
	
	public GlassFrame(Dimension d) {
		super();
		
		this.setPreferredSize(d);
		this.setSize(d);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setOpacity(0.1f);
	}
	
	public void paint(Graphics g) {
		
	}
}
