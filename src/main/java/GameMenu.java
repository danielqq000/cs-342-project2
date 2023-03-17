// GameMenu.java
// author: GuanYu Huang, UID: 677460448
// This file belongs to Project2 for CS342 Spring 2023
//
// Description:
//


import javafx.scene.control.Alert;
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
		alert.setContentText("These are the rules of the game...");
		alert.showAndWait();
	}

	private void displayOdds(){

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Odds of the Game");
		alert.setHeaderText(null);
		alert.setContentText("These are the odds of the game...");
		alert.showAndWait();
	}



}
