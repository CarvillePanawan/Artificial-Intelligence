package prelims;

import java.util.Scanner;
import java.util.ArrayList;

public class Convoy {    
    double totalTime = 0;
    
    public static void main(String[] args) {
        try {
            Convoy amazingProgram = new Convoy();
            amazingProgram.run();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        int maxLoad = sc.nextInt();
        int bridgeLength = sc.nextInt();
        int totalVehicles = sc.nextInt();
        
        Vehicle[] vehicles = new Vehicle[totalVehicles+1];
        
        for(int i = 0; i < totalVehicles; i++) {
            vehicles[i] = new Vehicle(sc.nextInt(), sc.nextInt());
        }
        vehicles[totalVehicles] = new Vehicle(0, 0);
        
        System.out.println();
        
        solution(maxLoad, bridgeLength, totalVehicles, vehicles);
//        tree(maxLoad, bridgeLength, totalVehicles, vehicles);
        
        sc.close();
    }
    
    public void solution(int maxLoad, int bridgeLength, int totalTrucks, Vehicle[] vehicles) {
        State initialState = new State(vehicles, null);
        
        ArrayList<State> frontier = new ArrayList<>();
        frontier.add(initialState);
        
        int totalStatesVisited = 0;
        int maxFrontierSize = 1;
        
        System.out.println("Input: ");
        System.out.println("Maximum bridge load: "+maxLoad);
        System.out.println("Bridge length: "+bridgeLength);
        System.out.println("Total number of trucks: "+totalTrucks);
        
        System.out.println();
        System.out.println("Solution:");
        
        while(frontier.size() > 0) {
            State currentState = frontier.remove(0);
                
            totalStatesVisited++;
            
            if(currentState.isGoal()) {
                System.out.println("Total States Visited: "+ totalStatesVisited);
                System.out.println("Maximum Size of Frontier: "+ maxFrontierSize);
                return;
            } else {
                ArrayList<State> successorStates = currentState.expand(vehicles, maxLoad, bridgeLength);
                frontier.addAll(0, successorStates);
                
                Vehicle[] temp = shorten(vehicles, maxLoad, bridgeLength);
                
                if(temp.length == 0) {
                    System.out.println();
                    System.out.println("Total Time: "+ totalTime);
                    System.out.println("Total States Visited: "+ totalStatesVisited);
                    System.out.println("Maximum Size of Frontier: "+ maxFrontierSize);
                    return;
                }
                
                vehicles = temp;
                
                maxFrontierSize = Math.max(maxFrontierSize, frontier.size());              
            }
        }
        System.out.println("No Solution");
    }
    
    public void tree(int maxLoad, int bridgeLength, int totalTrucks, Vehicle[] vehicles) {
        State initialState = new State(vehicles, null);
        ArrayList<State> frontier = new ArrayList<>();
        frontier.add(initialState);

        System.out.println();
        System.out.println("Search Tree:");
        while(frontier.size() > 0) {
            State currentState = frontier.remove(0);
            
            ArrayList<State> successorStates = currentState.expand(vehicles, maxLoad, bridgeLength);
            frontier.addAll(successorStates);
            
            Vehicle[] temp = shortened(vehicles, maxLoad, bridgeLength);
            if(temp.length == 0) {
                return;
            }
            vehicles = temp;
            
            for(int i = 0; i < successorStates.size(); i++) {
                successorStates.get(i).showVehicles();
            }
            System.out.println();
        }
    }
    
    public Vehicle[] shorten(Vehicle[] a, int maxLoad, int bridgeLength) {
        int counter = 0;
        double time = 0;
        int bufferWeight = 0;
        double tempLength = bridgeLength;
        
        while(counter < a.length) {
            bufferWeight += a[counter].getWeight();
            
            if(bufferWeight > maxLoad) {
                break;
            }        
            counter++;
        }

        Vehicle[] x = new Vehicle[a.length-counter];
        
        int bufferSpeed = 1000;
        for(int i = 0; i < counter; i++) {
            if(a[i].getSpeed() != 0) {
                System.out.print(a[i].toString());
            }
            if(a[i].getSpeed() < bufferSpeed && a[i].getSpeed() != 0) {
                bufferSpeed = a[i].getSpeed();
            }
        }
        if(bufferSpeed != 0) {
            time = (tempLength/bufferSpeed)*60;
            System.out.print("[Time: "+time+"]");
            totalTime += time;
        } else {
            System.out.print("");
        }
        
        System.out.print(" <-- ");
        
        for(int i = 0; i < x.length; i++) {
            x[i] = a[counter];
            if(x[i].getSpeed() != 0) {
                System.out.print(x[i].toString());
            }
            if(x[i].getSpeed() == 0) {
                break;
            }
            counter++;
        }

        System.out.println();
        return x;
    }
    
    public Vehicle[] shortened(Vehicle[] a, int maxLoad, int bridgeLength) {
        int counter = 0;
        int bufferWeight = 0;
        
        while(counter < a.length) {
            bufferWeight += a[counter].getWeight();
            
            if(bufferWeight > maxLoad) {
                break;
            }        
            counter++;
        }

        Vehicle[] x = new Vehicle[a.length-counter];
        
        for(int i = 0; i < x.length; i++) {
            x[i] = a[counter];
            if(x[i].getSpeed() == 0) {
                break;
            }
            counter++;
        }
        return x;
    }
}
