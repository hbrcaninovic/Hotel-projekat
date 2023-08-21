package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GuestManager;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.ReservationAndGuest;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AzuriranjeRezervacijeController {
    public TextField jmbgTextField;
    public TextField imeGostaTextField;
    public TextField prezimeGostaTextField;
    public TextField mailGostaTextField;
    public TextField kontaktBrojGostaTextField;
    public DatePicker datumDolaskaDatePicker;
    public DatePicker datumOdlaskaDatePicker;
    public ChoiceBox brojSobeBox;
    public Button odustaniBtn;
    public Button azuriranjeRezervacijeBtn;
    ReservationAndGuest reservationAndGuest = new ReservationAndGuest();
    Reservation reservation = new Reservation();
    Guest guest = new Guest();
    public RoomManager roomManager = new RoomManager();
    public GuestManager guestManager = new GuestManager();
    public ReservationManager reservationManager = new ReservationManager();

    public AzuriranjeRezervacijeController(ReservationAndGuest reservationAndGuest) {
        this.reservationAndGuest = reservationAndGuest;
        this.reservation = reservationAndGuest.getReservation();
        this.guest = reservationAndGuest.getGuest();
    }

    @FXML
    public void initialize() throws HotelExceptions {
        jmbgTextField.setText(String.valueOf(guest.getId()));
        imeGostaTextField.setText(guest.getFirst_name());
        prezimeGostaTextField.setText(guest.getLast_name());
        mailGostaTextField.setText(guest.getEmail());
        kontaktBrojGostaTextField.setText(guest.getContact_number());

        List<Room> rooms = roomManager.getAllRooms();
        List<Integer> roomsNumbers = new ArrayList<Integer>();
        for (Room room: rooms)
            roomsNumbers.add(room.getId());

        brojSobeBox.getItems().addAll(roomsNumbers);
        brojSobeBox.setValue(reservation.getRoom_id());
        datumDolaskaDatePicker.setValue(reservation.getDate_of_arrival());
        datumOdlaskaDatePicker.setValue(reservation.getDeparture_date());
    }


    public void validirajJMBG(KeyEvent keyEvent) {
    }

    public void validirajKontaktBroj(KeyEvent keyEvent) {
    }

    public void akcijaOdustani(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)odustaniBtn.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/rezervacije.fxml"));
        stage.setTitle("HOME - Rezervacije"); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        stage.show();  // Poziv za prikaz prozora
    }

    public void akcijaAzuriranjeRezervacije(ActionEvent actionEvent) {
        try {
            Integer jmbg = Integer.valueOf(jmbgTextField.getText());
            String ime = imeGostaTextField.getText();
            String prezime = prezimeGostaTextField.getText();
            String mail = mailGostaTextField.getText();
            String kontaktBroj = kontaktBrojGostaTextField.getText();
            LocalDate datumDolaska = datumDolaskaDatePicker.getValue();
            LocalDate datumOdlaska = datumOdlaskaDatePicker.getValue();
            Integer brojSobe = (Integer) brojSobeBox.getValue();

            guest = new Guest(jmbg, ime, prezime, mail, kontaktBroj);
            reservation = new Reservation(reservation.getId(), datumDolaska, datumOdlaska, guest.getId(), brojSobe);

            if (guestManager.updateGuest(guest) && reservationManager.updateReservation(reservation)){
                Stage stage = (Stage)azuriranjeRezervacijeBtn.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("/fxml/rezervacije.fxml"));
                stage.setTitle("HOME - Rezervacije"); // Postavlja tekstualno zaglavlje prozora
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
                stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
                stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
                stage.show();  // Poziv za prikaz prozora



            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ažuriranje rezervacije - greška");
                alert.setHeaderText("Greška prilikom ažuriranja rezervacije!");
                alert.setContentText("Nije moguće izvršiti ažuriranje rezervacije!");
                alert.showAndWait();
            }

        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ažuriranje rezervacije - greška");
            alert.setHeaderText("Greška prilikom ažuriranja rezervacije!");
            alert.setContentText("Svi podaci o rezervacije nisu uneseni!");
            alert.showAndWait();
        }

    }

}
