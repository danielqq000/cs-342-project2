// GameMenu.java
// author: GuanYu Huang, UID: 677460448
// This file belongs to Project2 for CS342 Spring 2023
//
// Description:
// Functions for Menu. Include rules, odds, and exit.
//


import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;


public class GameMenu{


	// private variables
	MenuItem rulesItem;
	MenuItem oddsItem;
	MenuItem exitItem;

	// Constructor
	public GameMenu(){

		this.rulesItem = new MenuItem("Display rules");
		this.oddsItem = new MenuItem("Display odds");
		this.exitItem = new MenuItem("Exit Game");

		rulesItem.setOnAction(event -> {
			displayRules();
		});

		oddsItem.setOnAction(event -> {
			displayOdds();
		});

		exitItem.setOnAction(event -> {
			System.exit(0);
		});
	}

	// public methods
	public MenuBar getMenuBar(){

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		menu.getItems().addAll(rulesItem, oddsItem, new SeparatorMenuItem(), exitItem);
		menuBar.getMenus().add(menu);

		return menuBar;
	}

	// private methods
	private void displayRules(){

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Rules of the Game");
		alert.setHeaderText("***Rules***");
		String rulesText = "These are the rules of the game...\n" +
			"1. Choose number of spots (1, 4, 8, 10)\n" +
			"2. Select numbers OR auto select\n" +
			"3. Choose number of draws\n" +
			"4. Click 'PLAY'\n" +
			"5. The more you match, the more you win\n";

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setStyle("-fx-min-width: 800px; -fx-min-height: 500px;");
		alert.setContentText(rulesText);
		alert.showAndWait();
	}

	private void displayOdds(){

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Odds of the Game");
		alert.setHeaderText("***Odds***");
		String oddsText = "Here are the odds of winning...\n" +
			"1 Number\t\t1\t1 in 4\n" +
			"\t\t\t0\t1 in 3.33\n" +
			"4 Numbers\t4\t1 in 326\n" +
			"\t\t\t3\t1 in 23.12\n" +
			"\t\t\t2\t1 in 4.70\n" +
			"8 Numbers\t8\t1 in 230,114\n" +
			"\t\t\t7\t1 in 6,232.27\n" +
			"\t\t\t6\t1 in 422.53\n" +
			"\t\t\t5\t1 in 54.64\n" +
			"\t\t\t4\t1 in 12.27\n" +
			"10 Numbers\t10\t1 in 8,911,711\n" +
			"\t\t\t9\t1 in 163,381\n" +
			"\t\t\t8\t1 in 7,384\n" +
			"\t\t\t7\t1 in 621\n" +
			"\t\t\t6\t1 in 87.11\n" +
			"\t\t\t5\t1 in 19.44\n" +
			"\t\t\t0\t1 in 21.84\n";

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setStyle("-fx-min-width: 800px; -fx-min-height: 500px;");	
		alert.setContentText(oddsText);
		alert.showAndWait();
	}



}
