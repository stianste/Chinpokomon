package animations;


import java.util.ArrayList;
import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import application.Direction;
import application.Main;

public class PlayerAnimation extends Transition{
	private ImageView imageView;
    private final Duration cycleDuration = Duration.millis(300);
    private Direction d;
    private static List<Direction> animationQue = new ArrayList<Direction>();
    
    public static boolean
    isGoingUp = false,
    isGoingDown = false,
    isGoingLeft = false,
    isGoingRight = false;
    
    public PlayerAnimation(ImageView iv, Direction d){
    	this.d = d;
    	imageView = iv;
    	switch(d){
		case DOWN:
			isGoingDown = true;
			break;
		case LEFT:
			isGoingLeft = true;
			break;
		case NONE:
			break;
		case RIGHT:
			isGoingRight = true;
			break;
		case UP:
			isGoingUp = true;
			break;
		default:
			break;
    	
    	}
    	setCycleDuration(cycleDuration);
        setInterpolator(Interpolator.LINEAR);
    }
    @Override
	protected void interpolate(double k) {
    	Main.isBusyAnimating = true;
    	String s = d.toString();
    	double delta = 100/6;
    	double i = k*100;
    	if(i < delta*3){
    		imageView.setImage(new Image(fileString(s,1)));
    		imageView.setFitHeight(Main.squareSize);
    		imageView.setFitWidth(Main.squareSize);
    		imageView.setSmooth(true);
    	}else if(i > delta*5){
    		imageView.setImage(new Image(fileString(s,null)));
    		imageView.setFitHeight(Main.squareSize);
    		imageView.setFitWidth(Main.squareSize);
    		imageView.setSmooth(true);
    	}else{
    		imageView.setImage(new Image(fileString(s,2)));
    		imageView.setFitHeight(Main.squareSize);
    		imageView.setFitWidth(Main.squareSize);
    		imageView.setSmooth(true);
    	}
    	
    	switch(d){
		case UP:
			imageView.relocate(Main.getPosX(), Main.validBoarder((int)(Main.getPosY() - k*Main.squareSize)));
			break;
		case DOWN:
			imageView.relocate(Main.getPosX(), Main.validBoarder((int)(Main.getPosY() + k*Main.squareSize)));
			break;
		
		case LEFT:
			imageView.relocate(Main.validBoarder((int)(Main.getPosX()- k*Main.squareSize)), Main.getPosY());
			break;
		
		case RIGHT:
			imageView.relocate(Main.validBoarder((int)(Main.getPosX() + k*Main.squareSize)), Main.getPosY());
			break;
		default:
			break;
		}
    	if(k == 1){
    		if (!Main.isChangingStage) {
				Main.movePlayer(d, false);
			}
			Main.isBusyAnimating = false;
			isGoingUp = false;
		    isGoingDown = false;
		    isGoingLeft = false;
		    isGoingRight = false;
		    
		    if(animationQue.size() > 0 && animationQue.get(animationQue.size()-1).equals(d)){
		    	animationQue.remove(animationQue.size()-1);
		    }else{
		    	playQuedAnimations(d);
		    }
    	}
    	
	}
    public static void addToQue(Direction d){
    if(!animationQue.contains(d)){
    	animationQue.add(d);
    }
    }
    private void playQuedAnimations(Direction d){
    	if(animationQue.size() == 0){
    		return;
    	}
    	if (Main.movePlayer(d,true)) {
			PlayerAnimation next = new PlayerAnimation(imageView,animationQue.get(0));
			animationQue.remove(0);
			next.setCycleCount(1);
			next.play();
		}
    	
    }
    private static String fileString(String s, Integer i){
    	if(i == null ){
    		return "file:/C:/Users/Stian/Pictures/PokemonGame/Player/"+"Player" + s + "Still"+".png";
    	}
		return "file:/C:/Users/Stian/Pictures/PokemonGame/Player/"+"Player" + s + +i+".png";
	}
    public void setSprite(Direction d){
    	String s = d.toString();
    	imageView.setImage(new Image(fileString(s,null)));
		imageView.setFitHeight(Main.squareSize);
		imageView.setFitWidth(Main.squareSize);
		imageView.setSmooth(true);
    }

}
