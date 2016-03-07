package events;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import people.Person;
import application.Main;

public class ConversationEvent extends Event {
	private static StackPane pane = new StackPane();
	private Person talkingTo;
	public final static Color textBoxColors = Color.WHITE;
	private List<String> labels = new ArrayList<String>();
	public static final double MAX_STRING_LENGTH = 50;
	public static final int converBoxHeight = 100,
			converBoxWidth = Main.gameSize;

	public ConversationEvent(Person talkingTo, int x, int y, boolean triggeredOnPress, boolean toBeRemoved,String... a) {
		super(x, y, triggeredOnPress, toBeRemoved);
		fillLabels(a);
		this.talkingTo = talkingTo;
	}

	@Override
	public void doEvents(Pane p, Main main, Scene scene) {
		playConversation(p, scene, main);
	}
	public void fillLabels(String... a){
		for (String s : a) {
			StringBuilder sb = new StringBuilder();
			
			int currentLineSize = 0;
			for (String b : s.split(" ")) {
				if (currentLineSize + b.length() <= MAX_STRING_LENGTH) {
					currentLineSize += b.length() +1;
					sb.append(b + " ");
					
				}
				else{
					labels.add(sb.toString());
					sb.setLength(0);
					currentLineSize = 0;
					currentLineSize += b.length() +1;
					sb.append(b + " ");
				}
				
			}
			labels.add(sb.toString());
			
		}
	}
	public void addText(String... a){
		fillLabels(a);
		System.out.println(labels);
	}
	
	
	public void playConversation(Pane p, Scene scene, Main main) {
		try {
			talkingTo.turnTowardsPlayer();
		} catch (NullPointerException e) {
			
		}
		if (!Main.isTalking && talkingTo!= null) {
			System.out.println("Stage was built in conversationEvent");
			main.getStage().buildStage(p, main.tc, main);
		}
		Main.isTalking = true;

		//Pane is in object
		VBox vbox = new VBox();
		Rectangle rekt = new Rectangle(converBoxWidth, converBoxHeight,textBoxColors);
		vbox.getStyleClass().add("textbox");
		pane.getChildren().addAll(rekt, vbox);
		pane.relocate(Main.gameSize - converBoxWidth, Main.gameSize - converBoxHeight);
		pane.toFront();

		if (!p.getChildren().contains(pane)) {
			p.getChildren().addAll(pane);
		}
		int c = 0;
		Text textOne;

		Text textTwo;
		if (labels.size() == 1) {
			textOne = new Text(labels.get(c));
			textOne.getStyleClass().add("textbox");
			c += 1;
			textTwo = new Text("");
			textTwo.getStyleClass().add("textbox");
			vbox.getChildren().addAll(textOne, textTwo);
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					if (event.getCode() == Main.confirmButton) {
						p.getChildren().removeAll(pane);
						Main.isTalking = false;
						main.addListeners(p, scene);
					}
				}
			});
		}

		else {
			textOne = new Text(labels.get(c));
			textOne.getStyleClass().add("textbox");
			c += 1;
			textTwo = new Text(labels.get(c));
			textTwo.getStyleClass().add("textbox");
			c += 1;
			vbox.getChildren().addAll(textOne, textTwo);

			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				int counter = 2;
				Text t1 = textOne;
				Text t2 = textTwo;

				@Override
				public void handle(KeyEvent event) {

					if (event.getCode() == Main.confirmButton) {
						if (counter < labels.size()) {
							vbox.getChildren().clear();
							t1 = new Text(labels.get(counter));
							counter += 1;
							if (counter == labels.size()) {
								t2 = new Text("");
							} else {
								t2 = new Text(labels.get(counter));
							}
							counter += 1;
							vbox.getChildren().addAll(t1, t2);
						} else {
							p.getChildren().removeAll(pane);
							Main.isTalking = false;
							main.addListeners(p, scene);
						}
					}

				}
			});
		}
	}
	public static void displayMessage(String s, Pane p, Main main, Scene scene){
		
		Main.isTalking = true;
		pane.getChildren().clear();
		//StackPane pane = new StackPane();
		VBox vbox = new VBox();
		Rectangle rekt = new Rectangle(converBoxWidth, converBoxHeight,textBoxColors);
		vbox.getStyleClass().add("textbox");
		pane.getChildren().addAll(rekt, vbox);
		pane.relocate(Main.gameSize - converBoxWidth, Main.gameSize - converBoxHeight);
		pane.toFront();
		Text text = new Text(s);
		text.getStyleClass().add("textbox");
		vbox.getChildren().addAll(text);
		pane.toFront();
		if(!p.getChildren().contains(pane)){
			p.getChildren().addAll(pane);
			
		}
		scene.setOnKeyPressed(event->{
				p.getChildren().remove(pane);
				Main.isTalking = false;
		});
	}
	public void clearText(){
		pane.getChildren().clear();
		labels.clear();
	}
	public Person getTalkingTo() {
		return talkingTo;
	}
	public void removeText(Pane p){
		p.getChildren().remove(pane);
		Main.isTalking = false;
	}
	public static void removeMessage(Pane p){
		p.getChildren().remove(pane);
		Main.isTalking = false;
	}

}
