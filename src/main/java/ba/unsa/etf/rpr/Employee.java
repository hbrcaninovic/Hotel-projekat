package ba.unsa.etf.rpr;

import java.util.Date;

public class Employee {

    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private Date hire_date;
    private String job_title;
    private double salary;


    /** A constructor that receives attribute values as parameters and initializes them
     * @param username String value that uniquely describes the object
     * @param password String value for password
     * @param first_name String value for storing a name of person
     * @param last_name String value for storing a surname of person
     * @param address String value for storing a address
     * @param email String value for storing a email address
     * @param hire_date Date value that represents hire_date
     * @param job_title String value for storing a job_title
     * @param salary double value for storing a salary */
    public Employee(String username, String password, String first_name, String last_name, String address, String email, Date hire_date, String job_title, double salary) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
        this.hire_date = hire_date;
        this.job_title = job_title;
        this.salary = salary;
    }
}
