import java.util.ArrayList;

public class Move {
	Chip tile;
	ArrayList<Chip> affectedTiles;

	public Move() {
		super();
		this.tile = null;
		this.affectedTiles = null;
	}
	
	public Move(Chip tile, ArrayList<Chip> affectedTiles) {
		super();
		this.tile = tile;
		this.affectedTiles = affectedTiles;
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
