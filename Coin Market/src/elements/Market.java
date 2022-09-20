package elements;

import java.util.ArrayList;
import java.util.PriorityQueue;
/**
 * Represent markets
 * @author Zeynep Baydemir
 *
 */
public class Market {
	/**
	 * Selling orders queue
	 */
	private PriorityQueue<SellingOrder> sellingOrders;
	/**
	 * Buying orders queue
	 */
	private PriorityQueue<BuyingOrder> buyingOrders;
	/**
	 * Transactions list
	 */
	private ArrayList<Transaction> transactions;
	/**
	 * fee of the market
	 */
	private int fee;
	/**
	 * Constructs a new market
	 * @param fee market fee
	 */
	public Market(int fee) {
		this.fee=fee;
		this.buyingOrders = new PriorityQueue<BuyingOrder>();
		this.sellingOrders = new PriorityQueue<SellingOrder>();
		this.transactions= new ArrayList<Transaction>();
	}
	/**
	 * Gives selling order
	 * @param order selling order
	 */
	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
	}
	/**
	 * Gives buying order
	 * @param order buying order
	 */
	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
	}
	/**
	 * Makes open market operation to set price
	 * @param price desired price level
	 * @param traders list of traders
	 */
	public void makeOpenMarketOperation(double price, ArrayList<Trader>traders) {
		while(this.buyingOrders.size()>0 && this.buyingOrders.peek().getPrice()>= price) {
			double transPrice = this.buyingOrders.peek().getPrice();
			double amount = this.buyingOrders.peek().getAmount();
			this.sellingOrders.add(new SellingOrder(0,amount,transPrice));
			checkTransactions(traders);
			
		}
		while(this.sellingOrders.size()>0 && this.sellingOrders.peek().getPrice()<= price) {
			double transPrice = this.sellingOrders.peek().getPrice();
			double amount = this.sellingOrders.peek().getAmount();
			this.buyingOrders.add(new BuyingOrder(0,amount,transPrice));
			checkTransactions(traders);
		}
	}
	/**
	 * Checks transactions 
	 * @param traders trader list
	 */
	public void checkTransactions(ArrayList<Trader>traders) { 
		if(this.buyingOrders.isEmpty() || this.sellingOrders.isEmpty()) {
			return;
		}
		while(this.buyingOrders.size()>0 && this.sellingOrders.size()>0 && this.buyingOrders.peek().getPrice()>= this.sellingOrders.peek().getPrice() ) {
			if(this.buyingOrders.peek().getPrice()!= this.sellingOrders.peek().getPrice()) {
				int buyID = this.buyingOrders.peek().getTraderID();
				int sellID = this.sellingOrders.peek().getTraderID();
				double price = this.sellingOrders.peek().getPrice();
				double buyingPrice = this.buyingOrders.peek().getPrice();
				if(this.buyingOrders.peek().getAmount()>this.sellingOrders.peek().getAmount()) {
					double transactionAmount = this.sellingOrders.peek().getAmount();
					double amount = this.buyingOrders.peek().getAmount();
					Transaction transaction = new Transaction(this.buyingOrders.peek(), this.sellingOrders.peek());
					this.transactions.add(transaction);
					traders.get(buyID).getWallet().removeblockDol(transactionAmount*buyingPrice);
					traders.get(buyID).getWallet().addCoin(transactionAmount);
					traders.get(buyID).getWallet().addDollars((buyingPrice-price)*transactionAmount);
					traders.get(sellID).getWallet().removeblockCoin(transactionAmount);
					traders.get(sellID).getWallet().addDollars(price*transactionAmount*((1-((double)fee/1000))));
					this.buyingOrders.poll();
					this.sellingOrders.poll();
					this.buyingOrders.add(new BuyingOrder(buyID, amount-transactionAmount,buyingPrice));
				
			}
				else if(this.buyingOrders.peek().getAmount()<this.sellingOrders.peek().getAmount()){
					double transactionAmount = this.buyingOrders.peek().getAmount();
					double amount = this.sellingOrders.peek().getAmount();
					Transaction transaction = new Transaction(this.buyingOrders.peek(), this.sellingOrders.peek());
					this.transactions.add(transaction);
					traders.get(buyID).getWallet().removeblockDol(transactionAmount*buyingPrice);
					traders.get(buyID).getWallet().addCoin(transactionAmount);
					traders.get(buyID).getWallet().addDollars((buyingPrice-price)*transactionAmount);
					traders.get(sellID).getWallet().removeblockCoin(transactionAmount);
					traders.get(sellID).getWallet().addDollars(price*transactionAmount*((1-((double)fee/1000))));
					this.buyingOrders.poll();
					this.sellingOrders.poll();
					this.sellingOrders.add(new SellingOrder(sellID, amount-transactionAmount,price));
					
				}
				else {
					double amount = this.sellingOrders.peek().getAmount();
					Transaction transaction = new Transaction(this.buyingOrders.peek(), this.sellingOrders.peek());
					this.transactions.add(transaction);
					traders.get(buyID).getWallet().removeblockDol(amount*buyingPrice);
					traders.get(buyID).getWallet().addCoin(amount);
					traders.get(buyID).getWallet().addDollars((buyingPrice-price)*amount);
					traders.get(sellID).getWallet().removeblockCoin(amount);
					traders.get(sellID).getWallet().addDollars(price*amount*((1-((double)fee/1000))));
					this.buyingOrders.poll();
					this.sellingOrders.poll();
				}
			}
			else {
				int buyID = this.buyingOrders.peek().getTraderID();
				int sellID = this.sellingOrders.peek().getTraderID();
				double price = this.sellingOrders.peek().getPrice();
				if(this.buyingOrders.peek().getAmount()>this.sellingOrders.peek().getAmount()) {
					double transactionAmount = this.sellingOrders.peek().getAmount();
					double amount = this.buyingOrders.peek().getAmount();
					Transaction transaction = new Transaction(this.buyingOrders.peek(), this.sellingOrders.peek());
					this.transactions.add(transaction);
					traders.get(buyID).getWallet().removeblockDol(transactionAmount*price);
					traders.get(buyID).getWallet().addCoin(transactionAmount);
					traders.get(sellID).getWallet().removeblockCoin(transactionAmount);
					traders.get(sellID).getWallet().addDollars(price*transactionAmount);
					this.buyingOrders.poll();
					this.sellingOrders.poll();
					this.buyingOrders.add(new BuyingOrder(buyID, amount-transactionAmount,price));
			}
				else if(this.buyingOrders.peek().getAmount()<this.sellingOrders.peek().getAmount()){
					double transactionAmount = this.buyingOrders.peek().getAmount();
					double amount = this.sellingOrders.peek().getAmount();
					Transaction transaction = new Transaction(this.buyingOrders.peek(), this.sellingOrders.peek());
					this.transactions.add(transaction);
					traders.get(buyID).getWallet().removeblockDol(transactionAmount*price);
					traders.get(buyID).getWallet().addCoin(transactionAmount);
					traders.get(sellID).getWallet().removeblockCoin(transactionAmount);
					traders.get(sellID).getWallet().addDollars(price*transactionAmount*((1-((double)fee/1000))));
					this.buyingOrders.poll();
					this.sellingOrders.poll();
					this.sellingOrders.add(new SellingOrder(sellID, amount-transactionAmount,price));
					
				}
				else {
					double amount = this.sellingOrders.peek().getAmount();
					Transaction transaction = new Transaction(this.buyingOrders.peek(), this.sellingOrders.peek());
					this.transactions.add(transaction);
					traders.get(buyID).getWallet().removeblockDol(amount*price);
					traders.get(buyID).getWallet().addCoin(amount);
					traders.get(sellID).getWallet().removeblockCoin(amount);
					traders.get(sellID).getWallet().addDollars(price*amount*((1-((double)fee/1000))));
					this.buyingOrders.poll();
					this.sellingOrders.poll();
				}
			}
		}
	}
	/**
	 * Gets selling orders queue
	 * @return selling orders queue
	 */
	public PriorityQueue<SellingOrder> getSellingOrder() {
		return this.sellingOrders;
	}
	/**
	 * Gets buying orders queue
	 * @return buying orders queue
	 */
	public PriorityQueue<BuyingOrder> getBuyingOrder() {
		return this.buyingOrders;
	}
	/**
	 * Gets market fee
	 * @return market fee
	 */
	public int getFee() {
		return this.fee;
	}
	/**
	 * Gets transactions list
	 * @return transactions list
	 */
	public ArrayList<Transaction> getTransactions(){
		return this.transactions;
	}
	
}
