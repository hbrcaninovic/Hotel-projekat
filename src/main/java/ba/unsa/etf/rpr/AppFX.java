package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * Entry point to GUI application
 */
public class AppFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("HOME - Log in"); // Postavlja tekstualno zaglavlje prozora
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        primaryStage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        primaryStage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        primaryStage.show();  // Poziv za prikaz prozora
    }
    public static void main(String[] args) {
        launch(args);
    }

}
