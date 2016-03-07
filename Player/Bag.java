package player;

import items.Item;
import items.ItemType;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import menus.StartMenu;

public class Bag {
	private static Map<ItemType, Map<Item, Integer>> bag = new HashMap <>();
	private static boolean runned = false;
	private final static int formatStringLength = StartMenu.buttonLength + 12;

	//Fill with basic ItemTypes
	private static void fillStandard(){
		if(runned){
			return;
		}
		bag.put(ItemType.ITEM, new HashMap<Item, Integer>());
		bag.put(ItemType.HEALTH_ITEM, new HashMap<Item, Integer>());
		//bag.get(ItemType.HEALTH_ITEM).put(new SuperPotion(), 1);
		bag.put(ItemType.POKEBALL, new HashMap<Item, Integer>());
		bag.put(ItemType.KEY_ITEM, new HashMap<Item, Integer>());
		runned = true;
	}
	
	public Bag(){
		fillStandard();
	}
	
	public static void addItem(int quantity, Item item){
		ItemType it = item.getItemType();
		fillStandard();
		if(bag.get(it).containsKey(item)){
			bag.get(it).put(item, quantity + bag.get(it).get(item));
		}
		else{
			bag.get(it).put(item, quantity);
		}
		if(bag.get(it).get(item) < 0){
			bag.get(it).put(item, 0);
		}
	}
	
	public static int getItemQuantity(Item item){
		ItemType it = item.getItemType();
		if(!bag.containsKey(it)){
			return 0;
		}
		else if(bag.get(it).containsKey(item)){
			return bag.get(it).get(item);
		}
		else{
			return 0;
		}
	}
	
	public static void removeItem(Item item, int quantity){
		if(getItemQuantity(item) == 0){
			return;
		}else{
			addItem(-quantity, item);
		}
	}
	public static boolean containsItem(Item item){
			return getItemQuantity(item) > 0;
	}
	public static void printBag(){
		System.out.println(bag);
	}

	public static void viewBag(TabPane tb) {
		fillStandard();
		for(ItemType it : ItemType.values()){
			Tab tab = new Tab(it.toString());
			tb.getTabs().add(fillTab(tb, tab, it));
		}
		
		
	}
	private static Tab fillTab(TabPane tb, Tab tab, ItemType it){
		tab.setClosable(false);
		int innerCounter = 0;
		VBox vbox = new VBox();
		vbox.getStyleClass().add("startMenu");
		for(Map.Entry<Item, Integer> e : bag.get(it).entrySet()){
			Button btn = new Button(String.format("%-"+ (formatStringLength  - 3) + "sx%02d", e.getKey().getDescription(), e.getValue()));
			btn.getStyleClass().add("startMenuButton");
			btn.setOnAction( event ->{
				e.getKey().actionInBag();
			});
			vbox.getChildren().add(btn);
			
			innerCounter += 1;
		}
		if (innerCounter < 1){
			vbox.getChildren().add((new Label("Empty")));
		}
		tab.setContent(vbox);
		return tab;
	}
	
}
