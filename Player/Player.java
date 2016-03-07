package player;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import pokemon.Pokemon;
import application.Main;

public class Player {
	
	private ImageView iv = new ImageView(
			new Image("file:/C:/Users/Stian/Pictures/PokemonGame/Player/PlayerUpStill.png", (double) Main.squareSize, (double) Main.squareSize, true, true ));
	public static Party party = new Party();
	public Player(){
		
	}
	public void addToParty(Pokemon p){
		party.addPokemon(p);
	}
	public static Pokemon getPokemonAt(int i){
		return party.getPokemon(i);
	}
	public ImageView getSprite(){
		return iv;
	}
	public static void viewParty(VBox vbox) {
		party.viewParty(vbox);
	}
}
