
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * Represents basic containers that extends Container super class.
 * @author Zeynep Baydemir
 *
 */
public class BasicContainer extends Container  {
	/**
	 * Constructs a new Basic Container
	 * The ID and weight of the container are given in the parameters.
	 * @param ID the port ID of the BasicContainer
	 * @param weight the weight of the BasicContainer
	 */
	public BasicContainer(int ID, int weight) {
		super(ID, weight);
	} 
	/**
	 * @Override 
	 * @return Fuel consumption per km by the Basic Container
	 */
	public double consumption() {
		return 2.50*(double)(this.getWeight());
	}
}




//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

