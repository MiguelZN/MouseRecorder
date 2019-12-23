import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

/*RIGHT SIDE OF RECORDER SCREEN*/
public class MouseIntervalClickerSection extends InnerPanel{
	private static PropDim proportions = new PropDim(.5,1);
	
	/*Mouse Detection, Mouse Position*/
	private GlassFrame TransparentFrame;
	private Point PressedMouse;
	private Point DraggedMouse;

	public enum CLICKTYPE{
		LEFTCLICK,RIGHTCLICK;
	}
	
	public enum TIMETYPE{
		MS, SECONDS, MINUTES, HOURS;
	}
	
	/*SettingsRegionMouseInterval fields*/
	private ArrayList<JComponent> all_components;
	private GridBagLayout gbl;
	
	/*All components in settings---------------*/
	//Header:
		private final String HEADER_STRING = "Mouse Interval:";
		private JLabel HeaderLabel;
		
	//First Row: Select Click Type 1)Left Click, 2)Right Click
		private JLabel ClickTypeLabel;
		private JComboBox ClickTypeCB;
		final CLICKTYPE[] ClickTypeCBOptions = {CLICKTYPE.LEFTCLICK,CLICKTYPE.RIGHTCLICK};
	
	//Second Row: Select Interval Speed: 
		private JLabel IntervalSpeedLabel;
		private JTextField IntervalSpeedTextField;
		private JComboBox IntervalSpeedCB;
		final TIMETYPE[] IntervalSpeedCBOptions = {TIMETYPE.MS, TIMETYPE.SECONDS, TIMETYPE.MINUTES, TIMETYPE.HOURS};
		
	//Third Row:
		private JCheckBox IntervalSpeedRandomDelayCheckBox;
		private JLabel IntervalSpeedRandomDelayLabel;
		private JTextField IntervalSpeedRandomDelayTextField;
		private JComboBox IntervalSpeedRandomDelayCB;
		final TIMETYPE[] IntervalSpeedRandomDelayCBOptions = {TIMETYPE.MS, TIMETYPE.SECONDS, TIMETYPE.MINUTES, TIMETYPE.HOURS};
		
	//Fourth Row: Select click region or click where the mouse is currently at
		private JLabel selectRegionLabel;
		private ButtonGroup selectRegionOrClickInPlace;
		private JRadioButton selectRegionRadioButton;
		private JRadioButton ClickInPlaceRadioButton;
		
		//Labels Displaying the Mouse's Position
		private JLabel CurrentMousePositionLabel;
		private JLabel SelectedRegionMousePositionLabel;
		
		public enum MOUSEMODE{
			SELECTION, INPLACE;
		}
		
		private JButton StartButton;
	
	/*Proportions for the components within the Mouse Interval Settings*/
	private final PropDim JBUTTON_PROP = new PropDim(.3,.05);
	private final PropDim JLABEL_PROP = new PropDim(.25,.05);
	private final PropDim JENTRY_PROP = new PropDim(.3,.05);
	private final PropDim JTEXTFIELD_PROP = new PropDim(.15,.05);
	private final PropDim JCOMBOBOX_PROP = new PropDim(.3,.05);
	
	
	
	
	/*Constructor*/
	public MouseIntervalClickerSection(Dimension parent_d) {
		super(parent_d, proportions);
		gbl = new GridBagLayout();
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.createMouseIntervalComponents();
		
		this.setBackground(Color.red);
		this.Show();
	}
		
		
	/**@author Miguel
	 * -Uses a forloop and a switch statement to create components for the GUI
	 * -Adds each created JCompoenent into an ArrayList<JComponent> to allow to show them
	 * or hide them, etc or manipulate them
	 */
	
