package events;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import application.Main;

public abstract class Event {
	private int x;
	private int y;
	boolean triggeredOnPress = false;
	private boolean toBeRemoved = false;
	
	public Event(int x, int y, boolean triggeredOnPress, boolean toBeRemoved){
		this.x = x;
		this.y = y;
		this.triggeredOnPress = triggeredOnPress;
		this.toBeRemoved  = toBeRemoved;
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isTriggeredOnPress(){
		return triggeredOnPress;
	}
	public boolean toBeRemoved(){
		return toBeRemoved;
	}
	@Override
	public String toString(){
		return getClass().getName() + " At: " + x + " : " + y; 
	}
	
	public abstract void doEvents(Pane p, Main main, Scene scene);
	
}
