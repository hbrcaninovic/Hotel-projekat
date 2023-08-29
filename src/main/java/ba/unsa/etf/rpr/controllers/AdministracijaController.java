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
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AdministracijaController {

    public Button odjavaBtn;
    public static Employee employee;
    public Label helloLabel;
    public Button roomBtn;

    public AdministracijaController(Employee employee) {
        AdministracijaController.employee = employee;
    }

    @FXML
    public void initialize() {
        helloLabel.setText(helloLabel.getText().concat(employee.getFirst_name()));
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

    public void akcijaSobe(ActionEvent actionEvent) throws IOException {

        UtilityMethodsForWindows.openWindow("/fxml/sobe.fxml","Sobe");
        /*
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sobe.fxml"));
        stage.setTitle("HOME - Sobe"); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        stage.show();  // Poziv za prikaz prozora
         */
    }


    public void akcijaAzuriranjeRacuna(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/azuriranjeAdminRacuna.fxml"));
        AzuriranjeAdminRacunaController controller = new AzuriranjeAdminRacunaController(employee);
        loader.setController(controller);
        stage.setTitle("HOME - Azuriranje racuna"); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.show();  // Poziv za prikaz prozora



        stage.setOnHidden(x->{
            Employee emp = controller.getEmployee();
            employee = emp;
            helloLabel.setText("Dobrodošli ".concat(employee.getFirst_name()));

        });




    }

    public void akcijaZaposlenici(ActionEvent actionEvent) throws IOException {
        UtilityMethodsForWindows.openWindow("/fxml/zaposlenici.fxml","Zaposlenici");
        /*
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/zaposlenici.fxml"));
        stage.setTitle("HOME - Zaposlenici"); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        stage.show();  // Poziv za prikaz prozora
         */
    }
}
