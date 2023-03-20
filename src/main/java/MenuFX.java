// MenuFX.java
// This file belongs to Project2 for CS342 Spring 2023
//
// Description:
// UI for Game Menu/welcome scene.
//


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MenuFX {

    public static void display(Stage primaryStage) {

        // title settings
        Label titleLabel = new Label("KENO!");
        titleLabel.setStyle("-fx-font-size: 150px; -fx-font-weight: bold;");
        titleLabel.setAlignment(Pos.CENTER);

        // content label
        Label contentLabel = new Label();
        contentLabel.setAlignment(Pos.CENTER);
        contentLabel.setPadding(new Insets(10, 10, 10, 10));
        contentLabel.setWrapText(true);
        contentLabel.setMaxWidth(500);

        // Create welcome screen
        BorderPane root = new BorderPane();
        Button startButton = new Button("Start Playing");
		startButton.setPrefSize(150, 50);
        
		// start button action
		startButton.setOnAction(e -> {
            Game game = new Game();
			game.playing(primaryStage);
        });

        // Create menu bar
        GameMenu menu = new GameMenu();
        MenuBar menuBar = menu.getMenuBar(primaryStage);

        // Create JavaFXTemplate animation
        JavaFXTemplate fxAnimation = new JavaFXTemplate();
        BorderPane animationPane = fxAnimation.getAnimationPane();
        animationPane.getChildren().addAll(startButton);

        // create the main layout with the menu bar and start button
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);
        layout.getChildren().addAll(menuBar, titleLabel, contentLabel, startButton);
		root.setTop(layout);
        root.setCenter(animationPane);

        // Create scene and show stage
        Scene scene = new Scene(root, 1000, 1000);
        primaryStage.setTitle("Keno");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

