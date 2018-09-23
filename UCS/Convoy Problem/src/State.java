import java.util.ArrayList;

public class State implements Comparable<State>{
	private ArrayList<Truck> vehiclesLeft;
	private int vehiclesPassed, pathTime, batchTime, batchWeight;
	private State parent;
	
    public State(ArrayList<Truck> vehiclesLeft, int vehiclesPassed, int pathTime, int batchTime, int batchWeight, State parent) {
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
    
	public int getVehiclesPassed() {
		return vehiclesPassed;
	}

	public void setVehiclesPassed(int trucksPassed) {
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
}