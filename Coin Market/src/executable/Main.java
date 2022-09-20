package executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

import elements.*;
/**
 * Necessary implementations including input output operations are executed in this class
 * @author Zeynep Baydemir
 * 
 */
public class Main {
	public static Random myRandom;
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		ArrayList<Trader> traderList = new ArrayList<Trader>();
		int seed = in.nextInt();
		myRandom= new Random(seed);
		int fee = in.nextInt();
		int nofUser = in.nextInt();
		int nofQueries = in.nextInt();
		
		Market market = new Market(fee);
		int invalidquery = 0;
		
		for (int i =0; i < nofUser; i++) {
			double dollar = in.nextDouble();
			double coin = in.nextDouble();
			Trader trader = new Trader(dollar,coin);
			traderList.add(trader);
		}
		
		for (int i =0; i < nofQueries; i++) {
			int input = in.nextInt();
			
			if (input == 10) {
				int ID = in.nextInt();
				double price = in.nextDouble();
				double amount = in.nextDouble();
				if(traderList.get(ID).buy(amount, price, market)==1) {
					BuyingOrder order = new BuyingOrder(ID,amount,price);
					market.giveBuyOrder(order);
					traderList.get(ID).getWallet().addblockDollars(price*amount);
					traderList.get(ID).getWallet().removeDollars(price*amount);
					market.checkTransactions(traderList);
				}
				else {
					invalidquery++;
				}
			}
			else if(input==11) {
				int ID = in.nextInt();
				double amount = in.nextDouble();
				if(market.getSellingOrder().size()>0) {
					double price = market.getSellingOrder().peek().getPrice();
					if(traderList.get(ID).buy(amount, price, market)==1) {
						market.giveBuyOrder(new BuyingOrder(ID,amount, price));
						traderList.get(ID).getWallet().addblockDollars(price*amount);
						traderList.get(ID).getWallet().removeDollars(price*amount);
						market.checkTransactions(traderList);
					}
					else {
						invalidquery++;
					}
				}
				else {
					invalidquery++;
					}
				}
			
			else if(input==20) {
				int ID = in.nextInt();
				double price = in.nextDouble();
				double amount = in.nextDouble();
				if(traderList.get(ID).sell(amount, price, market)==1) {
					SellingOrder order = new SellingOrder(ID,amount,price);
					market.giveSellOrder(order);
					traderList.get(ID).getWallet().addblockCoin(amount);
					traderList.get(ID).getWallet().removeCoin(amount);
					market.checkTransactions(traderList);
				}
				else { 
					invalidquery++;
				}
			}
			else if(input==21) {
				int ID = in.nextInt();
				double amount = in.nextDouble();
				if(market.getBuyingOrder().size()>0) {
					double price = market.getBuyingOrder().peek().getPrice();
					if(traderList.get(ID).sell(amount, price, market)==1) {
						SellingOrder order = new SellingOrder(ID,amount,price);
						market.giveSellOrder(order);
						traderList.get(ID).getWallet().addblockCoin(amount);
						traderList.get(ID).getWallet().removeCoin(amount);
						market.checkTransactions(traderList);
					}
					else {
						invalidquery++;
					}		
				}
				else {
					invalidquery++;
					}					
				}
			
			else if(input==3) {
				int ID = in.nextInt();
				double amount = in.nextDouble();
				traderList.get(ID).getWallet().addDollars(amount);
			}
			else if(input == 4) {
				int ID = in.nextInt();
				double amount = in.nextDouble();
				if(traderList.get(ID).getWallet().getDollars()>=amount) {
					traderList.get(ID).getWallet().removeDollars(amount);
				}
				else {
					invalidquery++;
				}
			}
			else if(input==5) {
				int ID = in.nextInt();
				out.println("Trader "+ID+": "+String.format("%.5f",traderList.get(ID).getWallet().getDollars())+"$ "+String.format("%.5f",traderList.get(ID).getWallet().getCoins())+"PQ");
			}
			else if(input==777) {
				for(Trader trader:traderList) {
					trader.getWallet().addCoin(myRandom.nextDouble()*10);
				}
			}
			else if(input==666) {
				double price = in.nextDouble();
				market.makeOpenMarketOperation(price, traderList);
				market.checkTransactions(traderList);
				
			}
			else if(input == 500) {
				Iterator<BuyingOrder> buyit = market.getBuyingOrder().iterator();
				Iterator<SellingOrder> sellit = market.getSellingOrder().iterator();
				double buyingSize = 0;
				double sellingSize =0;
				
				while(buyit.hasNext()) {
					BuyingOrder buyorder= (BuyingOrder)(buyit.next());
					buyingSize+=buyorder.getAmount()*buyorder.getPrice();
				}
				while(sellit.hasNext()) {
					SellingOrder sellorder = (SellingOrder)(sellit.next());
					sellingSize += sellorder.getAmount();
				}
				out.println("Current market size: "+String.format("%.5f", buyingSize)+" "+String.format("%.5f",sellingSize));
			}

			else if (input ==501) {
				out.println("Number of successful transactions: "+market.getTransactions().size());
			}
			else if(input == 502) {
				out.println("Number of invalid queries: "+invalidquery);
			}
			else if(input==505) {
				int buysize=market.getBuyingOrder().size();
				int sellsize = market.getSellingOrder().size();
				if(buysize>0 && sellsize>0 ) {
					double buyingPrice = market.getSellingOrder().peek().getPrice();
					double sellingPrice = market.getBuyingOrder().peek().getPrice();
					double average = (double)((buyingPrice+sellingPrice)/2);
					out.println("Current prices: "+String.format("%.5f",sellingPrice)+" "+String.format("%.5f",buyingPrice)+" "+String.format("%.5f",average));
				}	
				else if(buysize+sellsize==0) {
					out.println("Current prices: "+"0.00000"+" "+"0.00000"+" "+"0.00000");
				}
				else if (buysize==0 || sellsize==0){
					if(buysize==0) {
						double sellingPrice = market.getSellingOrder().peek().getPrice();
						out.println("Current prices: "+String.format("%.5f",sellingPrice)+"0.00000"+" "+" "+String.format("%.5f",sellingPrice));
					}
					else if(sellsize ==0) {
						double buyingPrice = market.getBuyingOrder().peek().getPrice();
						out.println("Current prices: "+"0.00000 "+" "+String.format("%.5f",buyingPrice)+String.format("%.5f",buyingPrice));
					}
				}
			}
			else if(input==555) {
				for(Trader trader:traderList) {
					out.println("Trader "+trader.getID()+": "+String.format("%.5f",trader.getWallet().getDollars()+trader.getWallet().getBlockedDollars())+"$ "+String.format("%.5f",trader.getWallet().getCoins()+trader.getWallet().getBlockedCoins())+"PQ");
				}
			}
			
		}
	in.close();
	}
	
}
