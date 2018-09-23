import java.util.ArrayList;

public class State implements Comparable<State>{
	private ArrayList<Truck> trucksLeft;
	private int trucksPassed, pathTime, batchTime, batchWeight;
	private State parent;
	
    public State(int trucksPassed, int pathTime, int batchWeight, State parent) {
    	this.trucksPassed = trucksPassed;
    	this.pathTime = pathTime;
    	this.batchWeight = batchWeight;
    	this.parent = parent;
    }
    
	public ArrayList<Truck> getTrucksLeft() {
		return trucksLeft;
	}

	public void setTrucksLeft(ArrayList<Truck> trucksLeft) {
		this.trucksLeft = trucksLeft;
	}
    
	public int getTrucksPassed() {
		return trucksPassed;
	}

	public void setTrucksPassed(int trucksPassed) {
		this.trucksPassed = trucksPassed;
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
}