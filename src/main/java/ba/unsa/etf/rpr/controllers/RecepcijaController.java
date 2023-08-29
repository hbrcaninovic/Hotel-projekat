package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class RecepcijaController {

    public static Employee employee = new Employee();
    public Label welcomeLabel;
    public Button odjavaBtn;
    public Button checkInBtn;
    public Button checkOutBtn;

    public RecepcijaController(Employee employee) {
        this.employee = employee;
    }

    @FXML
    public void initialize(){
        welcomeLabel.setText("Dobrodošli ".concat(employee.getFirst_name()));
    }


    public void akcijaPrikaziRezervacije(ActionEvent actionEvent) throws IOException {
        UtilityMethodsForWindows.openWindow("/fxml/rezervacije.fxml","Rezervacije");
        /*
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/rezervacije.fxml"));
        stage.setTitle("HOME - Rezervacije"); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        stage.show();  // Poziv za prikaz prozora
         */
    }

    public void akcijaOdjava(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) odjavaBtn.getScene().getWindow();
        stage.close();

        UtilityMethodsForWindows.openWindow("/fxml/login.fxml","Log in");

        /*
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage.setTitle("HOME - Log in"); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        stage.show();  // Poziv za prikaz prozora
         */
    }

    public void akcijaCheckIn(ActionEvent actionEvent) throws IOException {

        UtilityMethodsForWindows.openWindow("/fxml/checkIn.fxml","Check in");
        /*
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/checkIn.fxml"));
        stage.setTitle("HOME - Check in"); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        stage.show();  // Poziv za prikaz prozora
         */
    }

    public void akcijaCheckOut(ActionEvent actionEvent) throws IOException {

        UtilityMethodsForWindows.openWindow("/fxml/checkOut.fxml","Check out");
        /*
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/checkOut.fxml"));
        stage.setTitle("HOME - Check out"); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        stage.show();  // Poziv za prikaz prozora
         */
    }
}
