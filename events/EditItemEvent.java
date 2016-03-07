package events;

import items.Item;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import player.Bag;
import application.Main;

public class EditItemEvent extends Event{
	private Item item;
	private int quantity;
	
	public EditItemEvent(int x, int y, boolean triggeredOnPress, Item item, int quantity) {
		super(x, y, triggeredOnPress, true);
		this.item = item;
		this.quantity = quantity;
	}

	@Override
	public void doEvents(Pane p, Main main, Scene scene) {
		if(quantity > 0){
			Bag.addItem(quantity, item);
		}
		else{
			Bag.removeItem(item, quantity);
		}
	}

}
