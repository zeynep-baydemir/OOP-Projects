
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * Represents refrigerated containers that extends Container super class.
 * @author Zeynep Baydemir
 *
 */
public class RefrigeratedContainer extends HeavyContainer {
	
	/**
	 * Constructs a new Refrigerated Container
	 * The ID and weight of the container are given in the parameters.
	 * @param ID the port ID of the RefrigeratedContainer
	 * @param weight the weight of the RefrigeratedContainer
	 */
	public RefrigeratedContainer(int ID, int weight) {
		super(ID, weight);
	}
	
	/**
	 * @Override 
	 * @return Fuel consumption per km by the Refrigerated Container
	 */
	public double consumption() {
		return 5.00*(double)(this.getWeight());
	}
		
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

