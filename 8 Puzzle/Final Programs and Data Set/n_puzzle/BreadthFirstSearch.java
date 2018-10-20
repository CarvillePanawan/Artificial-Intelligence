package n_puzzle;

import java.util.ArrayList;
import java.util.Arrays;

public class BreadthFirstSearch {
    public static void main(String[] args) {
        try {
            BreadthFirstSearch amazingProgram = new BreadthFirstSearch();
            amazingProgram.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//main
    
    public void run() {
        byte[] puzzle = new byte[] {5, 3, 6, 2, 7, 1, 4, 0, 8};
        byte[] goal = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 0};
        
        State initialState = new State(puzzle, null, 0);
        
        ArrayList<State> frontier = new ArrayList<>();
        frontier.add(initialState);
        
        int totalStatesVisited = 0;
        int maxFrontierSize = 1;
        
        while(frontier.size() > 0) {
            State currentState = frontier.remove(0);
            
            totalStatesVisited++;
            
            if(currentState.isGoal(goal)) {
                showSolution(currentState, totalStatesVisited, maxFrontierSize);
                
                return;
            } else {
                byte[] temp = currentState.getPuzzle();
                ArrayList<State> successorStates = currentState.expandUninformed(temp);
                
                for(int i = 0; i < successorStates.size(); i++) {
                    if(!explored(successorStates.get(i).getPuzzle(), frontier)) {
                        frontier.add(successorStates.get(i));
                    }
                }
                
                maxFrontierSize = Math.max(maxFrontierSize, frontier.size());
            }
        }
        
        System.out.println("No Solution");
    }//run
    
    public boolean explored(byte[] x, ArrayList<State> y) {
        int counter = 0;
        
        for(int i = 0; i < y.size(); i++) {
            if(Arrays.equals(y.get(i).getPuzzle(), x)) {
                counter++;
            }
        }
        
        return counter > 0 ? true : false;
    }//explored
    
    public static void showSolution(State state, int totalStatesVisited, int maxFrontierSize) {
        ArrayList<State> path = new ArrayList<>();
        int totalMoves = 0;

        while (state != null) {
            path.add(0, state);
            totalMoves++;
            state = state.getParent();
        }

        System.out.printf("\nTotal States Visited: %d\n", totalStatesVisited);
        System.out.printf("Maximum Size of Frontier: %d\n", maxFrontierSize);
        System.out.printf("Total Number of Moves: %d\n", totalMoves-1);
        System.out.println();
        
        System.out.println("Solution:");
        for (State st : path) {
            st.showPuzzle();
        }
    }//showSolutions
}//class
