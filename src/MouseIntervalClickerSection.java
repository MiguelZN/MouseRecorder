import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MouseIntervalClickerSection extends InnerPanel{
	private JLabel label;
	private static PropDim proportions = new PropDim(.3,1);
	
	
	/*Select Region JPanel*/
	private JPanel selectRegion;
	private PropDim selectRegionProportions = new PropDim(.9,.8);
	private Dimension selectRegionDimension;
	
	private JButton selectRegionButton;
	
	
	public MouseIntervalClickerSection(Dimension parent_d) {
		super(parent_d, proportions);
		this.setBackground(Color.red);
		
		
		/*Components*/
		label = new JLabel();
		label.setText("Mouse Interval:");
		
		
		/*Select Region*/
		selectRegion = new JPanel();
		selectRegionDimension = PropDim.calcDimension(this.getDimension(), selectRegionProportions);
		selectRegion.setBackground(Color.magenta);
		selectRegion.setPreferredSize(selectRegionDimension);
		GridBagLayout gl = new GridBagLayout(); //as many rows as needed, 2 columns
		
		/*Example of GridBagLayout (allows you to resize elements)
		 * for(int i =0;i<5;i++) {
		 
			JButton b = new JButton("B"+i);
			b.setPreferredSize(new Dimension(100,60));
			//b.setSize(new Dimension(50,10));
			selectRegion.add(b);
			
			JTextField jft = new JTextField("Text");
			jft.setPreferredSize(new Dimension(100,50));
			//jft.setSize(new Dimension(90,30));
			selectRegion.add(jft);
		}
	*/
	
		
		/*Adding Components to master layout*/
		this.add(label, BorderLayout.NORTH);
		this.add(selectRegion, BorderLayout.CENTER);
	}


	@Override
	public void Show() {
		this.label.setVisible(true);
		this.selectRegion.setVisible(true);
		this.setVisible(true);
		this.validate();
		
		
	}

}
