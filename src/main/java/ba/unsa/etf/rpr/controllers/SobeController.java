package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class SobeController {

    private final RoomManager roomManager = new RoomManager();

    public Button opcijaDodajSobuBtn;
    public Button azurirajSobuBtn;
    public Button brisanjeSobeBtn;
    public TextField brojSobeEditText;
    public Button nazadBtn;
    public TableView<Room> tabelaSoba;
    public TableColumn<Room, Integer> brojSobe;
    public TableColumn<Room, Integer> tipSobe;
    public TableColumn<Room, String> statusSobe;
    public TableColumn<Room, String> vipUsluge;
    public TableColumn<Room, Double> cijena;

    UtilityMethodsForWindows methods = new UtilityMethodsForWindows();


    @FXML
    public void initialize(){
        brojSobe.setCellValueFactory(new PropertyValueFactory<Room, Integer>("id"));
        tipSobe.setCellValueFactory(new PropertyValueFactory<Room, Integer>("room_type"));
        statusSobe.setCellValueFactory(new PropertyValueFactory<Room, String>("status"));
        vipUsluge.setCellValueFactory(new PropertyValueFactory<Room, String>("VIP_services"));
        cijena.setCellValueFactory(new PropertyValueFactory<Room, Double>("price"));

        refreshRoomTable();
    }

    public SobeController() {
    }

    public void opcijaDodajSobu(ActionEvent actionEvent) throws IOException {
        Stage oldStage = (Stage) opcijaDodajSobuBtn.getScene().getWindow();
        oldStage.close();

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/dodajSobu.fxml"));
        stage.setTitle("HOME - Dodavanje sobe"); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        stage.show();  // Poziv za prikaz prozora
    }

    public void azurirajSobu(ActionEvent actionEvent) {
        String brojSobe = brojSobeEditText.getText();
        if (brojSobe.isEmpty()){
            UtilityMethodsForWindows.openInformationAlertWindow("Obavještenje",
                    "Obavještenje o ažuriranju sobe",
                    "Odaberite ili unesite broj sobe kako biste ažurirali sobu.");

            /*
            Alert information = new Alert(Alert.AlertType.INFORMATION);
            information.setTitle("Obavještenje o ažuriranju sobe");
            information.setContentText("Odaberite ili unesite broj sobe kako biste ažurirali sobu.");
            information.show();
             */
        }
        else {
            try {
                Room r = roomManager.getRoom(Integer.parseInt(brojSobe));

                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/azurirajSobu.fxml"));
                Object controller = new AzurirajSobuController(r);
                loader.setController(controller);
                stage.setTitle("HOME - Ažuriranje sobe"); // Postavlja tekstualno zaglavlje prozora
                stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
                stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
                stage.show();  // Poziv za prikaz prozora


                Stage oldStage = (Stage) opcijaDodajSobuBtn.getScene().getWindow();
                oldStage.close();


            }
            catch (Exception e){
                UtilityMethodsForWindows.openInformationAlertWindow("Obavještenje",
                        "Obavještenje o ažuriranju sobe",
                        "Soba sa datim brojem nije pronađena!");
                /*
                Alert information = new Alert(Alert.AlertType.INFORMATION);
                information.setTitle("Obavještenje o ažuriranju sobe");
                information.setContentText("Soba sa datim brojem nije pronađena!");
                information.show();
                 */
            }

        }

    }

    public void brisanjeSobe(ActionEvent actionEvent) {
        Room room = tabelaSoba.getSelectionModel().getSelectedItem();
        Optional<ButtonType> response = UtilityMethodsForWindows.openConfirmationAlertWindow("Brisanje",
                "Brisanje sobe iz liste",
                "Da li ste sigurni da želite da izbrišete sobu iz liste?");

      /*  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Brisanje sobe iz liste");
        alert.setContentText("Da li ste sigurni da želite da izbrišete sobu iz liste?");
        Optional<ButtonType> response = alert.showAndWait();
*/
        if(response.get() == ButtonType.OK){


            if(roomManager.deleteRoom(room) == false){

                UtilityMethodsForWindows.openInformationAlertWindow("Obavještenje",
                        "Obavještenje o brisanju sobe",
                        "Neuspješno brisanje sobe iz liste.");
                /*
                Alert information = new Alert(Alert.AlertType.INFORMATION);
                information.setTitle("Obavještenje o brisanju sobe");
                information.setContentText("Neuspješno brisanje sobe iz liste.");
                information.show();
                 */
            }
            else tabelaSoba.getItems().remove(room);
            brojSobeEditText.clear();
        }


    }

    public void akcijaNazad(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) nazadBtn.getScene().getWindow();
        stage.close();
    }

    public void validirajUnosBrojaSobe(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().matches("[^\\e\t\r\\d+$]")){
            keyEvent.consume();
            brojSobeEditText.setStyle("-fx-border-color: red");
            brojSobeEditText.setText("");
        }
        else brojSobeEditText.setStyle("-fx-border-color: transparent");
    }


    public void refreshRoomTable() {
        try {
            tabelaSoba.setItems(FXCollections.observableList(roomManager.getAllRooms()));
            tabelaSoba.refresh();

        } catch (HotelExceptions e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }


    public void rowClick(MouseEvent mouseEvent) {
        Room room = tabelaSoba.getSelectionModel().getSelectedItem();
        if (room != null) brojSobeEditText.setText(String.valueOf(room.getId()));

    }
}
