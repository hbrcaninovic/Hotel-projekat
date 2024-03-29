package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GuestManager;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckInController {
    public TextField jmbgTextField;
    public TextField imeGostaTextField;
    public TextField prezimeGostaTextField;
    public TextField mailGostaTextField;
    public TextField kontaktBrojGostaTextField;
    public DatePicker datumDolaskaDatePicker;
    public DatePicker datumOdlaskaDatePicker;
    public ChoiceBox<Integer> brojSobeBox;
    public Button odustaniBtn;
    public Button dodavanjeRacunaBtn;

    Reservation reservation = new Reservation();
    RoomManager roomManager = new RoomManager();
    GuestManager guestManager = new GuestManager();
    ReservationManager reservationManager = new ReservationManager();
    public List<Room> rooms;

    /** Controller constructor */
    public CheckInController() {
    }

    /** A method that initializes the stage of a window */
    @FXML
    public void initialize() throws HotelExceptions {
        datumDolaskaDatePicker.setValue(LocalDate.now());
        datumOdlaskaDatePicker.setPromptText(LocalDate.now().plusDays(1).toString());

        datumDolaskaDatePicker.setDayCellFactory(lambda-> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(LocalDate.now())) { //Disable all dates after required date
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
                if(item.isAfter(LocalDate.now().plusDays(31))){
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });

        datumOdlaskaDatePicker.setDayCellFactory(lambda->
                new DateCell(){
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(datumDolaskaDatePicker.getValue().plusDays(1))) { //Disable all dates after required date
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                        if(item.isAfter(LocalDate.now().plusDays(62))){
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                });

        rooms = roomManager.getAllRooms();
        List<Integer> roomsNumbers = new ArrayList<Integer>();
        for (Room room: rooms)
            roomsNumbers.add(room.getId());

        brojSobeBox.getItems().addAll(roomsNumbers);

    }

    /** The method that performs the action of validating the number entry */
    public void validirajJMBG(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().matches("[^\\e\t\r\\d+$]")){
            keyEvent.consume();
            jmbgTextField.setStyle("-fx-border-color: red");
            jmbgTextField.clear();
        }
        else jmbgTextField.setStyle("-fx-border-color: transparent");
    }

    /** The method that performs the action of validating the mail entry */
    public void validirajMail(KeyEvent keyEvent) {
        if (!mailGostaTextField.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            keyEvent.consume();
            mailGostaTextField.setStyle("-fx-border-color: red");
        }
        else mailGostaTextField.setStyle("-fx-border-color: transparent");

    }

    /** The method that performs the action of validating the constact number entry */
    public void validirajKontaktBroj(KeyEvent keyEvent) {
        if (!kontaktBrojGostaTextField.getText().matches("^[1-9]\\d{2}-\\d{3}-\\d{3}")){
            keyEvent.consume();
            kontaktBrojGostaTextField.setStyle("-fx-border-color: red");
        }
        else kontaktBrojGostaTextField.setStyle("-fx-border-color: transparent");

    }

    /**A method that performs the action of returning to the previous window */
    public void akcijaOdustani(ActionEvent actionEvent) {
        Stage stage = (Stage) odustaniBtn.getScene().getWindow();
        stage.close();
    }

    /** A method that executes the action of creating a user account */
    public void akcijaKreiranjeRezervacije(ActionEvent actionEvent) {
        String jmbgGosta = jmbgTextField.getText();
        String imeGosta = imeGostaTextField.getText();
        String prezimeGosta = prezimeGostaTextField.getText();
        String mailGosta = mailGostaTextField.getText();
        String kontaktBrojGosta = kontaktBrojGostaTextField.getText();
        LocalDate datumDolaskaGosta = datumDolaskaDatePicker.getValue();
        LocalDate datumOdlskaGosta = datumOdlaskaDatePicker.getValue();
        Integer brojSobe = brojSobeBox.getValue();

        try {
            reservation = new Reservation(reservationManager.getMaxReservationId()+1,
                    datumDolaskaGosta,
                    datumOdlskaGosta,
                    Integer.parseInt(jmbgGosta),
                    brojSobe
                    );
            Guest guest = new Guest(Integer.parseInt(jmbgGosta),
                    imeGosta,
                    prezimeGosta,
                    mailGosta,
                    kontaktBrojGosta);

            if (guestManager.addGuest(guest) && reservationManager.addReservation(reservation)){
                Stage stage = (Stage) dodavanjeRacunaBtn.getScene().getWindow();
                stage.close();
            }


        }
        catch (HotelExceptions e) {
            UtilityMethodsForWindows.openErrorAlertWindow("Dodavanje rezervacije - greška",
                    "Greška prilikom dodavanja rezervacije!",
                    "Svi podaci o rezervacije nisu uneseni!");
            /*
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dodavanje rezervacije - greška");
            alert.setHeaderText("Greška prilikom dodavanja rezervacije!");
            alert.setContentText("Svi podaci o rezervacije nisu uneseni!");
            alert.showAndWait();
             */
        }


    }
}
