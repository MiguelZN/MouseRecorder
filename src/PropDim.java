import java.awt.Dimension;

public class PropDim {
	double width_p, height_p;
	
	
	/*Takes in a width percentage and height percentage*/
	/*Width must be out of [1,100]
	 * Height must be out of [1,100]
	 */
	public PropDim(double width_p, double height_p){
		this.checkValidProportion(width_p, height_p);
	}
	
	/**@author Miguel
	 * @param width
	 * @param height
	 * 
	 * Checks to see if inputted doubles are between [0,1]
	 */
	public void checkValidProportion(double width, double height) {
		if(width>=0 && width<=1) {
			this.width_p = width;
		}
		else {
			this.width_p = 1;
		}
		
		if(height>=0 && height<=1) {
			this.height_p = height;
		}
		else {
			this.height_p = 1;
		}
	}
	
	
	/**@author Miguel
	 * @param d
	 * @param pd
	 * @return a Dimension containing a newly proportionized size
	 */
	public static Dimension calcDimension(Dimension d, PropDim pd) {
		int new_width = (int)(d.width*pd.width_p); //EX: 10 *.5 == 5
		int new_height = (int)(d.height*pd.height_p);
		
		return new Dimension(new_width, new_height);
	}

	public double getPorportion_width() {
		return width_p;
	}


	public double getPorportion_height() {
		return height_p;
	}
	
	public String toString() {
		return "[widthProp="+this.width_p+",heightProp="+this.height_p+"]";
	}
	
	
}
