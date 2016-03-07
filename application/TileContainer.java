package application;

import java.util.Iterator;

public class TileContainer implements Iterable<Tile>{
	Tile[][] tiles = new Tile[Main.grid][Main.grid];
	
	public TileContainer(){
		
	}
	public void setTile(int row, int col, Tile tile){
		tiles[row][col] = tile;
	}
	public Tile getTile(int row, int col){
		return tiles[row][col];
	}
	@Override
	public Iterator<Tile> iterator() {
		return new TileIterator();
	}
	
	public void clear(){
		
		for (int i = 0; i < Main.grid; i++) {
			for (int j = 0; j < Main.grid; j++) {
				if(tiles[i][j] != null){
					tiles[i][j] = null;
				}
			}
		}
	}
}
