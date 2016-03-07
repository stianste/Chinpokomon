package items;

public enum ItemType {
	HEALTH_ITEM ("Health Item"), KEY_ITEM("Key Items"), POKEBALL("Pokeballs"), ITEM("Item");
	
	private final String toString;
	private ItemType(String s){
		toString = s;
	}
	public String toString(){
		return toString;
	}
}
