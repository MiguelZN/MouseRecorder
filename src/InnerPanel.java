import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class InnerPanel extends JPanel{
	private Dimension parent_d; //Size of parent JPanel/JFrame
	private Dimension d; //Size of current JPanel
	private PropDim proportions; //proportion in respect to parent container size
	
	public InnerPanel(Dimension parent_d) {
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
	
	/**@author Miguel
	 * @param parent_d
	 * @param vertical_prop
	 * @return JLabel 
	 * 
	 * -Takes in a parent Dimension(width, height) and a vertical proportion
	 * in order to create a blank invisible JLabel which acts as an empty row
	 * within a JPanel. This creates spacing between JComponents
	 */
	public static JPanel createRow(Dimension parent_d, double vertical_prop) {
		JPanel row_space = new JPanel();
		PropDim pd = new PropDim(1,vertical_prop);
		Dimension size = PropDim.calcDimension(parent_d, pd);
		row_space.setPreferredSize(size);
		row_space.setSize(size);
		row_space.setOpaque(false);
		return row_space;
	}
	
	/**@author Miguel
	 * @param parent_d
	 * @param vertical_prop
	 * @return JLabel 
	 * 
	 * -Takes in a parent Dimension(width, height) and a vertical proportion
	 * in order to create a blank invisible JLabel which acts as an empty row
	 * within a JPanel. This creates spacing between JComponents
	 */
	public static JPanel createRow(Dimension parent_d) {
		double vertical_prop = .025; //2.5% of vertical spacing in terms of parent height
		JPanel row_space = new JPanel();
		PropDim pd = new PropDim(1,vertical_prop);
		Dimension size = PropDim.calcDimension(parent_d, pd);
		row_space.setPreferredSize(size);
		row_space.setSize(size);
		row_space.setOpaque(false);
		return row_space;
	}
	
	/**@author Miguel
	 * @param parent_d
	 * @param vertical_prop
	 * @return JLabel 
	 * 
	 * -Takes in a parent Dimension(width, height) and a vertical proportion
	 * in order to create a blank invisible JLabel which acts as an empty row
	 * within a JPanel. This creates spacing between JComponents
	 */
	public static JPanel createRow(Dimension parent_d, boolean opaque) {
		double vertical_prop = .025; //2.5% of vertical spacing in terms of parent height
		JPanel row_space = new JPanel();
		PropDim pd = new PropDim(1,vertical_prop);
		Dimension size = PropDim.calcDimension(parent_d, pd);
		row_space.setPreferredSize(size);
		row_space.setSize(size);
		row_space.setOpaque(opaque);
		return row_space;
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
