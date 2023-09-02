package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;

import java.io.IOException;

public class ProxyUser implements IUserLogIn {
    RealUser realUser;

    public boolean Autentification(String username, String password) throws IOException {
        Employee employee = DaoFactory.employeeDao().getEmployeeByUsernameAndPassword(username, password);

        if (employee.getAdmin() < 0 || password.length()<5 || employee.getId() == 0) {
            UtilityMethodsForWindows.openErrorAlertWindow("LogIn - greška",
                    "Greška prilikom prijave!",
                    "Neispravni pristupni podaci!");
            return false;
        }
        else {
            realUser = new RealUser(employee);
            realUser.openUserDasboard();
            return true;
        }
    }

    @Override
    public void openUserDasboard() throws IOException {
        Autentification("","");
    }
}
