import java.awt.*;
import javax.swing.*;

public class RecorderScreen extends JPanel{
	/*Functionality:
	 * 1) Click at selected region at random set intervals
	 * Random Interval: be allowed to set the time (in ms, sec, min etc)
	 * 2) Maybe randomly move mouse 
	 * 3) Be able to record mouse (where mouse is moving, clicking etc) and added pauses
	 * 
	 */
	private int width, height;
	
	public RecorderScreen(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
}
