import java.util.*;

class State{
    private ArrayList<Truck> vehiclesLeft, vehiclesPassed;
	private int batchWeight;
    private double pathTime, batchTime;
	private State parent;
	
    public State(ArrayList<Truck> vehiclesLeft, ArrayList<Truck> vehiclesPassed, double pathTime, int batchTime, int batchWeight, State parent) {
    	this.vehiclesLeft = vehiclesLeft;
    	this.vehiclesPassed = vehiclesPassed;
    	this.pathTime = pathTime;
    	this.batchTime = batchTime;
    	this.batchWeight = batchWeight;
    	this.parent = parent;
    }
    
	public ArrayList<Truck> getVehiclesLeft() {
		return vehiclesLeft;
	}

	public void setVehiclesLeft(ArrayList<Truck> vehiclesLeft) {
		this.vehiclesLeft = vehiclesLeft;
	}
    
	public ArrayList<Truck> getVehiclesPassed() {
		return vehiclesPassed;
	}

	public void setVehiclesPassed(ArrayList<Truck> vehiclesPassed) {
		this.vehiclesPassed = vehiclesPassed;
	}

	public double getPathTime() {
		return pathTime;
	}

	public void setPathTime(double pathTime) {
		this.pathTime = pathTime;
	}
	
	public double getBatchTime() {
		return batchTime;
	}

	public void setBatchTime(double batchTime) {
		this.batchTime = batchTime;
	}

	public int getBatchWeight() {
		return batchWeight;
	}

	public void setBatchWeight(int batchWeight) {
		this.batchWeight = batchWeight;
	}

	public State getParent() {
		return parent;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

    public boolean isGoal(int totalVehicles){
        return vehiclesLeft.size() == 0;
    }

    public ArrayList<State> expand(int bridgeLength, int maxLoad){
        ArrayList<State> successors = new ArrayList<State>();
        int tempBatchWeight = 0;
        int tempBatchTime = 0;
        double tempPathTime = 0.0;
        ArrayList<Truck> tempLeft = new ArrayList<Truck>(vehiclesLeft);
        ArrayList<Truck> tempPassed = new ArrayList<Truck>(vehiclesPassed);

        while (tempLeft.size() > 0 && (tempBatchWeight + tempLeft.get(0).getWeight()) <= maxLoad){
            tempPassed.add(tempLeft.get(0));
            tempBatchWeight += tempLeft.get(0).getWeight();
            if(tempBatchTime > tempLeft.get(0).getSpeed() || tempBatchTime == 0){
                tempBatchTime = tempLeft.get(0).getSpeed();
            }
            tempLeft.remove(0);
            tempPathTime = ((double)bridgeLength / (double)tempBatchTime) * 60;

            if(tempLeft.size() > 0){
                if((tempBatchWeight + tempLeft.get(0).getWeight()) <= maxLoad){
                    
                } else {
                    tempPathTime = this.pathTime + tempPathTime;
                }
            } else {
                tempPathTime = this.pathTime + tempPathTime;
            }
            successors.add(new State(tempLeft, tempPassed, tempPathTime, tempBatchTime, tempBatchWeight, this));
        } 
        return successors;
    }

    @Override
    public String toString() {
        return String.format("--------------------------------------------------------------------------------\n\nVehicles passed: %d \nVehicles left: %d \nBatch Weight: %d \nBatch Time: %2.1f \nTotal Path Time: %2.1f\n\n--------------------------------------------------------------------------------", vehiclesPassed.size(), vehiclesLeft.size(), batchWeight, batchTime, pathTime);
    }
}//end of Class State

class Truck{
    private int weight;
    private int speed;
    
    public Truck() {
        weight = 0;
        speed = 0;
    }
    
    public Truck(int weight, int speed) {
        this.weight = weight;
        this.speed = speed;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    @Override
    public String toString() {
        return this.weight +" "+ this.speed;
    }

    public int compareTo(Truck x) {
        if(this.getSpeed() > x.getSpeed()) {
            return 1;
        } else if(this.getSpeed() < x.getSpeed()) {
            return -1;
        } else {
            return 0;
        }
    }
}//end of Class Truck

public class Convoy{
    public static void main(String[] args){
        ArrayList<Truck> vehicleList = new ArrayList<Truck>();
        Scanner sc = new Scanner(System.in);

        int maxLoad = Integer.parseInt(sc.next());
        int bridgeLength = Integer.parseInt(sc.next());
        int vehicleTotal = Integer.parseInt(sc.next());

        for(int i = 0; i < vehicleTotal; i++){
            vehicleList.add(new Truck(Integer.parseInt(sc.next()), Integer.parseInt(sc.next())));
        }

        State initialState = new State(vehicleList, new ArrayList<Truck>(), 0, 0, 0, null);

        ArrayList<State> seen = new ArrayList<>();

        ArrayList<State> frontier = new ArrayList<>();
        frontier.add(initialState);

        int totalStatesVisited = 0;
        int maxFrontierSize = 1;

        while (frontier.size() > 0) {
            State currentState = frontier.remove(frontier.size() - 1);
            
            totalStatesVisited++;

            seen.add(currentState);

            if (currentState.isGoal(vehicleTotal)) {
                showSolution(currentState, totalStatesVisited, maxFrontierSize);
                return;

            } else {
                ArrayList<State> successorStates = currentState.expand(bridgeLength, maxLoad);

                for (State state : successorStates) {
                    if (seen.indexOf(state) == -1) {
                        frontier.add(state);
                    }
                }
                sc.nextLine();

                maxFrontierSize = Math.max(maxFrontierSize, frontier.size());
            }
        }

        System.out.println("No Solution.");

    }

    public static void showSolution(State state, int totalStatesVisited, int maxFrontierSize) {
        ArrayList<State> path = new ArrayList<>();

        while (state != null) {
            path.add(0, state);
            state = state.getParent();
        }

        System.out.println("Solution:");
        for (State st : path) {
            System.out.println(st);
        }

        System.out.printf("\nTotal States Visited: %d\n", totalStatesVisited);
        System.out.printf("Maximum Size of Frontier: %d\n", maxFrontierSize);
    }

}//end of Class Convoy