import java.util.ArrayList;
import java.util.HashSet;

public class Board {
    char[][] board;
    
    public Board(char[][] board) {
        this.board = board;
    }
    
    public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public void showBoard() {
        System.out.println("["+board[6][0]+"]   ["+board[6][2]+"]   ["+board[6][4]+"]   ["+board[6][6]+"]   ["+board[6][8]+"]");
        for(int i = board.length-2; i >= 0; i--) {
            System.out.println("   ["+board[i][1]+"]   ["+board[i][3]+"]   ["+board[i][5]+"]   ["+board[i][7]+"]");
            // System.out.println("");
            System.out.println("["+board[i][0]+"]   ["+board[i][2]+"]   ["+board[i][4]+"]   ["+board[i][6]+"]   ["+board[i][8]+"]");
            
//            System.out.println("   ["+i+"]   ["+i+"]   ["+i+"]   ["+i+"]");
//            System.out.println("["+i+"]   ["+i+"]   ["+i+"]   ["+i+"]   ["+i+"]");
        }
    }
    
    public void showArray() {
        for(int i = board.length-1; i >= 0 ; i--) {
            for(int j = 0; j < board[i].length; j++) {
            	if(board[i][j] == ' '){
            		System.out.print("["+i+","+j+"]");
            	}else{
            		System.out.print("[ "+board[i][j]+" ]");
            	}
            }
            System.out.println();
        }
    }
    
    public char[][] populate(int x, int y, char top) {
        char[][] res = board.clone();
        char other = top == 'r' ? 'g' : 'r';

        for(int i = res.length-1; i >= 0; i--){
            for(int j = res[i].length-1; j >= 0; j--){
                res[i][j]=' ';
            }
        }
        
        res[x][y] = top;
        
        if((x&1)==0 && (y&1)==1) { //col odd, row even
            res[x-1][y-1] = res[x-1][y+1] = top;
            res[x-2][y] = res[x][y-1] = res[x][y+1] = other;
        } else if((x&1)==0 && (y&1)==0) { //col even, row even
            res[x-2][y-1] = res[x-2][y+1] = top;
            res[x-2][y] = res[x-1][y-1] = res[x-1][y+1] = other;
        } else if((x&1)==1 && (y&1)==1) { //col odd, row odd
            res[x-1][y-1] = res[x-1][y+1] = top;
            res[x-2][y] = res[x][y-1] = res[x][y+1] = other;
        } else { //col even, row odd
            res[x-2][y-1] = res[x-2][y+1] = top;
            res[x-2][y] = res[x-1][y-1] = res[x-1][y+1] = other;
        }
        
        return res;
    }
    
