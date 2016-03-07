package animations;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import application.Direction;
import application.Main;
import application.Tile;

public class PathFinder {
	private int fromX; 
	private int fromY; 
	private int toX; 
	private int toY;
	Boolean[][] discovered = new Boolean[Main.grid][Main.grid];
	List<Tile> path = new ArrayList<Tile>();
	List<Direction> directions = new ArrayList<Direction>();
	public PathFinder(int fromX, int fromY, int toX, int toY) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
	}
//	public void BreadthFirstSearch(){
//		int x = fromX;
//		int y = fromY;
//		
//		Queue<Tile> q = new LinkedList<Tile>();
//		q.add(Main.tc.getTile(fromX, fromY));
//		discovered[x][y] = true;
//		while(!q.isEmpty() && !(x == toX && y == toY)){
//			Tile c = q.poll();
//			if(c == null || c.isWalkable()){
//				path.add(c);
//			}
//			if(!discovered[x+1][y]){
//				q.add(Main.tc.getTile(x+1,y));
//				discovered[x+1]
//			}
//		}
//		
//	}
	public List<Direction> findPath(){
		int xOrientation = 0;
		int yOrientation = 0;
		int currentX = fromX;
		int currentY = fromY;
		Direction d = Direction.NONE;
		boolean b = false;
		if(Main.tc.getTile(toX,toY) == null || Main.tc.getTile(toX,toY).isWalkable()){
			if(toX > fromX){
				xOrientation = 1;
			}else if(toX < fromX){
				xOrientation = -1;
			}
			if(toY > fromY){
				yOrientation = 1;
			}else if(toY < fromY){
				yOrientation = -1;
			}
			while(toX != currentX && toY != currentY ){
				if(xOrientation != 0){
					while (b = (Main.tc.getTile(currentX + xOrientation,currentY) == null || 
							Main.tc.getTile(currentX + xOrientation, currentY).isWalkable()) && currentX != toX) {
						if(!b){
							
						}
						if(xOrientation > 0){
							d = Direction.RIGHT;
							
						}else{
							d = Direction.LEFT;
						}
						directions.add(d);
						currentX += xOrientation;
						System.out.println(currentX);
					}
				}
				if(yOrientation != 0){
					while (b = (Main.tc.getTile(currentX,currentY+yOrientation) == null || 
							Main.tc.getTile(currentX, currentY+yOrientation).isWalkable()) && currentY != toY) {
						if(yOrientation > 0){
							d = Direction.DOWN;
							
						}else{
							d = Direction.UP;
						}
						directions.add(d);
						currentY += yOrientation;
						System.out.println(currentY);
					}
				}
			}
		}
		return directions;
	}
//	private void changeX(int currentX, int currentY, int xOrientation){
//		boolean b = false;
//		
//		}
//	}
	
	
}
