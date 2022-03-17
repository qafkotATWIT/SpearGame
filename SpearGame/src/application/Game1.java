package application;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game1 extends Main {
	private Pane root;
	private static Scene game1Scene;
	
	
	public Game1(Stage stage) {
		
		root = new Pane();
		
		game1Scene = new Scene(root,getWidth(),getHeight());
		stage.setTitle("Game 1");
		stage.setScene(getGame1Scene());
		stage.centerOnScreen();
		stage.show();	
		
		Player man = new Player(150, 100);
		Player man2 = new Player(150, 100);
        root.getChildren().add(man.getCharacter());
        root.getChildren().add(man2.getCharacter());
        
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
                if(pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    man.turnLeft();
                }
                if(pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    man.turnRight();
                }
                if(pressedKeys.getOrDefault(KeyCode.UP, false)) {
                    man.up();
                }
                if(pressedKeys.getOrDefault(KeyCode.DOWN, false)) {
                    man.down();
                }
                if(pressedKeys.getOrDefault(KeyCode.R, false)) {
                    man.restart();
                }
            }
        }.start();
        
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(pressedKeys.getOrDefault(KeyCode.A, false)) {
                    man2.turnLeft();
                }
                if(pressedKeys.getOrDefault(KeyCode.D, false)) {
                    man2.turnRight();
                }
                if(pressedKeys.getOrDefault(KeyCode.W, false)) {
                    man2.up();
                }
                if(pressedKeys.getOrDefault(KeyCode.S, false)) {
                    man2.down();
                }
                if(pressedKeys.getOrDefault(KeyCode.R, false)) {
                    man2.restart();
                }
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
