import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.File;
import java.io.FileNotFoundException;

public class Convoy {
	public static void main(String[] args) {
		File inputs = new File("inputs.txt");
		
		try {
			Scanner reader = new Scanner(inputs);
			int maxLoad = reader.nextInt();
			int bridgeLength = reader.nextInt();
			int totalVehicles = reader.nextInt();
			ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
			
			for(int i = 1; reader.hasNextLine() && i <= totalVehicles && i <= 1000; i++) {
				vehicleList.add(new Vehicle(reader.nextInt(), reader.nextInt()));
			}
			reader.close();
			
			PriorityQueue<State> frontier = new PriorityQueue<State>();
			State initialState = new State(vehicleList, new ArrayList<Vehicle>(), 0, 0, 0, null);
			frontier.add(initialState);
			int maxFrontierSize = 1;
			
			while (frontier.size() > 0){
				State currentState = frontier.poll();
				if(currentState.isGoal()) {
					showSolution(currentState, maxLoad, bridgeLength, totalVehicles, maxFrontierSize);
					break;
				} else {
					ArrayList<State> successorStates = currentState.expand(maxLoad, bridgeLength);
					if(successorStates != null) {
						frontier.addAll(successorStates);
		                maxFrontierSize = Math.max(maxFrontierSize, frontier.size());
					}
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("File not Found");
		}
	}
	
	public static void showSolution(State solution, int maxLoad, int bridgeLength, int totalVehicles, int maxFrontierSize) {
		if(solution == null) {
			System.out.println("There is no solution");
		} else {
			ArrayList<State> path = new ArrayList<State>();
			State currentState = solution;
			while (currentState != null) {
				path.add(0, currentState);
				currentState = currentState.getParent();
			}
			
			System.out.println("-----------Solution-----------");
			System.out.printf("Minimum Time Utilized: %.1f%n", solution.getPathTime());
			System.out.println("Maximum Bridge Load: " + maxLoad);
			System.out.println("Bridge Length: " + bridgeLength);
			System.out.println("Number of Vehicles: " + totalVehicles);
			System.out.println("Maximum Frontier Size: " + maxFrontierSize);
			
			for(State st : path) {
				System.out.println(st.toString());
			}
			
		}
	}
}
