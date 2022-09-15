
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;
/**
 * Methods that are related to ships are implemented in this class. 
 * @author Zeynep Baydemir
 */
import java.util.ArrayList;
import java.util.Collections;

import containers.*;
import interfaces.IShip;
import ports.Port;


public class Ship implements IShip, Comparable<Ship>{
	/**
	 * ID of the ship
	 */
	private final int ID;
	/**
	 * The amount of fuel in the ship
	 */
	private double fuel = 0;
	/**
	 * Current port of the ship
	 */
	private Port currentPort;
	/**
	 * Fuel consumption per km required by the ship
	 */
	private final double fuelConsumptionPerKM;
	/**
	 * Total container weight capacity of the ship
	 */
	private final int totalWeightCapacity;
	/**
	 * Maximum number of containers that can be on the ship
	 */
	private final int maxNumberOfAllContainers;
	/**
	 * Maximum number of heavy containers that can be on the ship
	 */
	private final int maxNumberOfHeavyContainers;
	/**
	 * Maximum number of refrigerated containers that can be on the ship
	 */
	private final int maxNumberOfRefrigeratedContainers;
	/**
	 * Maximum number of liquid containers that can be on the ship
	 */
	private final int maxNumberOfLiquidContainers;
	/**
	 * ArrayList of current containers on the ship
	 */
	private ArrayList<Container> shipContainers =  new ArrayList<Container>();
	/**
	 * Total weight of current containers on the ship
	 */
	private int currentWeight = 0;
	/**
	 * Total number of current containers on the ship
	 */
	private int	nofAllContainers = 0;
	/**
	 * Total number of current heavy containers on the ship
	 */
	private int	nofHeavyContainers = 0;
	/**
	 * Total number of current refrigerated containers on the ship
	 */
	private int	nofRefrigeratedContainers = 0;
	/**
	 * Total number of current liquid containers on the ship
	 */
	private int	nofLiquidContainers = 0;

