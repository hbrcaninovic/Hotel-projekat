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


}
