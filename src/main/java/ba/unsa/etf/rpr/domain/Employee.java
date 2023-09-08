package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Employee implements Idable {

    private int id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private String job_title;
    private double salary;
    private int admin;


    /**
     * A constructor that receives attribute values as parameters and initializes them
     *
     * @param id          int unique value
     * @param username    String value that uniquely describes the object
     * @param password    String value for password
     * @param first_name  String value for storing a name of person
     * @param last_name   String value for storing a surname of person
     * @param email       String value for storing an email address
     * @param job_title   String value for storing a job_title
     * @param salary      double value for storing a salary
     * @param admin       int value for admin privileges
     */
    public Employee(int id, String username, String password, String first_name, String last_name, String email, String job_title, double salary, int admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.job_title = job_title;
        this.salary = salary;
        this.admin = admin;
    }

    /** Implicit constructor
     */
    public Employee() {

    }

    /**
     * Getter method for employee's id attribute
     * @return String value that represents employee's id
     * */
    public int getId() {
        return id;
    }

    /**Setter method for employee's id attribute
     * @param id int unique value
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method for username attribute
     * @return String value that represents username
     * */
    public String getUsername() {
        return username;
    }

    /**Setter method for username attribute
     * @param username String value that uniquely describes employee's username
     * */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method for password attribute
     * @return String value that represents password
     * */
    public String getPassword() {
        return password;
    }

    /**Setter method for password attribute
     * @param password String value for password
     * */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for first_name attribute
     * @return String value that represents first_name
     * */
    public String getFirst_name() {
        return first_name;
    }

    /**Setter method for first_name attribute
     * @param first_name String value for storing a name of person
     * */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Getter method for last_name attribute
     * @return String value that represents last_name
     * */
    public String getLast_name() {
        return last_name;
    }

    /**Setter method for last_name attribute
     * @param last_name String value for storing a surname of person
     * */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Getter method for email attribute
     * @return String value that represents email
     * */
    public String getEmail() {
        return email;
    }

    /**Setter method for email attribute
     * @param email String value for storing an email address
     * */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for job_title attribute
     * @return String value that represents job_title
     * */
    public String getJob_title() {
        return job_title;
    }

    /**Setter method for job_title attribute
     * @param job_title String value for storing a job_title
     * */
    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    /**
     * Getter method for salary attribute
     * @return double value that represents salary
     * */
    public double getSalary() {
        return salary;
    }

    /**Setter method for salary attribute
     * @param salary double value for storing a salary
     * */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Getter method for admin attribute
     * @return int value that represents admin privileges
     * */
    public int getAdmin() {
        return admin;
    }
    /**Setter method for admin attribute
     * @param admin int value for admin privileges
     * */
    public void setAdmin(int admin) {
        this.admin = admin;
    }



    /** Equals method for comparing two object
     * @return boolean value (true if they are equal, otherwise false)
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Double.compare(employee.salary, salary) == 0 && admin == employee.admin && username.equals(employee.username) && password.equals(employee.password) && first_name.equals(employee.first_name) && last_name.equals(employee.last_name) && email.equals(employee.email) && job_title.equals(employee.job_title);
    }

    /** Makes a hash code of object
     * @return int value that represents hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, first_name, last_name, email, job_title, salary, admin);
    }

    /**Generate String that represents object suitable for printing and other usages
     * @return String value created of attributes of an object
     * */
    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", job_title='" + job_title + '\'' +
                ", salary=" + salary +
                ", admin=" + admin +
                '}';
    }



}
