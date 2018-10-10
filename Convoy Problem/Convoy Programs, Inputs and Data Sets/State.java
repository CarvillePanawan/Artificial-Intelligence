import java.util.ArrayList;
import java.lang.Math;

public class State implements Comparable<State>{
	private ArrayList<Vehicle> vehiclesLeft, vehiclesPassed;
	private float pathTime, batchTime;
	private int batchWeight;
	private State parent;
	
    public State(ArrayList<Vehicle> vehiclesLeft, ArrayList<Vehicle> vehiclesPassed, float pathTime, float batchTime, int batchWeight, State parent) {
    	this.vehiclesLeft = vehiclesLeft;
    	this.vehiclesPassed = vehiclesPassed;
    	this.pathTime = pathTime;
    	this.batchTime = batchTime;
    	this.batchWeight = batchWeight;
    	this.parent = parent;
    }
    
	public ArrayList<Vehicle> getVehiclesLeft() {
		return vehiclesLeft;
	}

	public void setVehiclesLeft(ArrayList<Vehicle> trucksLeft) {
		this.vehiclesLeft = trucksLeft;
	}
    
	public ArrayList<Vehicle> getVehiclesPassed() {
		return vehiclesPassed;
	}

	public void setVehiclesPassed(ArrayList<Vehicle> trucksPassed) {
		this.vehiclesPassed = trucksPassed;
	}

	public float getPathTime() {
		return pathTime;
	}

	public void setPathTime(float pathTime) {
		this.pathTime = pathTime;
	}
	
	public float getBatchTime() {
		return batchTime;
	}

	public void setBatchTime(float batchSpeed) {
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
        return Math.round(this.getBatchTime()) - Math.round(other.getBatchTime());
    }
    
    public ArrayList<State> expand(int maxLoad, int bridgeLength){
    	ArrayList<State> successorStates = new ArrayList<State>();
    	if(this.getVehiclesLeft().size()!=0) {
    		
    		ArrayList<Vehicle> left = new ArrayList<Vehicle>();
    		left.addAll(this.getVehiclesLeft());
    		ArrayList<Vehicle> passed = new ArrayList<Vehicle>();
    		passed.addAll(this.getVehiclesPassed());
        	State parent = new State(left, passed, this.getPathTime(), this.getBatchTime(), this.getBatchWeight(), this.getParent());
        	
    		for(int x = 0; x < parent.vehiclesLeft.size() && weightChecker(x) <= maxLoad; x++) {
    			
        		ArrayList<Vehicle> left1 = new ArrayList<Vehicle>();
        		left1.addAll(parent.getVehiclesLeft());
        		ArrayList<Vehicle> passed1 = new ArrayList<Vehicle>();
        		passed1.addAll(parent.getVehiclesPassed());
            	State successor = new State(left1, passed1, parent.getPathTime(), parent.getBatchTime(), parent.getBatchWeight(), parent.getParent());
            	
            	int slowestSpeed = successor.getVehiclesLeft().get(0).getSpeed();
            	int totalBatchWeight = 0;
            	
        		for(int y = 0; y <= x; y++) {
        			successor.getVehiclesPassed().add(successor.getVehiclesLeft().get(0));
        			totalBatchWeight += successor.getVehiclesLeft().get(0).getWeight();
        			if (slowestSpeed > successor.getVehiclesLeft().get(0).getSpeed()) {
        				slowestSpeed = successor.getVehiclesLeft().get(0).getSpeed();
        			}
        			successor.getVehiclesLeft().remove(0);
        		}
        		
        		successor.setBatchTime(((float)bridgeLength / slowestSpeed)*60);
        		successor.setBatchWeight(totalBatchWeight);
        		successor.setPathTime(parent.getPathTime() + successor.getBatchTime());
        		successor.setParent(parent);
        		
        		successorStates.add(successor);
        	}
        	return successorStates;
    	} else {
    		return null;
    	}
    }
    
    public int weightChecker(int limit) {
    	int weight = 0;
    	for(int x=0; x<=limit; x++) {
    		weight += this.vehiclesLeft.get(x).getWeight();
    	}
    	return weight;
    }
    
	public boolean isGoal() {
		if(this.getVehiclesLeft().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return ("------------------------------\n" + "Vehicles Passed: " + this.vehiclesPassed.size() + ";\n" + "Vehicles Left: " + this.vehiclesLeft.size() + ";\n" + "Batch Weight: " + this.batchWeight + ";\n" + "Batch Time: " + this.batchTime + ";\n" + "Total Path Time: " + this.pathTime);
	}
}