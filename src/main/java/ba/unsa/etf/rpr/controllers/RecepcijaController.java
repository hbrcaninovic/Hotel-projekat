package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class RecepcijaController {

    public static Employee employee;
    public Label welcomeLabel;

    public RecepcijaController(Employee employee) {
        this.employee = employee;
    }

    @FXML
    public void initialize(){
        welcomeLabel.setText(employee.getFirst_name());
        System.out.println(employee.getFirst_name());
    }
}
