package application;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game2 extends Main {
	private Pane root;
	private static Scene game2Scene;
	
	
	public Game2(Stage stage) {
		
		root = new Pane();
		
		game2Scene = new Scene(root,getWidth(),getHeight());
		stage.setTitle("Game 2");
		stage.setScene(getGame2Scene());
		stage.centerOnScreen();
		stage.show();		
	}


	public static Scene getGame2Scene() {
		return game2Scene;
	}


	public static void setGame2Scene(Scene game2Scene) {
		Game2.game2Scene = game2Scene;
	}

	
}
