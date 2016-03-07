package player;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import menus.StartMenu;
import pokemon.Pokemon;

public class PartyViewer {
	public final static double partyBoxHeight = 60;
	
	public void viewParty(VBox vbox){
		
		for(Pokemon p : Player.party){
			
			Rectangle rekt = new Rectangle(StartMenu.startMenuWidth+60, partyBoxHeight, Color.GAINSBORO);
			rekt.setArcHeight(20);
			rekt.setArcWidth(20);
			if (p != null) {
				StackPane stack = new StackPane();
				HBox hbox = new HBox();
				hbox.getStyleClass().add("startMenu");
				ImageView iv = p.getSprite();
				iv.setFitHeight(partyBoxHeight);
				VBox info = new VBox();
				info.getStyleClass().add("startMenu");
				Label name = new Label(p.getName());
				name.getStyleClass().add("startMenuButton");
				hbox.getChildren().addAll(iv, name);
				stack.getChildren().addAll(rekt,hbox);
				vbox.getChildren().add(stack);
			}else{
				vbox.getChildren().add(rekt);
			}
			
		}
	}
}
