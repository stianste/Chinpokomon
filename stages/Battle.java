package stages;

import events.ConversationEvent;
import player.Player;
import pokemon.Pokemon;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import application.Main;
import application.TileContainer;

public class Battle extends Stages{
	private static final double ratio = 1.9;
	public static final int converBoxHeight = 100;
	public static final int converBoxWidth = Main.gameSize;
	public static final double battleBoxHeight = 76;
	Pokemon[] vs;
	public Battle(Pokemon... vs) {
		super("Battle");
		this.vs = vs;
	}
	public void playBattle(Pane p, Main main){
		p.getChildren().clear();
		Rectangle black = new Rectangle(Main.gameSize,Main.gameSize);
		ImageView background = new ImageView(Stages.fileString("BattelBackgroundField"));
		ImageView platforms = new ImageView(Stages.fileString("GrassPlatforms"));
		background.toBack();
		background.relocate(0, 75);
		background.setFitWidth(Main.gameSize);
		background.setPreserveRatio(true);
		platforms.setFitWidth(Main.gameSize);
		platforms.setPreserveRatio(true);
		platforms.relocate(0, 240);
		p.getChildren().addAll(black,background, platforms);
		int opponentIndex = 0;
		Pokemon playerPoke;
		try {
			playerPoke = Player.getPokemonAt(0);
			ImageView playerPokemonBack = playerPoke.getBackSprite();
			playerPokemonBack.setFitWidth(playerPoke.getWidth()*ratio);
			playerPokemonBack.setFitHeight(playerPoke.getHeight()*ratio);
			playerPokemonBack.relocate(270-playerPoke.getWidth()*ratio, 490-playerPoke.getHeight()*ratio);
			playerPokemonBack.setSmooth(true);
			playerPokemonBack.toFront();
			p.getChildren().add(playerPokemonBack);
		} catch (NullPointerException e) {
			throw new IllegalStateException("Player needs to have at least one pokemon!");
		}
		
		Pokemon opponent = vs[0];
		ImageView opponentView = opponent.getVsSprite();
		opponentView.relocate(350, 150);
		p.getChildren().add(opponentView);
		
		StackPane mainMainBox = new StackPane();
		HBox mainBox = new HBox();
		mainMainBox.relocate(0, Main.gameSize - battleBoxHeight);
		Rectangle rekt = new Rectangle(Main.gameSize, battleBoxHeight, Color.WHITE);
		Text text = new Text("What will " + playerPoke.getName() + " do?");
		HBox optionsBox = new HBox();
		VBox topOptions = new VBox();
		VBox bottomOptions = new VBox();
		Button fight = new Button("Fight");
		Button bag = new Button("Bag");
		Button pokemon = new Button("Pokemon");
		Button run = new Button("Run");
		
		mainBox.setSpacing(200);
		mainMainBox.getChildren().addAll(rekt, mainBox);
		mainBox.getChildren().addAll(text, optionsBox);
		optionsBox.getChildren().addAll(topOptions, bottomOptions);
		topOptions.getChildren().addAll(fight, bag);
		bottomOptions.getChildren().addAll(pokemon, run);
		p.getChildren().addAll(mainMainBox);
		
		
	}
	@Override
	public void buildStage(Pane p, TileContainer tc, Main main) {
		super.buildStage(p, tc, main);
		playBattle(p, main);
	}

}
