package pokemon;

import nature.Nature;

public class Squirtle extends Pokemon{
	private static Stats stats = new Stats(44,48,65,50,64,43);
	private static double[] evYield = new double[]{0,0,1,0,0,0};
	
	public Squirtle(int level, Nature nature) {
		super("Squirtle", level, stats, nature, evYield, "SquirtleVS", "Squirtle_XY_Back_Sprite", 62, 53);
	}

}
