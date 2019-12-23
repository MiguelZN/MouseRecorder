import java.awt.Dimension;
import java.awt.Point;


public class Main {
	static int i=0;
	
	public static void main(String args[]) {
		//System.out.println("Hello");
		
		ScreenFrame MouseRecorder = new ScreenFrame();
		
		//Thread Loop
		Thread run = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					
					try {
						Thread.sleep(100); //10 ms
						System.out.println(i);
						
						GlassFrame CurrentTransparentFrame= MouseRecorder.getMainScreen().getMouseIntervalSection().getTransparentFrame();
						
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
		
//		for(int i =0;i<500;i++) {
//			Point rand = MouseIntervalClickerSection.generateRandomPosition(new Point(10,10), new Point(50,50));
//			if(rand.getX()==49 /*|| rand.getX()==10*/) {
//				//System.exit(0);
//			}
//			System.out.println();
//		}
		run.run();
	}
}
