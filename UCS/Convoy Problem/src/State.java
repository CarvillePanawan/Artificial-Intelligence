public class State {
	private int trucksPassed, batchSpeed, batchWeight;
	private State parent;
	
    public State(int trucksPassed, int batchSpeed, int batchWeight, State parent) {
    	this.trucksPassed = trucksPassed;
    	this.batchSpeed = batchSpeed;
    	this.batchWeight = batchWeight;
    	this.parent = parent;
    }
    
	public int getTrucksPassed() {
		return trucksPassed;
	}

	public void setTrucksPassed(int trucksPassed) {
		this.trucksPassed = trucksPassed;
	}

	public int getBatchSpeed() {
		return batchSpeed;
	}

	public void setBatchSpeed(int batchSpeed) {
		this.batchSpeed = batchSpeed;
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
	
}