package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class DodajZaposlenikaController {
    public Button odustaniBtn;
    public TextField jmbgTextField;
    public TextField imeTextField;
    public TextField prezimeTextField;
    public TextField mailTextField;
    public ChoiceBox posaoBox;
    public TextField plataTextField;
    public Button didavanjeRacunaBtn;
    public TextField korisnickoImeTextField;
    public TextField sifraTextField;
    public ChoiceBox adminPrivilegijeBox;

    private String[] jobs = {"administrator", "recepcioner"};
    private Integer[] adminPrivilegies = {0,1};

    Employee employee = new Employee();
    private EmployeeManager employeeManager = new EmployeeManager();


    @FXML
    public void initialize(){
        posaoBox.getItems().addAll(jobs);
        adminPrivilegijeBox.getItems().addAll(adminPrivilegies);
    }

    public void akcijaOdustani(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) odustaniBtn.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/zaposlenici.fxml"));
        stage.setTitle("HOME - Zaposlenici"); // Postavlja tekstualno zaglavlje prozora
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
        stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
        stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
        stage.show();  // Poziv za prikaz prozora
    }

    public void validirajJMBG(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().matches("[^\\e\t\r\\d+$]")){
            keyEvent.consume();
            jmbgTextField.setStyle("-fx-border-color: red");
            jmbgTextField.clear();
        }
        else jmbgTextField.setStyle("-fx-border-color: transparent");
    }

    public void validirajMail(KeyEvent keyEvent) {
        if (!mailTextField.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            keyEvent.consume();
            mailTextField.setStyle("-fx-border-color: red");
        }
        else mailTextField.setStyle("-fx-border-color: transparent");
    }

    public void validirajUnosPlate(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().matches("[^\\d*(\\.\\d+)?$]")){
            keyEvent.consume();
            plataTextField.setStyle("-fx-border-color: red");
            plataTextField.clear();
        }
        else plataTextField.setStyle("-fx-border-color: transparent");
    }

    public void validirajSifru(KeyEvent keyEvent) {
        if (!sifraTextField.getText().matches("((?=.*\\d)(?=.*[a-z])(?=.*[a-z]).{5,15})")){
            keyEvent.consume();
            sifraTextField.setStyle("-fx-border-color: red");
        }
        else sifraTextField.setStyle("-fx-border-color: transparent");
    }

    public void akcijaKreiranjeRacuna(ActionEvent actionEvent) {
        try {
            String jmbgZaposlenika = jmbgTextField.getText();
            String imeZaposlenika = imeTextField.getText();
            String prezimeZaposlenika = prezimeTextField.getText();
            String mailZaposlenika = mailTextField.getText();
            String posaoZaposlenika = posaoBox.getValue().toString();
            String plataZaposlenika = plataTextField.getText();
            String korisnickoImeZaposlenika = korisnickoImeTextField.getText();
            String sifraZaposlenika = sifraTextField.getText();
            String adminPrvilegijeZaposlenika = adminPrivilegijeBox.getValue().toString();

       /*     if(jmbgZaposlenika.isEmpty() || imeZaposlenika.isEmpty() || prezimeZaposlenika.isEmpty() ||
            mailZaposlenika.isEmpty() || posaoZaposlenika.isEmpty() || plataZaposlenika.isEmpty() ||
            korisnickoImeZaposlenika.isEmpty() || sifraZaposlenika.isEmpty() || adminPrvilegijeZaposlenika.isEmpty()) throw new HotelExceptions("Svi podaci o zaposleniku nisu uneseni.");


        */
            employee = new Employee(Integer.parseInt(jmbgZaposlenika),
                    korisnickoImeZaposlenika,
                    sifraZaposlenika,
                    imeZaposlenika,
                    prezimeZaposlenika,
                    mailZaposlenika,
                    posaoZaposlenika,
                    Double.parseDouble(plataZaposlenika),
                    Integer.parseInt(adminPrvilegijeZaposlenika)
                    );

            if (employeeManager.addEmployee(employee)){
                Stage stage = (Stage)didavanjeRacunaBtn.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("/fxml/zaposlenici.fxml"));
                stage.setTitle("HOME - Zaposlenici"); // Postavlja tekstualno zaglavlje prozora
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
                stage.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
                stage.setResizable(false); //Onemugućavanje izmjene veličine prozora
                stage.show();  // Poziv za prikaz prozora
            }
        }
        catch (Exception e) {
            UtilityMethodsForWindows.openErrorAlertWindow("Dodavanje zaposlenika - greška",
                    "Greška prilikom dodavanja zaposlenika",
                    "Svi podaci o zaposleniku nisu uneseni.");
            /*
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dodavanje zaposlenika - greška");
            alert.setHeaderText("Greška prilikom dodavanja zaposlenika");
            alert.setContentText("Svi podaci o zaposleniku nisu uneseni.");
            alert.showAndWait();
             */
        }

    }
}
