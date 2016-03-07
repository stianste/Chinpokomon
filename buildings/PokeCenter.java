package buildings;

import javafx.scene.layout.Pane;
import application.TileContainer;

public class PokeCenter extends Building{
	
	public static final int pokeCenterHeight = 3;
	public static final int pokeCenterWidth = 3;
	
	


	public PokeCenter() {
		super("PokeCenter", pokeCenterHeight, pokeCenterWidth);
		
	}
	@Override
	public void build(int startCornerX, int startCornerY, int doorX, int doorY,
			TileContainer tc, Pane p) {
		super.build(startCornerX, startCornerY, doorX, doorY, tc, p);
	}
	
}
