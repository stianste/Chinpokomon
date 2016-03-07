package application;


public class Tile {

	private int positionX;
	private int positionY;
	
	private boolean walkable;
	
	
	protected static final int squareSize = Main.squareSize;
	
	public Tile(int positionX, int positionY, boolean walkable) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.walkable = walkable;
		
	}
	@Override 
	public String toString(){
		return " Position: " + 
				positionX + " - " + positionY + " Walkable: "+
				walkable; //apperance.toString()
	}
	
	public int getPositionX() {
		return positionX*Main.squareSize;
	}
	public int getPositionY() {
		return positionY*Main.squareSize;
	}
	public boolean isWalkable(){
		return walkable;
	}
	public void setWalkable(boolean b){
		walkable = b;
	}
}
