import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;

public class MouseRecorderSection extends InnerPanel{
	private JLabel label;
	private JButton selectRegionButton;
	private static PropDim proportions = new PropDim(.3,1);
	
	
	public MouseRecorderSection(Dimension parent_d) {
		super(parent_d, proportions);
		
		
		
		label = new JLabel();
		label.setText("Mouse Interval:");
		
		this.add(label, BorderLayout.PAGE_START);
		this.setBackground(Color.green);
	}


	@Override
	public void Show() {
		this.label.setVisible(true);
		this.setVisible(true);
		
		
	}
	
	

}
