package application;

import java.awt.MouseInfo;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game1 extends Main {
	private Pane root;
	private static Scene game1Scene;
	double mouseX;
	double mouseY;
	
	public Game1(Stage stage) {
		
		root = new Pane();
    	
		game1Scene = new Scene(root,getWidth(),getHeight());
		stage.setTitle("Game 1");
		stage.setScene(getGame1Scene());
		stage.centerOnScreen();
		stage.show();	
		
		Player man = new Player(150, 100);
		
        
        Map <KeyCode, Boolean> pressedKeys = new HashMap<>();
        game1Scene.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });
        game1Scene.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });
        
        game1Scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
            	mouseX = event.getX();
            	mouseY = event.getY();
            	System.out.println("X: " + event.getX() + " Y: " + event.getY());
            	root.getChildren().clear();
            	Line line = new Line(man.getX() + man.getCharacter().getWidth()/2, man.getY() + +man.getCharacter().getHeight()/2, mouseX, mouseY);
            	line.setStrokeWidth(2f);
            	line.setStroke(Color.RED);
            	root.getChildren().add(line);
            	root.getChildren().add(man.getCharacter());
            
            }
          });
        
        Rectangle man2 = new Rectangle(250, 100);
		root.getChildren().add(man2);
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
                Path path = new Path();

            	// First move to starting point
            	MoveTo moveTo = new MoveTo();
            	moveTo.setX(100.0f);
            	moveTo.setY(50.0f);

            	// Then start drawing a line
            	LineTo lineTo = new LineTo();
            	lineTo.setX(75.0f);
            	lineTo.setY(255.0f);

            	path.getElements().add(moveTo);
            	path.getElements().add(lineTo);
            	root.getChildren().add(path);
            }
        }.start();
        
        
	}
	

	public static Scene getGame1Scene() {
		return game1Scene;
	}


	public static void setGame1Scene(Scene game1Scene) {
		Game1.game1Scene = game1Scene;
	}

	
	
}
