import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.stage.Stage;

public class PlayFX {
	private int spots;
	private int rounds;
	private ComboBox<String> spotComboBox;
	private ComboBox<String> roundComboBox;
	private Button returnButton;
	private Button continueButton;

	public PlayFX() {
		this.spotComboBox = new ComboBox<>();
		this.roundComboBox = new ComboBox<>();
		this.returnButton = new Button("Return");
		this.continueButton = new Button("Continue");
		Play play = new Play();

		// Button settings
		spotComboBox.setPrefSize(60, 30);
		roundComboBox.setPrefSize(60, 30);
		returnButton.setPrefSize(150, 50);
		returnButton.setStyle("-fx-font-size: 20px;");
		continueButton.setPrefSize(150, 50);
		continueButton.setStyle("-fx-font-size: 20px;");
	}

	private HBox gameBox() {
		Label spotLabel = new Label("Slots: ");
		spotLabel.setStyle("-fx-font-size: 20px;");
		spotComboBox.setValue("-");
		spotComboBox.getItems().addAll("1", "4", "8", "10");
		HBox spotBox = new HBox(10, spotLabel, spotComboBox);

		Label roundLabel = new Label("Rounds: ");
		roundLabel.setStyle("-fx-font-size: 20px;");
		roundComboBox.setValue("-");
		roundComboBox.getItems().addAll("1", "2", "3", "4");
		HBox roundBox = new HBox(10, roundLabel, roundComboBox);

		VBox vbox = new VBox(10, spotBox, roundBox);
		HBox comboBoxContainer = new HBox(vbox);
		comboBoxContainer.setAlignment(Pos.CENTER);

		return comboBoxContainer;
	}

	private HBox actionBox() {

		HBox returnBoxContainer = new HBox(10, returnButton);
		HBox continueBoxContainer = new HBox(10, continueButton);
		returnBoxContainer.setAlignment(Pos.BOTTOM_LEFT);
		continueBoxContainer.setAlignment(Pos.BOTTOM_RIGHT);

		HBox hbox = new HBox(returnBoxContainer, continueBoxContainer);
		hbox.setSpacing(700);
		return hbox;
	}


	private VBox numberButtons(Play user) {

		// Create the game button grid
		GridPane buttonGrid = new GridPane();
		buttonGrid.setVgap(10);
		buttonGrid.setHgap(10);
		buttonGrid.setAlignment(Pos.CENTER);

		// Set the default style of the button
		String defaultStyle = "-fx-background-color: orange; -fx-text-fill: darkblue; -fx-font-weight: bold";

		// Set the active style of the button
		String activeStyle = "-fx-background-color: lightgreen; -fx-text-fill: darkblue; -fx-font-weight: bold";

		// Set button action
		EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {
			public void handle(ActionEvent e) {
				Button button = (Button) e.getSource();

				if (button.getUserData() == null) {
					// Activate the button
					button.setStyle(activeStyle);
					button.setUserData(true);
					user.addNumber(Integer.parseInt(button.getText()));
				} else {
					// Deactivate the button
					button.setStyle(defaultStyle);
					button.setUserData(null);
					user.removeNumber(Integer.parseInt(button.getText()));
				}
			}
		};

		int count = 1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 10; j++) {
				Button button = new Button(Integer.toString(count));
				button.setPrefSize(50, 50);
				button.setStyle(defaultStyle);
				button.setOnAction(buttonHandler);
				buttonGrid.add(button, j, i);
				count ++;
			}
		}

		// Create the random pick button pane
		Button randomButton = new Button("Random Pick");
		String buttonStyle = randomButton.getStyle();
		randomButton.setPrefSize(150, 50);
		randomButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(user.getSlots() == 0){
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("You haven't choose Slots!!!");
					alert.showAndWait();
					return;
				}

				user.cleanList();
				user.randomList();
				List<Integer> pickedList = user.getList();
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
							button.setStyle(activeStyle);
							button.setUserData(true);
						}
					}
				}

				// reset random button
				randomButton.setStyle(buttonStyle);
			}
		});

		BorderPane randomButtonPane = new BorderPane();
		randomButtonPane.setCenter(randomButton);
		VBox vBox = new VBox(buttonGrid, randomButtonPane);
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(20);

		return vBox;
	}

	// check if user is ready to play
	private boolean checkStatus(Play user){

		Alert alert = new Alert(Alert.AlertType.ERROR);

		if(user.getSlots() == 0){
			alert.setContentText("You haven't choose Slots!!!");
			alert.showAndWait();
			return false;
		}
		else if(user.getRounds() == 0){
			alert.setContentText("You haven't choose Rounds!!!");
			alert.showAndWait();
			return false;
		}
		else if(user.getSize() != user.getSlots()){
			alert.setHeaderText("Different Amount Error");
			String text = "Slots: " + String.valueOf(user.getSlots()) + 
				"   Your have picked: " + String.valueOf(user.getSize());
			alert.setContentText(text);
			alert.showAndWait();
			return false;
		}

		return true;
	}

	// other button actions
	private void buttonActions(Stage primaryStage, Play user) {

		spotComboBox.setOnAction(e -> {
			try {
				this.spots = Integer.parseInt(spotComboBox.getValue());
				user.setSlots(Integer.parseInt(spotComboBox.getValue()));
			} catch (NumberFormatException ex) {
				// Handle invalid input
			}
		});

		roundComboBox.setOnAction(e -> {
			try {
				this.rounds = Integer.parseInt(roundComboBox.getValue());
				user.setRounds(Integer.parseInt(roundComboBox.getValue()));
			} catch (NumberFormatException ex) {
				// Handle invalid input
			}
		});

		returnButton.setOnAction(e -> {
			Game game = new Game();
			game.start(primaryStage);
		});

		continueButton.setOnAction(e -> {
			if(checkStatus(user)){
				Game game = new Game();
				game.result(primaryStage, user.getList());
			}
		});
	}



	public static void display(Stage primaryStage) {

		// Create a new instance of the Play class
		// userList to pass on to next stage
		Play user = new Play();

		// Create a new instance of the PlayFX class
		PlayFX playFX = new PlayFX();

		// Create the main layout for the window
		BorderPane root = new BorderPane();

		// Create the title label
		Label titleLabel = new Label("Pick Your Number!");
		titleLabel.setStyle("-fx-font-size: 80px; -fx-font-weight: bold;");
		titleLabel.setAlignment(Pos.CENTER);

		// Create the menu bar
		GameMenu menu = new GameMenu();
		MenuBar menuBar = menu.getMenuBar();

		// Create number buttons
		VBox buttonsBox = playFX.numberButtons(user);

		// Button actions
		playFX.buttonActions(primaryStage, user);

		// Create the game settings UI
		HBox comboBoxContainer = playFX.gameBox();
		HBox actionBoxContainer = playFX.actionBox();

		// Create the main layout for the window
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(50);
		layout.getChildren().addAll(menuBar, titleLabel, comboBoxContainer);

		// BorderPane setup
		root.setTop(layout);
		root.setCenter(buttonsBox);
		root.setBottom(actionBoxContainer);

		// Create the scene and show the stage
		Scene scene = new Scene(root, 1000, 1000);
		primaryStage.setTitle("Settings");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
