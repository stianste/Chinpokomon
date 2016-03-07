package stages;

import items.Item;
import items.Potion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import menus.YesNoMenu;
import nature.Adamant;
import people.Dude;
import people.ProfessorOak;
import player.Player;
import pokemon.Bulbasaur;
import pokemon.Charmander;
import pokemon.Pokemon;
import pokemon.Squirtle;
import application.Main;
import application.Tile;
import application.TileContainer;
import buildings.BPokeball;
import buildings.DoorMat;
import buildings.HomeHouse;
import events.ConversationEvent;
import events.CustomEvent;
import events.EditItemEvent;
import events.Event;
import events.MoveEvent;

public class Home extends Stages{
	public static final int 
	enterX = Stage1.houseDestionationX,
	enterY = Stage1.houseDestionationY,
	dudeX = 1,
	dudeY = 7,
	oakX = 6,
	oakY = 3,
	ballsX = oakX+1,
	ballsY = oakY-1;
	
	private boolean talkedToDude = false;
	
	private String conversationWithDude = "Hello man. Take this potion lol";
	private String convAfterPotionRecieved = "Hope the potion helps man. Take care it's going to be a long journey. I'm just rambling on to see how the text field reacts. Tralala";
	private String convWithOak = "Hello good sir. It's so nice to meet you. "
			+ "I would like for you to have a pokemon to begin your adventure. Choose one of the three on the side. Think long and hard for who you want to have as your"
			+ " companian for the rest of your journey.";
	
	private Dude dude = new Dude(dudeX,dudeY);
	private Potion potion = new Potion();
	private ProfessorOak oak = new  ProfessorOak(oakX,oakY);
	private BPokeball ball1 = new BPokeball();
	private BPokeball ball2 = new BPokeball();
	private BPokeball ball3 = new BPokeball();
	private Charmander charmander = new Charmander(5, new Adamant());
	private Bulbasaur bulbasaur = new Bulbasaur(5, new Adamant());
	private Squirtle squirtle = new Squirtle(5, new Adamant());
	
	ConversationEvent dudeTalkPartOne = new ConversationEvent(dude, dudeX,dudeY, true, true, 
			conversationWithDude, Item.recievedText(potion));
	
	ConversationEvent dudeTalkPartTwo = new ConversationEvent(dude, dudeX, dudeY, true, false, convAfterPotionRecieved);
	
	CustomEvent getBulbasaur = new CustomEvent(ballsX, ballsY, true, false, 
			(Pane p, Main main, Scene scene) -> {
				choosePokemon(p, bulbasaur, ballsX,ballsY, main, scene);
			});
	
	CustomEvent getSquirtle = new CustomEvent(ballsX+1, ballsY, true, false, 
			(Pane p, Main main, Scene scene) -> {
				choosePokemon(p, squirtle, ballsX+1,ballsY, main, scene);
			});
	
	CustomEvent getCharmander = new CustomEvent(ballsX+2, ballsY, true, false, 
			(Pane p, Main main, Scene scene) -> {
				choosePokemon(p, charmander, ballsX+2,ballsY, main, scene);
			});
	
	CustomEvent dudeConvEvent = new CustomEvent(dudeX,dudeY,true,false, (Pane p, Main main, Scene scene) -> {
		if(!talkedToDude){
			dudeTalkPartOne.doEvents(p,main,scene);
			talkedToDude = true;
		}else{
			dudeTalkPartTwo.doEvents(p,main,scene);
		}
	});
	
	ConversationEvent oakConv = new ConversationEvent(oak, oakX, oakY, true, false, convWithOak);
	EditItemEvent getPotion = new EditItemEvent(dudeX, dudeY, true, potion, 1);
	MoveEvent moveToStageOne = new MoveEvent(enterX, enterY, Stage1.houseX+HomeHouse.houseDoorX, Stage1.houseY+HomeHouse.houseDoorY, 
			"Stage1", false, false);
	
	List<Event> listOfEvents = new ArrayList<Event>(Arrays.asList(
			getCharmander,getBulbasaur,getSquirtle,oakConv,getPotion,moveToStageOne,dudeConvEvent
			));
	
	public Home(){
		super("Home");
		fillEvents(listOfEvents);
		
	}
	@Override
	public void buildStage(Pane p, TileContainer tc, Main main) {
		super.buildStage(p, tc, main);
		ball1.build(ballsX, ballsY, tc, p);
		ball2.build(ballsX+1, ballsY, tc, p);
		ball3.build(ballsX+2, ballsY, tc, p);
		oak.build(tc, p);
		tc.setTile(enterX, enterY, new Tile(enterX,enterY,true));
		tc.setTile(enterX+1, enterY, new Tile(enterX,enterY,true));
		p.getStyleClass().add("home");
		DoorMat mat = new DoorMat();
		mat.build(enterX, enterY, DoorMat.matDoorX, DoorMat.matDoorY, tc, p);
		dude.build(tc, p);
		
		Main.getPlayerView().toFront();
	}

	private void choosePokemon(Pane p, Pokemon poke, int ballsX,int ballsY, Main main, Scene scene){
		ConversationEvent.displayMessage("So you're going with " + poke.getName()+"?", p, main, scene);
		StackPane pane = new StackPane();
		Rectangle rekt = new Rectangle(300,300, Color.WHITE);
		rekt.setOpacity(0.5);
		rekt.setArcHeight(15);
	    rekt.setArcWidth(15);
		pane.getChildren().addAll(rekt, poke.getVsSprite());
		pane.relocate(Main.gameSize/2-150, Main.gameSize/2-150);
		p.getChildren().add(pane);
		YesNoMenu yn = new YesNoMenu();
		yn.ask(p, main, scene, 
				()->{
					p.getChildren().remove(pane);
					main.getPlayer().addToParty(poke);
					removeEvent(getBulbasaur);
					removeEvent(getSquirtle);
					removeEvent(getCharmander);
					ConversationEvent chooseNickname = new ConversationEvent(null, ballsX, ballsY, true, false,
							"You chose " + poke.getName() + ". Would you like to give " + poke.getName() + " a nickname?");
					chooseNickname.doEvents(p, main, scene);
					YesNoMenu nickname = new YesNoMenu();
					Main.isTalking = true;
					nickname.ask(p, main, scene, ()->{
						Label lable1 = new Label("Nickname: ");
						TextField textField = new TextField();
						VBox vbox = new VBox();
						Button button = new Button("Choose nickname");
						vbox.getChildren().addAll(lable1,textField,button);
						vbox.setSpacing(10);
						p.getChildren().add(vbox);
						button.setOnAction(event ->{
								Player.getPokemonAt(0).setName(textField.getText());
								p.getChildren().remove(vbox);
								chooseNickname.removeText(p);
								main.addListeners(p, scene);
								Main.isTalking = false;
						});
					}, ()->{
						Main.isTalking = false;
						chooseNickname.removeText(p);
						main.addListeners(p, scene);
						});
					
					}
					, 
				()->{
					p.getChildren().remove(pane);
					ConversationEvent.removeMessage(p);
					main.addListeners(p, scene);
				});
	}

}
