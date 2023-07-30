package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.util.List;

public class EmployeeManager {

    public boolean updateEmployee(Employee employee) {
        try {
            DaoFactory.employeeDao().update(employee);
            return true;
        }
        catch (HotelExceptions e) {
            return false;
        }
    }
}
