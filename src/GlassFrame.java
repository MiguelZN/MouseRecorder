import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GlassFrame extends JFrame{
	private Point Start;
	private Dimension SelectionSize;
	private final Color TRANSPARENT = new Color(255,255,255,15);
	
	public GlassFrame(Dimension d) {
		super();
		this.setPreferredSize(d);
		this.setSize(d);
		this.setUndecorated(true);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setBackground(TRANSPARENT);
		
		this.add(new Canvas(d));
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		this.setVisible(true);
	}

	   class Canvas extends JPanel {
		   	public Canvas(Dimension d) {
		   		super();
		   		this.setPreferredSize(d);
				this.setSize(d);
				this.setBackground(TRANSPARENT);
		   	}
		   
	        public void paintComponent(Graphics g) {
	    		this.setBackground(TRANSPARENT);
	    		g.setColor(Color.BLACK);
	    		
	    		if(Start!=null&&SelectionSize!=null) {
	    			g.drawRect(Start.x, Start.y, SelectionSize.width, SelectionSize.height);
	    			//System.out.printf("CURRENT RECT:x1:%d, y1:%d, width:%d, height:%d\n", rectx, recty, rectwidth, rectheight);
	        
	    		}
	        }
	 }
	
	public void updateRectangle(Point start, Point current) {
		this.Start = start;
		
		int width = current.x-start.x;
		int height = current.y-start.y;
		this.SelectionSize = new Dimension(width,height);
		
		
	}
}
