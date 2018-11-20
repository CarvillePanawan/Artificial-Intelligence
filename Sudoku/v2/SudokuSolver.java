package sudoku.v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SudokuSolver {
    public static void main(String[] args) {
        try {
            SudokuSolver solver = new SudokuSolver();
            solver.run();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        int[][] puzzle = {
                {0, 0, 0, 6, 3, 0, 0, 0, 9},
                {0, 0, 9, 0, 5, 0, 0, 4, 6},
                {0, 8, 0, 1, 9, 0, 0, 0, 2},
                {6, 0, 0, 5, 0, 0, 1, 0, 3},
                {8, 0, 0, 0, 0, 0, 0, 0, 4},
                {4, 0, 5, 0, 0, 3, 0, 0, 7},
                {5, 0, 0, 0, 2, 9, 0, 3, 0},
                {2, 4, 0, 0, 1, 0, 9, 0, 0},
                {9, 0, 0, 0, 8, 5, 0, 0, 0}
        };
        State initialState = new State(puzzle);
        
        initialState.show();
        initialState.populate();
        
        ArrayList<State> successors = initialState.expand(); 
        State currentState = successors.get(0);
        int countdown = 500;
        int resets = 0;
        Random randomizer = new Random();
        
        while(true) {
            int randomSuccessor = randomizer.nextInt(successors.size());
            State tempState = successors.get(randomSuccessor);
            
            if(currentState.getHeuristic() > tempState.getHeuristic()) {
                if(!Arrays.deepEquals(currentState.getPuzzle(), tempState.getPuzzle())) {
                    currentState = tempState;
                } else {
                    continue;
                }
            }
            
//            System.out.println("Chosen Successor: "+randomSuccessor);
//            System.out.println("Heuristic: "+currentState.getHeuristic());
//            System.out.println("Counter: "+countdown);
//            currentState.show();
            
            if(currentState.isGoal()) {
                System.out.println("Solved");
                System.out.println("Number of restarts: "+resets);
                currentState.show();
                break;
            } else {
                successors = currentState.expand();
                if(countdown-- < 0) {
//                    System.out.println("Reset");
                    
                    successors = initialState.expand();
                    randomSuccessor = randomizer.nextInt(successors.size());
                    currentState = successors.get(randomSuccessor);
                    
                    resets++;
                    countdown = 500;
                }
            }
        }
        
        sc.close();
    }
}
