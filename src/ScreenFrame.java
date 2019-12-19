import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

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
