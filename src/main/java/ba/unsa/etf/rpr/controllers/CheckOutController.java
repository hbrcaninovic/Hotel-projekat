package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GuestManager;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class CheckOutController {
    public ChoiceBox<Reservation> brojSobeBox;
    public DatePicker datumDolaskaDatePicker;
    public DatePicker datumOdlaskaDatePicker;
    public Button odustaniBtn;
    public Button checkOutBtn;
    public TextField jmbgTextField;
    public TextField imeGostaTextField;
    public TextField prezimeGostaTextField;
    public TextField mailGostaTextField;
    public TextField kontaktBrojGostaTextField;
    public TextField iznosTextField;


    ReservationManager reservationManager = new ReservationManager();
    GuestManager guestManager = new GuestManager();
    RoomManager roomManager = new RoomManager();
    List<Reservation> reservationList = null;
    Room room = new Room();
    Guest guest = new Guest();

    /** Controller constructor */
    public CheckOutController() {
    }

    /** A method that initializes the stage of a window */
    @FXML
    public void initialize() throws HotelExceptions {
        reservationList = reservationManager.getAllReservations();

        brojSobeBox.getItems().addAll(reservationList);

        brojSobeBox.setOnAction(e->{
            if (brojSobeBox.getValue() != null) {
                try {

                    room = roomManager.getRoom(brojSobeBox.getValue().getRoom_id());
                    guest = guestManager.getGuest(brojSobeBox.getValue().getGuest_id());
                }
                catch (HotelExceptions ex)
                {
                    UtilityMethodsForWindows.openErrorAlertWindow("CheckOut error",
                            "Greška prilikom inicijalizacije",
                            ex.getMessage());
                    System.out.println(ex);
                }
            }

            datumDolaskaDatePicker.setValue(brojSobeBox.getValue().getDate_of_arrival());
            datumOdlaskaDatePicker.setValue(brojSobeBox.getValue().getDeparture_date());

            jmbgTextField.setText(String.valueOf(guest.getId()));
            imeGostaTextField.setText(guest.getFirst_name());
            prezimeGostaTextField.setText(guest.getLast_name());
            mailGostaTextField.setText(guest.getEmail());
            kontaktBrojGostaTextField.setText(guest.getContact_number());

            iznosTextField.setText(String.format("%.2f",(ChronoUnit.DAYS.between(datumDolaskaDatePicker.getValue(), datumOdlaskaDatePicker.getValue()) * room.getPrice())));

        });


    }

    /**A method that performs the action of returning to the previous window */
    public void akcijaOdustani(ActionEvent actionEvent) {
        Stage stage = (Stage) odustaniBtn.getScene().getWindow();
        stage.close();
    }

    /** The method that executes the check out reservation action */
    public void akcijaCheckOut(ActionEvent actionEvent) {
        Optional<ButtonType> response = UtilityMethodsForWindows.openConfirmationAlertWindow("Potvrda odjave",
                "Potvrda odjave gosta sa rezervacije",
                "Da li ste sigurni da želite odjaviti goste s ove rezervacije?");
        /*
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda odjave");
        alert.setContentText("Da li ste sigurni da želite odjaviti goste s ove rezervacije?");
        Optional<ButtonType> response = alert.showAndWait();
         */

        if (response.get() == ButtonType.OK) {
            room.setStatus("slobodna");

            roomManager.updateRoom(room);
            reservationManager.deleteReservation(brojSobeBox.getValue());
            guestManager.deleteGuest(guest);


            Stage stage = (Stage) checkOutBtn.getScene().getWindow();
            stage.close();

        }
    }

}
