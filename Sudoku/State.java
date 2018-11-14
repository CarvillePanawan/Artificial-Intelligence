import java.util.ArrayList;

public class State {
	int[][] matrix = new int[9][9];
	int heuristic;
	State parent;
	
	
	public State(int[][] matrix, int heuristic, State parent) {
		super();
		this.matrix = matrix;
		this.heuristic = heuristic;
		this.parent = parent;
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	
	public int getHeuristic() {
		return heuristic;
	}
	
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
	
	public ArrayList<State> expand(){
		ArrayList<State> childrenStates = new ArrayList<State>();
		//...to-do
		return childrenStates;
	}
	
	public int computeHeuristic() {
		//...to-do
		return 0;
	}
	
	public boolean isValidMove(int row, int col, int move) {
		boolean checker = true;
		for (int x=0;x<matrix.length;x++) {
			if(matrix[row][x] == move) {
				checker = false;
				break;
			}
		}
		return checker;
	}
	
	public boolean isGoal() {
		boolean checker = true;
		for (int x=0; x<matrix.length; x++) {
			for (int y=0; y<matrix.length; y++) {
				if(matrix[x][y] != 0) {
					checker = this.isValidMove(x, y, matrix[x][y]);
				} else {
					checker = false;
					break;
				}
			}
		}
		return checker;
	}
	
	@Override
	public String toString() {
		String goalState = "";
		for (int x=0; x<matrix.length; x++) {
			for (int y=0; y<matrix.length; y++) {
				if(y != matrix.length-1) {
					goalState += " "+matrix[x][y]+" ";
				} else {
					goalState += " "+matrix[x][y]+" \n";
				}
			}
		}
		return goalState;
	}
}
