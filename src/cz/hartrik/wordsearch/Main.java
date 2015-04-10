package cz.hartrik.wordsearch;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Spouštěcí třída.
 *
 * @author Patrik Harag
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle(
                "cz.hartrik.wordsearch.strings", new Locale("cs", "CZ"));
        Parent root = FXMLLoader.load(getClass().getResource("Frame.fxml"), bundle);
        
        Scene scene = new Scene(root);
        
        stage.setMinHeight(400);
        stage.setMinWidth(650);
        stage.setTitle("Lamač osmisměrek");
        stage.getIcons().add(new Image(this.getClass()
                .getResourceAsStream("icon - search.png"))); 
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}