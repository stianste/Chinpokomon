package application;

public enum Direction {
	
	RIGHT("Right"), LEFT("Left"), UP("Up"), DOWN("Down"), NONE("None");
	String toString;
	private Direction(String s){
		toString = s;
	}
	public String toString(){
		return toString;
	}
}
