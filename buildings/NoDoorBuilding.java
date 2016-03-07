package buildings;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import application.Main;
import application.Tile;
import application.TileContainer;

public class NoDoorBuilding extends Building {

	public NoDoorBuilding(String sprite, int height, int width) {
		super(sprite, height, width);
	}
	
	public void makeTilesUnwalkable(TileContainer tc, int startRow, int startCol){

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Tile t = new Tile(i+startRow,j+startCol,false);
				tc.setTile(i+startRow, j+startCol, t);
			}
		}
		
	}	
	public void build(int startCornerX, int startCornerY, TileContainer tc, Pane p){
		makeTilesUnwalkable(tc, startCornerX, startCornerY);
		ImageView iv = getSprite();
		iv.relocate(startCornerX*Main.squareSize, startCornerY*Main.squareSize);
		p.getChildren().add(iv);
	}

}
