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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.*;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

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


    /** Controller constructor */
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

    /** A method that initializes the stage of a window */
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

    /** Refresh RoomTable content */
    public void refreshRoomTable() {
        tabelaRezervacija.setItems(FXCollections.observableList(reservationsAndGuests));
        tabelaRezervacija.refresh();
    }

    /** The method that executes rowClick selection on tabel element */
    public void rowClick(MouseEvent mouseEvent) {
        reservationAndGuest = tabelaRezervacija.getSelectionModel().getSelectedItem();
        if (reservationAndGuest!=null){
            reservation = reservationAndGuest.getReservation();
            guest = reservationAndGuest.getGuest();
        }
    }

    /**A method that performs the action of returning to the previous window */
    public void akcijaNazad(ActionEvent actionEvent) {
        ((Stage)nazadBtn.getScene().getWindow()).close();
    }

    /** The method that executes the reservation update action */
    public void azurirajRezervaciju(ActionEvent actionEvent) {

        if (reservation.getId() == 0 || guest.getId() == 0) {
            UtilityMethodsForWindows.openInformationAlertWindow("Obavještenje",
                    "Obavještenje o ažuriranju rezervacije",
                    "Odaberite rezervaciju u tabeli kako biste izvršili ažuriranje.");
            /*
            Alert information = new Alert(Alert.AlertType.INFORMATION);
            information.setTitle("Obavještenje o ažuriranju rezervacije");
            information.setContentText("Odaberite rezervaciju u tabeli kako biste izvršili ažuriranje.");
            information.show();
             */
        }
        else {
            try {
                UtilityMethodsForWindows.openWindow2("/fxml/azuriranjeRezervacije.fxml",
                        "Ažuriranje rezervacije",
                        new AzuriranjeRezervacijeController(reservationAndGuest));

                /*
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/azuriranjeRezervacije.fxml"));
                Object controller = new AzuriranjeRezervacijeController(reservationAndGuest);
                loader.setController(controller);
                stage.setTitle("HOME - Ažuriranje rezervacije"); // Postavlja tekstualno zaglavlje prozora
                stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
                stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
                stage.show();  // Poziv za prikaz prozora
                 */

                Stage oldStage = (Stage) azuriranjeRezervacije.getScene().getWindow();
                oldStage.close();


            } catch (Exception e) {
                UtilityMethodsForWindows.openInformationAlertWindow("Obavještenje",
                        "Obavještenje o ažuriranju rezervacije",
                        "Ažuriranje nije moguće!");
                /*
                Alert information = new Alert(Alert.AlertType.INFORMATION);
                information.setTitle("Obavještenje o ažuriranju rezervacije");
                information.setContentText("Ažuriranje nije moguće!");
                information.show();
                 */
            }

        }

    }

    /** A method that performs the action of deleting reservations */
    public void brisanjeRezervacije(ActionEvent actionEvent) {

        Optional<ButtonType> response = UtilityMethodsForWindows.openConfirmationAlertWindow("Brisanje rezervacije",
                "Brisanje rezervacije iz liste",
                "Da li ste sigurni da želite da otkažete rezervaciju iz liste?");
        /*
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Brisanje rezervacije iz liste");
        alert.setContentText("Da li ste sigurni da želite da otkažete rezervaciju iz liste?");
        Optional<ButtonType> response = alert.showAndWait();
         */

        if(response.get() == ButtonType.OK ){
            reservationManager.deleteReservation(reservation);
            guestManager.deleteGuest(guest);
            tabelaRezervacija.getItems().remove(reservationAndGuest);
        }

    }


}
