package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Room;
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

public class AzurirajZaposlenikovRacunController {


    public Button odustaniBtn;
    public TextField jmbgTextField;
    public TextField imeTextField;
    public TextField prezimeTextField;
    public TextField mailTextField;
    public ChoiceBox posaoBox;
    public TextField plataTextField;
    public Button azuriranjeRacunaBtn;
    public TextField korisnickoImeTextField;
    public TextField sifraTextField;
    public ChoiceBox adminPrivilegijeBox;
    private Employee employee = new Employee();

    private String[] jobs = {"administrator", "recepcioner"};
    private Integer[] adminPrivilegies = {0,1};


    private EmployeeManager employeeManager = new EmployeeManager();

    public AzurirajZaposlenikovRacunController(Employee employee) {
        this.employee = employee;
    }

    @FXML
    public void initialize(){
        posaoBox.getItems().addAll(jobs);
        adminPrivilegijeBox.getItems().addAll(adminPrivilegies);

        jmbgTextField.setText(String.valueOf(employee.getId()));
        imeTextField.setText(employee.getFirst_name());
        prezimeTextField.setText(employee.getLast_name());
        mailTextField.setText(employee.getEmail());
        posaoBox.setValue(employee.getJob_title());
        plataTextField.setText(String.valueOf(employee.getSalary()));
        korisnickoImeTextField.setText(employee.getUsername());
        sifraTextField.setText(employee.getPassword());
        adminPrivilegijeBox.setValue(employee.getAdmin());
    }

    public void akcijaOdustani(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) odustaniBtn.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/zaposlenici.fxml"));
        stage.setTitle("HOME - zaposlenici"); // Postavlja tekstualno zaglavlje prozora
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
            String administratorskePrivilege = adminPrivilegijeBox.getValue().toString();

            if (jmbg.isEmpty() || ime.isEmpty() || prezime.isEmpty() || mail.isEmpty() ||
                    posao.isEmpty() || plata.isEmpty() || korisnickoIme.isEmpty() || sifra.isEmpty()
            || administratorskePrivilege.isEmpty()) throw new HotelExceptions("Sva polja nisu ispunjena!");

            employee = new Employee(Integer.parseInt(jmbg),korisnickoIme,sifra,ime,prezime,mail,posao,Double.parseDouble(plata),Integer.parseInt(administratorskePrivilege));


            if (employeeManager.updateEmployee(employee)){
                Stage stage1 = (Stage)jmbgTextField.getScene().getWindow();
                stage1.close();

                Parent root = FXMLLoader.load(getClass().getResource("/fxml/zaposlenici.fxml"));
                stage1.setTitle("HOME - zaposlenici"); // Postavlja tekstualno zaglavlje prozora
                stage1.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); // Kreira Scenu prema USE_COMPUTED_SIZE konstanti
                stage1.getIcons().add(new Image("/img/logo.png")); //Dodavanje ikone u zaglavlju prozora
                stage1.setResizable(false); //Onemugućavanje izmjene veličine prozora
                stage1.show();  // Poziv za prikaz prozora
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
