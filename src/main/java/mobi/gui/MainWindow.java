package mobi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mobi.Mobi;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Mobi mobi;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/astro2.jpg"));
    private final Image mobiImage = new Image(this.getClass().getResourceAsStream("/images/astro3.jpg"));

    @FXML
    public void initialize() {
        dialogContainer.setStyle(
                "-fx-background-color: #fff7e6;"
        );
        dialogContainer.setStyle(
                "-fx-background-image: url('/images/chatbg.jpg');" +
                        "-fx-background-repeat: repeat;" +
                        "-fx-background-position: center;" +
                        "-fx-background-size: cover;"
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getMobiDialog("Greetings! I'm Mobi :D\n" +
                                                                       "Any commands for me?\n",
                                                                        mobiImage));
    }

    /** Injects the Mobi instance */
    public void setMobi(Mobi m) {
        mobi = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mobi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mobi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMobiDialog(response, mobiImage)
        );
        userInput.clear();
    }
}
