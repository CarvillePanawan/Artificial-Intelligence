package prelims;

public class Vehicle {
    private int weight;
    private int speed;
    
    public Vehicle(int weight, int speed) {
        this.weight = weight;
        this.speed = speed;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    @Override
    public String toString() {
        return "["+weight+","+speed+"]";
    }
}
