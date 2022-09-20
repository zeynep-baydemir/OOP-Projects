package elements;
/**
 * Represents traders
 * @author Zeynep Baydemir
 *
 */
public class Trader {
	/**
	 * ID of the trader
	 */
	private int id;
	/**
	 * Trader's wallet
	 */
	private Wallet wallet;
	/**
	 * Number of users
	 */
	public static int numberOfUsers = 0;
	/**
	 * Constructs a new trader
	 * @param dollars dollars of trader
	 * @param coins coins of trader
	 */
	public Trader(double dollars, double coins) {
		Wallet myWallet = new Wallet(dollars,coins);
		this.wallet = myWallet;
		this.id= numberOfUsers;
		numberOfUsers++;
	}
	/**
	 * Checks whether trader can give selling order
	 * @param amount amount of coins
	 * @param price price of order
	 * @param market market where orders are given
	 * @return 1 if trader can give order, 0 if trader cannot give order
	 */
	public int sell(double amount, double price, Market market) {
		if(this.wallet.getCoins()>=amount || this.id ==0) {
			return 1;
		}
		else{
			return 0;
			
		}
	}
	/**
	 * Checks whether trader can give buying order
	 * @param amount amount of coins
	 * @param price price of order
	 * @param market market where orders are given
	 * @return 1 if trader can give order, 0 if trader cannot give order
	 */
	public int buy(double amount, double price, Market market) {
		if(this.wallet.getDollars()>=price*amount || this.id ==0) {
			return 1;
		}
		return 0;
	}
	/**
	 * Gets trader's wallet
	 * @return trader's wallet
	 */
	public Wallet getWallet() {
		return this.wallet;
	}
	/**
	 * Gets trader's ID
	 * @return trader's ID
	 */
	public int getID() {
		return this.id;		
	}
	
}
