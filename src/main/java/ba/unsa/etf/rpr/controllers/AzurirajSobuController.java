package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AzurirajSobuController {
    public Button odustaniBtn;
    public TextField brojSobeTextField;
    public ChoiceBox<String> statusSobeBox;
    public ChoiceBox<String> vipUslugeBox;
    public ChoiceBox<Integer> tipSobeBox;
    public Button dodajSobuBtn;
    public TextField cijenaTextField;

    private final String[] optionsStatus = {"slobodna", "zauzeta", "renovira se", "nije u funkciji"};
    private final String[] optionsVip = {"DA", "NE"};
    private final Integer[] optionsType = {1, 2, 3, 4, 5, 6};

    RoomManager roomManager = new RoomManager();
    Room room = new Room();

    @FXML
    public void initialize() {
        statusSobeBox.getItems().addAll(optionsStatus);
        vipUslugeBox.getItems().addAll(optionsVip);
        tipSobeBox.getItems().addAll(optionsType);

        brojSobeTextField.setText(String.valueOf(room.getId()));
        statusSobeBox.setValue(room.getStatus());
        vipUslugeBox.setValue(room.getVIP_services());
        cijenaTextField.setText(String.valueOf(room.getPrice()));
        tipSobeBox.setValue(room.getRoom_type());
    }

    public AzurirajSobuController(Room room) {
        this.room = room;
    }

    public void akcijaOdustani(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) odustaniBtn.getScene().getWindow();
        stage.close();

        UtilityMethodsForWindows.openWindow("/fxml/sobe.fxml","Sobe");
/*
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sobe.fxml"));
        stage.setTitle("HOME - Sobe"); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        stage.show();  // Poziv za prikaz prozora
 */
    }

    public void validirajUnosBrojaSobe(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().matches("[^\\e\t\r\\d+$]")){
            keyEvent.consume();
            brojSobeTextField.setStyle("-fx-border-color: red");
            brojSobeTextField.clear();
        }
        else brojSobeTextField.setStyle("-fx-border-color: transparent");
    }

    public void validirajUnosCijene(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().matches("[^\\d*(\\.\\d+)?$]")){
            keyEvent.consume();
            cijenaTextField.setStyle("-fx-border-color: red");
            cijenaTextField.clear();
        }
        else cijenaTextField.setStyle("-fx-border-color: transparent");
    }

    public void akcijaAzurirajSobu(ActionEvent actionEvent) {
        try {
            String brojSobe = brojSobeTextField.getText();
            String statusSobe = statusSobeBox.getValue();
            String vipUsluge = vipUslugeBox.getValue();
            String cijenaSobe = cijenaTextField.getText();
            Integer tipSobe = (Integer) tipSobeBox.getValue();

            Room room = new Room(Integer.parseInt(brojSobe),tipSobe,Double.parseDouble(cijenaSobe),vipUsluge,statusSobe);

            if (roomManager.updateRoom(room)){
                Stage stage = (Stage)brojSobeTextField.getScene().getWindow();
                stage.close();

                UtilityMethodsForWindows.openWindow("/fxml/sobe.fxml","Sobe");
/*
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/sobe.fxml"));
                stage.setTitle("HOME - Sobe"); // Postavlja tekstualno zaglavlje prozora
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
                stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
                stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
                stage.show();  // Poziv za prikaz prozora
 */

            }
            else
            {
                UtilityMethodsForWindows.openErrorAlertWindow("Ažuriranje sobe - greška",
                        "Greška prilikom ažuriranja sobe!",
                        "Nije moguće izvršiti ažuriranje sobe!");
                /*
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ažuriranje sobe - greška");
                alert.setHeaderText("Greška prilikom ažuriranja sobe!");
                alert.setContentText("Nije moguće izvršiti ažuriranje sobe!");
                alert.showAndWait();
                 */
            }

        }
        catch (Exception e){
            UtilityMethodsForWindows.openErrorAlertWindow("Ažuriranje sobe - greška",
                    "Greška prilikom ažuriranja sobe!",
                    "Svi podaci o sobi nisu uneseni!");
            /*
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ažuriranje sobe - greška");
            alert.setHeaderText("Greška prilikom ažuriranja sobe!");
            alert.setContentText("Svi podaci o sobi nisu uneseni!");
            alert.showAndWait();

             */
        }

    }
}
