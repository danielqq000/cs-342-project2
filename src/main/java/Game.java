// Game.java
// author: GuanYu Huang, UID: 677460448
// This file belongs to Project2 for CS342 Spring 2023
//
// Description:
//


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Game extends Application {


	public void start(Stage primaryStage) {

		// Create welcome screen
		BorderPane root = new BorderPane();
		Button startButton = new Button("Start Playing");

		// Create menu bar
		GameMenu menu = new GameMenu();
		MenuBar menuBar = menu.getMenuBar();
		VBox vbox = new VBox();
		vbox.getChildren().addAll(menuBar);

		// Create JavaFXTemplate aninmation
		JavaFXTemplate fxAnimation = new JavaFXTemplate();
		BorderPane animationPane = fxAnimation.getAnimationPane();
		animationPane.getChildren().addAll(startButton);

		// set up border pane
		root.setTop(vbox);
		root.setCenter(animationPane);

		// Create scene and show stage
		Scene scene = new Scene(root, 700, 700);
		primaryStage.setTitle("Game");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	//Main Function
	public static void main(String[] args) {
		System.out.println("***Program Starts***");
		launch(args);
	}
}

