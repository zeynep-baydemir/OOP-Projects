
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * Represents liquid containers that extends Container super class.
 * @author Zeynep Baydemir
 *
 */
public class LiquidContainer extends HeavyContainer {
	/**
	 * Constructs a new Liquid Container
	 * The ID and weight of the container are given in the parameters.
	 * @param ID the port ID of the LiquidContainer
	 * @param weight the weight of the LiquidContainer
	 */
	public LiquidContainer(int ID, int weight) {
		super(ID, weight);
	}
	
	/**
	 * @Override 
	 * @return Fuel consumption per km by the Liquid Container
	 */
	public double consumption() {
		return 4.00*(double)(this.getWeight());
	}
 
}




//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

