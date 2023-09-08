package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class UtilityMethodsForWindows {
    /** Constructor */
    public UtilityMethodsForWindows() {
    }

    /** A method to open a window without passing an object */
    public static void openWindow(String fxmlStagePath, String title) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(UtilityMethodsForWindows.class.getResource(fxmlStagePath));
        stage.setTitle("HOME - ".concat(title)); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        stage.show();  // Poziv za prikaz prozora
    }

    /** A method to open a window with passing an object */
    public static Stage openWindow2(String fxmlStagePath, String title, Object controller) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(UtilityMethodsForWindows.class.getResource(fxmlStagePath));
        loader.setController(controller);
        stage.setTitle("HOME - ".concat(title)); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.show();  // Poziv za prikaz prozora
        return stage;
    }

    /** Method for opening the Error Alert window */
    public static void openErrorAlertWindow(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /** Method for opening the Information Alert window */
    public static void openInformationAlertWindow(String title, String headerText, String contentText) {
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        information.setTitle(title);
        information.setHeaderText(headerText);
        information.setContentText(contentText);
        information.showAndWait();
    }

    /** Method for opening the Confirmation Alert window */
    public static Optional<ButtonType> openConfirmationAlertWindow(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert.showAndWait();
    }
}
