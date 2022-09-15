
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;
/**
 * Methods that are related to ports are implemented in this class. 
 * @author Zeynep Baydemir
 */

import java.util.ArrayList;
import java.util.Collections;

import containers.Container;
import interfaces.IPort;
import ships.Ship;


public class Port implements IPort {
	/**
	 * ID of the port
	 */
	private final int ID;
	/**
	 * X coordinate of the port
	 */
	private final double X;
	/**
	 * Y coordinate of the port
	 */
	private final double Y;
	/**
	 * ArrayList of containers in the port
	 */
	private ArrayList<Container> containers = new ArrayList<Container>();
	/**
	 * ArrayList of ships that have visited the port
	 */
	private ArrayList<Ship> history = new ArrayList<Ship>();
	/**
	 * ArrayList of ships currently in the port
	 */
	private ArrayList<Ship> current = new ArrayList<Ship>();
		
	/**
	 * Constructs a new Port
	 * X and Y coordinates, and ID of the port are given in parameters.
	 * @param ID the ID of the port
	 * @param X X coordinate of the port
	 * @param Y Y coordinate of the port
	 */
	public Port(int ID, double X, double Y) {
		this.ID = ID;
		this.X = X;
		this.Y = Y;
	}
	/**
	 * @Override
	 * Adding ship to the port when ship came to the port
	 */
	public void incomingShip(Ship s) {
		boolean containBoolean = current.contains(s);
		if (!containBoolean) {
			this.current.add(s);
 		}	
	}
	/**
	 * @Override
	 * Taking ship out of the port and adding ship to the history of the port
	 */
	public void outgoingShip(Ship s) {
 		this.current.remove(s);
 		boolean containBoolean = history.contains(s);
 		if (!containBoolean) {
 			this.history.add(s);
 		}
 	}
	/**
	 * Calculating the distance between this port and the other Port
	 * @param other the other port from which the distance to this port will be calculated  
	 * @return the distance between two ports
	 */
	public double getDistance(Port other) {
		double distance = Math.sqrt((this.X - other.X)*(this.X - other.X)+(this.Y-other.Y)*(this.Y-other.Y));
		return distance;
	}
	
	/**
	 * Adding container to storage of the port
	 * @param container the container which is added to storage of the port
	 */
	public void addContainer(Container container) {
		this.containers.add(container);
	}
	/**
	 * Taking out the container from storage of the port
	 * @param container the container which is taken out from the storage of the port
	 */
	public void subtractContainer(Container container) {
		this.containers.remove(container);
	}
	/**
	 * Getter method for ID of the port
	 * @return ID of the port
	 */
	public int getID() {
		return this.ID;
	}
	/**
	 * Getter method for X coordinate of the port
	 * @return X coordinate of the port
	 */
	public double getX() {
		return this.X;
	}
	/**
	 * Getter method for Y coordinate of the port
	 * @return Y coordinate of the port
	 */
	public double getY() {
		return this.Y;
	}
	/**
	 * Getter method for ArrayList of current ships in the port
	 * @return ArrayList of current ships in the port
	 */
	public ArrayList<Ship> getCurrent() {
		Collections.sort(current);
		return this.current;
	}
	/**
	 * Getter method for ArrayList of containers in the storage of the port
	 * @return ArrayList of containers in the storage of the port
	 */
	public ArrayList<Container> getContainers(){
		Collections.sort(containers);
		return this.containers;
	}	
	/**
	 * Getter method for ArrayList of ships that have visited port
	 * @return  ArrayList of ships that have visited the port
	 */
	public ArrayList<Ship> getHistory(){
		return this.history;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

