import java.util.ArrayList;

public class Board {
    char[][] board;
    
    public Board(char[][] board) {
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
                System.out.println();
            }
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
    
    public ArrayList<Chip> checkSouthWest(Chip move, ArrayList<Chip> chips) {
        ArrayList<Chip> res = new ArrayList<Chip>();
        int row = move.getRow();
        int col = move.getCol();
        char color = move.getColor();
        
        for(int i = col; i < board.length; i++) {
            if(board[row][col] != color && board[row][col] != ' ') {
                row--;
                col--;
            } else {
                
            }
        }
        
        return res;
    }

    public ArrayList<Chip> checkSouthWest(Chip lastMove) {
        ArrayList<Chip> res = new ArrayList<Chip>();
        int col = 0;
        int row = 0;

        try{
            for(int i = board.length-1; i >= 0; i--){
                for(int j = board[i].length-1; j >= 0; j--){
                    if(board[i][j] != ' '){
                        
                        if((j&1) == 0){
                            row = i-1;
                            col = j-1;
                        }else{
                            row = i;
                            col = j-1;
                        }

                        while((j&1) == 0 && board[row][col] != ' ' && board[row][col] != board[i][j]) {
                            if((col&1) == 0){
                                col -= 1;
                                row -= 1;
                            } else {
                                col -= 1;
                            }
                            if(board[row][col] == ' ' && board[row][col] != board[i][j]){
                                res.add(new Chip(row,col,board[i][j]));
                            }
                        }

                        while((j&1) == 1 && board[row][col] != ' ' && board[row][col] != board[i][j]) {
                            if((col&1) == 0){
                                col -= 1;
                                row -= 1;
                            } else {
                                row -= 1;
                            }
                            if(board[row][col] == ' ' && board[row][col] != board[i][j]){
                                res.add(new Chip(row,col,board[i][j]));
                            }
                        }
                    }
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){
            
        }

        return res;

    }

    public ArrayList<Chip> checkSouthEast(Chip lastMove) {
        ArrayList<Chip> res = new ArrayList<Chip>();
        int col = 0;
        int row = 0;

        try{
            for(int i = board.length-1; i >= 0; i--){
                for(int j = board[i].length-1; j >= 0; j--){
                    if(board[i][j] != ' '){
                        
                        if((j&1) == 0){
                            row = i-1;
                            col = j+1;
                        }else{
                            row = i;
                            col = j+1;
                        }

                        while((j&1) == 0 && board[row][col] != ' ' && board[row][col] != board[i][j]) {
                            if((col&1) == 0){
                                col += 1;
                                row -= 1;
                            } else {
                                col += 1;
                            }
                            if(board[row][col] == ' ' && board[row][col] != board[i][j]){
                                res.add(new Chip(row,col,board[i][j]));
                            }
                        }

                        while((j&1) == 1 && board[row][col] != ' ' && board[row][col] != board[i][j]) {
                            if((col&1) == 0){
                                col += 1;
                                row -= 1;
                            } else {
                                col += 1;
                            }
                            if(board[row][col] == ' ' && board[row][col] != board[i][j]){
                                res.add(new Chip(row,col,board[i][j]));
                            }
                        }
                    }
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){
            
        }

        return res;

    }

    public ArrayList<Chip> checkNorthWest(Chip lastMove) {
        ArrayList<Chip> res = new ArrayList<Chip>();
        int col = 0;
        int row = 0;

        try{
            for(int i = board.length-1; i >= 0; i--){
                for(int j = board[i].length-1; j >= 0; j--){
                    if(board[i][j] != ' '){
                        
                        if((j&1) == 0){
                            row = i;
                            col = j-1;
                        }else{
                            row = i+1;
                            col = j-1;
                        }

                        while((j&1) == 0 && board[row][col] != ' ' && board[row][col] != board[i][j]) {
                            if((col&1) == 0){
                                col -= 1;
                            } else {
                                col -= 1;
                                row += 1;
                            }
                            if(board[row][col] == ' '  &&  board[row][col] != board[i][j]){
                                res.add(new Chip(row,col,board[i][j]));
                            }
                        }

                        while((j&1) == 1 && board[row][col] != ' ' && board[row][col] != board[i][j]) {
                            if((col&1) == 0){
                                col -= 1;
                            } else {
                                col -= 1;
                            }
                            if(board[row][col] == ' ' && board[row][col] != board[i][j]){
                                res.add(new Chip(row,col,board[i][j]));
                            }
                        }
                    }
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){
            
        }

        return res;

    }

    public ArrayList<Chip> checkNorthEast(Chip lastMove) {
        ArrayList<Chip> res = new ArrayList<Chip>();
        int col = 0;
        int row = 0;

        try{
            for(int i = board.length-1; i >= 0; i--){
                for(int j = board[i].length-1; j >= 0; j--){
                    if(board[i][j] != ' '){
                        
                        if((j&1) == 0){
                            row = i;
                            col = j+1;
                        }else if((j&1) == 1){
                            row = i+1;
                            col = j+1;
                        }

                        while((j&1) == 0 && board[row][col] != ' ' && board[row][col] != board[i][j]) {
                            if((col&1) == 0){
                                col += 1;
                            } else {
                                col += 1;
                                row += 1;
                            }
                            if(board[row][col] == ' ' && board[row][col] != board[i][j]){
                                res.add(new Chip(row,col,board[i][j]));
                            }
                        }

                        while((j&1) == 1 && board[row][col] != ' ' && board[row][col] != board[i][j]) {
                            if((col&1) == 0){
                                col += 1;
                            } else {
                                col += 1;
                                row += 1;
                            }
                            if(board[row][col] == ' ' && board[row][col] != board[i][j]){
                                res.add(new Chip(row,col,board[i][j]));
                            }
                        }
                    }
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){
            
        }

        return res;

    }

    public ArrayList<Chip> checkNorth(Chip lastMove) {
        ArrayList<Chip> res = new ArrayList<Chip>();
        int row = 0;

        try{
            for(int i = board.length-1; i >= 0; i--){
                for(int j = board[i].length-1; j >= 0; j--){
                    if(board[i][j] != ' '){
                        row = i + 1;
                        while(board[row][j] != ' ' && board[row][j] != board[i][j]) {
                            row++;
                            if(board[row][j] == ' ' && board[row][j] != board[i][j]){
                                res.add(new Chip(row,j,board[i][j]));
                            }
                        }
                    }
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){
            
        }

        return res;

    }

    public ArrayList<Chip> checkSouth(Chip lastMove) {
        ArrayList<Chip> res = new ArrayList<Chip>();
        int row = 0;

        try{
            for(int i = board.length-1; i >= 0; i--){
                for(int j = board[i].length-1; j >= 0; j--){
                    if(board[i][j] != ' '){
                        row = i - 1;
                        while(board[row][j] != ' ' && board[row][j] != board[i][j]) {
                            row--;
                            if(board[row][j] == ' ' && board[row][j] != board[i][j]){
                                res.add(new Chip(row,j,board[i][j]));
                            }
                        }
                    }
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){
            
        }

        return res;

    }

    public char[][] setBoard(){
        char[][] res = board.clone();

        for(int i = res.length-1; i >= 0; i--){
            for(int j = res[i].length-1; j >= 0; j--){
                res[i][j]=' ';
            }
        }

        res[3][2] = 'r';
        res[2][3] = 'g';
        res[3][4] = 'g';

        res[4][2] = 'g';
        res[4][3] = 'r';
        res[4][4] = 'g';

        res[2][2] = 'r';
        res[2][4] = 'g';
        res[5][4] = 'g';

        res[5][5] = 'g';
        res[3][5] = 'r';
        res[3][6] = 'r';

        res[5][2] = 'r';
        res[1][2] = 'g';
        res[0][3] = 'g';
        res[1][1] = 'r';
        return res;
    }

    public void updateBoard(Chip lastMove) {
        
    }

    public String showAllPossibleMoves(ArrayList<Chip> chips) {
        return "";
    }
    
    public char[][] getboard() {
        return board;
    }
}
