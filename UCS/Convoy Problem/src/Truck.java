public class Truck implements Comparable<Truck> {
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

    @Override
    public int compareTo(Truck x) {
        if(this.getSpeed() > x.getSpeed()) {
            return 1;
        } else if(this.getSpeed() < x.getSpeed()) {
            return -1;
        } else {
            return 0;
        }
    }
}//Class
