package ba.unsa.etf.rpr.controllers;

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

    public void akcijaPrijava(ActionEvent actionEvent) throws IOException {

        if(usernameId.getText().trim().isEmpty() || passwordId.getText().trim().isEmpty()) {

            usernameId.setText("");
            passwordId.setText("");
            errorLabelId1.setText("");
            errorLabelId2.setText("");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("LogIn-greška");
            alert.setHeaderText("Greška prilikom prijave!");
            alert.setContentText("Korisničko ime ili lozinka nisu uneseni!");
            alert.showAndWait();
        }
        else if(usernameId.getText().trim().equals("admin") && passwordId.getText().trim().isEmpty()==false)
        {
            usernameId.setText("");
            passwordId.setText("");
            errorLabelId1.setText("");
            errorLabelId2.setText("");

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/administracija.fxml"));
            stage.setTitle("HOME - Admin"); // Postavlja tekstualno zaglavlje prozora
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
            stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora

            stage.show();  // Poziv za prikaz prozora
        }
        else if(usernameId.getText().trim().equals("admin")==false && passwordId.getText().trim().isEmpty()==false)
        {
            usernameId.setText("");
            passwordId.setText("");
            errorLabelId1.setText("");
            errorLabelId2.setText("");

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/recepcija.fxml"));
            stage.setTitle("HOME - Recepcija"); // Postavlja tekstualno zaglavlje prozora
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
            stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora

            stage.show();  // Poziv za prikaz prozora

        }
        else
        {
            usernameId.setText("");
            passwordId.setText("");
            errorLabelId1.setText("");
            errorLabelId2.setText("");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("LogIn-greška");
            alert.setHeaderText("Greška prilikom prijave!");
            alert.setContentText("Neispravni pristupni podaci!");
            alert.showAndWait();

        }


    }
    /**Konstruktor koji kreira objekat LogIn prozora*/
    public LogInController() {
    }

    @FXML
    public void initialize()
    {
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
}
