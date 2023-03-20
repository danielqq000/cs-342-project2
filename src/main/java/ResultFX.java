// ResultFX.java
// author: GuanYu Huang, UID: 677460448
// This file belongs to Project2 for CS342 Spring 2023
//
// Description:
// this file runs the target List and compare to user's pick.
// It also calculate how much does the user wins.
//


import java.util.List;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ResultFX {


	private NumberBase userList;
	private NumberBase targetList = new NumberBase();
	private Button continueButton;
	private Button leaveButton;
	private int numMatches;
	private int win;

	// Constructor 
	public ResultFX(Play user) {

		this.userList = new NumberBase(user.getList());
		this.numMatches = 0;
		this.win = 0;

		// button settings
		this.continueButton = new Button("Start");
		this.leaveButton = new Button("leave");

		continueButton.setPrefSize(150, 50);
		continueButton.setStyle("-fx-font-size: 20px;");
		leaveButton.setPrefSize(150, 50);
		leaveButton.setStyle("-fx-font-size: 20px;");
	}

	private VBox headers(Play user) {

		List<Integer> list = targetList.getList();
		Label SlotsLabel = new Label("Slots: " + String.valueOf(user.getSlots()));
		Label RoundsLabel = new Label("Total rounds: " + String.valueOf(user.getRounds()));
		Label targetLabel = new Label(list.toString());
		Label winLabel = new Label("Total wins: " + win);
		SlotsLabel.setStyle("-fx-font-size: 20px;");
		RoundsLabel.setStyle("-fx-font-size: 20px;");
		targetLabel.setStyle("-fx-font-size: 20px;");
		winLabel.setStyle("-fx-font-size: 20px;");

		VBox vbox = new VBox(10, SlotsLabel, RoundsLabel, targetLabel, winLabel);
		vbox.setAlignment(Pos.CENTER);

		return vbox;
	}


	private GridPane numberButtons() {

		//Create the game button grid
		GridPane buttonGrid = new GridPane();
		buttonGrid.setVgap(10);
		buttonGrid.setHgap(10);
		buttonGrid.setAlignment(Pos.CENTER);

		// Set the default style of the button
		String defaultStyle = "-fx-background-color: orange; -fx-text-fill: darkblue; -fx-font-weight: bold";

		// Set the active style of the button
		String activeStyleBlue = "-fx-background-color: lightblue; -fx-text-fill: darkblue; -fx-font-weight: bold";

		String activeStyleGreen = "-fx-background-color: lightgreen; -fx-text-fill: darkblue; -fx-font-weight: bold";
		String activeStyleRed = "-fx-background-color: red; -fx-text-fill: darkblue; -fx-font-weight: bold";

		int count = 1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 10; j++) {
				Button button = new Button(Integer.toString(count));
				button.setPrefSize(50, 50);
				button.setStyle(defaultStyle);
				buttonGrid.add(button, j, i);
				count ++;
			}
		}

		// take two lists
		List<Integer> pickedList = userList.getList();
		List<Integer> generatedList = targetList.getList();

		// Deactivate all buttons first
		for (Node node : buttonGrid.getChildren()) {
			if (node instanceof Button) {
				Button button = (Button) node;
				button.setStyle(defaultStyle);
				button.setUserData(null);
			}
		}

		// Activate buttons corresponding to the picked list
		for (Node node : buttonGrid.getChildren()) {
			if (node instanceof Button) {
				Button button = (Button) node;
				int number = Integer.parseInt(button.getText());
				if (pickedList.contains(number)) {
					button.setStyle(activeStyleBlue);
					button.setUserData(true);
				}
			}
		}

		// Check if targetList is not empty before attempting to match
		if (!generatedList.isEmpty()) {
			// Match the targetList with the buttons
			for (Node node : buttonGrid.getChildren()) {
				if (node instanceof Button) {
					Button button = (Button) node;
					int number = Integer.parseInt(button.getText());
					if (generatedList.contains(number)) {
						if (pickedList.contains(number)) {
							button.setStyle(activeStyleGreen);
						} else {
							button.setStyle(activeStyleRed);
						}
					}
				}
			}
		}

		return buttonGrid;
	}
	private HBox actionBox() {

		HBox leaveBoxContainer = new HBox(10, leaveButton);
		HBox continueBoxContainer = new HBox(10, continueButton);
		leaveBoxContainer.setAlignment(Pos.BOTTOM_LEFT);
		continueBoxContainer.setAlignment(Pos.BOTTOM_RIGHT);

		HBox hbox = new HBox(leaveBoxContainer, continueBoxContainer);
		hbox.setSpacing(700);
		return hbox;
	}

	private void gameRun() {

		// start game
		createTarget();
		checkMatch();
	}

	// create target list by random
	private void createTarget() {

		targetList.cleanList();
		targetList.randomList(20);
	}

	private void checkMatch() {

		numMatches = 0;
		for(int i : userList.getList()){
			if (targetList.getList().contains(i))
				numMatches++;
		}

	}

	private void winAmount(Play user) {
		switch (user.getSlots()){
			case 1:
				win += 2;
				break;

			case 4:
				if (numMatches == 2)
					win += 1;
				else if (numMatches == 3)
					win += 5;
				else if (numMatches == 4)
					win += 75;
				break;

			case 8:
				if (numMatches == 4)
					win += 2;
				else if (numMatches == 5)
					win += 12;
				else if (numMatches == 6)
					win += 50;
				else if (numMatches == 7)
					win += 750;
				else if (numMatches == 8)
					win += 10000;
				break;

			case 10:
				if (numMatches == 0)
					win += 5;
				else if (numMatches == 5)
					win += 2;
				else if (numMatches == 6)
					win += 15;
				else if (numMatches == 7)
					win += 40;
				else if (numMatches == 8)
					win += 450;
				else if (numMatches == 9)
					win += 4250;
				else if (numMatches == 10)
					win += 100000;
				break;

			default:
				break;
		}
	}


	public static void display(Stage primaryStage, Play user) {

		// Create a new instance of the Result class
		ResultFX resultFX = new ResultFX(user);

		// Create the menu bar
		GameMenu menu = new GameMenu();
		MenuBar menuBar = menu.getMenuBar(primaryStage);

		// Create header labels
		VBox header = resultFX.headers(user);

		// Create GridPane of number blocks
		GridPane gridPane = resultFX.numberButtons();

		// Create buttom buttons
		HBox hbox = resultFX.actionBox();

		// Create the main layout
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(50);
		layout.getChildren().addAll(menuBar, header);

		// Create BorderPane
		BorderPane root = new BorderPane();
		root.setTop(layout);
		root.setCenter(gridPane);
		root.setBottom(hbox);


		// Button Action
		resultFX.continueButton.setOnAction(e -> {
			if(user.getRounds() == 1)
				resultFX.continueButton.setText("Again");
			else resultFX.continueButton.setText("continue");

			if(user.getRounds() == 0) {
				Game game = new Game();
				game.start(primaryStage);
			}
			else {
				user.setRounds(user.getRounds() - 1);
				resultFX.gameRun();
				resultFX.winAmount(user);
				header.getChildren().clear(); // clear header
				header.getChildren().addAll(resultFX.headers(user)); // update score label
				gridPane.getChildren().clear(); // clear number buttons
				gridPane.getChildren().addAll(resultFX.numberButtons()); // add new number buttons

				root.setTop(layout);
				root.setCenter(gridPane);
				root.setBottom(hbox);
			}
		});

		// create scene and show stage
		Scene scene = new Scene(root, 1000, 1000);
		primaryStage.setTitle("Result");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
