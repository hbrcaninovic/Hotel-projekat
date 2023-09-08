package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;

import java.io.IOException;

public interface IUserLogIn {
    /** The method opens a window for the user whose access is granted by authentication */
    void openUserDasboard() throws IOException;
}
