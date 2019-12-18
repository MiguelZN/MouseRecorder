import java.awt.*;
import javax.swing.*;

public class RecorderScreen extends InnerPanel{
	/*Functionality:
	 * 1) Click at selected region at random set intervals
	 * Random Interval: be allowed to set the time (in ms, sec, min etc)
	 * 2) Maybe randomly move mouse 
	 * 3) Be able to record mouse (where mouse is moving, clicking etc) and added pauses
	 */
	
	MouseIntervalClickerSection MouseIntervalSection;
	MouseRecorderSection MouseRecorderSection;
	
	public RecorderScreen(Dimension parent_d, PropDim pd) {
		super(parent_d, pd);
		System.out.println("RS:"+this.getDimension());
		
		MouseRecorderSection = new MouseRecorderSection(this.getDimension(),new PropDim(.3,1));
		MouseIntervalSection = new MouseIntervalClickerSection(this.getDimension(),new PropDim(.3,1));
		BorderLayout bl = new BorderLayout();
		
		this.setLayout(bl);
		this.add(MouseRecorderSection, BorderLayout.WEST);
		this.add(MouseIntervalSection, BorderLayout.EAST);

		System.out.println(MouseRecorderSection.getDimension());
		System.out.println(MouseIntervalSection.getDimension());
		
		
	}

	@Override
	public void Show() {
		this.MouseIntervalSection.Show();
		this.MouseRecorderSection.Show();
		
		this.setVisible(true);
		this.validate();
	}
	
	
	
}
