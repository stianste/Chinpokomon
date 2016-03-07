package application;

import java.util.Iterator;

public class TileIterator implements Iterator<Tile> {
	//Iterator not working atm. To tired to fix. 
	
	TileContainer tc = new TileContainer();
	int row = 0;
	int col = 0;
	
	public boolean hasNextRow(){
		return row < Main.grid -1;
	}
	public boolean hasNextCol(){
		return col < Main.grid -1;
	}
	@Override
	public boolean hasNext() {
		if(hasNextCol()){
			System.out.println("True");
			return true;
		}else{
			col = 0;
			row += 1;
			System.out.println(hasNextRow());
			return hasNextRow();
		}
	}

	@Override
	public Tile next() {
		if(hasNextCol()){
			return tc.getTile(row, col++);
		}
		else{
			return tc.getTile(row, col++);
		}
	}

}
