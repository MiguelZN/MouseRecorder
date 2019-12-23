import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class Main {
	static int i=0;
	final static int TIME_SLEEP = 100;
	
	public static void main(String args[]) {
		//Creates and starts the JFrame, and GUI 
		ScreenFrame MouseRecorder = new ScreenFrame();
		
		
		//Thread Loop
		Thread run = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					
					try {
						Thread.sleep(TIME_SLEEP); //10 ms
						//System.out.println();
						
						
						MouseRecorder.updateMousePositionDisplay();
						
						GlassFrame CurrentTransparentFrame= MouseRecorder.getMainScreen().getMouseIntervalSection().getTransparentFrame();
				
						//Checks if there is a GlassFrame (when user selects option to select mouse region where mouse will click randomly)
						if(CurrentTransparentFrame!=null) {
							//System.out.println("GLASS FRAME OPENED");
							CurrentTransparentFrame.validate();
							CurrentTransparentFrame.repaint();
							CurrentTransparentFrame.setVisible(true);
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					i++;
				}
			}
			
		});
		
		run.run();
	}
}
