package stages;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.layout.Pane;
import application.Main;
import application.TileContainer;
import buildings.BPokeball;
import buildings.Fence;
import buildings.HomeHouse;
import events.Event;
import events.MoveEvent;

public class Stage1 extends Stages{
	
	public static final int 
	houseX = 1,
	houseY = 1,
	
	toStage2X = 7,
	toStage2Y = 0,
	
	stage2DestinationX = 7,
	stage2DestinationY = 11,
	
	houseDestionationX = 6,
	houseDestionationY = 11,
	
	
	fromStage2X = toStage2X,
	fromStage2Y = toStage2Y,
	
	pokeBallX = 11,
	pokeBallY = 0;
	Boolean gotPokeball = false;
	BPokeball ball = new BPokeball();
	
	MoveEvent moveToStageTwo = new MoveEvent(toStage2X,toStage2Y,stage2DestinationX,stage2DestinationY, 
			"Stage2", false,false);
	MoveEvent moveToStageHouse = new MoveEvent(HomeHouse.houseDoorX+houseX,HomeHouse.houseDoorY+houseY,
					houseDestionationX,houseDestionationY, "Home", false, false);
	
	List<Event> listOfEvents = new ArrayList<Event>(Arrays.asList(moveToStageTwo,moveToStageHouse)); 
	
	public Stage1(){
		super("Stage1");
		fillEvents(listOfEvents);
	}
	
	@Override
	public void buildStage(Pane p, TileContainer tc, Main main){
		super.buildStage(p, tc, main);
		p.getStyleClass().clear();
		p.getStyleClass().add("levelOne");
		Fence lf = new Fence("Left");
		Fence rf = new Fence("Right");
		lf.build(toStage2X-1,0, tc, p);
		rf.build(toStage2X+1,0, tc, p);
		HomeHouse hh = new HomeHouse();
		hh.build(houseX, houseY, HomeHouse.houseDoorX, HomeHouse.houseDoorY, tc, p);
		
		main.getPlayerView().toFront();
	}

}
