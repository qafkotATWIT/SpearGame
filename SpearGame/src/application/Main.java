package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static int WIDTH = 1100;
	private static int HEIGHT = 800;
	@Override
	public void start(Stage stage) {
		try {
			new Game(stage);
			//Test 1 for the commit button Tedi
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
}
