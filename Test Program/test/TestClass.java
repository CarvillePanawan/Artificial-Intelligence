package test;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 100 5 10 
 * 40 25 
 * 50 20 
 * 50 20 
 * 70 10 
 * 12 50 
 * 9 70 
 * 49 30 
 * 38 25 
 * 27 50 
 * 19 70
 */

public class TestClass {
    public static void main(String[] args) {
        try {
            TestClass amazingProgram = new TestClass();
            amazingProgram.run();
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }//Main
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        int maxLoad = 0;
        int bridgeLength = 0;
        int truckTotal = 0;
        
        maxLoad = sc.nextInt();
        bridgeLength = sc.nextInt();
        truckTotal = sc.nextInt();
        
        Truck[] trucks = new Truck[truckTotal];
        
        for(int i = 0; i < truckTotal; i++) {
            trucks[i] = new Truck();
            trucks[i].setWeight(sc.nextInt());
            trucks[i].setSpeed(sc.nextInt());
        }
        
        solution(maxLoad, bridgeLength, trucks);
        
        sc.close();
    }//Run
    
    public void solution(int maxLoad, int bridgeLength, Truck[] trucks) {
        int totalTrucks = trucks.length;
        int bufferWeight = 0;
        int bufferSpeed = 1000;
        int index = 0;
        int tempWeight = 0;
        ArrayList<Truck> batches = new ArrayList<Truck>();
        
        while(index != totalTrucks) {
            do {
                if(index == totalTrucks) {
                    break;
                }
                
                tempWeight = bufferWeight + trucks[index].getWeight();            
                
                if(tempWeight > maxLoad) {
                    batches.add(new Truck(bufferWeight, bufferSpeed));
                    tempWeight = 0;
                    bufferWeight = 0;
                    bufferSpeed = 1000;
                    index--;
                } else {
                    bufferWeight += trucks[index].getWeight();
                }
                
                if(trucks[index].getSpeed() < bufferSpeed) {
                    bufferSpeed = trucks[index].getSpeed();
                }
                
                index++;
            } while(bufferWeight < maxLoad);

            batches.add(new Truck(bufferWeight, 0));
            bufferWeight = 0;
            bufferSpeed = 1000;
        }
        
        for(int i = 0; i < batches.size(); i++) {
            System.out.println("Batch "+(i+1)+": "+batches.get(i).toString());
        }
    }//Solution
}//Class
