package events;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import pokemon.Pokemon;
import animations.WildBattleAnimation;
import application.Main;

public class WildPokemonEvent extends Event{
	double probability;
	Pokemon[] pokemons;
	public WildPokemonEvent(int x, int y, double probability, Pokemon...a) {
		super(x, y, false, false);
		this.probability = probability;
		this.pokemons = a;
	}

	@Override
	public void doEvents(Pane p, Main main, Scene scene) {
		Random rand = new Random();
		double i = rand.nextDouble();
		int j = 0;
		if(pokemons.length != 1){
			j = rand.nextInt(pokemons.length-1);
		}
	
		if(i < probability){
			Pokemon pokemon = pokemons[j];
			WildBattleAnimation a = new WildBattleAnimation();
			a.setCycleCount(1);
			a.play();
			System.out.println("Met a wild" + pokemon.getName());
			MoveEvent moveToBattle = new MoveEvent(0,0,0,0,"Battle",false,false);
			moveToBattle.doEvents(p,main,scene);
		}
	}

}
