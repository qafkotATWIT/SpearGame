package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Main{
	public Stage stage;
	private BorderPane menuRoot;
	private static Scene menuScene;
	private Insets INSETS = new Insets(10, 10, 10, 10);
	private Pane gameRoot;
	private static Scene game1Scene;
	double mouseX;
	double mouseY;
	private List<PathTransition> bullets;
	private static int level = 1;
	private static int bossCount;
	private String displayLevel = "Level: " + level;
	private static double percentHealth;
	private ArrayList<Boss> bossNum = new ArrayList<Boss>();
	private static int hasHit=-1;
	public Game(Stage stage) {
		this.stage = stage;
		buildMenu();	
	}
	
/*--------------------------------------------------------------Menu Starts----------------------------------------------------------------------*/
	public void buildMenu() {
		menuRoot = new BorderPane(); // layout of scene 

		VBox top = new VBox(); // Space contains label.
		VBox options = new VBox(8); // Space contains buttons.
		
		// Buttons
		Button playgame1 = createButton("Game 1");	//1
		playgame1.setOnAction(e -> buildGame(level));
		Button playgame2 = createButton("Game 2");	//2
		playgame2.setOnAction(e -> buildGame(level));
		Button exit = createButton("Exit");			//3
		exit.setOnAction(e -> Platform.exit());		
		
		// Labels
		Label name = new Label("Spear Game");
		name.setFont(Font.font("Cambria", 75));
		
		options.setAlignment(Pos.CENTER); // these align and size the VBoxs
		top.setAlignment(Pos.BOTTOM_CENTER);
		top.setPrefSize(100, 250);
		
		top.getChildren().addAll(name);
		options.getChildren().addAll(playgame1,playgame2,exit);		
		menuRoot.setCenter(options);
		menuRoot.setTop(top);
		menuScene = new Scene(menuRoot,getWidth(),getHeight());
		stage.setTitle("Spear Game");
		stage.setScene(menuScene);
		stage.centerOnScreen();
		stage.show();
	}
/*-----------------------------------------------------------------Menu Ends-----------------------------------------------------------------*/
	
	
	
/*---------------------------------------------------------Start of Game-----------------------------------------------------------------*/
	public void buildGame(int level) {
		gameRoot = new Pane();
		game1Scene = new Scene(gameRoot,getWidth(),getHeight());
		stage.setTitle("Game 1");
		stage.setScene(game1Scene);
		stage.centerOnScreen();
		stage.show();	
		
		System.out.println("bossarray size:"+bossNum.size());
		setbossCount(level+2);
		bossNum.clear();
		System.out.println(getbossCount());
		Player man = new Player(150, 100);
		for(int i=0; i<=getbossCount(); i++) {
			bossNum.add(new Boss());
		}
		//setbossCount(bossNum.size());
		bullets = new ArrayList<>();
		Text levelText = new Text();
		setPercentHealth((man.getCurrentHealth()/man.getMaxHealth())*100);
		Rectangle healthbarPlayer = new Rectangle();
		healthbarPlayer.setFill(Color.RED);
		healthOnScreen(man, healthbarPlayer);
		Line topLine = new Line(0,50,getWidth(),50);
		displayLevel = "Level: " + level;
		levelOnScreen(displayLevel, levelText);
		
		bossNum.get(0).getBoss().setFill(Color.GREEN);
        bossNum.get(1).getBoss().setFill(Color.BLUE);
        bossNum.get(2).getBoss().setFill(Color.GRAY);
        
        Map <KeyCode, Boolean> pressedKeys = new HashMap<>();
        game1Scene.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });
        game1Scene.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });
        
        
        new AnimationTimer() {
            @Override
            public void handle(long now) {            	
                if(pressedKeys.getOrDefault(KeyCode.LEFT, false) || pressedKeys.getOrDefault(KeyCode.A, false)) {
                    man.turnLeft();
                }
                if(pressedKeys.getOrDefault(KeyCode.RIGHT, false) || pressedKeys.getOrDefault(KeyCode.D, false)) {
                    man.turnRight();
                }
                if(pressedKeys.getOrDefault(KeyCode.UP, false) || pressedKeys.getOrDefault(KeyCode.W, false)) {
                    man.up();
                }
                if(pressedKeys.getOrDefault(KeyCode.DOWN, false) || pressedKeys.getOrDefault(KeyCode.S, false)) {
                    man.down();
                }
                if(pressedKeys.getOrDefault(KeyCode.R, false)) {
                    man.restart();
                }
                if(pressedKeys.getOrDefault(KeyCode.ESCAPE, false)) {
                	this.stop();
                }
//                if(bossCount==0 || bossNum.size()==0) {
//	            	Platform.exit();
//	            }
                if(bossNum.size()!=0) {
                	for(int i=0; i<bossNum.size();i++) {
                    	if(bossNum.get(i).getBossCurrentHealth()==0) {        	            
            	            gameRoot.getChildren().remove(bossNum.get(hasHit).getBoss());
            	            bossNum.remove(hasHit);
            	            System.out.println(getbossCount());
            	            System.out.println("bossarray size:"+bossNum.size());
            	            if(bossNum.size()==0) {
            	            	levelUp();
            	            }
            	            bossNum.get(i).setBossCurrentHealth(100);
            	            if(bossNum.size()!=0)
            	            	setbossCount(getbossCount()-1);
            	            //bullets.get(i).stop();
            	            //bullets.clear();
            	        }
                    }
                	
                	 if(bossNum.size()!=0) {
                     	for(int i=0; i<bossNum.size();i++) {
                     		if(overlap(man,bossNum.get(i))) {
                        		man.restart();
                        		System.out.println("Died");
                        		gameRoot.getChildren().remove(levelText);
                        		levelOnScreen(displayLevel, levelText);
                        		man.setCurrentHealth(man.getCurrentHealth()-10);
                        		gameRoot.getChildren().remove(healthbarPlayer);
                        		healthOnScreen(man, healthbarPlayer);
                        	}
                     	}
                	 }
                   
                    if(bullets.size()>0) {
                    	hasHit= bulletOverlap( ((Rectangle)bullets.get(0).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                		}
                	}
                }
                
                if(percentHealth==0) {
                	cleanUp();
                }
//                if(bullets.size()>0)
//                	System.out.println( bullets.size());
                
            }
        }.start();
        
        
        game1Scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
            	mouseX = event.getX();
            	mouseY = event.getY();
            	fire(man);
            }
          });
        
        for(int i=0; i<bossNum.size(); i++) {
        	gameRoot.getChildren().add(bossNum.get(i).getBoss());
        }
        gameRoot.getChildren().add(man.getCharacter());
        gameRoot.getChildren().add(topLine);
	
	}
