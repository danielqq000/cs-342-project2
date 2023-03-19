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

	public GameResult(List<Integer> list, int numDrawings) {

		this.userList = new NumberBase(list);
		this.numDrawings = numDrawings;
		numMatches = 0;
	}

	public void gameRun() {

		createTarget();
		checkMatch();
	}

	public void display() {

		Stage stage = new Stage();
		stage.setTitle("Game Results");

		// Create label to show number of matches
	}

	// create target list by random
	private void createTarget() {

		targetList.randomList(userList.getSize());
	}

	private void checkMatch() {

		numMatches = 0;
		for(int i : userList){
			if (targetList.getList().contains(i))
				numMatches++;
		}
	}

}
