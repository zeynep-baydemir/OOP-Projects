
package question;

public class Bill {

	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	private double limitingAmount;
	private double currentDebt;
	private double moneySpent;
	
	Bill (double limitingAmount){
		this.limitingAmount = limitingAmount;
		this.currentDebt = 0;
		this.moneySpent = 0;
	}
	
	public boolean check(double amount) {
		if ((amount + currentDebt) <= limitingAmount) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void add(double amount) {
		currentDebt += amount; 
	}
	
	public void pay(double amount) {
		if (amount >= currentDebt) {
			moneySpent+=currentDebt;
			currentDebt = 0;
		}
		else {
			currentDebt -= amount;
			moneySpent+=amount;
		}
	}
	
	public void changeTheLimit(double amount) {
		if (currentDebt <= amount) {
			limitingAmount = amount;
		}
	}
	
	// Getter Methods
	
	public double getLimitingAmount(){
		return limitingAmount;
	}
	
	public double getCurrentDebt(){
		return currentDebt;
	}
	public double getMoneySpent() {
		return moneySpent;
	}

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

