package player;

import java.util.Iterator;

import javafx.scene.layout.VBox;
import pokemon.Pokemon;

public class Party implements Iterable<Pokemon> {
	private Pokemon[] party = new Pokemon[6];
	private PartyViewer partyViewer = new PartyViewer();
	
	public void addPokemon(Pokemon p){
		for(int i = 0; i < party.length; i++){
			if(party[i] == null){
				party[i] = p;
				return;
			}
		}
		throw new IllegalArgumentException("Party full. Need to implement box");
	}

	public Pokemon getPokemon(int i){
		return party[i];
	}
	@Override
	public Iterator<Pokemon> iterator() {
		
		Iterator<Pokemon> iterator = new Iterator<Pokemon>(){
			int count = 0;
			
			@Override
			public boolean hasNext() {
				return count < 6;
			}

			@Override
			public Pokemon next() {
				return party[count++];
			}
			
		};
		return iterator;
	}
	public void viewParty(VBox vbox){
		partyViewer.viewParty(vbox);
	}
	
}
