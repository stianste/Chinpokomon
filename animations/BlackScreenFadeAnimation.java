package animations;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import application.Main;

public class BlackScreenFadeAnimation extends Transition{
	private Rectangle rekt = new Rectangle(Main.gameSize,Main.gameSize);
	private final Duration cycleDuration = Duration.millis(400);
	private int moveToX; 
	private int moveToY;
	public BlackScreenFadeAnimation(Main main,int toPosX, int toPosY) {
		Main.addToCenter(rekt);
		setCycleDuration(cycleDuration);
        setInterpolator(Interpolator.LINEAR);
        this.moveToX = toPosX;
        this.moveToY = toPosY;
	}
	@Override
	protected void interpolate(double k) {
		Main.isBusyAnimating = true;
		Main.isChangingStage = true;
		Main.removeFromCenter(Main.getPlayerView());
		if (!Main.centerContains(rekt)) {
			Main.addToCenter(rekt);
		}
		rekt.setOpacity((1-k));
		if(k == 1){
			Main.removeFromCenter(rekt);
			Main.setPosX(moveToX*Main.squareSize);
			Main.setPosY(moveToY*Main.squareSize);
			Main.movePlayer(Main.playerDirection, false);
			Main.updatePlayer();
			Main.addToCenter(Main.getPlayerView());
			Main.isBusyAnimating = false;
			Main.isChangingStage = false;
		}
	}

}
