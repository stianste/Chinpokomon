package nature;


public abstract class Nature {
	private String description;
	public String getDescription() {
		return description;
	}
	
	private double[] yields;
	protected Nature(String description, double hp, double atk, double def, double spAtk, double spDef, double spd){
		this.description = description;
		yields = new double[]{hp,atk,def,spAtk,spDef,spd};
	}
	public double getNatureYield(int i){
		return yields[i];
	}
	
}
