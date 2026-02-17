package mobi;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mobi.gui.MainWindow;

/**
 * A GUI for Mobi using FXML.
 */
public class Main extends Application {

    private final Mobi mobi = new Mobi();

    @Override
    public void start(Stage stage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root, 500, 600);
            scene.getStylesheets().add(
                    Main.class.getResource("/view/theme.css").toExternalForm()
            );
            stage.setTitle("Mobi");
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setMobi(mobi);  // inject the Mobi instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
