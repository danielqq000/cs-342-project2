import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;

import javafx.geometry.Insets;

import javafx.scene.control.SeparatorMenuItem;//??

import javafx.scene.control.ComboBox;


public class JavaFXTemplate extends Application {
	private MenuBar menuBar;
	private MenuItem rulesMenuItem;
	private MenuItem oddsMenuItem;
	private MenuItem exitMenuItem;
	private EventHandler<ActionEvent> gridHandler;
	private boolean displayingOdds = false;
	private boolean displayingRules = false;

	private Label titleLabel;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Keno");
		// create the main menu bar with one tab "Menu"
		menuBar = new MenuBar();
		Menu menu = new Menu("Menu");

		// create menu items for "Rules", "Odds", and "Exit"
		rulesMenuItem = new MenuItem("Rules");
		oddsMenuItem = new MenuItem("Odds");
		exitMenuItem = new MenuItem("Exit");

		// add menu items to the menu
		menu.getItems().addAll(rulesMenuItem, oddsMenuItem, new SeparatorMenuItem(), exitMenuItem);

		// add the menu to the menu bar
		menuBar.getMenus().add(menu);

		// title label
		titleLabel = new Label("KENO!");
		titleLabel.setFont(Font.font("Clarendon", FontWeight.BOLD, 100));
		titleLabel.setAlignment(Pos.CENTER);

		// content label
		Label contentLabel = new Label();
		contentLabel.setAlignment(Pos.CENTER);
		contentLabel.setPadding(new Insets(10, 10, 10, 10));
		contentLabel.setWrapText(true);
		contentLabel.setMaxWidth(500);

		// create the button to start playing
		Button startButton = new Button("PLAY");

		// create the main layout with the menu bar and start button
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(20);
		layout.getChildren().addAll(titleLabel, contentLabel, startButton);

		// create the starting scene
		//Scene startScene = new Scene(layout, 600, 400);

		// set up actions for the menu items
		/*rulesMenuItem.setOnAction(e -> {
			if (titleLabel.getText().equals("KENO!")) {
				String rulesText = "1. Choose number of spots (1, 4, 8, 10)\n" +
						"2. Select numbers OR auto select\n" +
						"3. Choose number of draws\n" +
						"4. Click 'PLAY'\n" +
						"5. The more you match, the more you win";
				titleLabel.setFont(Font.font("Clarendon", 25));
				titleLabel.setText(rulesText);
			} else {
				titleLabel.setFont(Font.font("Clarendon", FontWeight.BOLD, 100));
				titleLabel.setText("KENO!");
			}
		});*/
		rulesMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			//boolean displayingRules = false;
			@Override
			public void handle(ActionEvent event) {
				if (displayingRules) {
					titleLabel.setText("KENO!");
					contentLabel.setText("");
					displayingRules = false;
					displayingOdds = false;
				} else {
					titleLabel.setText("Rules:");
					contentLabel.setText("1. Choose number of spots (1, 4, 8, 10)\n"
							+ "2. Select numbers OR auto select\n"
							+ "3. Choose number of draws\n"
							+ "4. Click 'PLAY'\n"
							+ "5. The more you match, the more you win");
					displayingRules = true;
					displayingOdds = false;
				}
			}
		});



		oddsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			//boolean displayingOdds = false;
			@Override
			public void handle(ActionEvent event) {
				if (displayingOdds) {
					titleLabel.setText("KENO!");
					contentLabel.setText("");
					displayingOdds = false;
					displayingRules = false;
				} else {
					titleLabel.setText("Odds:");
					contentLabel.setText("1 Number\t1\t1 in 4\n" +
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
							"\t\t\t0\t1 in 21.84");
					displayingOdds = true;
					displayingRules = false;
				}
			}
		});

		exitMenuItem.setOnAction(e -> {
			// exit the game
			primaryStage.close();
		});

		startButton.setOnAction(e -> {
			startGame(primaryStage);
		});
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		borderPane.setCenter(layout);
		//borderPane.setCenter(centerVBox);
		//Scene startScene = new Scene(borderPane, 1200, 800);
		Scene startScene = new Scene(borderPane, 800, 600);
		// set the starting scene
		primaryStage.setScene(startScene);
		primaryStage.show();
	}

	public void startGame(Stage primaryStage) {
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10));
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		gridHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Button button = (Button)e.getSource();
				if (button.getStyle().equals("-fx-background-color: yellow;")) {
					button.setStyle("");
				} else {
					button.setStyle("-fx-background-color: yellow;");
				}
			}
		};

		int count = 1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 10; j++) {
				Button button = new Button(Integer.toString(count));
				button.setPrefSize(50, 50);
				button.setOnAction(gridHandler);
				//button.setDisable(true);
				gridPane.add(button, j, i);
				count++;
			}
		}

		ComboBox<String> slotComboBox = new ComboBox<>();
		slotComboBox.setValue("Slots");
		slotComboBox.getItems().addAll("1", "4", "8", "10");
		ComboBox<String> roundComboBox = new ComboBox<>();
		roundComboBox.setValue("Rounds");
		roundComboBox.getItems().addAll("1", "2", "3", "4");
		slotComboBox.setOnAction(e -> {
			String selectedValue = slotComboBox.getValue();
			System.out.println("Selected value: " + selectedValue);
		});

		/*titleLabel.setFont(Font.font("Clarendon", FontWeight.BOLD, 50));
		titleLabel.setAlignment(Pos.CENTER);
		VBox menuTitle = new VBox(menuBar, titleLabel);*/
		titleLabel.setFont(Font.font("Clarendon", FontWeight.BOLD, 50));
		VBox titleBox = new VBox(titleLabel);
		titleBox.setAlignment(Pos.CENTER);
		BorderPane menuTitle = new BorderPane();
		menuTitle.setTop(menuBar);
		menuTitle.setCenter(titleBox);
		HBox comboBoxContainer = new HBox(10, slotComboBox, roundComboBox);
		// Create root pane and add menu bar and grid pane
		BorderPane root = new BorderPane();
		root.setTop(menuTitle);
		root.setCenter(gridPane);
		root.setBottom(comboBoxContainer);

		// Create scene and set it to the stage
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Keno");
		primaryStage.show();

		/*BorderPane borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		Scene startScene = new Scene(borderPane, 800, 600);
		primaryStage.setScene(startScene);
		primaryStage.show();*/


	}
	public static void main(String[] args) {
		launch(args);
	}
}