    public ArrayList<Chip> playerRedChips() {
        ArrayList<Chip> chips = new ArrayList<Chip>();
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if('r' != board[i][j] && board[i][j] != ' ') {
                    chips.add(new Chip(i, j, 'r'));
                }
            }
        }
        
        return chips;
    }
    
    public ArrayList<Chip> playerGreenChips() {
        ArrayList<Chip> chips = new ArrayList<Chip>();
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if('g' != board[i][j] && board[i][j] != ' ') {
                    chips.add(new Chip(i, j, 'g'));
                }
            }
        }
        return chips;
    }
    

    public void makeMove(Move move) {
        char[][] array = this.getBoard();
    	for(int x = 0; x < move.getAffectedTiles().size(); x++) {
    		if(array[move.getAffectedTiles().get(x).getRow()][move.getAffectedTiles().get(x).getCol()] == 'g') {
        		array[move.getAffectedTiles().get(x).getRow()][move.getAffectedTiles().get(x).getCol()] = 'r';
        		if(array[move.getTile().getRow()][move.getTile().getCol()] != 'r'){
            		array[move.getTile().getRow()][move.getTile().getCol()] = 'r';
        		}
    		} else if (array[move.getAffectedTiles().get(x).getRow()][move.getAffectedTiles().get(x).getCol()] == 'r') {
        		array[move.getAffectedTiles().get(x).getRow()][move.getAffectedTiles().get(x).getCol()] = 'g';
        		if(array[move.getTile().getRow()][move.getTile().getCol()] != 'g'){
            		array[move.getTile().getRow()][move.getTile().getCol()] = 'g';
        		}
    		}
    	}
    	this.setBoard(array);
    }
    
    public ArrayList<Move> getPossibleMoves(char turnColor, char waitColor){
    	ArrayList<Move> result = new ArrayList<Move>();
    	ArrayList<Move> temp = new ArrayList<Move>();
    	for(int i = 0; i<board.length; i++) {
        	for(int j = 0; j<board[i].length; j++) {
        		if(board[i][j] == turnColor) {
        			temp.add(getMove("nw", i, j, turnColor, waitColor));
        			temp.add(getMove("n", i, j, turnColor, waitColor));
        			temp.add(getMove("ne", i, j, turnColor, waitColor));
        			temp.add(getMove("se", i, j, turnColor, waitColor));
        			temp.add(getMove("s", i, j, turnColor, waitColor));
        			temp.add(getMove("sw", i, j, turnColor, waitColor));
        			temp.removeIf(n -> (n == null));
        		}
        	}
    	}
    	
    	if(temp.isEmpty()) {
    		result = null;
    	} else {
        	for(int x = 0; x < temp.size(); x++) {
    			if(result.isEmpty()) {
    				result.add(temp.get(x));
    			} else {
    				boolean added = false;
    				int size = result.size();
            		for(int y = 0; y < size; y++) {
            			if(temp.get(x).getTile().equals(result.get(y).getTile())) {
            				result.get(y).getAffectedTiles().addAll(temp.get(x).getAffectedTiles());
            				added = true;
            			} else if((y == result.size()-1) && !added){
            				result.add(temp.get(x));
            			}
            		}
    			}
        	}
    	}
    	return result;
    }
    
    public ArrayList<Chip> removeDuplicates(ArrayList<Chip> list) {
        ArrayList<Chip> result = new ArrayList<Chip>();
        HashSet<Chip> set = new HashSet<Chip>();

        for (Chip item : list) {
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }
    
    public Move getMove(String direction, int row, int col, char turnColor, char waitColor){
        Move result = new Move();
        int operationOnRow = getOperation("row", direction, col);
        int operationOnCol = getOperation("col", direction, col);
        ArrayList<Chip> affectedTiles = new ArrayList<Chip>();
        Chip tileMove = new Chip();
        
        if(checkIfValidTile(row + operationOnRow, col + operationOnCol)) {
        	if(board[row + operationOnRow][col + operationOnCol] == waitColor){
                while(checkIfValidTile(row + operationOnRow, col + operationOnCol)){
                    row += operationOnRow;
                    col += operationOnCol;
                    operationOnRow = getOperation("row", direction, col);
                	if((board[row][col] == waitColor) && checkIfValidTile(row + operationOnRow, col + operationOnCol)){
                        affectedTiles.add(new Chip(row, col, waitColor));
                    	if(board[row + operationOnRow][col + operationOnCol] == ' ') {
                    		tileMove.setRow(row + operationOnRow);
                    		tileMove.setCol(col + operationOnCol);
                    		tileMove.setColor(' ');
                    		break;
                    	}
                	} else {
                		affectedTiles = null;
                		tileMove = null;
                		break;
                	}
                }
                if(affectedTiles != null && tileMove != null) {
                	result.setAffectedTiles(affectedTiles);
                	result.setTile(tileMove);
                	return result;
                } else {
                	result = null;
                }
            } else {
                result = null;
            }
        } else {
        	result = null;
        }
        
        return result;
    }
    
    public int getOperation(String choice, String direction, int col) {
    	if(choice.equalsIgnoreCase("col")) {
    		switch (direction) {
            case "nw":
            	return -1;
            case "n":
            	return 0;
            case "ne":
            	return 1;
            case "se":
            	return 1;
            case "s":
            	return 0;
            case "sw":
            	return -1;
            }
    	} else {
    		if(col%2 != 0) {
                switch (direction) {
                case "nw":
                	return 1;
                case "n":
                	return 1;
                case "ne":
                	return 1;
                case "se":
                	return 0;
                case "s":
                	return -1;
                case "sw":
                	return 0;
                }
        	} else {
        		switch (direction) {
                case "nw":
                	return 0;
                case "n":
                	return 1;
                case "ne":
                	return 0;
                case "se":
                	return -1;
                case "s":
                	return -1;
                case "sw":
                	return -1;
                }
        	}
    	}
    	return 0;
    }
    
    public boolean checkIfValidTile(int row, int col) {
    	if((row == board.length-1 && col%2 != 0) || (row >= board.length || row < 0 || col >= board[row].length || col < 0)) {
    		return false;
    	} else {
    		return true;
    	}
    }
}
