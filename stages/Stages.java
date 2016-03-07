package stages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import pokemon.Pokemon;
import application.Main;
import application.TileContainer;
import buildings.Grass;
import events.Event;
import events.WildPokemonEvent;

public abstract class Stages {
	protected int squareSize = Main.squareSize;	
	public int fadeTime = 50;
	private final String description;
	protected List<Event> listOfEvents = new ArrayList<Event>();
	private static List<Stages> stages = new ArrayList<Stages>(Arrays.asList(new Stage1(), new Stage2(), new Home(), new Battle()));
	
	public Stages(String description){
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	
	protected void fillEvents(List<Event> e){
		for(Event list: e){
			listOfEvents.add(list);
		}
	}
	public static String fileString(String s){
		return "file:/C:/Users/Stian/Pictures/PokemonGame/" + s + ".png";
	}
	public static String fileStringGif(String s){
		return "file:/C:/Users/Stian/Pictures/PokemonGame/" + s + ".gif";
	}
	public void doEvents(Pane p, int posX, int posY, Main main, Scene scene){
		int x = posX/(squareSize);
		int y = posY/(squareSize);
		List<Event> returnList = new ArrayList<Event>();
		
		for(Event e: listOfEvents){
			
			if(e.getX() == x && e.getY() == y){
				
				e.doEvents(p, main, scene);
				
				if(!e.toBeRemoved()){
					returnList.add(e);
				}
			}else{
				returnList.add(e);
			}
		}
		listOfEvents = returnList;
			
	}
	
	public void buildStage(Pane p, TileContainer tc, Main main){
		tc.clear();
		p.getChildren().clear();
		if(Main.checkIfCenterClear){
			System.out.println("Center was cleared (in super stage)");
		}
		p.getChildren().add(main.getPlayerView());
	}
	public static Stages getStage(String s){
		for(Stages stage : stages){
			if(stage.getDescription().equals(s)){
				return stage;
			}
		}
		throw new IllegalArgumentException("Cannot find stage: " + s);
	}
	protected void addEvent(Event list){
		listOfEvents.add(list);
	}
	protected void removeEvent(Event list){
		listOfEvents.remove(list);
	}
	protected List<Event> getEvents(){
		return listOfEvents;
	}
	protected void wildPokemonField(int startCornerX, int startCornerY, int width, int height, double probability, Pokemon...a){
		for (int i = startCornerX; i < width + startCornerX; i++) {
			for (int j = startCornerY; j < height + startCornerY; j++) {
				WildPokemonEvent e = new WildPokemonEvent(i, j, probability, a);
				addEvent(e);
			}
		}
	}
	protected void buildGrass(TileContainer tc, Pane p, int startCornerX, int startCornerY, int width, int height){
		for (int i = startCornerX; i < width + startCornerX; i++) {
			for (int j = startCornerY; j < height + startCornerY; j++) {
				Grass grass = new Grass();
				grass.build(i, j, 0, 0, tc, p);
			}
		}
	}
	
	
}
