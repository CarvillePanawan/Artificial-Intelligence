import java.util.ArrayList;

public class State implements Comparable<State>{
	private ArrayList<Truck> vehiclesLeft, vehiclesPassed;
	private int pathTime, batchTime, batchWeight;
	private State parent;
	
    public State(ArrayList<Truck> vehiclesLeft, ArrayList<Truck> vehiclesPassed, int pathTime, int batchTime, int batchWeight, State parent) {
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

	public void setVehiclesLeft(ArrayList<Truck> trucksLeft) {
		this.vehiclesLeft = trucksLeft;
	}
    
	public ArrayList<Truck> getVehiclesPassed() {
		return vehiclesPassed;
	}

	public void setVehiclesPassed(ArrayList<Truck> trucksPassed) {
		this.vehiclesPassed = trucksPassed;
	}

	public int getPathTime() {
		return pathTime;
	}

	public void setPathTime(int pathTime) {
		this.pathTime = pathTime;
	}
	
	public int getBatchTime() {
		return batchTime;
	}

	public void setBatchTime(int batchSpeed) {
		this.batchTime = batchSpeed;
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
	
    public int compareTo(State other) {
        return this.getBatchTime() - other.getBatchTime();
    }
    
    public ArrayList<State> expand(int maxLoad, int bridgeLength){
    	ArrayList<State> successorStates = new ArrayList<State>();
    	for(int x = 0; x < this.vehiclesLeft.size() && weightChecker(x) <= maxLoad; x++) {
        	State successor = this;
        	int slowestSpeed = 0;
        	int totalBatchWeight = 0;
        	
    		for(int y = 0; y <= x; y++) {
    			successor.getVehiclesPassed().add(successor.getVehiclesLeft().get(0));
    			totalBatchWeight += successor.getVehiclesLeft().get(0).getWeight();
    			if (slowestSpeed < successor.getVehiclesLeft().get(0).getSpeed()) {
    				slowestSpeed = successor.getVehiclesLeft().get(0).getSpeed();
    			}
    			successor.getVehiclesLeft().remove(0);
    		}
    		
    		successor.setBatchTime((bridgeLength / slowestSpeed)*60);
    		successor.setBatchWeight(totalBatchWeight);
    		successor.setPathTime(this.getPathTime() + successor.getBatchTime());
    		successor.setParent(this);
    		
    		successorStates.add(successor);
    	}
    	return successorStates;
    }
    
    public int weightChecker(int limit) {
    	int weight = 0;
    	for(int x=0; x<=limit; x++) {
    		weight += this.vehiclesLeft.get(x).getWeight();
    	}
    	return weight;
    }
}