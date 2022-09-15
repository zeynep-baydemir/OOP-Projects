
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * Represents heavy containers that extends Container super class.
 * @author Zeynep Baydemir
 *
 */
public class HeavyContainer extends Container {
	/**
	 * Constructs a new Heavy Container
	 * The ID and weight of the container are given in the parameters.
	 * @param ID the port ID of the HeavyContainer
	 * @param weight the weight of the HeavyContainer
	 */
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
	}
	/**
	 * @Override 
	 * @return Fuel consumption per km by the Heavy Container
	 */
	public double consumption() {
		return 3.00*(double)(this.getWeight());
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

