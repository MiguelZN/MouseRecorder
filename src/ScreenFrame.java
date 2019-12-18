import java.awt.Dimension;

import javax.swing.*;

public class ScreenFrame extends JFrame{
	private int width, height;
	
	public ScreenFrame(String title, int width, int height) {
		super(title);
		this.width = width;
		this.height = height;
		this.setSize(new Dimension(width, height));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
