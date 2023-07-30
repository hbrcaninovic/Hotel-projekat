package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Optional;

public class ZaposleniciController {
    public TableView<Employee> tabelaZaposlenika;
    public TableColumn<Employee, Integer> jmbg;
    public TableColumn<Employee, String> ime;
    public TableColumn<Employee, String> prezime;
    public TableColumn<Employee, String> korisnickoIme;
    public TableColumn<Employee, String> sifra;
    public TableColumn<Employee, String> mail;
    public TableColumn<Employee, String> posao;
    public TableColumn<Employee, Integer> admin;
    public TableColumn<Employee, Double> plata;
    public Button opcijaDodajZaposlenikaBtn;
    public Button azurirajZaposlenikaBtn;
    public Button brisanjeZaposlenikaBtn;
    public Button nazadBtn;
    private final EmployeeManager employeeManager = new EmployeeManager();


    @FXML
    public void initialize(){
       jmbg.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
       ime.setCellValueFactory(new PropertyValueFactory<Employee, String>("first_name"));
       prezime.setCellValueFactory(new PropertyValueFactory<Employee, String>("last_name"));
       korisnickoIme.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
       sifra.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
       mail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
       posao.setCellValueFactory(new PropertyValueFactory<Employee, String>("job_title"));
       admin.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("admin"));
       plata.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));

        refreshRoomTable();
    }
    public void refreshRoomTable() {
        try {
            tabelaZaposlenika.setItems(FXCollections.observableList(employeeManager.getAllEmployees()));
            tabelaZaposlenika.refresh();

        } catch (HotelExceptions e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }


    public void rowClick(MouseEvent mouseEvent) {
        Employee employee = (Employee) tabelaZaposlenika.getSelectionModel().getSelectedItem();
    }

    public void opcijaDodajZaposlenika(ActionEvent actionEvent) {
    }

    public void azurirajzaposlenika(ActionEvent actionEvent) {
    }

    public void brisanjeZaposlenika(ActionEvent actionEvent) {
        Employee employee = tabelaZaposlenika.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Brisanje zaposlenika iz liste");
        alert.setContentText("Da li ste sigurni da želite da izbrišete zaposlenika iz liste?");
        Optional<ButtonType> response = alert.showAndWait();

        if(response.get() == ButtonType.OK){


            if(employeeManager.deleteEmployee(employee) == false){

                Alert information = new Alert(Alert.AlertType.INFORMATION);
                information.setTitle("Obavještenje o brisanju zaposlenika");
                information.setContentText("Neuspješno brisanje zaposlenika iz liste.");
                information.show();
            }
            else tabelaZaposlenika.getItems().remove(employee);
        }


    }

    public void akcijaNazad(ActionEvent actionEvent) {
        ((Stage)nazadBtn.getScene().getWindow()).close();
    }
}
