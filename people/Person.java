package people;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import stages.Stages;
import application.Direction;
import application.Main;
import application.TileContainer;
import buildings.NoDoorBuilding;

public abstract class Person extends NoDoorBuilding{
	private String up;
	private String down;
	private String left;
	private String right;
	private ImageView imageView;
	
	private int x;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	private int y;
	
	public Person(int x, int y, String sprite, String up, String down, String left, String right, String standardView ) {
		super(sprite, 1, 1);
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.x = x;
		this.y = y;
		this.imageView = new ImageView(new Image(Stages.fileString(standardView), (double) Main.squareSize, (double) Main.squareSize, true, true ));
		
	}
	public ImageView setDirection(Direction d){

		ImageView iv = new ImageView();
		iv.setFitHeight((double) Main.squareSize);
		iv.setFitWidth((double) Main.squareSize);
		String s = "";
		
		switch (d){
			case RIGHT:
				s = right;
				break;
			case LEFT:
				s = left;
				break;
			case UP:
				s = up;
				break;
			case DOWN:
				s = down;
				break;
			}
		Image image = new Image(Stages.fileString(s), (double) Main.squareSize, (double) Main.squareSize, true, true );
		iv.setImage(image);
		this.imageView = iv;
		return iv;
	}
	public ImageView getSprite(){
		return imageView;
	}
	
	public void build(TileContainer tc, Pane p) {
		super.build(x, y, tc, p);
	}
	public static Direction oppositeDirection(Direction d){
		if(d == Direction.RIGHT){
			return Direction.LEFT;
		}
		if(d == Direction.LEFT){
			return Direction.RIGHT;
		}
		if(d == Direction.UP){
			return Direction.DOWN;
		}
		else{
			return Direction.UP;
		}
	}
	public void turnTowardsPlayer(){
		setDirection(oppositeDirection(Main.playerDirection));
	}
	

}
