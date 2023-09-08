package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;

import java.io.IOException;

/** A class that models a proxy user */
public class ProxyUser implements IUserLogIn {
    RealUser realUser;

    /** A method that performs authentication when logging in and accessing the actual object */
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

    /** The method opens a window for the user whose access is granted by authentication */
    @Override
    public void openUserDasboard() throws IOException {
        Autentification("","");
    }
}
