// Game.java
// author: GuanYu Huang, UID: 677460448
// This file belongs to Project2 for CS342 Spring 2023
//
// Description:
// main function. Call others to display.


import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Game extends Application {


   @Override 
    public void start(Stage primaryStage) {

		MenuFX.display(primaryStage);
     }

    public void playing(Stage primaryStage) {

		PlayFX.display(primaryStage);
    }

	public void result(Stage primaryStage, Play user) {

		ResultFX.display(primaryStage, user);
	}

    //Main Function
    public static void main(String[] args) {
        System.out.println("***Program Starts***");
        launch(args);
    }
}
