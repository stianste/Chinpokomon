package events;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import application.Main;

@FunctionalInterface
public interface DoEvent {
	public void doEvents(Pane p, Main main, Scene scene);
}