	/**
	 * Constructs new Ship
	 * ID, port, total weight capacity, maximum number of all containers, maximum number of heavy containers, maximum number of refrigerated containers
	 * maximum number of liquid containers, the fuel consumption per km of the ship is given in parameters
	 * @param ID the ID of the ship
	 * @param p the port of the ship
	 * @param totalWeightCapacity  the total weight capacity of the ship
	 * @param maxNumberOfAllContainers  maximum number of all containers that ship can keep
	 * @param maxNumberOfHeavyContainers maximum number of heavy containers that ship can keep
	 * @param maxNumberOfRefrigeratedContainers maximum number of refrigerated containers that ship can keep
	 * @param maxNumberOfLiquidContainers maximum number of liquid containers that ship can keep
	 * @param fuelConsumptionPerKM the fuel consumption per km of ship 
	 */
	
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers,
			int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM) {
		this.ID = ID;
		this.currentPort = p;
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM; 	
		p.incomingShip(this);
	}
	/**
	 * Getter method for ArrayList of current containers on the ship
	 * @return ArrayList of current containers on the ship
	 */
	public ArrayList<Container> getCurrentContainers(){    
		Collections.sort(shipContainers);
		return this.shipContainers;
	}
	/**
	 * @Override
	 * Boolean method to check whether ship can have enough fuel to sail
	 * @return true if ship can sail to another port
	 */
	public boolean sailTo(Port p) { 
		double fuelCons = getTotalFuelConsumption() * this.currentPort.getDistance(p);
		if (fuelCons <= this.fuel) {
			this.currentPort.outgoingShip(this);
			this.setPort(p);
			p.incomingShip(this);
			this.usingFuel(fuelCons);
			return true;
		}
		return false;
	}
	/**
	 * @Override
	 * Adding fuel to current fuel of the ship
	 * @param amount of fuel to be added
	 */
	public void reFuel(double newFuel) {
		this.fuel += newFuel;
	}
	/**
	 * Subtracting amount of used fuel from current fuel of the ship
	 * @param fuel amount of used fuel 
	 */
	public void usingFuel(double fuel) {
		this.fuel -= fuel;
	}
	/**
	 * @Override
	 * Boolean method to check whether container can be loaded into ship or not
	 * @return true if container can be loaded into the ship
	 */
	public boolean load(Container cont) {
		if (this.currentPort.getContainers().contains(cont)) {
			if ((this.nofAllContainers < this.maxNumberOfAllContainers) && (this.totalWeightCapacity-this.currentWeight >= cont.getWeight())) {
				if (cont instanceof BasicContainer) {
					nofAllContainers++;
					shipContainers.add(cont);
					this.currentWeight += cont.getWeight();
					this.currentPort.subtractContainer(cont);
					return true;
				}
				else if (this.nofHeavyContainers< this.maxNumberOfHeavyContainers) {
					if (cont instanceof LiquidContainer) {
						if (this.nofLiquidContainers < this.maxNumberOfLiquidContainers) {
							nofAllContainers++;
							shipContainers.add(cont);
							this.currentWeight += cont.getWeight();
							this.currentPort.subtractContainer(cont);
							nofHeavyContainers ++;
							nofLiquidContainers++;
							return true;
						}
					}
					else if (cont instanceof RefrigeratedContainer) {
						if (this.nofRefrigeratedContainers < this.maxNumberOfRefrigeratedContainers) {
							nofRefrigeratedContainers++;
							nofAllContainers++;
							shipContainers.add(cont);
							this.currentWeight += cont.getWeight();
							this.currentPort.subtractContainer(cont);
							nofHeavyContainers ++;
							return true;
						}
					}
					else if (cont instanceof HeavyContainer) {
						nofAllContainers++;
						shipContainers.add(cont);
						this.currentWeight += cont.getWeight();
						this.currentPort.subtractContainer(cont);
						nofHeavyContainers ++;
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * @Override
	 * Boolean method to check whether container can be unloaded from ship
	 * @return true if container can be unloaded from the ship
	 */
	public boolean unLoad(Container cont) {
		if (shipContainers.contains(cont)){
			nofAllContainers--;
			shipContainers.remove(cont);
			this.currentWeight -= cont.getWeight();
			this.currentPort.addContainer(cont);
			if (cont instanceof RefrigeratedContainer) {
				nofHeavyContainers --;
				nofRefrigeratedContainers --;
				return true;
			}
			else if (cont instanceof LiquidContainer) {
				nofHeavyContainers --;
				nofLiquidContainers --;
				return true;
			}
			else if (cont instanceof HeavyContainer) {
				nofHeavyContainers --;
				return true;
			}
			return true;			
		}
		return false;
	}
	
		
	/**
	 * @Override 
	 */
	public int compareTo(Ship ship) {
		int compareShipID = ((Ship)ship).getID();
		return this.ID - compareShipID;
	}
	
	/**
	 * Getter method for ID of the ship
	 * @return ID of the ship
	 */
	public int getID() {
		return this.ID;
	}
	/**
	 * Getter method for port of the ship
	 * @return port of the ship
	 */
	public Port getPort() {
		return this.currentPort;
	}
	/**
	 * Getter method for current fuel of the ship
	 * @return current fuel of the ship
	 */
	public double getFuel() {
		return this.fuel;
	}
	/**
	 * Getter method for total fuel consumption of the ship
	 * @return total fuel consumption of the ship
	 */
	public double getTotalFuelConsumption() {
		double containerConsumption = 0;
		ArrayList<Container> containerList = this.getCurrentContainers();
		for (Container container : containerList) {
			containerConsumption += container.consumption();
		}
		return (this.fuelConsumptionPerKM + containerConsumption); 
	}
	/**
	 * Setter method to change port of the ship when ship sails
	 * @param port port of the ship that ship sails
	 */
	public void setPort(Port port) {
		this.currentPort = port;
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

