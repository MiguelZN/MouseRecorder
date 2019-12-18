import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;

public class MouseIntervalClickerSection extends InnerPanel{
	private JLabel label;
	private JButton selectRegionButton;
	
	
	public MouseIntervalClickerSection(Dimension parent_d, PropDim proportions) {
		super(parent_d, proportions);
		
		
		label = new JLabel();
		label.setText("Mouse Interval:");
		
		this.add(label, BorderLayout.PAGE_START);
		this.setBackground(Color.red);
		
	}


	@Override
	public void Show() {
		this.label.setVisible(true);
		this.setVisible(true);
		this.validate();
		
	}

}
