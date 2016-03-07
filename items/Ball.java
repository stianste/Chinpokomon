package items;

import java.util.Random;

public abstract class Ball extends Item{
	private int bonus; //Bonus ball
	
	public Ball(String type, int bonus) {
		super(type, ItemType.POKEBALL);
		this.bonus = bonus;
	}

	@Override
	public void actionInBag() {
		System.out.println("What do with ball?");
	}
	public boolean calculateCaught(int HPMax, int HPCurrent, int pokemonRate, double bonusStatus){
		int a = (int) ((((3*HPMax - 2*HPCurrent) * pokemonRate * bonus)/(3*HPMax)) * bonusStatus);
		if(a >= 255){
			return true;
		}
		int b = (int) (1048560 / (Math.sqrt(Math.sqrt(16711680/a))));
		Random n = new Random();
		for(int i = 0; i < 4; i++){
			if(n.nextInt(65535) >= b){
				return false;
			}
		}
		return true;
		
		
	}

}
