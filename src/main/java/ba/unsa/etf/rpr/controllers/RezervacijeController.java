package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GuestManager;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.ReservationAndGuest;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import com.sun.javafx.collections.MappingChange;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.*;

public class RezervacijeController {
    public TableView<ReservationAndGuest> tabelaRezervacija;
    public TableColumn<ReservationAndGuest, LocalDate> datumOdlaskaKolona;
    public TableColumn<ReservationAndGuest, LocalDate> datumDolaskaKolona;
    public TableColumn<ReservationAndGuest, Integer> brojSobeKolona;
    public TableColumn<ReservationAndGuest, String> imeKolona;
    public TableColumn<ReservationAndGuest, String> prezimeKolona;
    public TableColumn<ReservationAndGuest, String> mailKolona;
    public TableColumn<ReservationAndGuest, String> kontaktBrojKolona;

    public Button nazadBtn;
    public Button azuriranjeRezervacije;
    public Button brisanjeRezervacijeBtn;

    ReservationManager reservationManager = new ReservationManager();
    GuestManager guestManager = new GuestManager();
    Reservation reservation = new Reservation();
    Guest guest = new Guest();
    ReservationAndGuest reservationAndGuest = new ReservationAndGuest();

    List<ReservationAndGuest> reservationsAndGuests = new ArrayList<ReservationAndGuest>();


    public RezervacijeController() {
        try {
            List<Reservation> reservations = reservationManager.getAllReservations();

            for (Reservation r: reservations)
                reservationsAndGuests.add(new ReservationAndGuest(r,guestManager.getGuest(r.getGuest_id())));
        }
        catch (HotelExceptions e) {
            System.out.println(e);
        }

    }

    @FXML
    public void initialize(){
        brojSobeKolona.setCellValueFactory(new PropertyValueFactory<ReservationAndGuest, Integer>("room_id"));
        datumDolaskaKolona.setCellValueFactory(new PropertyValueFactory<ReservationAndGuest, LocalDate>("date_of_arrival"));
        datumOdlaskaKolona.setCellValueFactory(new PropertyValueFactory<ReservationAndGuest, LocalDate>("departure_date"));
        imeKolona.setCellValueFactory(new PropertyValueFactory<ReservationAndGuest, String>("first_name"));
        prezimeKolona.setCellValueFactory(new PropertyValueFactory<ReservationAndGuest, String>("last_name"));
        mailKolona.setCellValueFactory(new PropertyValueFactory<ReservationAndGuest, String>("email"));
        kontaktBrojKolona.setCellValueFactory(new PropertyValueFactory<ReservationAndGuest, String>("contact_number"));

        refreshRoomTable();
    }

    public void refreshRoomTable() {
        tabelaRezervacija.setItems(FXCollections.observableList(reservationsAndGuests));
        tabelaRezervacija.refresh();
    }

    public void rowClick(MouseEvent mouseEvent) {
        reservationAndGuest = tabelaRezervacija.getSelectionModel().getSelectedItem();
        if (reservationAndGuest!=null){
            reservation = reservationAndGuest.getReservation();
            guest = reservationAndGuest.getGuest();
        }
    }

    public void akcijaNazad(ActionEvent actionEvent) {
        ((Stage)nazadBtn.getScene().getWindow()).close();
    }

    public void azurirajzaposlenika(ActionEvent actionEvent) {
    }

    public void brisanjeZaposlenika(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Brisanje rezervacije iz liste");
        alert.setContentText("Da li ste sigurni da želite da otkažete rezervaciju iz liste?");
        Optional<ButtonType> response = alert.showAndWait();

        if(response.get() == ButtonType.OK ){
            reservationManager.deleteReservation(reservation);
            guestManager.deleteGuest(guest);
            tabelaRezervacija.getItems().remove(reservationAndGuest);
        }

    }


}
