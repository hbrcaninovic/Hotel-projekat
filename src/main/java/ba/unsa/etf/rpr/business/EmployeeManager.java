package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.util.List;

public class EmployeeManager {

    public Employee getEmployee(String username) throws HotelExceptions {
        return DaoFactory.employeeDao().getByUsername(username);
    }
    public boolean addEmployee(Employee employee) throws HotelExceptions {
        try {
            DaoFactory.employeeDao().add(employee);
            return true;
        }
        catch (Exception m)
        {
            //System.out.println(m);
            //System.out.println(m.getMessage());
            return true;
        }
    }

    public boolean updateEmployee(Employee employee) {
        try {
            DaoFactory.employeeDao().update(employee);
            return true;
        }
        catch (HotelExceptions e) {
            return false;
        }
    }

    public List<Employee> getAllEmployees() throws HotelExceptions {
        return DaoFactory.employeeDao().getAll();
    }

    public boolean deleteEmployee(Employee employee){
        try {
            DaoFactory.employeeDao().deleteEmployee(employee.getId());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
