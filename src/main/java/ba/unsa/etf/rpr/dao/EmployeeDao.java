package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.util.Date;
import java.util.List;

public interface EmployeeDao extends Dao<Employee>{

    /**
     * Gives all employees which hire date comes after the given (including the forwarded date).
     * @param date Date type parameter for searching
     * @return List of employees who were employed after the given date*/
    List<Employee> getAfterByHireDate(Date date);

    /**
     * Gives all employees which hire date comes before the given (including the forwarded date).
     * @param date Date type parameter for searching
     * @return List of employees who were employed before the given date
     * */
    List<Employee> getBeforeByHireDate(Date date);


    /**
     * Gives all employees which job_title is same as given.
     * @param job_title String that represents job_title
     * @return List of employees whose job title same as given
     * */
    List<Employee> getByJob(String job_title);


    /**
     * Gives an employee which username is same as given.
     * @param username String that represents username
     * @return Employee objects whose username is as same as given
     */
    Employee getByUsername(String username) throws HotelExceptions;

    /**
     * Gives an employee which username and password are same as given.
     * @param username String that represents username
     * @param password String that represents password
     * @return Employee objects whose username and password are as same as given
     */
    Employee getEmployeeByUsernameAndPassword(String username, String password);

    /** Deletes an Employee from the database based on the id parameter
     * @param id int value that uniquely define Employee
     * */
    void deleteEmployee(int id) throws HotelExceptions;
}