	/*Features:
	 * 1) Select Mouse Click Type
	 * 2) Interval Speed: input how many ms,s,m
	 * 3) Select Click Region (Rectangle area) or click where the mouse is currently
	 * 4) Start Button with key to stop 
	 */
	public void createMouseIntervalComponents() {
			//Header-------------
			HeaderLabel = new JLabel(HEADER_STRING);
		
			this.add(HeaderLabel);
			this.add(InnerPanel.createRow(this.getDimension(),true));
			
			//First Row: Select Mouse Click Type------------------
			ClickTypeLabel = new JLabel("Click Type:");
			ClickTypeLabel.setPreferredSize(PropDim.calcDimension(this.getDimension(), JLABEL_PROP));
			ClickTypeCB = new JComboBox(ClickTypeCBOptions);
			ClickTypeCB.setPreferredSize(PropDim.calcDimension(this.getDimension(), JCOMBOBOX_PROP));
			
			this.add(ClickTypeLabel);
			this.add(ClickTypeCB);
			this.add(InnerPanel.createRow(this.getDimension(),true));
			
			//Second Row: Select Interval Speed------------------
			IntervalSpeedLabel = new JLabel("Interval Speed:");
			IntervalSpeedTextField = new JTextField();
			IntervalSpeedTextField.setPreferredSize(PropDim.calcDimension(this.getDimension(), JTEXTFIELD_PROP));
			IntervalSpeedCB = new JComboBox(IntervalSpeedCBOptions);
			IntervalSpeedCB.setPreferredSize(PropDim.calcDimension(this.getDimension(), JCOMBOBOX_PROP));
			
			this.add(IntervalSpeedLabel);
			this.add(IntervalSpeedTextField);
			this.add(IntervalSpeedCB);
			this.add(InnerPanel.createRow(this.getDimension(),true));
			
			
			
			//Third Row:---------------------
			IntervalSpeedRandomDelayCheckBox = new JCheckBox("Randomized Delay?");
			IntervalSpeedRandomDelayCheckBox.setEnabled(true);

			IntervalSpeedRandomDelayLabel = new JLabel("Delay Time:");
			
			IntervalSpeedRandomDelayTextField = new JTextField();
			IntervalSpeedRandomDelayTextField.setEnabled(false);
			IntervalSpeedRandomDelayTextField.setPreferredSize(PropDim.calcDimension(this.getDimension(), JTEXTFIELD_PROP));
			IntervalSpeedRandomDelayTextField.setBackground(Color.LIGHT_GRAY);
			
			IntervalSpeedRandomDelayCB = new JComboBox(IntervalSpeedRandomDelayCBOptions);
			IntervalSpeedRandomDelayCB.setEnabled(false);
			IntervalSpeedRandomDelayCB.setPreferredSize(PropDim.calcDimension(this.getDimension(), JCOMBOBOX_PROP));
			
			//ActionListeners:
				IntervalSpeedRandomDelayCheckBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean isSelected = IntervalSpeedRandomDelayCheckBox.isSelected();
						
						if(isSelected) {
							IntervalSpeedRandomDelayTextField.setEnabled(true);
							//IntervalSpeedRandomDelayTextField.setEditable(true);
							IntervalSpeedRandomDelayTextField.setBackground(Color.white);
							//IntervalSpeedRandomDelayTextField.setDisabledTextColor(Color.white);
							IntervalSpeedRandomDelayCB.setEnabled(true);
						}
						else {
							IntervalSpeedRandomDelayTextField.setEnabled(false);
							//IntervalSpeedRandomDelayTextField.setEditable(false);
							//IntervalSpeedRandomDelayTextField.setDisabledTextColor(Color.BLACK);
							IntervalSpeedRandomDelayTextField.setBackground(Color.LIGHT_GRAY);
							IntervalSpeedRandomDelayTextField.setText("");
							IntervalSpeedRandomDelayCB.setEnabled(false);
						}
					}
					
				});
				
				
			
			
			this.add(IntervalSpeedRandomDelayCheckBox);
			this.add(InnerPanel.createRow(this.getDimension(),.01));
			this.add(IntervalSpeedRandomDelayLabel);
			this.add(IntervalSpeedRandomDelayTextField);
			this.add(IntervalSpeedRandomDelayCB);
			this.add(InnerPanel.createRow(this.getDimension(),true));
			
			//Fourth Row: Select Click Region (Rectangle Area)---------
			selectRegionLabel = new JLabel("Mouse Mode:");
			
			selectRegionOrClickInPlace = new ButtonGroup();
			selectRegionRadioButton = new JRadioButton(MOUSEMODE.SELECTION.toString());
			ClickInPlaceRadioButton = new JRadioButton(MOUSEMODE.INPLACE.toString());
			StartButton = new JButton("Start");

			
			selectRegionOrClickInPlace.add(selectRegionRadioButton);
			selectRegionOrClickInPlace.add(ClickInPlaceRadioButton);
			
			this.add(selectRegionLabel);
			this.add(InnerPanel.createRow(this.getDimension(),.01));
			this.add(selectRegionRadioButton);
			this.add(ClickInPlaceRadioButton);
			this.add(InnerPanel.createRow(this.getDimension(),.01));
			this.add(StartButton);
			
			
			
			/*Action Listeners*/
			
			/*Start Button should:
			 * 1) Get values of ALL different JComponents
			 * 2) Should error check to make sure the user typed in something for required fields
			 * 3) 
			 */
			StartButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//Retrieving all the input from the JComponents:
					String ClickTypeValue = ClickTypeCB.getSelectedItem().toString();
					String IntervalSpeedInput = IntervalSpeedTextField.getText();
					String IntervalSpeedCBTimeSelection = IntervalSpeedCB.getSelectedItem().toString();
					boolean isRandomizedDelay = IntervalSpeedRandomDelayCheckBox.isSelected();
					String DelayTimeInput = IntervalSpeedRandomDelayTextField.getText();
					String DelayTimeCBSelection = IntervalSpeedRandomDelayCB.getSelectedItem().toString();
					String MouseModeRadioButtonSelection;
					
					//Checks to see which of the two radio buttons is selected
					if(selectRegionRadioButton.isSelected()) {
						MouseModeRadioButtonSelection = selectRegionRadioButton.getText();
					}
					else if(ClickInPlaceRadioButton.isSelected()) {
						MouseModeRadioButtonSelection = ClickInPlaceRadioButton.getText();
					}
					else {
						MouseModeRadioButtonSelection = "";
					}
					
					System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n",ClickTypeValue,
							IntervalSpeedInput,IntervalSpeedCBTimeSelection,isRandomizedDelay,
							DelayTimeInput,DelayTimeCBSelection,MouseModeRadioButtonSelection);
					
					selectRegion();
				}
				
			});
	}
	
	public void selectRegion() {
		Cursor c = Cursor.getDefaultCursor();
		Cursor c2 = new Cursor(Cursor.WAIT_CURSOR);
		
		
		/*Making Screen Transparent*/
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		TransparentFrame = new GlassFrame(screenSize);
		//TransparentFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		
		
		TransparentFrame.addMouseListener(new MouseListener() {
		
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				PressedMouse = ScreenFrame.getCurrentMousePosn(e);
				
				System.out.println("PRESSEDMOUSE:"+PressedMouse);
				System.out.println("MOUSE POSITION MOUSEEVENT:"+"x:"+e.getX()+", y:"+e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//Pressed and held:
				/*This opens up a TransparentFrame where it allows the user
				 * to select the region that they want the mouse to randomly click within
				 */
				
				if(TransparentFrame!=null) {
					TransparentFrame.dispose();
					TransparentFrame = null;
				}
				
				setCursor(c);

				
				System.out.println("RELEASED");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				//TransparentFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		TransparentFrame.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				DraggedMouse = ScreenFrame.getCurrentMousePosn(e);
				TransparentFrame.updateRectangle(PressedMouse, DraggedMouse);
			

				System.out.println("DRAGGED MOUSE:"+DraggedMouse);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
		});
		
		
	}
	
	public GlassFrame getTransparentFrame() {
		return TransparentFrame;
	}


	public void setTransparentFrame(GlassFrame transparentFrame) {
		TransparentFrame = transparentFrame;
	}


	@Override
	public void Show() {
		this.setVisible(true);
		this.validate();
	}
	
	/*Mouse Interval Functions:*/
	/**@author Miguel
	 * @param TopLeftPoint
	 * @param BottomRightPoint
	 * @return Point [x,y]
	 * -Given a TopLeft Point and a BottomRight Point, it generates a random point
	 * within those constraints
	 * NOTE: Random.nextInt(int bount) produces int [0,Bound)
	 */
	public static Point generateRandomPosition(Point TopLeftPoint, Point BottomRightPoint) {
		Random rand = new Random();
		int width = BottomRightPoint.x-TopLeftPoint.x;
		int height = BottomRightPoint.y-TopLeftPoint.y;
		
		/*We get a random value from width and height and add it to TopLeftPoint's posnx, posny*/
		int posn_x = rand.nextInt(width)+TopLeftPoint.x;
		int posn_y = rand.nextInt(height)+TopLeftPoint.y;
		Point random_point = new Point(posn_x,posn_y);
		System.out.println("TOPLEFT:"+TopLeftPoint);
		System.out.println("RANDOMPOINT:"+random_point);
		System.out.println("BOTTOMRIGHT:"+BottomRightPoint);
		return random_point;
	}

}
