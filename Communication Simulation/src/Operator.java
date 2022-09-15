
package question;

public class Operator {
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	private int ID;
	private double talkingCharge;
	private double messageCost;
	private double networkCharge;
	private int discountRate;
	private int talkingTime;
	private int nofMessages;
	private double mb;
	
	Operator(int ID, double talkingCharge, double messageCost, double networkCharge, int discountRate ){
		this.ID = ID;
		this.talkingCharge = talkingCharge;
		this.messageCost = messageCost;
		this.networkCharge = networkCharge;
		this.discountRate = discountRate;
		this.talkingTime = 0;
		this.nofMessages=0;
		this.mb=0;
	}
	
	public double calculateTalkingCost(int minute, Customer customer) {
		if (customer.getAge() < 18 || customer.getAge() > 65) {
			double talkingCost = (double)(minute * talkingCharge*((double)(100-discountRate)/100));
			return talkingCost;
		}
		else {
			double talkingCost = (double)(minute * talkingCharge);
			return talkingCost;
		}
	}
	
	public double calculateMessageCost(int quantity, Customer customer, Customer other) {
		if (customer.getOperator() == other.getOperator()) {
			double messagebill = (double)(quantity * messageCost*((double)(100-discountRate)/100));
			return messagebill;
		}
		else {
			double messagebill = (double)(quantity * messageCost);
			return messagebill;
		}
	}
	
	public	double calculateNetworkCost(double amount) {
			double networkBill = amount * networkCharge;
			return networkBill;
		}
	
	public int talkingTime(int amount) {   									//talking time tutmak için
		talkingTime += amount;
		return talkingTime;
	}
	public int numofMessages(int number) {
		nofMessages+= number;
		return nofMessages;
	}
	public double mbUse(double amount) {
		mb+= amount;
		return mb;
	}
		
	
	// getter methods for talkingCharge, messageCost, networkCharge, discountRate
	public double getTalkingCharge() {
		return talkingCharge;
	}
	public double getMessageCost() {
		return messageCost;
	}
	public double getNetworkCharge() {
		return networkCharge;
	}
	public int getDiscountRate() {
		return discountRate;
	}
	public int getTalkingTime(){
		return talkingTime;
	}
	
	public int getNofMessages() {
		return nofMessages;
	}
	public double getMb() {
		return mb;
	}
	// Setter Methods
	
	public void setTalkingCharge(double talkingCharge) {
		this.talkingCharge = talkingCharge;
	}
	
	public void setMessageCost(double messageCost) {
		this.messageCost = messageCost;
	}
	
	public void setNetworkCharge(double networkCharge) {
		this.networkCharge = networkCharge;
	}
	
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	
	
	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

