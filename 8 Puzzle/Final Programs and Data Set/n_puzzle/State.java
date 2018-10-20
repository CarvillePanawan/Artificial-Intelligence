package n_puzzle;

import java.util.ArrayList;

public class State implements Comparable<State>{
    byte[] puzzle;
    int actualCost;
    int heuristicCost;
    State parent;
    
    public State(byte[] puzzle, int actualCost, int heuristicCost, State parent) {
        this.puzzle = puzzle;
        this.actualCost = actualCost;
        this.heuristicCost = heuristicCost;
        this.parent = parent;
    }
    
    public State(byte[] puzzle, int heuristicCost, State parent) {
        this.puzzle = puzzle;
        this.heuristicCost = heuristicCost;
        this.parent = parent;
    }
    
    public State(byte[] puzzle, State parent, int actualCost) {
        this.puzzle = puzzle;
        this.actualCost = actualCost;
        this.parent = parent;
    }
    
    public State(byte[] puzzle, State parent) {
        this.puzzle = puzzle;
        this.parent = parent;
    }
    
    public byte[] getPuzzle() {
        return puzzle;
    }
    
    public int getActualCost() {
        return actualCost;
    }
    
    public int getHeuristicCost() {
        return heuristicCost;
    }
    
    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }
    
    public State getParent() {
        return parent;
    }
    
    public boolean isGoal(byte[] goal) {
        boolean flag = false;
        byte counter = 0;
        
        for(byte i = 0; i < goal.length; i++) {
            if(puzzle[i] == goal[i]) {
                counter++;
            }
        }
        
        if(counter == 9) {
            flag = true;
        }
        
        return flag;
    }//isGoal
    
    /**
     * Method for generating child nodes.
     * 
     * @param a as the State's current puzzle configuration
     * @return all generated child nodes.
     */
    public ArrayList<State> expandUninformed(byte[] a) {
        ArrayList<State> successors = new ArrayList<>();
        
        for(byte i = 0; i < a.length; i++) {
            if(i != 0) {
                if(a[i]+a[i-1] == a[i] && i != 3 && i != 6) {
                    byte[] b = copyPuzzle(a);
                    
                    b[i-1] = a[i];
                    b[i] = 0;
                    
                    successors.add(new State(b, this, this.getActualCost() + 1));
                }
            }//move tile to the left
            
            if(i != 8) {
                if(a[i]+a[i+1] == a[i] && i != 2 && i != 5) {
                    byte[] b = copyPuzzle(a);
                    
                    b[i+1] = a[i];
                    b[i] = 0;
                    
                    successors.add(new State(b, this, this.getActualCost() + 1));
                }
            }//move tile to the right
            
            if(i != 0 && i != 1 && i != 2) {
                if(a[i]+a[i-3] == a[i]) {
                    byte[] b = copyPuzzle(a);
                    
                    b[i-3] = a[i];
                    b[i] = 0;
                    
                    successors.add(new State(b, this, this.getActualCost() + 1));
                }
            }//move tile upward
            
            if(i != 6 && i != 7 && i != 8) {
                if(a[i]+a[i+3] == a[i]) {
                    byte[] b = copyPuzzle(a);
                    
                    b[i+3] = a[i];
                    b[i] = 0;
                    
                    successors.add(new State(b, this, this.getActualCost() + 1));
                }
            }//move tile downward
        }
        
        return successors;
    }//expandDepthFirstSearch
    
    /**
     * Method for generating child nodes. Used for the informed search called "A* Search". 
     * 
     * Note: To change heuristic cost being used, simply replace the method invocation seen
     * in the adding of child nodes.
     * 
     * @param a as the State's current puzzle configuration.
     * @return all generated child nodes.
     */
    public ArrayList<State> expandInformedA(byte[] a) {
        ArrayList<State> successors = new ArrayList<>();
        
        for(byte i = 0; i < a.length; i++) {
            if(i != 0) {
                if(a[i]+a[i-1] == a[i] && i != 3 && i != 6) {
                    byte[] b = copyPuzzle(a);
                    
                    b[i-1] = a[i];
                    b[i] = 0;
                    
                    successors.add(new State(b, this.getActualCost() + 1, computeHeuristicCostMD(b), this));
                }
            }//move tile to the left
            
            if(i != 8) {
                if(a[i]+a[i+1] == a[i] && i != 2 && i != 5) {
                    byte[] b = copyPuzzle(a);
                    
                    b[i+1] = a[i];
                    b[i] = 0;
                    
                    successors.add(new State(b, this.getActualCost() + 1, computeHeuristicCostMD(b), this));
                }
            }//move tile to the right
            
            if(i != 0 && i != 1 && i != 2) {
                if(a[i]+a[i-3] == a[i]) {
                    byte[] b = copyPuzzle(a);
                    
                    b[i-3] = a[i];
                    b[i] = 0;
                    
                    successors.add(new State(b, this.getActualCost() + 1, computeHeuristicCostMD(b), this));
                }
            }//move tile upward
            
            if(i != 6 && i != 7 && i != 8) {
                if(a[i]+a[i+3] == a[i]) {
                    byte[] b = copyPuzzle(a);
                    
                    b[i+3] = a[i];
                    b[i] = 0;
                    
                    successors.add(new State(b, this.getActualCost() + 1, computeHeuristicCostMD(b), this));
                }
            }//move tile downward
        }
    
        return successors;
    }//expand
    
    /**
     * Method for generating child nodes. Used for the informed search called "Greedy-Best
     * First Search". 
     * 
     * Note: To change heuristic cost being used, simply replace the method invocation seen
     * in the adding of child nodes.
     * 
     * @param a as the State's current puzzle configuration.
     * @return all generated child nodes.
     */
    public ArrayList<State> expandInformedB(byte[] a) {
        ArrayList<State> successors = new ArrayList<>();
        
        for(byte i = 0; i < a.length; i++) {
            if(i != 0) {
                if(a[i]+a[i-1] == a[i] && i != 3 && i != 6) {
                    byte[] b = copyPuzzle(a);
                    
                    b[i-1] = a[i];
                    b[i] = 0;
                    
                    successors.add(new State(b, computeHeuristicCostMP(b), this));
                }
            }//move tile to the left
            
            if(i != 8) {
                if(a[i]+a[i+1] == a[i] && i != 2 && i != 5) {
                    byte[] b = copyPuzzle(a);
                    
                    b[i+1] = a[i];
                    b[i] = 0;
                    
                    successors.add(new State(b, computeHeuristicCostMP(b), this));
                }
            }//move tile to the right
            
            if(i != 0 && i != 1 && i != 2) {
                if(a[i]+a[i-3] == a[i]) {
                    byte[] b = copyPuzzle(a);
                    
                    b[i-3] = a[i];
                    b[i] = 0;
                    
                    successors.add(new State(b, computeHeuristicCostMP(b), this));
                }
            }//move tile upward
            
            if(i != 6 && i != 7 && i != 8) {
                if(a[i]+a[i+3] == a[i]) {
                    byte[] b = copyPuzzle(a);
                    
                    b[i+3] = a[i];
                    b[i] = 0;
                    
                    successors.add(new State(b, computeHeuristicCostMP(b), this));
                }
            }//move tile downward
        }
        
        return successors;
    }//expandexpandGreedyBestFirstSearch
    
    public byte[] copyPuzzle(byte[] a) {
        byte[] b = new byte[a.length];
        
        for(byte i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        
        return b;
    }//copyPuzzle
    
    public int computeHeuristicCostMD(byte[] a) {
        int cost = 0;
        byte[] goal = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
        
        for(byte i = 0; i < goal.length; i++) {
            for(byte j = 0 ; j < a.length; j++) {
                if(goal[i] == a[j] && goal[i] != 0) {  
                    byte x = (byte) ((goal[i] > 6) ? 3 : (goal[i] > 3) ? 2 : 1);
                    byte y = (byte) ((goal[i] % 3) == 0 ? 3 : (goal[i] % 2) == 0 ? 2 : 1);
                    
                    byte c = (byte) ((j+1) > 6 ? 3 : (j+1) > 3 ? 2 : 1);
                    byte d = (byte) ((j+1) % 3 == 0 ? 3 : (j+1) % 2 == 0 ? 2 : 1);
                    
                    cost += Math.abs(x-c) + Math.abs(y-d);
                }
            }
        }
        
        return cost;
    }//computeHeuristicCostMD
    
    public int computeHeuristicCostMP(byte[] a) {
        int cost = 0;
        byte[] goal = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
        
        for(byte i = 0; i < goal.length; i++) {
            if(a[i] != goal[i]) {
                cost++;
            }
        }

        return cost-1 < 0 ? 0 : cost-1;
    }//computeHeuristicCostMP
    
    public void showPuzzle() {
        for(int i = 0; i < 3; i++) {
            System.out.print(i == 2 ? puzzle[i] : puzzle[i]+",");
        }
        System.out.println();
        for(int i = 3; i < 6; i++) {
            System.out.print(i == 5 ? puzzle[i] : puzzle[i]+",");
        }
        System.out.println();
        for(int i = 6; i < 9; i++) {
            System.out.print(i == 8 ? puzzle[i] : puzzle[i]+",");
        }
        System.out.println("\n");
    }//showPuzzle
    
    public void showPuzzleArray() {
        for(int j = 0; j < puzzle.length; j++) {
            System.out.print(j == puzzle.length ? puzzle[j]: puzzle[j]+",");
        }
        System.out.println();
    }//showPuzzleArray
    
    public int compareTo(State a) {
        if(this.getActualCost() > 0) {
            return (this.getHeuristicCost() + this.getActualCost()) - (a.getHeuristicCost() + a.getActualCost()); 
        }
        return this.getHeuristicCost() - a.getHeuristicCost();
    }//compareTo
}//Class
