import java.util.ArrayList;

public class Board {
    char[][] board;
    
    public Board(char[][] board) {
        this.board = board;
    }
    
    public void show() {
        System.out.println("["+board[6][0]+"]   ["+board[6][2]+"]   ["+board[6][4]+"]   ["+board[6][6]+"]   ["+board[6][8]+"]");
        for(int i = board.length-2; i >= 0; i--) {
            System.out.println("   ["+board[i][1]+"]   ["+board[i][3]+"]   ["+board[i][5]+"]   ["+board[i][7]+"]");
            // System.out.println("");
            System.out.println("["+board[i][0]+"]   ["+board[i][2]+"]   ["+board[i][4]+"]   ["+board[i][6]+"]   ["+board[i][8]+"]");
            
//            System.out.println("   ["+i+"]   ["+i+"]   ["+i+"]   ["+i+"]");
//            System.out.println("["+i+"]   ["+i+"]   ["+i+"]   ["+i+"]   ["+i+"]");
        }
    }
    
    public void showBoard() {
        for(int i = board.length-1; i >= 0 ; i--) {
            for(int j = board[i].length-1; j >= 0; j--) {
                System.out.print("["+board[i][j]+"]");
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
        } else if((x&1)==1 && (y&1)==1) { //col even, row even
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
    
    public ArrayList<Chip> checkLeftDiagonalDown(Chip move, ArrayList<Chip> chips) {
        ArrayList<Chip> res = new ArrayList<Chip>();
        int row = move.getRow();
        int col = move.getCol();
        char color = move.getColor();
        
        for(int i = col; i < board.length; i++) {
            if(board[row][col] != color && board[row][col] != ' ' && (col%1 == 0)) {
                row--;
                col--;
            } else {
                
            }
        }
        
        return res;
    }

    public ArrayList<Chip> checkLeftDiagonalDown() {
        ArrayList<Chip> res = new ArrayList<Chip>();
        byte s = 0;

        try{
            for(int i = board.length-1; i >= 0; i--){
                for(int j = board[i].length-1; j >= 0; j--){
                    if(board[i][j] != ' '){
                        if((j&1) == 0 && board[i-1][j-1] != ' ' &&  board[i-1][j-1] != board[i][j]){
                            System.out.printf("even column\n");
                            System.out.printf("Column: %d Row: %d Char: %s%n",i,j,board[i][j]);
                            System.out.printf("Next Column: %d Next Row: %d Char: %s%n",(i-1),(j-1),board[i-1][j-1]);
                        } else if((j&1) == 1 && board[i][j-1] != ' ' && board[i][j-1] != board[i][j]) {
                            System.out.printf("odd column\n");
                            System.out.printf("Column: %d Row: %d Char: %s%n",i,j,board[i][j]);
                            System.out.printf("Next Column: %d Row: %d Char: %s%n",i,(j-1),board[i][j-1]);
                        }
                    }
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){
            
        }

        return res;

    }

    public ArrayList<Chip> checkRightDiagonalDown() {
        ArrayList<Chip> res = new ArrayList<Chip>();

        try{
            for(int i = board.length-1; i >= 0; i--){
                for(int j = board[i].length-1; j >= 0; j--){
                    if(board[i][j] != ' '){
                        if((j&1) == 0 && board[i+1][j+1] != ' ' && board[i+1][j+1] != board[i][j]){
                            System.out.printf("even column\n");
                            System.out.printf("Column: %d Row: %d Char: %s%n",i,j,board[i][j]);
                            System.out.printf("Next Column: %d Row: %d Char: %s%n",(i-1),(j+1),board[i-1][j+1]);
                        } else if((j&1) == 1 && board[i][j+1] != ' ' && board[i][j+1] != board[i][j]) {
                            System.out.printf("odd column\n");
                            System.out.printf("Column: %d Row: %d Char: %s%n",i,j,board[i][j]);
                            System.out.printf("Next Column: %d Row: %d Char: %s%n",i,(j+1),board[i][j+1]);
                        }
                    }
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){
            
        }

        return res;

    }

    public ArrayList<Chip> checkLeftDiagonalUp() {
        ArrayList<Chip> res = new ArrayList<Chip>();

        try{
            for(int i = board.length-1; i >= 0; i--){
                for(int j = board[i].length-1; j >= 0; j--){
                    if(board[i][j] != ' '){
                        if((j&1) == 0 && board[i][j-1] != ' ' && board[i][j-1] != board[i][j]){
                            System.out.printf("even column\n");
                            System.out.printf("Column: %d Row: %d Char: %s%n",i,j,board[i][j]);
                            System.out.printf("Next Column: %d Row: %d Char: %s%n",i,(j-1),board[i][j-1]);
                        } else if((j&1) == 1 && board[i+1][j-1] != ' ' && board[i+1][j-1] != board[i][j]) {
                            System.out.printf("odd column\n");
                            System.out.printf("Column: %d Row: %d Char: %s%n",i,j,board[i][j]);
                            System.out.printf("Next Column: %d Row: %d Char: %s%n",(i+1),(j-1),board[i+1][j-1]);
                        }
                    }
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){
            
        }

        return res;

    }

    public ArrayList<Chip> checkRightDiagonalUp() {
        ArrayList<Chip> res = new ArrayList<Chip>();

        try{
            for(int i = board.length-1; i >= 0; i--){
                for(int j = board[i].length-1; j >= 0; j--){
                    if(board[i][j] != ' '){
                        if((j&1) == 0 && board[i][j+1] != ' ' && board[i][j+1] != board[i][j]){
                            System.out.printf("even column\n");
                            System.out.printf("Column: %d Row: %d Char: %s%n",i,j,board[i][j]);
                            System.out.printf("Next Column: %d Row: %d Char: %s%n",i,(j+1),board[i][j+1]);
                        } else if((j&1) == 1 && board[i+1][j+1] != ' ' && board[i+1][j+1] != board[i][j]) {
                            System.out.printf("odd column\n");
                            System.out.printf("Column: %d Row: %d Char: %s%n",i,j,board[i][j]);
                            System.out.printf("Next Column: %d Row: %d Char: %s%n",(i+1),(j+1),board[i+1][j+1]);
                        }
                    }
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){
            
        }

        return res;

    }

    public void updateBoard(Chip move) {

    }

    public String showAllPossibleMoves(ArrayList<Chip> chips) {
        return "";
    }
    
    public char[][] getboard() {
        return board;
    }
}
