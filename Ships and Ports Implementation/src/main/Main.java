
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;
/**
 * Necessary implementations including input output operations are executed in this class
 * @author Zeynep Baydemir
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import containers.*;
import ports.Port;
import ships.Ship;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		ArrayList<Container> container = new ArrayList<Container>();
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ArrayList<Port> ports = new ArrayList<Port>();
		
		int N = in.nextInt();
		int portID = 0;
		int containerID = 0;
		int shipID = 0;
		for (int i=0; i<N; i++) {
			int input = in.nextInt();
					
			if (input == 1) {
				int pID = in.nextInt();
				int weight = in.nextInt();
				
				if(weight <= 3000) {
					if (in.hasNextInt()) {
						container.add(new BasicContainer(containerID, weight));
					}
					else {
						String letter = in.next();
						if (letter.equals("R")){
							container.add(new RefrigeratedContainer(containerID,weight));
						}
						else if (letter.equals("L")) {
							container.add(new LiquidContainer(containerID, weight));
						}
					}
				}
				else {
					if (in.hasNextInt()) {
						container.add(new HeavyContainer(containerID, weight));
					}
					else {
						String letter = in.next();
						if (letter.equals("R")){
							container.add(new RefrigeratedContainer(containerID,weight));
						}
						else if (letter.equals("L")) {
							container.add(new LiquidContainer(containerID, weight));
						}
					}	
				}
				ports.get(pID).addContainer(container.get(container.size()-1));
				containerID++;
			}
			else if (input == 2) {
				int pID = in.nextInt();
				int maxWeight = in.nextInt();
				int maxNofContainer = in.nextInt();
				int maxNofHeavy = in.nextInt();
				int maxNofRefrigerated = in.nextInt();
				int maxNofLiquid = in.nextInt();
				double fuelConsumption = in.nextDouble();
				ships.add(new Ship(shipID, ports.get(pID), maxWeight, maxNofContainer, maxNofHeavy, maxNofRefrigerated, maxNofLiquid, fuelConsumption));
				shipID++;
			}
			else if (input == 3) {
				double X = in.nextDouble();
				double Y = in.nextDouble();
				ports.add(new Port(portID,X,Y));
				portID++;
			}
			else if (input == 4) {
				int sID = in.nextInt();
				int cID = in.nextInt();
				ships.get(sID).load(container.get(cID));
			}
			else if (input == 5) {
				int sID = in.nextInt();
				int cID = in.nextInt();
				ships.get(sID).unLoad(container.get(cID)); 
			}
			else if (input == 6) {
				int sID = in.nextInt();
				int pID = in.nextInt();
				ships.get(sID).sailTo(ports.get(pID));				
			}
			else if (input == 7) {
				int ID = in.nextInt();
				double fuel = in.nextDouble();
				ships.get(ID).reFuel(fuel);
			}
		}
				
		for (Port p : ports) {
			out.println("Port"+" "+ p.getID()+": ("+String.format("%.2f",p.getX())+", "+String.format("%.2f",p.getY())+")");
			ArrayList<Container> cList = p.getContainers();
			ArrayList<Ship> shipList = p.getCurrent();
			String basicIDs = "";
			String heavyIDs = "";
			String refrigeratedIDs = "";
			String liquidIDs = "";
			for (Container c :cList) {
				if(c instanceof BasicContainer) {	
					basicIDs = basicIDs+" "+c.getID();
				}
				else if (c instanceof RefrigeratedContainer) {
					refrigeratedIDs = refrigeratedIDs+" "+c.getID();
				}
				else if (c instanceof LiquidContainer) {
					liquidIDs = liquidIDs+" "+c.getID();
				}
				else if(c instanceof HeavyContainer) {
					heavyIDs = heavyIDs+" "+c.getID();
				}		
			}
			if (basicIDs != "") {
				out.println("\s"+"\s"+"BasicContainer:"+basicIDs);
			}
			if (heavyIDs != "") {
				out.println("\s"+"\s"+"HeavyContainer:"+heavyIDs);
			}
			if (refrigeratedIDs != "") {
				out.println("\s"+"\s"+"RefrigeratedContainer:"+refrigeratedIDs);
			}
			if (liquidIDs != "") {
				out.println("\s"+"\s"+"LiquidContainer:"+liquidIDs);
			}	
			for (Ship s : shipList) {
				out.println("\s"+"\s"+"Ship"+" "+s.getID()+": "+ String.format("%.2f",s.getFuel()));
				ArrayList<Container> contList = s.getCurrentContainers();
				basicIDs = "";
				heavyIDs = "";
				refrigeratedIDs = "";
				liquidIDs = "";
				for (Container c : contList) {
					if(c instanceof BasicContainer) {	
						basicIDs = basicIDs+" "+c.getID();
					}
					else if (c instanceof RefrigeratedContainer) {
						refrigeratedIDs = refrigeratedIDs+" "+c.getID();
					}
					else if (c instanceof LiquidContainer) {
						liquidIDs = liquidIDs+" "+c.getID();
					}
					else if(c instanceof HeavyContainer) {
						heavyIDs = heavyIDs+" "+c.getID();
					}		
				}
				if (basicIDs != "") {
					out.println("\s"+"\s"+"\s"+"\s"+"BasicContainer:"+basicIDs);
				}
				if (heavyIDs != "") {
					out.println("\s"+"\s"+"\s"+"\s"+"HeavyContainer:"+heavyIDs);
				}
				if (refrigeratedIDs != "") {
					out.println("\s"+"\s"+"\s"+"\s"+"RefrigeratedContainer:"+refrigeratedIDs);
				}
				if (liquidIDs != "") {
					out.println("\s"+"\s"+"\s"+"\s"+"LiquidContainer:"+liquidIDs);
				}	
			}
		}

		in.close();
		out.close();
	}
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

