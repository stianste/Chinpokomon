package buildings;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import stages.Stages;
import application.Main;
import application.Tile;
import application.TileContainer;

public abstract class Building {

	private ImageView iv;
	protected int height;
	protected int width;
	
	public Building(String sprite, int width, int height) {
		this.height = height;
		this.width = width;
		iv = new ImageView(new Image(Stages.fileString(sprite), (double) (width)*Main.squareSize, (double) height*Main.squareSize, false, true ));
	}
	public void makeTilesUnwalkable(TileContainer tc, int startRow, int startCol, int doorX, int doorY){

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				if(i != doorX || j != doorY){
					Tile t = new Tile(i+startRow,j+startCol,false);
					tc.setTile(i+startRow, j+startCol, t);
				}
			}
		}
		
	}	
	public ImageView getSprite() {
		return iv;
	}
	public void build(int startCornerX, int startCornerY, int doorX, int doorY, TileContainer tc, Pane p){
		makeTilesUnwalkable(tc, startCornerX, startCornerY, doorX, doorY);
		ImageView iv = getSprite();
		iv.relocate(startCornerX*Main.squareSize, startCornerY*Main.squareSize);
		p.getChildren().add(iv);
	}
}
