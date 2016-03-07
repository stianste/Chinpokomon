package stages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.layout.Pane;
import nature.Adamant;
import pokemon.Bulbasaur;
import application.Main;
import application.TileContainer;
import buildings.Fence;
import buildings.Gym;
import events.Event;
import events.MoveEvent;

public class Stage2 extends Stages{

	public static final int 
	gymX = 5,
	gymY = 1,
	fromStage1X = Stage1.stage2DestinationX,
	fromStage1Y = Stage1.stage2DestinationY;
	
	MoveEvent moveToStageOne = new MoveEvent(fromStage1X, fromStage1Y, Stage1.toStage2X, 
			Stage1.toStage2Y, "Stage1", false, false);
	
	List<Event> listOfEvents = new ArrayList<Event>(Arrays.asList(moveToStageOne));
	
	public Stage2() {
		super("Stage2");
		fillEvents(listOfEvents);
		wildPokemonField(0, 0, 4, 4, 0.3, new Bulbasaur(5, new Adamant()));
	}

	@Override
	public void buildStage(Pane p, TileContainer tc, Main main) {
		super.buildStage(p, tc, main);
		
		buildGrass(tc, p, 0,0,4,4);
		Gym gym = new Gym();
		gym.build(gymX, gymY, Gym.gymDoorX, Gym.gymDoorY, tc, p);
		Fence rf = new Fence("Right");
		Fence lf = new Fence("Left");
		rf.build(fromStage1X+1, fromStage1Y, tc, p);
		lf.build(fromStage1X-1, fromStage1Y, tc, p);
		
		Main.getPlayerView().toFront();
	}

}
