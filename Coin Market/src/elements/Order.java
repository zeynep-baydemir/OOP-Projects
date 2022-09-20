package elements;
/**
 * Represents orders
 * @author Zeynep Baydemir
 *
 */
public class Order {
	/**
	 * Amount of PQoin
	 */
	private double amount;
	/**
	 * Price 
	 */
	private double price;
	/**
	 * ID of the trader placing the order
	 */
	private int traderID;
	/**
	 * Constructs a new order
	 * @param traderID ID of the trader placing the order
	 * @param amount Amount of PQoin
	 * @param price Price 
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID=traderID;
		this.amount=amount;
		this.price=price;
	}
	/**
	 * Gets the price in the order
	 * @return price
	 */
	public double getPrice() {
		return this.price;
	}
	/**
	 * Gets the amount of PQoin in the order
	 * @return amount of PQoin
	 */
	public double getAmount() {
		return this.amount;
	}
	/**
	 * Gets the ID of the trader
	 * @return ID of the trader
	 */
	public int getTraderID() {
		return this.traderID;
	}
	/**
	 * Sets the amount in the order
	 * @param amount amount of the PQoin
	 */
	public void setAmount(double amount) {
		this.amount=amount;
	}
}



