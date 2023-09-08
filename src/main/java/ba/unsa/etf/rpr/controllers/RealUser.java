package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;

import java.io.IOException;

public class RealUser implements IUserLogIn{
    Employee employee;

    /** Constructor */
    public RealUser(Employee employee) {
        this.employee = employee;
    }

    /** The method opens a window for the user whose access is granted by authentication */
    @Override
    public void openUserDasboard() throws IOException {
        if (employee.getAdmin() == 0) UtilityMethodsForWindows.openWindow2("/fxml/recepcija.fxml",
                "Recepcija",
                new RecepcijaController(employee));
        else UtilityMethodsForWindows.openWindow2("/fxml/administracija.fxml",
                "Admin",
                new AdministracijaController(employee));
    }
}
