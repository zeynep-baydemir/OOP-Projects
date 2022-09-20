package elements;
/**
 * Represents wallets
 * @author Zeynep Baydemir
 *
 */
public class Wallet {
	/**
	 * Dollars of trader
	 */
	private double dollars;
	/**
	 * PQoins of trader
	 */
	private double coins;
	/**
	 * blocked dollars of trader
	 */
	private double blockedDollars;
	/**
	 * blocked PQoins of trader
	 */
	private double blockedCoins;
	/**
	 * Constructs new waller
	 * @param dollars Dollars of trader
	 * @param coins PQoins of trader
	 */
	public Wallet(double dollars, double coins) {
		this.dollars=dollars;
		this.coins=coins;
	}
	/**
	 * Gets dollars of trader
	 * @return dollars of trader
	 */
	public double getDollars() {
		return this.dollars;
	}
	/**
	 * Gets coins of trader
	 * @return coins of trader
	 */
	public double getCoins() {
		return this.coins;
	}
	/**
	 * Gets blocked dollars of trader
	 * @return blocked dollars of trader
	 */
	public double getBlockedDollars() {
		return this.blockedDollars;
	}
	/**
	 * Gets blocked coins of trader
	 * @return blocked coins of trader
	 */
	public double getBlockedCoins() {
		return this.blockedCoins;
	}
	/**
	 * Adds dollars to trader's wallet
	 * @param dollar added dollars
	 */
	public void addDollars(double dollar) {
		this.dollars += dollar;
	}
	/**
	 * Adds coins to trader's wallet
	 * @param coin added coins
	 */
	public void addCoin(double coin) {
		this.coins += coin;
	}
	/**
	 * Adds blocked dollars to trader's wallet
	 * @param dollar added blocked dollars
	 */
	public void addblockDollars(double dollar) {
		this.blockedDollars += dollar;
	}
	/**
	 * Adds blocked coins to trader's wallet
	 * @param coin added blocked coins
	 */
	public void addblockCoin(double coin) {
		this.blockedCoins += coin;
	}
	/**
	 * Removes dollars from trader's wallet
	 * @param dollar removed dollars
	 */
	public void removeDollars(double dollar) {
		this.dollars-=dollar;
	}
	/**
	 * Removes coins from traders's wallet
	 * @param coin removed coins
	 */
	public void removeCoin(double coin) {
		this.coins -= coin;
	}
	/**
	 * Removes blocked dollars from trader's wallet
	 * @param dollar removed blocked dollars
	 */
	public void removeblockDol(double dollar) {
		this.blockedDollars -= dollar;
	}
	/**
	 *  Removes blocked coins from traders's wallet
	 * @param coin removed blocked coins
	 */
	public void removeblockCoin(double coin) {
		this.blockedCoins -= coin;
	}
}
