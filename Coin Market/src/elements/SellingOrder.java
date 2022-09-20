package elements;
/**
 * Represents selling orders
 * @author Zeynep Baydemir
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder> {
/**
 * Constructs a new selling order	
 * @param traderID ID of the trader placing the order
 * @param amount amount of PQoin
 * @param price price in order
 */
	
public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	/**
	 * Overrides compareTo method to sort Selling Order Queue according to price, amount and trader ID
	 * @Override
	 */
	public int compareTo(SellingOrder e) {
		if(this.getPrice() == e.getPrice()) {
			if(this.getAmount()==e.getAmount()) {
				return this.getTraderID()-e.getTraderID();
			}
			else if (this.getAmount()>e.getAmount()) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else if (this.getPrice() > e.getPrice()) {
			return 1;
		}
		else {
			return -1;
		}
	}
}

