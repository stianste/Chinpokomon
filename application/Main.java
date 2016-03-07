package application;
	
import nature.Adamant;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import menus.StartMenu;
import player.Player;
import pokemon.Charizard;
import pokemon.Charmander;
import pokemon.Pokemon;
import stages.Battle;
import stages.Home;
import stages.Stage1;
import stages.Stage2;
import stages.Stages;
import animations.PathFinder;
import animations.PlayerAnimation;


public class Main extends Application {
	Main main = this;
	public static KeyCode confirmButton = KeyCode.X;
	public final static int 
	walkDuration = 500,
	gameSize = 600, 
	grid = 12, 
	circleSize = gameSize / grid / 2,
	squareSize = gameSize / grid;
	
	private static int 
	posX = gameSize / 2;
	private static int posY = gameSize / 2;
	
	private boolean 
	showGrid = false, 
	debug = true;
	
	public static boolean 
	isPaused = false, 
	isTalking = false,
	isBattling = false,
	isBusyAnimating = false,
	isChoosing = false,
	isChangingStage = false;
	
	public static Direction playerDirection = Direction.UP;
	public static boolean checkIfCenterClear = false;
	
	private static ImageView playerView;
	
	public static Player player = new Player();
	private Pokemon charmander = new Charmander(5,new Adamant());
	private Pokemon charizard = new Charizard("Charizard",5,new Adamant());
	private Stages stage = new Battle(charizard);
	
	public static Pane center = new Pane();
	private StackPane startMenu;
	public static TileContainer tc = new TileContainer();
	private KeyEvent previousEvent;
	private static BorderPane root = new BorderPane();
	private static Scene scene = new Scene(root, gameSize, gameSize);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			playerView = player.getSprite();
			
			player.addToParty(charmander);
			
			stage.buildStage(center, tc, this);
			startMenu = StartMenu.createStartMenu(center);
			center.getStyleClass().add("levelOne");
			playerView.relocate(posX, posY);
			
			root.setCenter(center);
			
			addListeners(center, scene);
			//mouseMovementListener(center, scene);
			scene.getStylesheets().add(
			getClass().getResource("application.css").toExternalForm());
//			scene.setCursor(Cursor.NONE);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	public Player getPlayer() {
		return player;
	}
	public static ImageView getPlayerView() {
		return playerView;
	}
	public KeyEvent getPreviousEvent() {
		return previousEvent;
	}

	public void showGrid(Pane pane, Boolean showGrid) {
		if (!showGrid) {
			return;
		}
		double delta = gameSize / grid;

		for (int i = 0; i < grid; i++) {
			Line line = new Line(delta * (i + 1), 0, delta * (i + 1), gameSize);
			pane.getChildren().add(line);
		}
		for (int i = 0; i < grid; i++) {
			Line line = new Line(0, delta * (i + 1), gameSize, delta * (i + 1));
			pane.getChildren().add(line);
		}
	}
	public static boolean movePlayer(Direction direction, boolean check){
		
		switch (direction){
			case RIGHT:
				if(!isWalkable(validBoarder(posX + circleSize*2), posY)){
					return false;
				}
				if(!check){
					posX = validBoarder(posX + circleSize * 2);
				}
				return true;
			case LEFT:
				if(!isWalkable(validBoarder(posX - circleSize * 2), posY)){
					return false;
				}
				if(!check){
					posX = validBoarder(posX - circleSize * 2);
				}
				return true;
			case UP:
				if(!isWalkable(posX, validBoarder(posY - circleSize * 2))){
					return false;
				}
				if(!check){
					posY = validBoarder(posY - circleSize * 2);
				}
				return true;
			case DOWN:
				if(!isWalkable(posX, validBoarder(posY + circleSize * 2))){
					return false;
				}
				if(!check){
					posY = validBoarder(posY + circleSize * 2);
				}
				return true;
			case NONE:
				return false;
		}
		return false;
		
	}
	public static void updatePlayer(){
		playerView.relocate(posX, posY);
	}
	public static boolean isWalkable(int x, int y){
		Tile t = tc.getTile(x/squareSize, y/squareSize);
		return (t == null) ? true : t.isWalkable();
	}
	public static int validBoarder(int n){
    	if(n > gameSize - circleSize*2){
    		n = gameSize - circleSize*2;
    	}
    	else if(n < 0){
    		n = 0;
    	}
    	return n;
    }
	public static int getPosX(){
		return posX;
	}
	public static int getPosY(){
		return posY;
	}
