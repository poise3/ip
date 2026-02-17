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
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.getStyleClass().add("scroll-pane");
        dialogContainer.getStyleClass().add("chat-container");
        dialogContainer.getChildren().add(DialogBox.getMobiDialog("Hi friend! I'm Mobi :D\n" +
                                                                       "Need any help with your task list?\n",
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

        DialogBox mobiDialog = DialogBox.getMobiDialog(response, mobiImage);
        if (response.endsWith(":/")) {
            mobiDialog.getDialogLabel().getStyleClass().add("error-bubble");
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                mobiDialog
        );

        userInput.clear();
    }
}
