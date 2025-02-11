package gui;

import java.io.IOException;

import brownie.Brownie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import windows.MainWindow;

/**
 * Main class serves as the entry point for the JavaFX application.
 * It initializes and sets up the primary stage, loads the main FXML layout,
 * and links the application logic (Brownie instance) to the GUI controller.
 *
 * This class extends the Application class and overrides the start method
 * to configure the JavaFX GUI components.
 */
public class Main extends Application {
    private Brownie brownie = new Brownie();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBrownie(brownie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
