package menus;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import application.Main;
import events.ConversationEvent;
import events.CustomDoEvent;

public class YesNoMenu {
	private static final int boxWidth = 70;
	private static final int boxHeight = 70;
	
	private static final int x = Main.gameSize-50;
	private static final int y = Main.gameSize-ConversationEvent.converBoxHeight- 50;

	public YesNoMenu() {
	
	}
	public void ask(Pane p,  Main main, Scene scene, CustomDoEvent ifYes, CustomDoEvent ifNo){
		Main.isTalking = true;
		Main.isChoosing = true;
		Rectangle rekt = new Rectangle(boxWidth,boxHeight, Color.WHITE);
		rekt.setArcHeight(5);
		rekt.setArcWidth(5);
		
		Button yes = new Button("Yes");
		yes.getStyleClass().add("startMenuButton");
		Button no = new Button("No");
		no.getStyleClass().add("startMenuButton");
		VBox vbox = new VBox();
		rekt.relocate(x,y);
		vbox.relocate(x, y);
		vbox.getChildren().addAll(yes,no);
		p.getChildren().addAll(rekt, vbox);
		yes.setOnAction(event -> {
				ifYes.doEvents();
				p.getChildren().removeAll(rekt,vbox);
				Main.isChoosing = false;
				Main.isTalking = false;
		});
		no.setOnAction(event -> {
				ifNo.doEvents();
				p.getChildren().removeAll(rekt,vbox);
				Main.isChoosing = false;
				Main.isTalking = false;
		});
		
	}
	 
	
}
