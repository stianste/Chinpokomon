package items;

public abstract class Item {
	public static final int kindsOfItems = 5;
	
	private String description;
	private ItemType type;
	
	
	public Item(String description, ItemType type) {
		this.description = description;
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public ItemType getItemType() {
		return type;
	}
	public static String recievedText(Item item, String article){
		return "You recieved " + article + " " + item.getDescription();
	}
	public static String recievedText(Item item){
		return "You recieved a" + " " + item.getDescription();
	}
	public abstract void actionInBag();
	
	
}
