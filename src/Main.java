import java.awt.Dimension;


public class Main {
	static int i=0;
	
	public static void main(String args[]) {
		//System.out.println("Hello");
		
		ScreenFrame MouseRecorder = new ScreenFrame();
		
		//Thread Loop
		Thread run = new Thread(new Runnable() {

			@Override
			public void run() {
				while(i<10000) {
					
					try {
						Thread.sleep(10); //10 ms
						System.out.println(i);
						
						GlassFrame CurrentTransparentFrame= MouseRecorder.getMainScreen().getMouseIntervalSection().getTransparentFrame();
						
						if(CurrentTransparentFrame!=null) {
							System.out.println("GLASS FRAME OPENED");
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
