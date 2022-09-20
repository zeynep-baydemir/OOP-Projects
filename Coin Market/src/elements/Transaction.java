package elements;
/**
 * Represents transactions
 * @author Zeynep Baydemir
 *
 */
public class Transaction {
	/**
	 * Selling order of the transaction
	 */
	private SellingOrder sellingOrder;
	/**
	 * Buying order of the transaction
	 */
	private BuyingOrder buyingOrder;
	/**
	 * Constructs a new transaction
	 * @param buyingOrder Buying order of the transaction
	 * @param sellingOrder Selling order of the transaction
	 */
	public Transaction(BuyingOrder buyingOrder, SellingOrder sellingOrder) {
		this.sellingOrder = sellingOrder;
		this.buyingOrder = buyingOrder;
	}
	
	
	
	
}
 