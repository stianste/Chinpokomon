package events;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import stages.Stages;
import animations.BlackScreenFadeAnimation;
import application.Main;

public class MoveEvent extends Event{

	private int moveToY;
	private int moveToX;
	private String toStage;
	public MoveEvent(int x, int y, int moveToX, int moveToY, String toStage, 
			boolean triggeredOnPress, boolean toBeRemoved) {
		super(x, y, triggeredOnPress, toBeRemoved);
		this.moveToX = moveToX;
		this.moveToY = moveToY;
		this.toStage = toStage;
	}

	@Override
	public void doEvents(Pane p, Main main, Scene scene) {
			moveToStage(main);
	}
	public void moveToStage(Main main){
		BlackScreenFadeAnimation a = new BlackScreenFadeAnimation(
				main, moveToX,moveToY);
		a.setCycleCount(1);
		a.play();
		main.setStage(Stages.getStage(toStage));
		//Animation takes care of changing x and y;
	}
}
