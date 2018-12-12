import java.util.ArrayList;

public class Move {
    Chip move;
    char[][] board;
    
    public Move(Chip move, char[][] board) {
        this.move = move;
        this.board = board;
    }
    
    public void setMove(Chip move) {
        this.move = move;
    }
    
    public void setBoard(char[][] board) {
        this.board = board;
    }
    
    public Chip getMove() {
        return move;
    }
    
    public char[][] getBoard() {
        return board;
    }

}
