package pokemon;

import nature.Nature;

public class Charmander extends Pokemon{
	
	private static Stats stats = new Stats(39,52,43,60,50,65);
	private static double[] evYield = new double[]{0,0,0,0,0,1};
	
	public Charmander( int level, Nature nature) {
		super("Charmander", level, stats, nature, evYield, "CharmanderVS", "CharmanderBackSprite", 64, 57);
	}

}
