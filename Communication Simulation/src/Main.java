
package question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {


	public static void main(String args[]) {

		Customer[] customers;
		Operator[] operators;

		int C, O, N;

		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		try {
			PrintStream outstream = new PrintStream(outFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		C = reader.nextInt();
		O = reader.nextInt();
		N = reader.nextInt();

		customers = new Customer[C];
		operators = new Operator[O];

		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
		
		PrintStream outstream1;
		try {
		        outstream1 = new PrintStream(outFile);
		}catch(FileNotFoundException e2) {
		        e2.printStackTrace();
		        return;
		}
		
		String[][] customerArray = new String[C][];
		String[][] operatorArray = new String[O][];
		String[][] operations = new String[N][];
		
		int customerCount = 0;
		int operatorCount = 0;
		int operationCount = 0;
				
		while (reader.hasNextLine()){
			String inputLine = reader.nextLine();			
			if (inputLine.isBlank()) {
				continue;
			}
			else {	
				
				if (inputLine.startsWith("1")) {
					customerArray[customerCount] = inputLine.split(" ");
					customerCount++;
				}
				else if (inputLine.startsWith("2")) {
					operatorArray[operatorCount] = inputLine.split(" ");
					operatorCount ++;
					
				}
				else {
					operations[operationCount] = inputLine.split(" ");
					operationCount++;
				}
			}
		}
		for (int i=0; i<O; i++) {
			double talkingCharge, messageCost, networkCharge;
			int discountRate;
			talkingCharge = Double.parseDouble(operatorArray[i][1]);
			messageCost = Double.parseDouble(operatorArray[i][2]) ;
			networkCharge = Double.parseDouble(operatorArray[i][3]) ;
			discountRate = Integer.parseInt(operatorArray[i][4]) ;
			operators[i] = new Operator(i,talkingCharge,messageCost,networkCharge,discountRate);
		}
		
		for (int i=0; i<C; i++) {
			int ID, age;
			String name;
			Operator operator;
			double limitingAmount;
			ID = Integer.parseInt(customerArray[i][3]);
			age = Integer.parseInt(customerArray[i][2]);
			name = customerArray[i][1];
			operator = operators[ID];
			limitingAmount = Double.parseDouble(customerArray[i][4]);
			customers[i] = new Customer(ID, name, age, operator, limitingAmount );
		}
	
		for (int i=0; i<N-C-O; i++) {
			  if (operations[i][0].equals("3")) {
				  int time, ID1, ID2;
				  ID1 = Integer.parseInt(operations[i][1]);
				  ID2 = Integer.parseInt(operations[i][2]);
				  time = Integer.parseInt(operations[i][3]);
				  customers[ID1].talk(time,customers[ID2]);
			  }
			  else if (operations[i][0].equals("4")) {
				  int quantity,ID1,ID2;
				  ID1 = Integer.parseInt(operations[i][1]);
				  ID2 = Integer.parseInt(operations[i][2]);
				  quantity = Integer.parseInt(operations[i][3]);
				  customers[ID1].message(quantity, customers[ID2]);
			  }
			  else if (operations[i][0].equals("5")) {
				  int ID;double amount;
				  ID = Integer.parseInt(operations[i][1]);
				  amount = Double.parseDouble(operations[i][2]);
				  customers[ID].connection(amount);
			  }
			  else if (operations[i][0].equals("6")) {
				  int ID; double amount;
				  ID = Integer.parseInt(operations[i][1]);
				  amount = Double.parseDouble(operations[i][2]);
				  customers[ID].getBill().pay(amount);
			  }
			  else if (operations[i][0].equals("7")) {
				  int custID, operatorID;
				  custID = Integer.parseInt(operations[i][1]);
				  operatorID = Integer.parseInt(operations[i][2]);
				  customers[custID].setOperator(operators[operatorID]);
			  }
			  
			  else if (operations[i][0].equals("8")) {
				  int ID; double amount;
				  ID = Integer.parseInt(operations[i][1]);
				  amount = Double.parseDouble(operations[i][2]);
				  customers[ID].getBill().changeTheLimit(amount);
			  }
			  
		}
			for (int i=0; i<O ;i++) {
				outstream1.println("Operator "+i+" : "+operators[i].getTalkingTime()+" "+operators[i].getNofMessages()+" "+String.format("%.2f", operators[i].getMb()));

			}
			for (int i=0; i<C ;i++) {
				outstream1.println("Customer "+i+" : "+String.format("%.2f", customers[i].getBill().getMoneySpent())+" "+String.format("%.2f", customers[i].getBill().getCurrentDebt()));
			}
			int maxtalktime = 0;
			int talkingID=0;
			for (int i=0; i<C ;i++) {
				if (customers[i].getTimeTalk() > maxtalktime){
					maxtalktime = customers[i].getTimeTalk();
					talkingID = i;
				}
			}
			
			outstream1.println(customers[talkingID].name+" : "+maxtalktime);
			
			int maxMessage = 0;
			int messageID = 0;
			for (int i=0; i<C ;i++) {
				if (customers[i].getMessageNum()>maxMessage) {
					maxMessage = customers[i].getMessageNum();
					messageID = i;
				}
			}
			
			outstream1.println(customers[messageID].name+" : "+maxMessage);
			
			double maxMB = 0;
			int connectionID = 0;
			for (int i=0; i<C ;i++) {
				if (customers[i].getConnectionAmont()> maxMB) {
					maxMB = customers[i].getConnectionAmont();
					connectionID = i;
				}
			}
			outstream1.println(customers[connectionID].name+" : "+String.format("%.2f",maxMB));
		
		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
	} 
}

