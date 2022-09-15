
package question;

public class Customer {
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

	private int ID;
	public String name;
	private int age;
	private Operator operator;
	private Bill bill;
	private int timeTalk=0; 
	private int messageNum=0;
	private double connectionAmont=0;
	
	Customer (int ID, String name, int age, Operator operator, double limitingAmount){
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.operator = operator;
		this.bill = new Bill(limitingAmount);   
	}
	
	
	public void talk(int minute, Customer other) { 			
		double talkingCost = operator.calculateTalkingCost(minute, this);
		if (bill.check(talkingCost)) {
			if (other != this) {
				bill.add(talkingCost);										// adding debt
				operator.talkingTime(minute);								// adding operator time
				(other.getOperator()).talkingTime(minute);					// adding other operator time
				this.timeTalk+=minute;
				other.timeTalk+=minute;
			}
		}
	}
	
	public void message(int quantity, Customer other) {
		double messageCharge = operator.calculateMessageCost(quantity, this, other);
		if (bill.check(messageCharge)) {
			if (other != this) {
			bill.add(messageCharge);
			operator.numofMessages(quantity);
			this.messageNum+=quantity;
			}
		}	
	}
	
	public void connection(double amount) {
		double connectionCost = operator.calculateNetworkCost(amount);
		if (bill.check(connectionCost)){	
			bill.add(connectionCost);
			operator.mbUse(amount);
			this.connectionAmont+=amount;
		}
	}
	
	
	// Getter methods
	
	
	public int getAge() {
		return age;
	}
	
	public Operator getOperator() {
		return operator;
	}
	
	public Bill getBill() {
		return bill;
	}
	public int getTimeTalk() {
		return timeTalk;
	}
	public int getMessageNum() {
		return messageNum;
	}
	public double getConnectionAmont() {
		return connectionAmont;
	}
	
	// Setter Methods
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

