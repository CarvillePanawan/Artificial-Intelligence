package Trucks;

import java.util.ArrayList;
import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        try {
            Test amazingProgram = new Test();
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
        
    }//Solution
}//Class
