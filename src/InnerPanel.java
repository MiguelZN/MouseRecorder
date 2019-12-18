import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class InnerPanel extends JPanel{
	private Dimension parent_d; //Size of parent JPanel/JFrame
	private Dimension d; //Size of current JPanel
	
	private PropDim proportions;
	
	public InnerPanel(Dimension parent_d, PropDim proportions) {
		super();
		this.parent_d = parent_d;
		this.proportions = proportions;
		this.d = PropDim.calcDimension(parent_d, proportions);
		
		this.setSize(d);
		this.setPreferredSize(d); //BorderLayout checks this
		this.setLocation(0, 0);
		this.setBackground(Color.CYAN);
		this.validate();
	}

	public abstract void Show();

	public Dimension getParentDimension() {
		return this.parent_d;
	}
	
	public Dimension getDimension() {
		return d;
	}

	public int getWidth() {
		return d.width;
	}

	public int getHeight() {
		return d.height;
	}

	public void setWidth(int width) {
		this.d = new Dimension(width, this.getHeight());
	}

	public void setHeight(int height) {
		this.d = new Dimension(this.getWidth(), height);
	}
	
	
}
