package application;

import java.io.InputStream;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Game extends Main{
	public Stage stage;
	private BorderPane menuRoot;
	private BorderPane optionsRoot;
	private Pane gameRoot;
	private static Scene optionsScene;
	private static Scene menuScene;
	private static Scene game1Scene;
	private Insets INSETS = new Insets(10, 10, 10, 10);
	double mouseX;
	double mouseY;
	private static boolean multiKill= true;	// multi or single kill
	private static int playerSpeed = 3; 	// 1-10
	private static int bossSpeedGame=100; 	// 100-1000
	private static int bossSizeGame = 90; 	// 50 70 ... 230 250
	private static int bulletSpeed = 20;
	private ArrayList<PathTransition> bullets;
	private static boolean isShooting = false;
	private static int level = 1;
	private static int bossCount;
	private String displayLevel = "Level: " + level;
	private String displayBulletSpeed = "Bullet Speed: " + getBulletSpeed()/20;
	private String displayBossSpeed = "Boss Speed: " + (getBossSpeed()/100);
	private String displayPlayerSpeed = "Player Speed: " + (getPlayerSpeed());
	private String displayBossSize = "Boss Size: " + (getBossSize());
	private static double percentHealth;
	private ArrayList<Boss> bossNum = new ArrayList<Boss>();
	private static int hasHit=-1;
	public Game(Stage stage) {
		this.stage = stage;
		buildMenu();	
	}
	
/*-------------------------------------------------------------GAME OPTIONS MENU-------------------------------------------------------------------*/
	public void buildOptions() {
		optionsRoot = new BorderPane(); // layout of scene 

		VBox top = new VBox(); // Space contains label.
		VBox menu = new VBox(8); // Space contains buttons.
		HBox bulletSpeedBox = new HBox();
		HBox bossSpeedBox = new HBox();
		HBox playerSpeedBox = new HBox();
		HBox bossSizeBox = new HBox();
		HBox killOption = new HBox();
		
		/*-------------------------------------------------BULLET SPEED OPTION--------------------------------------------------*/
		Button bulletSpeed = createButton(displayBulletSpeed);
		Button minusBulletSpeed = createButton("+");
		minusBulletSpeed.setMinWidth(60);
		minusBulletSpeed.setOnAction(e -> {
			if(getBulletSpeed()!=200) {
				bulletSpeed.setDisable(false);
				setBulletSpeed(getBulletSpeed()+20);
				displayBulletSpeed = "Bullet Speed: " + (getBulletSpeed()/20);
				bulletSpeed.setText(displayBulletSpeed);
				if(getBulletSpeed()==200)
					bulletSpeed.setDisable(true);
			}
		});
		Button addBulletSpeed = createButton("-");
		addBulletSpeed.setMinWidth(60);
		addBulletSpeed.setOnAction(e -> {
			if(getBulletSpeed()!=20) {
				bulletSpeed.setDisable(false);
				setBulletSpeed(getBulletSpeed()-20);
				displayBulletSpeed = "Bullet Speed: " + (getBulletSpeed()/20); // 200 = 1, 180 = 2, 160 = 3, 140 = 4, 120 = 5
				bulletSpeed.setText(displayBulletSpeed);
				if(getBulletSpeed()==20)
					bulletSpeed.setDisable(true);
			}
		});
		if(getBulletSpeed()!=200) {
			bulletSpeed.setDisable(true);
		} else if(getBulletSpeed()!=20) {
			bulletSpeed.setDisable(true);
		}
		
		/*-------------------------------------------------PLAYER SPEED OPTION--------------------------------------------------*/
		Button playerSpeed = createButton(displayPlayerSpeed);
		playerSpeed.setOnAction(e -> {});
		Button minusPlayerSpeed = createButton("+");
		minusPlayerSpeed.setMinWidth(60);
		minusPlayerSpeed.setOnAction(e -> {
			if(getPlayerSpeed()!=10) {
				playerSpeed.setDisable(false);
				setPlayerSpeed(getPlayerSpeed()+1);
				displayPlayerSpeed = "Player Speed: " + (getPlayerSpeed());
				playerSpeed.setText(displayPlayerSpeed);
				if(getPlayerSpeed()==10)
					playerSpeed.setDisable(true);
			}
		});
		Button addPlayerSpeed = createButton("-");
		addPlayerSpeed.setMinWidth(60);
		addPlayerSpeed.setOnAction(e -> {
			if(getPlayerSpeed()!=1) {
				playerSpeed.setDisable(false);
				setPlayerSpeed(getPlayerSpeed()-1);
				displayPlayerSpeed = "Player Speed: " + (getPlayerSpeed()); // 200 = 1, 180 = 2, 160 = 3, 140 = 4, 120 = 5
				playerSpeed.setText(displayPlayerSpeed);
				if(getPlayerSpeed()==1)
					playerSpeed.setDisable(true);
			}
		});
		
		/*-------------------------------------------------BOSS SIZE OPTION--------------------------------------------------*/
		Button bossSize = createButton(displayBossSize);
		bossSize.setOnAction(e -> {});	
		Button minusBossSize = createButton("+");
		minusBossSize.setMinWidth(60);
		minusBossSize.setOnAction(e -> {
			if(getBossSize()!=230) {
				bossSize.setDisable(false);
				setBossSize(getBossSize()+20);
				displayBossSize = "Boss Size: " + (getBossSize());
				bossSize.setText(displayBossSize);
				if(getBossSize()==230)
					bossSize.setDisable(true);
			}
		});
		Button addBossSize = createButton("-");
		addBossSize.setMinWidth(60);
		addBossSize.setOnAction(e -> {
			if(getBossSize()!=50) {
				bossSize.setDisable(false);
				setBossSize(getBossSize()-20);
				displayBossSize = "Boss Size: " + (getBossSize());
				bossSize.setText(displayBossSize);
				if(getBossSize()==50)
					bossSize.setDisable(true);
			}
		});
		if(getBossSize()==230) {
			bossSize.setDisable(true);
		} else if(getBossSize()==50) {
			bossSize.setDisable(true);
		}
		
		/*-------------------------------------------------BOSS SPEED OPTION--------------------------------------------------*/
		Button bossSpeed = createButton(displayBossSpeed);
		bossSpeed.setOnAction(e -> {});
		Button minusBossSpeed = createButton("+");
		minusBossSpeed.setMinWidth(60);
		minusBossSpeed.setOnAction(e -> {
			if(getBossSpeed()!=1000) {
				bossSpeed.setDisable(false);
				setBossSpeed(getBossSpeed()+100);
				displayBossSpeed = "Boss Speed: " + (getBossSpeed()/100);
				bossSpeed.setText(displayBossSpeed);
				if(getBossSpeed()==1000)
					bossSpeed.setDisable(true);
			}
		});
		Button addBossSpeed = createButton("-");
		addBossSpeed.setMinWidth(60);
		addBossSpeed.setOnAction(e -> {
			if(getBossSpeed()!=100) {
				bossSpeed.setDisable(false);
				setBossSpeed(getBossSpeed()-100);
				displayBossSpeed = "Boss Speed: " + (getBossSpeed()/100); // 200 = 1, 180 = 2, 160 = 3, 140 = 4, 120 = 5
				bossSpeed.setText(displayBossSpeed);
				if(getBossSpeed()==100)
					bossSpeed.setDisable(true);
			}
		});
		if(getBossSpeed()!=200) {
			bossSpeed.setDisable(true);
		} else if(getBossSpeed()!=20) {
			bossSpeed.setDisable(true);
		}
		
		/*-------------------------------------------------MULTI KILL OPTION--------------------------------------------------*/
		Button multiKill = createButton("Bullet multi-kill");
		Button singleKill = createButton("Bullet single-kill");
		multiKill.setMinWidth(50);
		singleKill.setMinWidth(50);
		multiKill.setOnAction(e -> {setMultiKill(true); singleKill.setDisable(false); multiKill.setDisable(true);});
		singleKill.setOnAction(e -> {setMultiKill(false); singleKill.setDisable(true); multiKill.setDisable(false);});
		if(getMultiKill()) {
			multiKill.setDisable(true);
			singleKill.setDisable(false);
		} else if(getMultiKill()) {
			multiKill.setDisable(false);
			singleKill.setDisable(true);
		}
		
		Button goBack = createButton(" < Go Back");
		goBack.setOnAction(e -> {buildMenu();});
		
		
		// Labels
		Label name = new Label("Game Options");
		name.setFont(Font.font("Cambria", 75));
		
		/*-----------------------------------------------ALIGN BUTTONS ON OPTIONS ROOT---------------------------------------------*/
		menu.setAlignment(Pos.CENTER); // these align and size the VBoxs
		bulletSpeedBox.setAlignment(Pos.CENTER);
		bossSpeedBox.setAlignment(Pos.CENTER);
		bossSizeBox.setAlignment(Pos.CENTER);
		playerSpeedBox.setAlignment(Pos.CENTER);
		killOption.setAlignment(Pos.CENTER);
		top.setAlignment(Pos.BOTTOM_CENTER);
		top.setPrefSize(100, 250);
		
		top.getChildren().addAll(name);
		menu.getChildren().addAll(killOption,bulletSpeedBox,playerSpeedBox,bossSpeedBox,bossSizeBox,goBack);
		killOption.getChildren().addAll(singleKill,multiKill);
		bulletSpeedBox.getChildren().addAll(bulletSpeed,addBulletSpeed, minusBulletSpeed);
		bossSpeedBox.getChildren().addAll(bossSpeed,addBossSpeed,minusBossSpeed);
		bossSizeBox.getChildren().addAll(bossSize,addBossSize,minusBossSize);
		playerSpeedBox.getChildren().addAll(playerSpeed,addPlayerSpeed,minusPlayerSpeed);
		optionsRoot.setCenter(menu);
		optionsRoot.setTop(top);
		optionsScene = new Scene(optionsRoot,getWidth(),getHeight());
		stage.getIcons().add(new Image(getClass().getResourceAsStream("man.png"))); 
		stage.setTitle("Spear Game");
		stage.setScene(optionsScene);
		stage.centerOnScreen();
		stage.show();
	}
	
	/*----------------------------------------------BUILD MENU----------------------------------------------------------*/
	
	public void buildMenu() {
		menuRoot = new BorderPane(); // layout of scene 

		VBox top = new VBox(); // Space contains label.
		VBox menu = new VBox(8); // Space contains buttons.
		
		// Buttons
		Button playgame1 = createButton("Start Game");
		playgame1.setOnAction(e -> buildGame(level));		
		Button tutorial = createButton("How to Play");
		tutorial.setOnAction(e -> {});	
		Button options = createButton("Options");
		options.setOnAction(e -> {buildOptions();});
		Button exit = createButton("Quit");
		exit.setOnAction(e -> Platform.exit());
		
		// Labels
		Label name = new Label("Spear Game");
		name.setFont(Font.font("Cambria", 75));
		
		menu.setAlignment(Pos.CENTER); // these align and size the VBoxs
		top.setAlignment(Pos.BOTTOM_CENTER);
		top.setPrefSize(100, 250);
		
		top.getChildren().addAll(name);
		menu.getChildren().addAll(playgame1,tutorial,options,exit);		
		menuRoot.setCenter(menu);
		menuRoot.setTop(top);
		menuScene = new Scene(menuRoot,getWidth(),getHeight());
		stage.getIcons().add(new Image(getClass().getResourceAsStream("man.png"))); 
		stage.setTitle("Spear Game");
		stage.setScene(menuScene);
		stage.centerOnScreen();
		stage.show();
	}
	
	
	
	/*------------------------------------------------------START GAME CONSTRUCTOR--------------------------------------------------------------*/
	public void buildGame(int level) {
		gameRoot = new Pane();
		game1Scene = new Scene(gameRoot,getWidth(),getHeight());
		stage.getIcons().add(new Image(getClass().getResourceAsStream("man.png"))); 
		stage.setTitle("Spear Game");
		stage.setResizable(false);
		stage.setScene(game1Scene);
		stage.centerOnScreen();
		stage.show();
		gameRoot.setBackground( new Background(new BackgroundFill(Color.rgb(0,0,0, .9), null, null)) );
		setbossCount(level+2);
		bossNum.clear();
		Player man = new Player(150, 100, getPlayerSpeed());
		for(int i=0; i<=getbossCount(); i++) {
			bossNum.add(new Boss(getBossSpeed(), getBossSize()));
		}		
		bullets = new ArrayList<>();
		Text levelText = new Text();
		levelText.setFill(Color.WHITE);
		setPercentHealth((man.getCurrentHealth()/man.getMaxHealth())*100);
		Rectangle healthbarPlayer = new Rectangle();
		healthbarPlayer.setFill(Color.RED);
		healthOnScreen(man, healthbarPlayer);
		Line topLine = new Line(0,50,getWidth(),50);
		displayLevel = "Level: " + level;
		levelOnScreen(displayLevel, levelText);
		
		Label popupText = new Label("Spear Game") {{setFont(new Font(20));setPrefSize(200,250);}};
		Popup popup = new Popup();
		BorderPane insidePopup = new BorderPane(){
			{
				setBackground( new Background(new BackgroundFill(Color.rgb(255, 255, 255, .9), null, null)) );
				setBorder(new Border(new BorderStroke(Color.BLACK, 
			            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				setPrefSize(500, 500);
			}
		};
		VBox buttonHolder = new VBox();
		
		Button resume = createButton("Resume");
		resume.setOnAction(e -> {popup.hide();});
		Button options = createButton("Game Options");
		options.setOnAction(e -> {buildOptions();popup.hide();});
		Button exit = createButton("Exit Game");
		exit.setOnAction(e -> {popup.hide();cleanUp();buildMenu();});
		
		BorderPane.setAlignment(popupText, Pos.BOTTOM_CENTER);
		popup.getContent().addAll(insidePopup, popupText);
		insidePopup.setCenter(buttonHolder);
		insidePopup.setTop(popupText);
		popupText.setAlignment(Pos.CENTER);
		popupText.setTextAlignment(null);
		buttonHolder.setAlignment(Pos.CENTER);
		buttonHolder.getChildren().addAll(resume, options, exit);
        
        Map <KeyCode, Boolean> pressedKeys = new HashMap<>();
        game1Scene.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });
        game1Scene.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });
        
        /*----------------------------------------Animation Timer starts---------------------------------------------------------------*/
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
                	popup.show(stage);
                }
                if(bossNum.size()!=0) {
                	/*------------------------Start: If player hits boss--------------------------------*/
                	for(int i=0; i<bossNum.size();i++) {
                		if(bossNum.get(i).getBossCurrentHealth()==0) {        	            
            	            gameRoot.getChildren().remove(bossNum.get(hasHit).getBoss());
            	            bossNum.remove(hasHit);
            	            if(bossNum.size()==0) {
            	            	levelUp();
            	            }
            	            bossNum.get(i).setBossCurrentHealth(100);
            	            if(bossNum.size()!=0)
            	            	setbossCount(getbossCount()-1);
            	        }
                    	
                    	if(overlap(man,bossNum.get(i))) {
                        	System.out.println("Died");
                        	gameRoot.getChildren().remove(levelText);
                       		levelOnScreen(displayLevel, levelText);
                       		man.setCurrentHealth(man.getCurrentHealth()-10);
                       		gameRoot.getChildren().remove(healthbarPlayer);
                       		healthOnScreen(man, healthbarPlayer);
                       		man.restart();
                      	}
                   	}
                    /*------------------------End: If player hits boss--------------------------------*/
                    
                    
                    /*-----------------------------Bullets work for up to 12 CPS start----------------------------*/
                    if(bullets.size()>12) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(12).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}               
                	} else if(bullets.size()>11) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(11).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}
                	} else if(bullets.size()>10) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(10).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}
                	} else if(bullets.size()>9) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(9).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}
                	} else if(bullets.size()>8) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(8).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}
                	} else if(bullets.size()>7) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(7).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}
                	} else if(bullets.size()>6) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(6).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}
                	} else if(bullets.size()>5) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(5).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}
                	} else if(bullets.size()>4) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(4).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}
                	} else if(bullets.size()>3) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(3).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}
                	} else if(bullets.size()>2) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(2).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}
                	} else if(bullets.size()>1) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(1).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}
                	} else if(bullets.size()>0) {
                    	hasHit= bulletOverlap(((Rectangle)bullets.get(0).nodeProperty().get()),bossNum);
                    	if(hasHit >= 0) {
                			bossNum.get(hasHit).setBossCurrentHealth(0);
                			if(!getMultiKill()) {bullets.clear();}
                		}
                	}
                }
                /*-----------------------------Bullets work for up to 12 CPS ends----------------------------*/
                
                                
                // If you health is zero you will be reset to the menu
                if(percentHealth==0) {
                	cleanUp();
                }
            }
        }.start();
        /*-----------------------------------------------Animation Timer Ends---------------------------------------------------------------*/
        
           
        /*--------------------------------------------Shooting Bullets starts---------------------------------------------------------------*/
        game1Scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
            	mouseX = event.getX();
            	mouseY = event.getY();
            	if(!isShooting) {
            		fire(man);
            	}
            }
          });
        /*--------------------------------------------Shooting Bullets Ends---------------------------------------------------------------*/
        
        // Adding objects to scene
        for(int i=0; i<bossNum.size(); i++) {
        	gameRoot.getChildren().add(bossNum.get(i).getBoss());
        }
        gameRoot.getChildren().add(man.getCharacter());
        gameRoot.getChildren().add(topLine);
	
	}
	/*-----------------------------------------------------END OF GAME CONSTRUCTOR-----------------------------------------------------------------*/	
	
	
	public void fire(Player man) {
		isShooting=true;
		Line bulletPath = new Line(man.getX() + man.getCharacter().getWidth()/2, man.getY() + man.getCharacter().getHeight()/2, mouseX, mouseY);
		bullets.add(bullet(new Rectangle(10,10,Color.WHITE), bulletPath, man));
	}
	
	
	public PathTransition bullet(Rectangle bullet, Line bulletPath, Player man) {
		gameRoot.getChildren().add(bullet);
		PathTransition transition = new PathTransition();
		transition.setNode(bullet);
		transition.setPath(bulletPath);
		transition.setDelay(new Duration(220-getBulletSpeed()));
		transition.play();
		transition.setOnFinished(e -> {gameRoot.getChildren().remove(bullet);bullets.clear();isShooting=false;});
		return transition;
	}
	
	
	public boolean overlap(Player man, Boss b){
		if((((man.getX()>b.getX()) && (man.getX()<(b.getX()+b.getSizeX()))) 
			&& ((man.getY()>b.getY()) && (man.getY() < (b.getY()+b.getSizeY()))))
			|| ((((man.getX()+man.getSizeX())>b.getX()) && ((man.getX()+man.getSizeX()) < (b.getX()+b.getSizeX()))) 
			&& (((man.getY()+man.getSizeY())>b.getY()) && ((man.getY()+man.getSizeY()) < (b.getY()+b.getSizeY())))))  {
			return true;
		}
		return false;
	}
	
	
	public int bulletOverlap(Rectangle bullet, ArrayList<Boss> b){
		for(int i=0; i<b.size();i++) {
			if((((bullet.getTranslateX()>(b.get(i)).getX()) && (bullet.getTranslateX()<((b.get(i)).getX()+(b.get(i)).getSizeX()))) 
				&& ((bullet.getTranslateY()>(b.get(i)).getY()) && (bullet.getTranslateY() < ((b.get(i)).getY()+(b.get(i)).getSizeY()))))
				|| ((((bullet.getTranslateX()+bullet.getBoundsInParent().getWidth())>(b.get(i)).getX()) 
				&& ((bullet.getTranslateX()+bullet.getBoundsInParent().getWidth()) < ((b.get(i)).getX()+(b.get(i)).getSizeX()))) 
				&& (((bullet.getTranslateY()+bullet.getBoundsInParent().getHeight())>(b.get(i)).getY()) 
				&& ((bullet.getTranslateX()+bullet.getBoundsInParent().getWidth()) < ((b.get(i)).getY()+(b.get(i)).getSizeY()))))){
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
	
	
	public static int getBulletSpeed() {
		return bulletSpeed;
	}
	
	
	public void setBulletSpeed(int bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}
	
	
	public static void setBossSpeed(int bossSpeed) {
		bossSpeedGame = bossSpeed;
	}
	
	
	public static int getBossSpeed() {
		return bossSpeedGame;
	}
	
	
	public static void setBossSize(int bossSize) {
		bossSizeGame = bossSize;
	}
	
	
	public static int getBossSize() {
		return bossSizeGame;
	}

	public static int getPlayerSpeed() {
		return playerSpeed;
	}
	
	
	public static void setPlayerSpeed(int speed) {
		playerSpeed = speed;
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
	
	public static boolean getMultiKill() {
		return multiKill;
	}
	
	public static void setMultiKill(boolean on) {
		multiKill = on;
	}

}
