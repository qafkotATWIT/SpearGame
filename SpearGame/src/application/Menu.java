package application;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Menu extends Main{
	public Stage stage;
	private BorderPane root;
	private static Scene menuScene;
	private Insets INSETS = new Insets(10, 10, 10, 10);
	
	public Menu(Stage stage) {
		this.stage = stage;
		buildMenu();	
	}
	
	
	public void buildMenu() {
		root = new BorderPane(); // layout of scene 

		VBox top = new VBox(); // Space contains label.
		VBox options = new VBox(8); // Space contains buttons.
		
		// Buttons
		Button playgame1 = createButton("Game 1");	//1
		playgame1.setOnAction(e -> buildGame1());
		Button playgame2 = createButton("Game 2");	//2
		playgame2.setOnAction(e -> buildGame2());
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
		root.setCenter(options);
		root.setTop(top);
		
		menuScene = new Scene(root,getWidth(),getHeight());
		stage.setTitle("Spear Game");
		stage.setScene(menuScene);
		stage.centerOnScreen();
		stage.show();
	}
	
	
	public void buildGame1() {
		new Game1(stage);
		stage.setScene(Game1.getGame1Scene());
	}
	
	
	public void buildGame2() {
		new Game2(stage);
		stage.setScene(Game2.getGame2Scene());
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
	
}
