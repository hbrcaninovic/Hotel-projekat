package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Room;
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

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AzuriranjeAdminRacunaController {
    public Button odustaniBtn;
    public TextField jmbgTextField;
    public TextField imeTextField;
    public TextField prezimeTextField;
    public TextField mailTextField;
    public ChoiceBox posaoBox;
    public TextField plataTextField;
    public TextField korisnickoImeTextField;
    public TextField sifraTextField;
    public Button azuriranjeRacunaBtn;
    private String[] jobs = {"administrator", "recepcioner"};

    Employee employee = new Employee();
    private EmployeeManager employeeManager = new EmployeeManager();

    public AzuriranjeAdminRacunaController(Employee employee) {
        this.employee = employee;
    }


    @FXML
    public void initialize(){
        jmbgTextField.setText(String.valueOf(employee.getId()));
        imeTextField.setText(employee.getFirst_name());
        prezimeTextField.setText(employee.getLast_name());
        mailTextField.setText(employee.getEmail());
        posaoBox.getItems().addAll(jobs);
        posaoBox.setValue(employee.getJob_title());
        plataTextField.setText(String.valueOf(employee.getSalary()));
        korisnickoImeTextField.setText(employee.getUsername());
        sifraTextField.setText(employee.getPassword());
    }

    public void akcijaOdustani(ActionEvent actionEvent) {
        Stage stage = (Stage) odustaniBtn.getScene().getWindow();
        stage.close();
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
       /* if (!keyEvent.getCharacter().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$") == false){
            keyEvent.consume();
            mailTextField.setStyle("-fx-border-color: red");
        }
        else mailTextField.setStyle("-fx-border-color: transparent");

        */
    }

    public void validirajUnosPlate(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().matches("[^\\d*(\\.\\d+)?$]")){
            keyEvent.consume();
            plataTextField.setStyle("-fx-border-color: red");
            plataTextField.clear();
        }
        else plataTextField.setStyle("-fx-border-color: transparent");
    }

    public void akcijaAzuriranjeRacuna(ActionEvent actionEvent) {
        try {
            String jmbg = jmbgTextField.getText();
            String ime = imeTextField.getText();
            String prezime = prezimeTextField.getText();
            String mail = mailTextField.getText();
            String posao = posaoBox.getValue().toString();
            String plata = plataTextField.getText();
            String korisnickoIme = korisnickoImeTextField.getText();
            String sifra = sifraTextField.getText();

            Employee employee = new Employee(Integer.parseInt(jmbg),korisnickoIme,sifra,ime,prezime,mail,posao,Double.parseDouble(plata),1);

            if (employeeManager.updateEmployee(employee)){
                Stage stage = (Stage)jmbgTextField.getScene().getWindow();
                stage.close();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ažuriranje računa - greška");
                alert.setHeaderText("Greška prilikom ažuriranja računa!");
                alert.setContentText("Nije moguće izvršiti ažuriranje računa!");
                alert.showAndWait();
            }

        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ažuriranje računa - greška");
            alert.setHeaderText("Greška prilikom ažuriranja računa!");
            alert.setContentText("Svi podaci o računu nisu uneseni!");
            alert.showAndWait();
        }

    }
}
