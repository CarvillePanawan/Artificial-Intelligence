import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class ConvoyBFS_Left {
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
			
			ArrayList<State> frontier = new ArrayList<State>();
			State initialState = new State(vehicleList, new ArrayList<Vehicle>(), 0, 0, 0, null);
			frontier.add(initialState);
			int maxFrontierSize = 1;
			int totalStatesVisited = 0;
			float averageBranchingFactor = 0;
			
			while (frontier.size() > 0){
				State currentState = frontier.remove(0);
				totalStatesVisited ++;
				if(currentState.isGoal()) {
					averageBranchingFactor = averageBranchingFactor/(totalStatesVisited-1);
					showSolution(currentState, maxLoad, bridgeLength, totalVehicles, maxFrontierSize, totalStatesVisited, averageBranchingFactor);
					break;
				} else {
					ArrayList<State> successorStates = currentState.expand(maxLoad, bridgeLength);
					if(successorStates != null) {
						frontier.addAll(frontier.size(),successorStates);
		                maxFrontierSize = Math.max(maxFrontierSize, frontier.size());
		                averageBranchingFactor += successorStates.size();
					}
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("File not Found");
		}
	}
	
	public static void showSolution(State solution, int maxLoad, int bridgeLength, int totalVehicles, int maxFrontierSize, int totalStatesVisited, float averageBranchingFactor) {
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
//			System.out.printf("Minimum Time Utilized: %.1f%n", solution.getPathTime());
//			System.out.println("Maximum Bridge Load: " + maxLoad);
//			System.out.println("Bridge Length: " + bridgeLength);
//			System.out.println("Number of Vehicles: " + totalVehicles);
			System.out.println("Total States Visited: " + totalStatesVisited);
			System.out.println("Maximum Frontier Size: " + maxFrontierSize);
//			System.out.printf("Average Branching Factor: %.2f%n", averageBranchingFactor);
			System.out.println("------------------------------");
//			System.out.println("          Path Taken");
			
			
//			for(State st : path) {
//				System.out.println(st.toString());
//			}
			
		}
	}
}
