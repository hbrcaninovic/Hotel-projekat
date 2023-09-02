package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LogInController {
    public Button prijaviBtn;
    public TextField usernameId;
    public PasswordField passwordId;
    public Label errorLabelId1;
    public Label errorLabelId2;
    private static Employee employee;

    /**Konstruktor koji kreira objekat LogIn prozora*/
    public LogInController() {
    }

    @FXML
    public void initialize() {
        usernameId.textProperty().addListener((obs,oldValue,newValue)->
        {
            if(newValue.trim().length()>=1) errorLabelId1.setText("");
            else errorLabelId1.setText("Niste unijeli korisničko ime!");
        });
        passwordId.textProperty().addListener((obs,oldValue,newValue)->
        {
            if(newValue.trim().length()>=5) errorLabelId2.setText("");
            else if(newValue.trim().length()==0) errorLabelId2.setText("Niste unijeli šifru!");
            else errorLabelId2.setText("Minimalno 5 karaktera");
        });
    }

    public void akcijaPrijava(ActionEvent actionEvent) throws IOException {
        String username = usernameId.getText().trim();
        String password = passwordId.getText().trim();

       if(username.isEmpty() || password.isEmpty()) {
            refreshScreen();
            UtilityMethodsForWindows.openErrorAlertWindow("LogIn - greška",
                    "Greška prilikom prijave!",
                    "Korisničko ime ili lozinka nisu uneseni!");

            /*
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("LogIn - greška");
            alert.setHeaderText("Greška prilikom prijave!");
            alert.setContentText("Korisničko ime ili lozinka nisu uneseni!");
            alert.showAndWait();
             */
        }
        else {
          /*  employee = DaoFactory.employeeDao().getEmployeeByUsernameAndPassword(username, password);

            if (employee.getAdmin() < 0 || password.length()<5 || employee.getId() == 0) {
                UtilityMethodsForWindows.openErrorAlertWindow("LogIn - greška",
                        "Greška prilikom prijave!",
                        "Neispravni pristupni podaci!");
           */

                /*
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("LogIn - greška");
                alert.setHeaderText("Greška prilikom prijave!");
                alert.setContentText("Neispravni pristupni podaci!");
                alert.showAndWait();
                 */
           // }
            //else openEmployeeDasboard(employee.getAdmin());

           ProxyUser proxyUser = new ProxyUser();
           if (proxyUser.Autentification(username,password)) {
               Stage logInStage = (Stage) prijaviBtn.getScene().getWindow();
               logInStage.close();
           }
        }
    }

    public void refreshScreen() {
        usernameId.setText("");
        passwordId.setText("");
        errorLabelId1.setText("");
        errorLabelId2.setText("");
    }

    public void openEmployeeDasboard(int employeeType) throws IOException {
        refreshScreen();

        if (employeeType == 0) UtilityMethodsForWindows.openWindow2("/fxml/recepcija.fxml",
                "Recepcija",
                new RecepcijaController(employee));
        else UtilityMethodsForWindows.openWindow2("/fxml/administracija.fxml",
                "Admin",
                new AdministracijaController(employee));
        /*
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/recepcija.fxml"));
        Object controller = new RecepcijaController(employee);
        loader.setController(controller);
        stage.setTitle("HOME - Recepcija"); // Postavlja tekstualno zaglavlje prozora

        if (employeeType == 1) {
            loader = new FXMLLoader(getClass().getResource("/fxml/administracija.fxml"));
            controller = new AdministracijaController(employee);
            loader.setController(controller);
            stage.setTitle("HOME - Admin"); // Postavlja tekstualno zaglavlje prozora
        }

        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.show();  // Poziv za prikaz prozora
         */
        Stage logInStage = (Stage) prijaviBtn.getScene().getWindow();
        logInStage.close();
    }
}
