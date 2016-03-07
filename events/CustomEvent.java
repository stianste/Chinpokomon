package events;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import application.Main;

public class CustomEvent extends Event{
	DoEvent event = null;
	CustomDoEvent customEvent = null;
	public CustomEvent(int x, int y, boolean triggeredOnPress, boolean toBeRemoved, DoEvent event) {
		super(x, y, triggeredOnPress, toBeRemoved);
		this.event = event;
	}
	public CustomEvent(int x, int y, boolean triggeredOnPress, boolean toBeRemoved, CustomDoEvent customEvent) {
		super(x, y, triggeredOnPress, toBeRemoved);
		this.customEvent = customEvent;
	}

	@Override
	public void doEvents(Pane p, Main main, Scene scene) {
		if(this.event == null){
			customEvent.doEvents();
		}else{
			this.event.doEvents(p,main,scene);
		}
	}

}
