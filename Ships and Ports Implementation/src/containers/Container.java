
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * An abstract super class which has necessary methods about containers. 
 * @author Zeynep Baydemir
 *
 */

public abstract class Container implements Comparable<Container> {
	/**
	 * ID of the container
	 */
	private final int ID;
	/**
	 * weight of the container
	 */
	private final int weight;
	/**
	 * Constructs a new Container
	 * The ID and weight of the container are given in the parameters.
	 * @param ID the ID of the container
	 * @param weight the weight of the container
	 */
	public Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
	}
	/**
	 * Fuel consumption per km required by the container
	 * 
	 */
	public abstract double consumption();	
	/**
	 * Boolean method to check whether ID, weight and type of the containers are the same or not.
	 * @return true if ID, weight and type of the container are the same
	 */
	public boolean equals(Container other) {
		if ((this.ID == other.ID) && (this.weight == other.weight) && (this.getClass()== other.getClass())){
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * @Override
	 */
	public int compareTo(Container cont) {
		if(this.ID > cont.ID) {
			return 1;
		}
		else if(this.ID == cont.ID) {
			return 0;
		}
		else{
			return -1;
		}
	}
	/**
	 * Getter method for ID of the container
	 * @return ID of the container
	 */
	public int getID() {
		return this.ID;
	}
	/**
	 * Getter method for container weight
	 * @return weight of the container
	 */
	
	public int getWeight() {
		return this.weight;
	}

}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

