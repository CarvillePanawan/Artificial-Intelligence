package sudoku.v2;

import java.util.ArrayList;

public class State implements Comparable<State>{
    int[][] puzzle;
    int heuristic;
    
    public State(int[][] puzzle) {
        this.puzzle = puzzle;
        this.heuristic = computeHeuristic();
    }

    public int[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }
    
    public boolean isGoal() {
        return this.getHeuristic() == 0;
    }
    
    public int compareTo(State other) {
        return this.getHeuristic() - other.getHeuristic();
    }
    
    public void show() {
        for(int i = 0; i < puzzle.length; i++) {
            for(int j = 0; j < puzzle[i].length; j++) {
                System.out.print(j == 8 ? Math.abs(puzzle[i][j]) : Math.abs(puzzle[i][j])+", ");
                //System.out.print(j == 8 ? puzzle[i][j] : puzzle[i][j]+", ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public int computeHeuristic() {
        int res = 0;
        
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(Math.abs(puzzle[i][0]) == Math.abs(puzzle[j][0]) && i != j) {
                    res++;
                }
                if(Math.abs(puzzle[i][1]) == Math.abs(puzzle[j][1]) && i != j) {
                    res++;
                }
                if(Math.abs(puzzle[i][2]) == Math.abs(puzzle[j][2]) && i != j) {
                    res++;
                }
                if(Math.abs(puzzle[i][3]) == Math.abs(puzzle[j][3]) && i != j) {
                    res++;
                }
                if(Math.abs(puzzle[i][4]) == Math.abs(puzzle[j][4]) && i != j) {
                    res++;
                }
                if(Math.abs(puzzle[i][5]) == Math.abs(puzzle[j][5]) && i != j) {
                    res++;
                }
                if(Math.abs(puzzle[i][6]) == Math.abs(puzzle[j][6]) && i != j) {
                    res++;
                }
                if(Math.abs(puzzle[i][7]) == Math.abs(puzzle[j][7]) && i != j) {
                    res++;
                }
                if(Math.abs(puzzle[i][8]) == Math.abs(puzzle[j][8]) && i != j) {
                    res++;
                }
            }
        }
        
        return res;
    }
    
    public void populate() {
        for(int i = 0; i < puzzle.length; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            row.add(1);
            row.add(2);
            row.add(3);
            row.add(4);
            row.add(5);
            row.add(6);
            row.add(7);
            row.add(8);
            row.add(9);
            
            for(int j = 0; j < puzzle[i].length; j++) {
                if(row.contains(puzzle[i][j])) {
                    row.remove((Object)puzzle[i][j]);
                }
            }
            
            int counter = 0;
            
            for(int x = 0; x < puzzle[i].length; x++) {
                if(puzzle[i][x] == 0) {
                    puzzle[i][x] = row.get(counter)*-1;
                    counter++;
                }
            }
        }
    }
    
    public int[][] copyPuzzle(int[][] puzzle) {
        int[][] res = new int[puzzle.length][puzzle[0].length];
        
        for(int i = 0; i < puzzle.length; i++) {
            for(int j = 0; j < puzzle.length; j++) {
                res[i][j] = puzzle[i][j];
            }
        }
        
        return res;
    }

    
    public ArrayList<State> expand() {
        ArrayList<State> successors = new ArrayList<State>();
        
        for(int i = 0; i < puzzle.length; i++) {
            for(int  j = 0; j < puzzle[i].length; j++) {
                for(int k = 0; k < puzzle[i].length; k++) {
                    if(puzzle[i][j] != puzzle[i][k] && puzzle[i][j] <= 0 && puzzle[i][k] <= 0) {
                        int[][] newPuzzle = copyPuzzle(puzzle);
                        
                        int temp = newPuzzle[i][j];
                        newPuzzle[i][j] = newPuzzle[i][k];
                        newPuzzle[i][k] = temp;
    
                        State state = new State(newPuzzle);
                        successors.add(state);
                    }
                }
             }
        }
        
        return successors;
    }
}
