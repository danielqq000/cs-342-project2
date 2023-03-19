// GameResult.java
// author: GuanYu Huang, UID: 677460448
// This file belongs to Project2 for CS342 Spring 2023
//
// Description:
//


import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class GameResult {


	private NumberBase userList;
	private NumberBase targetList;
	private int numMatches;
	private int numDrawings;
	private int win;

	public GameResult(List<Integer> list, int numDrawings) {

		this.userList = new NumberBase(list);
		this.numDrawings = numDrawings;
		numMatches = 0;
	}

	public void gameRun() {

		createTarget();
		checkMatch();
		display();
	}

	public void display() {

		Stage stage = new Stage();
		stage.setTitle("Game Results");

		// Create label to show number of matches
		Label playerListLabel = new Label("Player's List: " + userList.toString());
		Label targetListLabel = new Label("Target List: " + targetList.toString());
		Label matchesLabel = new Label("Number of Matches: " + String.valueOf(numMatches));
		Label winLabel = new Label("Win: $" + String.valueOf(win));

		Button continueButton = new Button("Continue");
		continueButton.setOnAction(e -> {
			numDrawings--;
			if(numDrawings == 0) {
				stage.close();
			}
			else {
				gameRun();
			}
		});

		// HBox settings
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(continueButton);

		// Pane settings
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(playerListLabel, 0, 0);
        gridPane.add(targetListLabel, 0, 1);
        gridPane.add(matchesLabel, 0, 2);
        gridPane.add(winLabel, 0, 3);
        gridPane.add(hbox, 0, 4);

        Scene scene = new Scene(gridPane, 400, 300);
        stage.setScene(scene);
        stage.show();
	}

	// create target list by random
	private void createTarget() {

		targetList = new NumberBase();
		targetList.randomList(userList.getSize());
	}

	private void checkMatch() {

		numMatches = 0;
		for(int i : userList.getList()){
			if (targetList.getList().contains(i))
				numMatches++;
		}
		
		win = numMatches * 5;
	}

}
