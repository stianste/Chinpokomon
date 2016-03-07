package pokemon;

import nature.Nature;

public class Bulbasaur extends Pokemon{
	private static Stats stats = new Stats(45,49,49,65,65,45);
	private static double[] evYield = new double[]{0,0,0,1,0,0};
	
	public Bulbasaur(int level, Nature nature) {
		super("Bulbasaur", level, stats, nature, evYield, "BulbasaurVS", "Bulbasaur_XY_Back", 52, 50);
	}

}
