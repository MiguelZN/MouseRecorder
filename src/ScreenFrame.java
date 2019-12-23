import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

/*Super class reference, subclass behavior*/

/*The base JFrame for the application*/
public class ScreenFrame extends JFrame{
	final int SCREEN_WIDTH = 750;
	final int SCREEN_HEIGHT = 500;
	final static String TITLE = "Mouse Recorder by Miguel Zavala";
	
	private Dimension d; //Size of the JFrame
	
	/*Screens:*/
	RecorderScreen MainScreen;
	
	
	
	public ScreenFrame() {
		super(TITLE);
		this.initial(); //JFrame initial settings
		this.RecorderScreenInitial(); //RecorderSceen intial settings
		
		this.setVisible(true);
		this.validate();
	}
	
	public void initial() {
		this.setSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.d = new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT);
	}
	
	/*Sets up the RecorderScreen such as adding it to the JFrame etc*/
	public void RecorderScreenInitial() {
		MainScreen = new RecorderScreen(d,new PropDim(1,1));
		Container cntr = this.getContentPane();
		BorderLayout bl = new BorderLayout();
		cntr.setLayout(bl);
		cntr.add(MainScreen, BorderLayout.PAGE_START);
		
	}
	
	/**@author Miguel
	 * @return Point
	 * -Returns the Actual Position of the Mouse in regards to the usable area
	 * that the mouse can click on (removing the upper tab portion)
	 */
	public static Point getCurrentMousePositionUsableArea() {
		Point absolute_posn = getCurrentMousePosnAbsolute();
		Point new_usable_posn = new Point(absolute_posn.x, absolute_posn.y-getScreenVerticalOffset());	
		return new_usable_posn;
	}
	
	/**@author Miguel
	 * @return Dimension
	 * -Returns the Mouse's Current Position as a Dimension Object
	 */
	public static Point getCurrentMousePosnAbsolute() {
		int xposn = (int)MouseInfo.getPointerInfo().getLocation().getX();
		int yposn = (int)MouseInfo.getPointerInfo().getLocation().getY();
		
		Point mouse_posn = new Point(xposn,yposn);
		return (Point) mouse_posn;
	}
	
	/**@author Miguel
	 * @return Dimension
	 * -Returns the Mouse's Current Position as a Dimension Object
	 */
	public static Point getCurrentMousePosnRelative(MouseEvent e) {
		int xposn = e.getX();
		int yposn = e.getY();
		
		Point mouse_posn = new Point(xposn,yposn);
		return (Point) mouse_posn;
	}
	
	public void updateMousePositionDisplay() {
		Point mouseLocation = this.getCurrentMousePositionUsableArea();
				//this.getCurrentMousePosnAbsolute();
		//Current Mouse Position:
		int current_x = (int)mouseLocation.getX();
		int current_y = (int)mouseLocation.getY();
		this.getMainScreen().getMouseIntervalSection().getCurrentMousePositionLabel().setText(MouseIntervalClickerSection.CurrentMousePositionString+"("+current_x+","+current_y+")");
	}
	
	/**@author Miguel
	 * @return int
	 * 
	 * -MACOS ISSUE:
	 * this returns the screen offset in regards to the size of
	 * the upper tab portion of the screen (where it is unclickable; contains Apple logo,
	 * File, Edit, Source, etc tabs on MACS)
	 * 
	 */
	public static int getScreenVerticalOffset() {
		return (int)(getScreenResolution().getHeight()-getMaximumUsableArea().getHeight());
	}
	
	/**@author Miguel
	 * @return Dimension
	 * -Returns the Dimension size of the screen
	 */
	public static Dimension getScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize;
	}
	
	/**@author Miguel
	 * @return Dimension
	 * -Returns the maxiumum usable area (minus the taskbars, menu bars EX: MACOS The topmost tab containing
	 * Apple Logo, File, Edit, Source, Refactor, etc)
	 */
	public static Dimension getMaximumUsableArea() {
		Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		
		return rect.getSize();
	}
	

	public RecorderScreen getMainScreen() {
		return MainScreen;
	}

	public int getWidth() {
		return this.SCREEN_WIDTH;
	}

	public int getHeight() {
		return this.SCREEN_HEIGHT;
	}

	
}
