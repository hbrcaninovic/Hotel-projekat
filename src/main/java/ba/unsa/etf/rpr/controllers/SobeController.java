package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class SobeController {

    private final RoomManager roomManager = new RoomManager();

    public Button dodajSobuBtn;
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

    public void dodajSobu(ActionEvent actionEvent) {
    }

    public void azurirajSobu(ActionEvent actionEvent) {
    }

    public void brisanjeSobe(ActionEvent actionEvent) {
    }

    public void akcijaNazad(ActionEvent actionEvent) {
        Stage stage = (Stage) nazadBtn.getScene().getWindow();
        stage.close();
    }

    public void validirajUnosBrojaSobe(KeyEvent keyEvent) {
    }


    private void refreshRoomTable() {
        try {
            tabelaSoba.setItems(FXCollections.observableList(roomManager.getAllRooms()));
            tabelaSoba.refresh();

        } catch (HotelExceptions e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

}
