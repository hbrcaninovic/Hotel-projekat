package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Employee;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

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

    public void opcijaDodajZaposlenika(ActionEvent actionEvent) throws IOException {
        Stage oldStage = (Stage) opcijaDodajZaposlenikaBtn.getScene().getWindow();
        oldStage.close();

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/dodajZaposlenika.fxml"));
        stage.setTitle("HOME - Dodavanje zaposlenika"); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        stage.show();  // Poziv za prikaz prozora
    }

    public void azurirajzaposlenika(ActionEvent actionEvent) {
        Employee employee = tabelaZaposlenika.getSelectionModel().getSelectedItem();
        if (employee == null || employee.getUsername().isEmpty()) {
            UtilityMethodsForWindows.openInformationAlertWindow("Obavještenje",
                    "Obavještenje o ažuriranju računa zaposlenika",
                    "Odaberite zaposlenika u tabeli kako biste ažurirali njegov račun.");

            /*
            Alert information = new Alert(Alert.AlertType.INFORMATION);
            information.setTitle("Obavještenje o ažuriranju računa zaposlenika");
            information.setContentText("Odaberite zaposlenika u tabeli kako biste ažurirali njegov račun.");
            information.show();
             */
        }
        else {
            try {
                employee = employeeManager.getEmployee(employee.getUsername());

                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/azurirajZaposlenikovRacun.fxml"));
                Object controller = new AzurirajZaposlenikovRacunController(employee);
                loader.setController(controller);
                stage.setTitle("HOME - Ažuriranje računa zaposlenika"); // Postavlja tekstualno zaglavlje prozora
                stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
                stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
                stage.show();  // Poziv za prikaz prozora


                Stage oldStage = (Stage) azurirajZaposlenikaBtn.getScene().getWindow();
                oldStage.close();


            } catch (Exception e) {
                UtilityMethodsForWindows.openInformationAlertWindow("Obavještenje",
                        "Obavještenje o ažuriranju računa zaposlenika",
                        "Ažuriranje nije moguće!");
                /*
                Alert information = new Alert(Alert.AlertType.INFORMATION);
                information.setTitle("Obavještenje o ažuriranju računa zaposlenika");
                information.setContentText("Ažuriranje nije moguće!");
                information.show();
                 */
            }

        }
    }


    public void brisanjeZaposlenika(ActionEvent actionEvent) {
        Employee employee = tabelaZaposlenika.getSelectionModel().getSelectedItem();

        Optional<ButtonType> response = UtilityMethodsForWindows.openConfirmationAlertWindow("Brisanje",
                "Brisanje zaposlenika iz liste",
                "Da li ste sigurni da želite da izbrišete zaposlenika iz liste?");

        /*
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Brisanje zaposlenika iz liste");
        alert.setContentText("Da li ste sigurni da želite da izbrišete zaposlenika iz liste?");
        Optional<ButtonType> response = alert.showAndWait();
         */

        if(response.get() == ButtonType.OK){


            if(!employeeManager.deleteEmployee(employee)){

                UtilityMethodsForWindows.openInformationAlertWindow("Obavještenje",
                        "Obavještenje o brisanju zaposlenika",
                        "Neuspješno brisanje zaposlenika iz liste.");
                /*
                Alert information = new Alert(Alert.AlertType.INFORMATION);
                information.setTitle("Obavještenje o brisanju zaposlenika");
                information.setContentText("Neuspješno brisanje zaposlenika iz liste.");
                information.show();
                 */
            }
            else tabelaZaposlenika.getItems().remove(employee);
        }


    }

    public void akcijaNazad(ActionEvent actionEvent) {
        ((Stage)nazadBtn.getScene().getWindow()).close();
    }
}