//	public void mouseMovementListener(Pane center, Scene scene){
//		scene.setOnMouseClicked(event -> {
//			int x = ((int) event.getSceneX())/squareSize;
//			int y = ((int) event.getSceneY())/squareSize;
//			PathFinder pf = new PathFinder(posX/squareSize,posY/squareSize,x,y);
//			System.out.println(pf.findPath());
//			
//		});
//	}
	public void addListeners(Pane center, Scene scene){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			Direction d = Direction.NONE;
			
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.RIGHT) {
					 d = Direction.RIGHT;
				} 
				else if (event.getCode() == KeyCode.UP) {
					d = Direction.UP;
				} 
				else if (event.getCode() == KeyCode.LEFT) {
					d = Direction.LEFT;
				} 
				else if (event.getCode() == KeyCode.DOWN) {
					d = Direction.DOWN;
				}
				else if(event.getCode() == KeyCode.G){
					showGrid = true;
				}
				else if(event.getCode() == KeyCode.X && !isTalking && !isPaused &&!isBusyAnimating){
					doEvents(center, event, scene);
				}
				else if((event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.ESCAPE) && !isTalking){
					if(changePaused()){
						center.getChildren().add(0,startMenu = StartMenu.createStartMenu(center));
						startMenu.toFront();
					}
					else{
						center.getChildren().remove(startMenu);
					}
				}
				if(!isPaused && !isTalking)	{//  && event.getCode() != KeyCode.X
					if(d != Direction.NONE){
						playerDirection = d;
						
						if((d.toString().equals("Left") && !PlayerAnimation.isGoingLeft) || 
						(d.toString().equals("Right") && !PlayerAnimation.isGoingRight) ||
						(d.toString().equals("Up") && !PlayerAnimation.isGoingUp) ||
						(d.toString().equals("Down") && !PlayerAnimation.isGoingDown)){
							PlayerAnimation.addToQue(d);
						}
						PlayerAnimation animation = new PlayerAnimation(playerView, d);
						if (
							(!isBusyAnimating &&(!isChangingStage && movePlayer(d,true) && center.getChildren().contains(playerView)))) {
							animation.setCycleCount(1);
							animation.play();
						}else{
							animation.setSprite(d);
						}

					}
					
					stage.doEvents(center, posX, posY, main, scene);
					previousEvent = event;
					stage.buildStage(center, tc, main);
					showGrid(center, showGrid);
					//playerView.relocate(posX,posY);
					if(debug){
						System.out.println("PosX: " + posX + " PosY: " + posY);
					}
				}
				d=Direction.NONE;
			}
		});
	}
	public boolean changePaused(){
		if(isPaused){
			isPaused = false;
		}
		else{
			isPaused = true;
		}
		return isPaused;
	}
	public static boolean oppositeBoolean (boolean b){
		return b ? false : true;
	}
	
	public Stages getStage() {
		return stage;
	}

	public void setStage(Stages stage) {
		this.stage = stage;
	}

	public static void setPosX(int posX) {
		Main.posX = posX;
	}

	public static void setPosY(int posY) {
		Main.posY = posY;
	}
	private void doEvents(Pane p, KeyEvent event, Scene scene) {
		switch(playerDirection){
		case UP:
			stage.doEvents(p, posX, posY - squareSize, main, scene);
			break;
		case DOWN:
			stage.doEvents(p, posX, posY + squareSize, main,  scene);
			break;
		
		case LEFT:
			stage.doEvents(p, posX - squareSize, posY, main, scene);
			break;
		
		case RIGHT:
			stage.doEvents(p, posX + squareSize, posY , main,scene);
			break;
		default:
			break;
		}
	}
	public static void addToCenter(Node n){
		center.getChildren().add(n);
	}
	public static void removeFromCenter(Node n){
		center.getChildren().remove(n);
	}
	public static boolean centerContains(Node n){
		return center.getChildren().contains(n);
	}

	public static Scene getScene() {
		return scene;
	}
}
