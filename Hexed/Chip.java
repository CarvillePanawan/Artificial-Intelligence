public class Chip {
    int row;
    int col;
    char color;
    
    public Chip(int row, int col, char color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }
    
    public void setRow(int row) {
        this.row = row;
    }
    
    public void setCol(int col) {
        this.col = col;
    }
    
    public void setColor(char color) {
        this.color = color;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }

    public char getColor() {
        return color;
    }
}
