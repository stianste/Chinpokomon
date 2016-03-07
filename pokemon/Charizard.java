package pokemon;

import nature.Nature;

public class Charizard extends Pokemon{
	private static Stats stats = new Stats(78,84,78,109,85,100);
	private static double[] evYield = new double[]{0,0,0,3,0,0};
	public Charizard(String name, int level, Nature nature) {
		super(name, level, stats, nature, evYield, "CharizardVS", "CharizardBack_XY", 172, 166);
		// TODO Auto-generated constructor stub
	}

}
