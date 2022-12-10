package ba.unsa.etf.rpr;

public class Guest {

    private int ID;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String contact_number;

    /**
     * A constructor that receives attribute values as parameters and initializes them
     * @param ID int value that represents the unique key of object
     * @param first_name String value for storing a name of person
     * @param last_name String value for storing a surname of person
     * @param address String value for storing a address
     * @param email String value for storing a email address
     * @param contact_number String value for storing a contact_number
     * */
    public Guest(int ID, String first_name, String last_name, String address, String email, String contact_number) {
        this.ID = ID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
        this.contact_number = contact_number;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
}
