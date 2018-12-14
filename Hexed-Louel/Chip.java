public class Chip {
    int row;
    int col;
    char color;
    
    public Chip() {
        this.row = 0;
        this.col = 0;
        this.color = ' ';
    }
    
    public Chip(int row, int col, char color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }
    
    public Chip clone() {
    	Chip c = new Chip();
    	c.row = this.row;
    	c.col = this.col;
    	c.color = this.color;
    	return c;
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

    public String toString(){
        return "Column: "+col+" Row: "+row+" Color: "+color;
    }
    
    public boolean equals(Chip other) {
    	if(this.getRow() == other.getRow() && this.getCol() == other.getCol() && this.getColor() == other.getColor()) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
