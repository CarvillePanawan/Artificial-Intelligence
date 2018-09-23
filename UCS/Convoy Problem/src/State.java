public class State {
	private int trucksPassed, pathTime, batchWeight;
	private State parent;
	private String description;
	
    public State(int trucksPassed, int pathTime, int batchWeight, State parent, String description) {
    	this.trucksPassed = trucksPassed;
    	this.pathTime = pathTime;
    	this.batchWeight = batchWeight;
    	this.parent = parent;
    	this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}