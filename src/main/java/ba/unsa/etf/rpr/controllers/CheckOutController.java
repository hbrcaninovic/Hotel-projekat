package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
    List<Reservation> reservationList = null;

    public CheckOutController() {


    }

    @FXML
    public void initialize() throws HotelExceptions {
        reservationList = reservationManager.getAllReservations();
        brojSobeBox.getItems().addAll(reservationList);
        brojSobeBox.setOnAction(e->{

            datumDolaskaDatePicker.setValue(brojSobeBox.getValue().getDate_of_arrival());
            datumOdlaskaDatePicker.setValue(brojSobeBox.getValue().getDeparture_date());
            iznosTextField.setText(String.valueOf(ChronoUnit.DAYS.between(datumDolaskaDatePicker.getValue(),datumOdlaskaDatePicker.getValue())));

        });


    }

    public void akcijaOdustani(ActionEvent actionEvent) {
        Stage stage = (Stage) odustaniBtn.getScene().getWindow();
        stage.close();
    }


    public void akcijaCheckOut(ActionEvent actionEvent) {
    }
}
