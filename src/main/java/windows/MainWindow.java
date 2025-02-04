package windows;

import boxes.DialogBox;
import brownie.Brownie;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Brownie brownie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Arnold.jpg"));
    private Image brownieImage = new Image(this.getClass().getResourceAsStream("/images/Brownie.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setBrownie(Brownie b) {
        this.brownie = b;

        // Define a callback that updates the dialog container in the GUI
        this.brownie.setDialogUpdater((text, image) -> {
            // Update the UI with Brownie's response (on the JavaFX Application Thread)
            dialogContainer.getChildren().add(DialogBox.getBrownieDialog(text, image));
        });

        dialogContainer.getChildren().add(DialogBox.getBrownieDialog("Hello, my name is Brownie.", brownieImage));
    }



    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();

        PauseTransition pause = new PauseTransition(Duration.seconds(2)); //

        brownie.respondToUser(input);
        if (input.equals("bye")) {
            pause.setOnFinished(event -> {
                // Display Brownie's response after the delay
                System.exit(0);
            });
            // Start the pause
            pause.play();
        }
    }

}

