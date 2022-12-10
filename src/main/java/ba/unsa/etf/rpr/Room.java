package ba.unsa.etf.rpr;

public class Room {

    private int room_id;
    private int room_type;
    private double price;
    private String VIP_services;
    private String status;


    /**A constructor that receives attribute values as parameters and initializes them
     * @param room_id int value that represents the unique key of object
     * @param room_type int value that represents the number of beds in room
     * @param price double value for price
     * @param VIP_services String value that has 2 possible states: YES/NO
     * @param status String value that has 2 possible states: busy/free */
    public Room(int room_id, int room_type, double price, String VIP_services, String status) {
        this.room_id = room_id;
        this.room_type = room_type;
        this.price = price;
        this.VIP_services = VIP_services;
        this.status = status;
    }


    /**
     * Getter method for room_id attribute
     * @return int value that represents room_ID
     * */
    public int getRoom_id() {
        return room_id;
    }

    /**Setter method for room_id attribute */
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    /**
     * Getter method for room_type attribute
     * @return int value that represents room_type
     * */
    public int getRoom_type() {
        return room_type;
    }

    /**Setter method for room_type attribute */
    public void setRoom_type(int room_type) {
        this.room_type = room_type;
    }

    /**
     * Getter method for price attribute
     * @return double value that represents price of room
     * */
    public double getPrice() {
        return price;
    }

    /**Setter method for price attribute */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter method for VIP_serivices attribute
     * @return String value that represents VIP_services
     * */
    public String getVIP_services() {
        return VIP_services;
    }

    /**Setter method for VIP_services attribute */
    public void setVIP_services(String VIP_services) {
        this.VIP_services = VIP_services;
    }

    /**
     * Getter method for status attribute
     * @return String value that represents status
     * */
    public String getStatus() {
        return status;
    }

    /**Setter method for status attribute */
    public void setStatus(String status) {
        this.status = status;
    }
}