/*-----------------------------------------------------End of Game---------------------------------------------------------------------------*/	
	
	
	public void fire(Player man) {
		Line bulletPath = new Line(man.getX() + man.getCharacter().getWidth()/2, man.getY() + man.getCharacter().getHeight()/2, mouseX, mouseY);
		bullets.add(bullet(new Rectangle(10,10), bulletPath, man));
	}
	
	
	public PathTransition bullet(Rectangle bullet, Line bulletPath, Player man) {
		gameRoot.getChildren().add(bullet);
    	PathTransition transition = new PathTransition();
    	transition.setNode(bullet);
    	transition.setPath(bulletPath);
    	transition.play();
    	transition.setOnFinished(e -> {gameRoot.getChildren().remove(bullet);bullets.clear();});
    	return transition;
	}
	
	
	public boolean overlap(Player man, Boss b){
		if( ( ((man.getX()>b.getX()) && (man.getX()<(b.getX()+b.getSizeX()) ) ) 
				&& ((man.getY()>b.getY()) && (man.getY() < (b.getY()+b.getSizeY()) ) ) )
		|| ((((man.getX()+man.getSizeX())>b.getX()) && ((man.getX()+man.getSizeX()) < (b.getX()+b.getSizeX()) ) ) 
				&& (((man.getY()+man.getSizeY())>b.getY()) && ((man.getY()+man.getSizeY()) < (b.getY()+b.getSizeY()) ) ) ) )  {
			return true;
		}
		return false;
	}
	
	public int bulletOverlap(Rectangle bullet, ArrayList<Boss> b){
		for(int i=0; i<b.size();i++) {
			if( ( ((bullet.getTranslateX()>(b.get(i)).getX()) && (bullet.getTranslateX()<((b.get(i)).getX()+(b.get(i)).getSizeX()) ) ) 
					&& ((bullet.getTranslateY()>(b.get(i)).getY()) && (bullet.getTranslateY() < ((b.get(i)).getY()+(b.get(i)).getSizeY()) ) ) )
			|| ((((bullet.getTranslateX()+bullet.getBoundsInParent().getWidth())>(b.get(i)).getX()) && ((bullet.getTranslateX()+bullet.getBoundsInParent().getWidth()) < ((b.get(i)).getX()+(b.get(i)).getSizeX()) ) ) 
					&& (((bullet.getTranslateY()+bullet.getBoundsInParent().getHeight())>(b.get(i)).getY()) && ((bullet.getTranslateX()+bullet.getBoundsInParent().getWidth()) < ((b.get(i)).getY()+(b.get(i)).getSizeY()) ) ) ) )  {
				
				return i;
			}
		}
		return -1;
	}
	
	
	public void levelOnScreen(String displayLevel, Text text) {
		text.setText(displayLevel);
		text.setX(50);
		text.setY(100);
		text.setFont(new Font(20));
		gameRoot.getChildren().add(text);
	}
	
	
	public void healthOnScreen(Player man, Rectangle healthbarPlayer) {
		setPercentHealth(man.getCurrentHealth()/man.getMaxHealth());
		healthbarPlayer.setWidth(getWidth()*percentHealth);
		healthbarPlayer.setHeight(50);
		gameRoot.getChildren().add(healthbarPlayer);
	}
	
	public void cleanUp() {
		bullets.clear();
		displayLevel = "Level: " + level;
		percentHealth=100;
		bossNum.clear();
		hasHit=-1;
    	Character.currentHealth=100;
		stage.setScene(game1Scene);
		gameRoot.getChildren().removeAll();
		gameRoot.getChildren().clear();
		level=1;
		buildMenu();
	}
	
	public void levelUp() {
		level++;
		System.out.println(getbossCount());
		bossNum.removeAll(bossNum);
		bossNum.clear();
		displayLevel = "Level: " + level;
		hasHit=-1;
    	Character.currentHealth=100;
		gameRoot.getChildren().clear();
		buildGame(level);
		
	}
	
	// A function to make a button
	private Button createButton(String text) {
		Button button = new Button(text);
		button.setFont(Font.font("Georgia",18));
		button.setMaxWidth(300);
		button.setMaxHeight(100);
		button.setMinWidth(150);
		BorderPane.setMargin(button, INSETS);
		BorderPane.setAlignment(button, Pos.CENTER);
		return button;	
	}
	
	
	public static Scene getGame1Scene() {
		return game1Scene;
	}
	
	
	public static void setPercentHealth(double value) {
		percentHealth = value;
	}
	public static int getLevel() {
		return level;
	}
	
	public static int getbossCount() {
		return bossCount;
	}
	
	public static void setbossCount(int value) {
		bossCount = value;
	}
	
	
	
}
