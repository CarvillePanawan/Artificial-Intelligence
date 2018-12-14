import java.util.ArrayList;

public class Move {
	Chip tile;
	ArrayList<Chip> affectedTiles;

	public Move() {
		super();
		this.tile = new Chip();
		this.affectedTiles = new ArrayList<Chip>();
	}
	
	public Move(Chip tile, ArrayList<Chip> affectedTiles) {
		super();
		this.tile = tile;
		this.affectedTiles = affectedTiles;
	}
	

    public Move clone() {
    	Move m = new Move();
    	ArrayList<Chip> temp = new ArrayList<Chip>();
    	m.tile = this.tile.clone();
    	for(int x = 0; x < this.affectedTiles.size(); x++) {
    		temp.add(this.affectedTiles.get(x).clone());
    	}
		m.setAffectedTiles(temp);
    	return m;
    }

	public Chip getTile() {
		return tile;
	}

	public void setTile(Chip tile) {
		this.tile = tile;
	}

	public ArrayList<Chip> getAffectedTiles() {
		return affectedTiles;
	}

	public void setAffectedTiles(ArrayList<Chip> affectedTiles) {
		this.affectedTiles = affectedTiles;
	}
	
	
}
