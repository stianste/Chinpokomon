package pokemon;

import java.util.Random;

public class Stats {
	private int 
	baseHP = 20,
	baseAtk = 10,
	baseDef = 10,
	baseSpAtk = 10,
	baseSpDef = 10,
	baseSpd = 10,
	hpIV, atkIV, defIV, spAtkIV, spDefIV,spdIV;
	Pokemon pokomon;
	private int[] stats = new int[7];
	
	@Override
	public String toString(){
		return ("HP " + stats[0] + " " + "Attack " + stats[1] + " " + "Defense " + stats[2] + " " + "SpAtk " + stats[3] + " " 
				+ "SpDef " + stats[4] + " " +  "Speed " + stats[5] + " " );
	}
	
	public Stats(int hp, int attack, int def, int spAtk, int spDef, int speed) {
		if (hp > 0 && attack > 0 && def > 0 && spAtk > 0 && spDef > 0 && speed > 0) {
			Random rand = new Random();
			
			baseHP = hp;
			baseAtk = attack;
			baseDef = def;
			baseSpAtk = spAtk;
			baseSpDef = spDef;
			baseSpd = speed;
			hpIV = rand.nextInt(32);
			atkIV = rand.nextInt(32);
			defIV = rand.nextInt(32);
			spAtkIV = rand.nextInt(32);
			spDefIV = rand.nextInt(32);
			spdIV = rand.nextInt(32);
//			this.pokomon = p;
//			calculateStats(p);
			
		} 
		else{
			throw new IllegalArgumentException("Base stats must be a positive number");
		}
		
	}
	private int[] calculateStats(Pokemon p){
		int level = p.getLevel();
		stats[0] = ((hpIV + (2 * baseHP) + (p.getHPEV()/4) + 100)*level)/100 + 10;
		stats[1] = calculateOthers(atkIV, baseAtk, p.getAtkEV(), p.getNature(1), level);
		stats[2] = calculateOthers(defIV, baseDef, p.getDefEV(), p.getNature(2), level);
		stats[3] = calculateOthers(spAtkIV, baseSpAtk, p.getSpAtkEV(), p.getNature(3), level);
		stats[4] = calculateOthers(spDefIV, baseSpDef, p.getSpDefEV(), p.getNature(4), level);
		stats[5] = calculateOthers(spdIV, baseSpd, p.getSpdEV(), p.getNature(5), level);
		return stats;
		
	}
	private int calculateOthers(int iv, int base, int EV, double nature, int level){
		Double n = ((((iv + (2 * base) + (EV/4.0) + 100)*level)/100.0 + 5)*nature);
		System.out.println(n);
		return n.intValue();
	}
	public Stats getStats(Pokemon p){
		calculateStats(p);
		return this;
	}
	
	
}
