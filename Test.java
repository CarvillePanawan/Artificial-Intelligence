package Trucks;

import java.util.*;

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
        
//        for(Truck truck: trucks) {
//            System.out.println(truck.toString());
//        }
        
//        solution(maxLoad, bridgeLength, truckTotal, trucks);
        
        sc.close();
    }
    
//    public void solution(int maxLoad, int bridgeLength, int truckTotal, Truck[] trucks) {
//        int bufferWeight = 0;
//        int lastIndex = 0;
//        ArrayList<Truck> batches = new ArrayList<Truck>();
//        
//        while(true) {
//            if(bufferWeight < maxLoad) {
//                if((bufferWeight += trucks[lastIndex].getWeight()) <= maxLoad) {
//                    lastIndex++;
//                }
//            } else {
//                batches.add(new Truck(bufferWeight, 0));
//            }
//            
//            if(lastIndex == truckTotal-1) {
//                break;
//            }
//        }
//        
//        for(int i = 0; i < batches.size(); i++) {
//            System.out.println(batches.get(i).toString());
//        }
//    }//Solution
}//Class
