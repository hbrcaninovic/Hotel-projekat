package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Guest implements Idable{

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String contact_number;




    /**
     * A constructor that receives attribute values as parameters and initializes them
     * @param id int value that represents the unique key of object
     * @param first_name String value for storing a name of person
     * @param last_name String value for storing a surname of person
     * @param email String value for storing an email address
     * @param contact_number String value for storing a contact_number
     * */
    public Guest(int id, String first_name, String last_name, String email, String contact_number) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.contact_number = contact_number;
    }

    public Guest() {

    }

    /**
     * Getter method for ID attribute
     * @return int value that represents ID
     * */
    public int getId() {
        return id;
    }

    /**Setter method for ID attribute */
    public void setId(int id) {
        this.id = id;
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
     * Getter method for email attribute
     * @return String value that represents E-mail
     * */
    public String getEmail() {
        return email;
    }

    /**Setter method for email attribute */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for Contact_number attribute
     * @return String value that represents Contact_number
     * */
    public String getContact_number() {
        return contact_number;
    }

    /**Setter method for Contact_number attribute */
    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }





    /**This method helps to compare two objects
     * @return boolean value true - if two objects are equal, otherwise  returns false */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return id == guest.id && first_name.equals(guest.first_name) && last_name.equals(guest.last_name) && email.equals(guest.email) && contact_number.equals(guest.contact_number);
    }

    /**Create and return hash code of object
     * @return int value that represents hash code*/
    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, email, contact_number);
    }

    /**Generate String that represents object suitable for printing and other usages
     * @return String value created of attributes of an object
     * */
    @Override
    public String toString() {
        return "Guest{" +
                "ID=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", contact_number='" + contact_number + '\'' +
                '}';
    }

}
