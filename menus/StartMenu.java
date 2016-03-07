package menus;

import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import player.Bag;
import player.Player;
import application.Main;



public class StartMenu {
	static final Color startMenuColor = Color.WHITE;
	final public static double  startMenuWidth = 200;
	final public static double startMenuHeight = Main.gameSize - 300;
	final public static double space = 40;
	final static double buttonSpace = 40;
	public final static int buttonLength = 15;
	final static String pokemonButtonString = String.format("%-"+ buttonLength +"s", "Pokemon");
	final static String bagButtonString = String.format("%-"+ buttonLength +"s", "Bag");
	
	public static StackPane createStartMenu(Pane p){
		
		StackPane startMenu = new StackPane();
		VBox buttonBox = new VBox();
		
		Button pokemonButton = new Button(pokemonButtonString);
		pokemonButton.getStyleClass().add("startMenuButton");
		StackPane choosePane = new StackPane();
		Rectangle chooseBackground = new Rectangle(startMenuWidth + 100, Main.gameSize, startMenuColor);
		chooseBackground.setArcHeight(20);
		chooseBackground.setArcWidth(20);
		chooseBackground.setOpacity(0.6);
		choosePane.relocate(space, space);
		choosePane.getChildren().add(chooseBackground);
		choosePane.setVisible(false);
		p.getChildren().add(choosePane);
		Button bagButton = new Button(bagButtonString);
		bagButton.getStyleClass().add("startMenuButton");
		pokemonButton.setOnAction(event ->{
		
				choosePane.getChildren().clear();
				choosePane.getChildren().add(chooseBackground);
				
				viewParty(choosePane);
			
		});
		bagButton.setOnAction(event -> {

			choosePane.getChildren().clear();
			choosePane.getChildren().add(chooseBackground);
			viewBag(choosePane);
			
		});
		
		buttonBox.setMinSize(startMenuWidth - buttonSpace, startMenuHeight - buttonSpace);
		buttonBox.relocate(Main.gameSize - startMenuWidth - space + buttonSpace, space + buttonSpace );
		buttonBox.getChildren().addAll(pokemonButton, bagButton);
		
		Rectangle background = new Rectangle(startMenuWidth, startMenuHeight, startMenuColor);
		//background.
		background.setArcHeight(20);
		background.setArcWidth(20);
		background.setOpacity(0.6);
		background.getStyleClass().add("startMenu");
		startMenu.getChildren().addAll(background, buttonBox);
		buttonBox.getStyleClass().add("startMenu");
		startMenu.setMinSize( startMenuWidth, startMenuHeight);
		startMenu.relocate(Main.gameSize - startMenuWidth - space, space);
		background.relocate(Main.gameSize - startMenuWidth - space, space);
		return startMenu;
		
	}
	private static void viewParty(StackPane choosePane) {
		choosePane.setVisible(Main.oppositeBoolean(choosePane.isVisible()));
		VBox vbox = new VBox();
		vbox.getStyleClass().add("startMenu");
		choosePane.getChildren().add(vbox);
		Player.viewParty(vbox);
	}
	private static void viewBag(Pane choosePane){
		choosePane.setVisible(Main.oppositeBoolean(choosePane.isVisible()));
    	TabPane tabPane = new TabPane();
    	choosePane.getChildren().add(tabPane);
    	tabPane.getStyleClass().add("startMenu");
    	Bag.viewBag(tabPane);
	}
}
