import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class GameSetting {
    private int spots;
    private int rounds;
    private ComboBox<String> spotComboBox;
    private ComboBox<String> roundComboBox;
    private Button playButton;
    private Button continueButton;


    public GameSetting() {
        spotComboBox.setOnAction(e -> {
            this.spots = Integer.parseInt(spotComboBox.getValue());
        });

        roundComboBox.setOnAction(e -> {
            this.rounds = Integer.parseInt(roundComboBox.getValue());
        });

        this.spotComboBox = new ComboBox<>();
        this.roundComboBox = new ComboBox<>();
        this.playButton = new Button("Play");
        this.continueButton = new Button("Continue");
    }

    public HBox gameBox() {
        spotComboBox.setValue("Slots");
        spotComboBox.getItems().addAll("1", "4", "8", "10");

        roundComboBox.setValue("Rounds");
        roundComboBox.getItems().addAll("1", "2", "3", "4");

        HBox comboBoxContainer = new HBox(10, spotComboBox, roundComboBox);
        return comboBoxContainer;
    }

    public HBox playBox() {
        HBox playBoxContainer = new HBox(10, playButton, continueButton);
        return playBoxContainer;
    }
}
