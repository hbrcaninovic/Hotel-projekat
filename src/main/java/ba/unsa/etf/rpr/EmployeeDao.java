package ba.unsa.etf.rpr;

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

}
