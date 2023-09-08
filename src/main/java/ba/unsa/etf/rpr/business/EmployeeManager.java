package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.util.List;

public class EmployeeManager {

    /** Gives information about the employee from the database based on the username
     * @param username Stirng value that represents employee's username
     * @return Employee value that username is same as given
     * */
    public Employee getEmployee(String username) throws HotelExceptions {
        return DaoFactory.employeeDao().getByUsername(username);
    }

    /** Adds a employee to the database
     * @param employee mployee object that represent employee
     * @return boolean value which gives confirmation of the successful addition of the employee to the database
     * */
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

    /** Updates the specified employee from the database
     * @param employee Employee object that updates
     * @return boolean value which gives confirmation of the successful update of the employee to the database
     * */
    public boolean updateEmployee(Employee employee) {
        try {
            DaoFactory.employeeDao().update(employee);
            return true;
        }
        catch (HotelExceptions e) {
            return false;
        }
    }

    /** A method that returns a list of all employees from the database
     * @return List of Employee
     * */
    public List<Employee> getAllEmployees() throws HotelExceptions {
        return DaoFactory.employeeDao().getAll();
    }

    /** Deletes the specified employee from the database
     * @param employee Employee object that deletes
     * @return boolean value which gives confirmation of the successful deletion of the employee to the database
     * */
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
