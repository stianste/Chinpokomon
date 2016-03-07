package items;

public class HealthItem extends Item {
	private int healthValue;
	
	public HealthItem(String description, int healthValue) {
		super(description, ItemType.HEALTH_ITEM);
		this.healthValue = healthValue;
	}
	
	public int getHealthValue() {
		return healthValue;
	}

	@Override
	public void actionInBag() {
		System.out.println("What do with health item?");
	}

}
