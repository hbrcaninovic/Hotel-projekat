package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Employee implements Idable {

    private int employee_id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private LocalDate hire_date;
    private String job_title;
    private double salary;
    private int admin;


    /**
     * A constructor that receives attribute values as parameters and initializes them
     *
     * @param employee_id int unique value
     * @param username    String value that uniquely describes the object
     * @param password    String value for password
     * @param first_name  String value for storing a name of person
     * @param last_name   String value for storing a surname of person
     * @param address     String value for storing an address
     * @param email       String value for storing an email address
     * @param hire_date   Date value that represents hire_date
     * @param job_title   String value for storing a job_title
     * @param salary      double value for storing a salary
     * @param admin       int value for admin privileges
     */
    public Employee(int employee_id, String username, String password, String first_name, String last_name, String address, String email, LocalDate hire_date, String job_title, double salary,int admin) {
        this.employee_id = employee_id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
        this.hire_date = hire_date;
        this.job_title = job_title;
        this.salary = salary;
        this.admin = admin;
    }

    /** Implicit constructor
     */
    public Employee() {

    }

    /**
     * Getter method for employee_id attribute
     * @return String value that represents employee_id
     * */
    public int getEmployee_id() {
        return employee_id;
    }

    /**Setter method for employee_id attribute */
    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    /**
     * Getter method for username attribute
     * @return String value that represents username
     * */
    public String getUsername() {
        return username;
    }

    /**Setter method for username attribute */
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

    /**Setter method for password attribute */
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

    /**Setter method for first_name attribute */
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

    /**Setter method for last_name attribute */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Getter method for address attribute
     * @return String value that represents address
     * */
    public String getAddress() {
        return address;
    }

    /**Setter method for address attribute */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter method for email attribute
     * @return String value that represents email
     * */
    public String getEmail() {
        return email;
    }

    /**Setter method for email attribute */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for hire_date attribute
     *
     * @return LocalDate value that represents hire_date
     */
    public LocalDate getHire_date() {
        return hire_date;
    }

    /**Setter method for hire_date attribute */
    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    /**
     * Getter method for job_title attribute
     * @return String value that represents job_title
     * */
    public String getJob_title() {
        return job_title;
    }

    /**Setter method for job_title attribute */
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

    /**Setter method for salary attribute */
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
    /**Setter method for admin attribute */
    public void setAdmin(int admin) {
        this.admin = admin;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employee_id == employee.employee_id && Double.compare(employee.salary, salary) == 0 && admin == employee.admin && username.equals(employee.username) && password.equals(employee.password) && first_name.equals(employee.first_name) && last_name.equals(employee.last_name) && address.equals(employee.address) && email.equals(employee.email) && hire_date.equals(employee.hire_date) && job_title.equals(employee.job_title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_id, username, password, first_name, last_name, address, email, hire_date, job_title, salary, admin);
    }

    /**Generate String that represents object suitable for printing and other usages
     * @return String value created of attributes of an object
     * */
    @Override
    public String
    toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", hire_date=" + hire_date +
                ", job_title='" + job_title + '\'' +
                ", salary=" + salary +
                ", admin=" + admin +
                '}';
    }




    /**
     * Implementation of setID method from Idable
     * */
    @Override
    public void setId(int i) {
        setEmployee_id(i);
    }


    /**
     * Implementation of getID method from Idable
     * */
    @Override
    public int getId() {
        return getEmployee_id();
    }
}
