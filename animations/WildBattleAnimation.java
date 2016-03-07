package animations;

import application.Main;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class WildBattleAnimation extends Transition{
	private Rectangle rekt = new Rectangle(Main.gameSize,Main.gameSize);
	private final Duration cycleDuration = Duration.millis(1000);
	
	public WildBattleAnimation() {
		setCycleDuration(cycleDuration);
        setInterpolator(Interpolator.LINEAR);
	}
	@Override
	protected void interpolate(double k) {
		int value = (int) k*100;
		System.out.println();
		if (!Main.centerContains(rekt)) {
			Main.addToCenter(rekt);
		}
		if(value % 10 == 0){
			if(rekt.getFill().equals(Color.BLACK)){
				rekt.setFill(Color.WHITE);
			}else{
				rekt.setFill(Color.BLACK);
			}
		}
		
		if(k == 1){
			Main.removeFromCenter(Main.getPlayerView());
			Main.removeFromCenter(rekt);
			Main.isBusyAnimating = false;
			Main.isChangingStage = false;
		}
	}

}
