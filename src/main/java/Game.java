// Game.java
// author: GuanYu Huang, UID: 677460448
// This file belongs to Project2 for CS342 Spring 2023
//
// Description:
//


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
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Game extends Application {


    @Override
    public void start(Stage primaryStage) {

        // title settings
        Label titleLabel = new Label("KENO!");
        titleLabel.setStyle("-fx-font-size: 100px; -fx-font-weight: bold;");
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
        startButton.setOnAction(e -> {
            startGame(primaryStage, root);
        });

        // Create menu bar
        GameMenu menu = new GameMenu();
        MenuBar menuBar = menu.getMenuBar();

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
        Scene scene = new Scene(root, 700, 700);
        primaryStage.setTitle("Keno");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void startGame(Stage primaryStage, BorderPane root) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        EventHandler<ActionEvent> gridHandler = new EventHandler<>() {

            public void handle(ActionEvent e) {
                Button button = (Button)e.getSource();
                button.setDisable(true);
            }
        };

        int count = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = new Button(Integer.toString(count));
                button.setPrefSize(50, 50);
                button.setOnAction(gridHandler);
                button.setDisable(true);
                gridPane.add(button, j, i);
                count++;
            }
        }

        // replace the center of the root pane with the gridPane
        root.setCenter(gridPane);
    }

    //Main Function
    public static void main(String[] args) {
        System.out.println("***Program Starts***");
        launch(args);
    }
}
