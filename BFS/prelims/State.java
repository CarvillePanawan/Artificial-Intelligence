package prelims;

import java.util.ArrayList;

public class State {
    private Vehicle[] a;
    private int depth;
    private double time;
    private int length;
    private State parent;
    
    public State(Vehicle[] a, int length, State parent, int d) {
        this.a = a;
        this.length = length;
        this.parent = parent;
        this.depth = d;
    }
    
    public State(Vehicle[] a, State parent) {
        this.a = a;
        this.parent = parent;
    }
    
    public State(Vehicle[] a, State parent, int d) {
        this.a = a;
        this.parent = parent;
        this.depth = d;
    }
    
    public State getParent() {
        return parent;
    }
    
    public int getDepth() {
        return depth;
    }
    
    public double getTime() {
        return time;
    }
    
    public void setTime() {
        int bufferSpeed = 1000;
        double tempTime = 0;
        
        for(int i = 0; i < a.length; i++) {
            if(a[i].getSpeed() < bufferSpeed) {
                bufferSpeed = a[i].getSpeed();
                tempTime += bufferSpeed;
            }
        }
        
        time = (length/tempTime)*60;
    }
    
    public Vehicle[] getA() {
        return a;
    }
    
    public boolean isGoal() {
        return a[0].getSpeed() == 0 && a.length == 1;
    }
    
    public ArrayList<State> expand(Vehicle[] x, int maxLoad, int bridgeLength) {
        ArrayList<State> successors = new ArrayList<>();
        Vehicle[] z = new Vehicle[x.length];
        int index = 0;
        
        for(int i = 0, j = x.length-1; i < z.length; i++, j--) {
            z[i] = x[j];
        }
        
        while(index < x.length) {
            Vehicle[] y = new Vehicle[z.length - index];
            
            for(int i = y.length-1; i >= 0; i--) {
                y[i] = z[i];
            }
            
            successors.add(new State(y, bridgeLength, this, depth++));
            index++;
        }
        
        return successors;
    }
    
    public void showVehicles() {
        System.out.print("[Depth:"+depth+"] ");
        System.out.print("{");
        for(int i = 0; i < a.length; i++) {
            System.out.print(i == a.length-1 ? a[i].toString(): a[i].toString()+",");
        }
        System.out.print("}");
        System.out.println();
    }
}
