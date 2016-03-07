package buildings;


public class HomeHouse extends Building {
	public static final int houseHeight = 4;
	public static final int houseWidth = 4,
	houseDoorX = 1,
	houseDoorY = 3;
	
	public HomeHouse() {
		super("HomeHouse", houseWidth, houseHeight);
	}

}
